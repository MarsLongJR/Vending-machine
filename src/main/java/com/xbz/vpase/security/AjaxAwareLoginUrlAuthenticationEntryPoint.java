package com.xbz.vpase.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by WeiJinTechnology on 2017/2/23.
 */

//自定义的入口引用结点，位于整个过滤器链的前方
public class AjaxAwareLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException, ServletException {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");//对于ajax请求不重定向  而是返回错误代码
        } else {
            System.out.println("重定向开始");
            System.out.println(request.getRequestURL());
            System.out.println("重定向结束");
            super.commence(request, response, authException);
        }
    }

    public AjaxAwareLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }
}