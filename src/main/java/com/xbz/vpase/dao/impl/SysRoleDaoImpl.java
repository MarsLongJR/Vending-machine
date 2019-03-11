package com.xbz.vpase.dao.impl;

import com.xbz.vpase.dao.SysRoleDao;
import com.xbz.vpase.dao.base.impl.BaseDaoImpl;
import com.xbz.vpase.persistent.entity.SysRole;
import org.springframework.stereotype.Repository;

@Repository("sysRoleDao")
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole> implements SysRoleDao {

    @Override
    public SysRole getByUserId(Integer id) {
//        return sqlSessionTemplate.selectOne("SysRole",id);
        return null;
    }
}
