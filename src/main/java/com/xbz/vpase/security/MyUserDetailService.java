package com.xbz.vpase.security;

import com.xbz.vpase.authorities.UserInfo;
import com.xbz.vpase.persistent.entity.SysRole;
import com.xbz.vpase.service.SysRoleService;
import com.xbz.vpase.service.SysUserService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MyUserDetailService implements UserDetailsService {
	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysRoleService roleService;
	
	@Resource
	private transient SqlSessionTemplate sqlSessionTemplate;
	
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();// 用户的权限集合
		UserInfo user = null;
		String name = username;
		if(username==null){
			throw new RuntimeException(MyAuthenticationFailureHandler.ACCOUNT_NO_EXIST);
		}
		com.xbz.vpase.persistent.entity.SysUser u = sqlSessionTemplate.selectOne("tbl_user.selectUser",name);
		if(u==null){
			throw new RuntimeException(MyAuthenticationFailureHandler.ACCOUNT_NO_EXIST);
		}

		if(u.getStatus()==1){
			throw new RuntimeException(MyAuthenticationFailureHandler.ACCOUNT_BAN);
		}
		List<Integer>roleIds=sqlSessionTemplate.selectList("tbl_user.selectRoleIdsByName",name);
		if(u!=null){
			if(roleIds!=null&&roleIds.size()!=0){
				for(Integer id:roleIds){
					/**
					 * 获得role的id，每次配置需要重新写
					 */
					Integer roleId = id;

					SysRole role = sqlSessionTemplate.selectOne("tbl_role.selectByPrimaryKey", roleId);
					SimpleGrantedAuthority auth = new SimpleGrantedAuthority(
							role.getRoleName());
						auths.add(auth);// 把权限添加进权限集合
				}
			}
		}
		user = new UserInfo(u.getId(),u.getAccount(),u.getPassword(), u.getUserName(),u.getUserCode(), u.getPhone(),u.getEmail(),u.getOpenId(),u.getStatus(), u.getCreateTime(),  u.getCreateId(),u.getFirstLogin(),u.getModifierId(),u.getModifyTime(),u.getImageUrl(),u.getLastTime(), (ArrayList<GrantedAuthority>) auths,true, true,
				true, true);
		return user;
	}

}
