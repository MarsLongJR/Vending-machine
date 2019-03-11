package com.xbz.vpase.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

public class MyAccessDecisionManager implements AccessDecisionManager {

	 //In this method, need to compare authentication with configAttributes.
    // 1, A object is a URL, a filter was find permission configuration by this URL, and pass to here.
    // 2, Check authentication has attribute in permission configuration (configAttributes)
    // 3, If not match corresponding authentication, throw a AccessDeniedException.
    public void decide(Authentication authentication, Object object,
            Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
    	//如果权限配置为空，返回(即通过认证)
        if(configAttributes == null){
            return ;
        }
        System.out.println(object.toString());  //object is a URL.
        Iterator<ConfigAttribute> ite=configAttributes.iterator();
        while(ite.hasNext()){//遍历所有的权限配置
            ConfigAttribute ca=ite.next();
            String needRole=((SecurityConfig)ca).getAttribute();
            for(GrantedAuthority ga:authentication.getAuthorities()){//从权限集合authentication中拿到所有的权限，一一进行比较
                if(needRole.equals(ga.getAuthority())){  //ga is user's role.
                    return;
                }
            }
        }
        throw new AccessDeniedException("您的权限不够");//抛出无权访问异常
    }

	public boolean supports(ConfigAttribute arg0) {
		  return true;
	}

	public boolean supports(Class<?> arg0) {
		  return true;
	}


}

