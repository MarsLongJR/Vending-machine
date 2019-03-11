package com.xbz.vpase.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by WeiJinTechnology on 2017/2/23.
 */

/**
 * 自定义的网络请求缓存，与SpringScurity包下HttpSessionRequestCache的类完全相同，在进入SpringSecurity登录页之前，将想在登录完成后跳转的页面记录进缓存，以供SpringScurity成功登录后跳转
 */
public class HttpSessionRequestCache implements RequestCache {
    static final String SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";
    protected final Log logger = LogFactory.getLog(this.getClass());
    private PortResolver portResolver = new PortResolverImpl();
    private boolean createSessionAllowed = true;
    private RequestMatcher requestMatcher;
    private String sessionAttrName;

    private String paramName="toUrl";//ajax登录成功后的跳转页面的参数


    public HttpSessionRequestCache() {
        this.requestMatcher = AnyRequestMatcher.INSTANCE;
        this.sessionAttrName = "SPRING_SECURITY_SAVED_REQUEST";
    }

    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        if(this.requestMatcher.matches(request)) {
            if (!"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
                System.out.println("保存SavedRequest到session开始");
                DefaultSavedRequest savedRequest = new DefaultSavedRequest(request, this.portResolver);
                if(this.createSessionAllowed || request.getSession(false) != null) {
                    request.getSession().setAttribute(this.sessionAttrName, savedRequest);
                    this.logger.debug("DefaultSavedRequest added to Session: " + savedRequest);
                }
                System.out.println(savedRequest.getRequestURL());
                System.out.println("保存SavedRequest到session结束");
            }else{
                if(request.getParameter(paramName)==null){ //如果ajax没有上传指定登录完成后跳转的路径参数，不操作，默认进入SpringSecurity的登录结果处理类
                    this.logger.debug("Request not saved as configured RequestMatcher did not match");
                }else{
                    DefaultSavedRequest savedRequest = new DefaultSavedRequest(request, this.portResolver);
                    savedRequest.setRequestURI(request.getParameter(paramName));//将 指定登录完成后跳转的路径 存入缓存中，
                    if(this.createSessionAllowed || request.getSession(false) != null) {
                        request.getSession().setAttribute(this.sessionAttrName, savedRequest);
                        this.logger.debug("DefaultSavedRequest added to Session: " + savedRequest);
                    }
                }

            }
        } else {
            this.logger.debug("Request not saved as configured RequestMatcher did not match");
        }

    }

    public SavedRequest getRequest(HttpServletRequest currentRequest, HttpServletResponse response) {
        HttpSession session = currentRequest.getSession(false);
        return session != null?(SavedRequest)session.getAttribute(this.sessionAttrName):null;
    }

    public void removeRequest(HttpServletRequest currentRequest, HttpServletResponse response) {
        HttpSession session = currentRequest.getSession(false);
        if(session != null) {
            this.logger.debug("Removing DefaultSavedRequest from session if present");
            session.removeAttribute(this.sessionAttrName);
        }

    }

    public HttpServletRequest getMatchingRequest(HttpServletRequest request, HttpServletResponse response) {
        DefaultSavedRequest saved = (DefaultSavedRequest)this.getRequest(request, response);
        if(saved == null) {
            return null;
        } else if(!saved.doesRequestMatch(request, this.portResolver)) {
            this.logger.debug("saved request doesn\'t match");
            return null;
        } else {
            this.removeRequest(request, response);
            return new SavedRequestAwareWrapper(saved, request);
        }
    }

    public void setRequestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    public void setCreateSessionAllowed(boolean createSessionAllowed) {
        this.createSessionAllowed = createSessionAllowed;
    }

    public void setPortResolver(PortResolver portResolver) {
        this.portResolver = portResolver;
    }

    public void setSessionAttrName(String sessionAttrName) {
        this.sessionAttrName = sessionAttrName;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}
