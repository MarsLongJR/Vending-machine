package com.xbz.vpase.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 自定义的登录出错处理
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private String defaultFailureUrl;
    private String handleFailureUrl="/page/failedLogin";//处理登录错误的页面
    private boolean forwardToDestination = true;
    private boolean allowSessionCreation = true;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public static final String PASSWORD_ERROR="Bad credentials";//密码错误
    public static final String ACCOUNT_NO_EXIST="账号不存在";//账号不存在
    public static final String ACCOUNT_BAN="账号被禁止登录";//账号被封禁


    public MyAuthenticationFailureHandler() {
    }

    public MyAuthenticationFailureHandler(String defaultFailureUrl) {
        this.setDefaultFailureUrl(defaultFailureUrl);
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(this.defaultFailureUrl == null) {
            this.logger.debug("No failure URL set, sending 401 Unauthorized error");
            response.sendError(401, "Authentication Failed: " + exception.getMessage());
        } else {
            this.saveException(request, exception);
                //根据错误不同进行不同的处理逻辑
                switch (exception.getMessage()){
                    case PASSWORD_ERROR://密码错误
                        this.logger.debug("Redirecting to " + this.defaultFailureUrl);
                        request.setAttribute("failureMsg","登录密码错误");
                        request.getRequestDispatcher(this.handleFailureUrl).forward(request, response);
                        break;
                    case ACCOUNT_NO_EXIST://账号不存在
                        this.logger.debug("Redirecting to " + this.defaultFailureUrl);
                        request.setAttribute("failureMsg",ACCOUNT_NO_EXIST);
                        request.getRequestDispatcher(this.handleFailureUrl).forward(request, response);
                        break;
                    case ACCOUNT_BAN://账号被封禁
                        this.logger.debug("Redirecting to " + this.defaultFailureUrl);
                        request.setAttribute("failureMsg",ACCOUNT_BAN);
                        request.getRequestDispatcher(this.handleFailureUrl).forward(request, response);
                        break;
                    default:
                        this.logger.debug("Redirecting to " + this.defaultFailureUrl);
                        this.redirectStrategy.sendRedirect(request, response, this.defaultFailureUrl);
                }


        }

    }

    protected final void saveException(HttpServletRequest request, AuthenticationException exception) {
        if(this.forwardToDestination) {
            request.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
        } else {
            HttpSession session = request.getSession(false);
            if(session != null || this.allowSessionCreation) {
                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", exception);
            }
        }

    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultFailureUrl), "\'" + defaultFailureUrl + "\' is not a valid redirect URL");
        this.defaultFailureUrl = defaultFailureUrl;
    }

    protected boolean isUseForward() {
        return this.forwardToDestination;
    }

    public void setUseForward(boolean forwardToDestination) {
        this.forwardToDestination = forwardToDestination;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return this.redirectStrategy;
    }

    protected boolean isAllowSessionCreation() {
        return this.allowSessionCreation;
    }

    public void setAllowSessionCreation(boolean allowSessionCreation) {
        this.allowSessionCreation = allowSessionCreation;
    }
}
