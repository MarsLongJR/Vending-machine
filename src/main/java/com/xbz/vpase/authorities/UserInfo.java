package com.xbz.vpase.authorities;

import com.xbz.vpase.persistent.entity.SysUser;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class UserInfo extends SysUser implements UserDetails, CredentialsContainer {

    public UserInfo(ArrayList<GrantedAuthority> authorities,
                    boolean accountNonExpired, boolean accountNonLocked,
                    boolean credentialsNonExpired, boolean enabled) {
        super();
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }



//方法重写
private final ArrayList<GrantedAuthority> authorities;

    public UserInfo(Integer id, String account, String password, String userName, String userCode,
                    String phone, String email, String openId, Short status, Date createTime, String createId,
                    Boolean firstLogin, Integer modifierId, Date modifyTime, String imageUrl, Date lastTime,
                    ArrayList<GrantedAuthority> authorities,
                    boolean accountNonExpired, boolean accountNonLocked,
                    boolean credentialsNonExpired, boolean enabled) {
        super( id,  account,  password,  userName,  userCode,  phone,  email,  openId,  status,createTime,createId,firstLogin,modifierId,modifyTime,imageUrl,lastTime);
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }




    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;



    @Override
    public void eraseCredentials() {
        setPassword(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public String getUsername() {
        return getUserName();
    }

    @Override
    public String getUserCode() {
        return getUserCode();
    }

    @Override
    public String getAccount() {
        return getAccount();
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
