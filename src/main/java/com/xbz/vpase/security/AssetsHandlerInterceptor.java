package com.xbz.vpase.security;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AssetsHandlerInterceptor implements HandlerInterceptor {

    private String AssetInfoURl = "/page/assetInfo_allow?assetId=";
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        String[] pathURI =  requestURI.split("/");
        String assetNum = pathURI[pathURI.length-1];
        String redirectUrl = AssetInfoURl+assetNum;
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+redirectUrl);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
       // System.out.println("============================= postHandle  ===========================");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //System.out.println("============================= afterCompletion  ===========================");
    }
}
