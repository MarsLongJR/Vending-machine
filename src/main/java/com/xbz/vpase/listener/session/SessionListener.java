package com.xbz.vpase.listener.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;


public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext application = session.getServletContext();

        //把用户名放入在线列表,在application范围有一个List集保存所有的Session
        List onlineUserList = (List) application.getAttribute("onlineUserList");

        // 第一次使用前，需要初始化
        if (onlineUserList == null) {
            onlineUserList = new ArrayList();
            application.setAttribute("onlineUserList", onlineUserList);
        }
        // 新创建的session均添加到List集中
        onlineUserList.add(onlineUserList);
        System.out.println("当前在线人数：" + onlineUserList.size() + "人");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext application = session.getServletContext();
        List onlineUserList = (List) application.getAttribute("onlineUserList");
        //销毁的session均从List中移除
        onlineUserList.remove(onlineUserList);
        System.out.println("用户退出或登录超时之后用户在线人数：" + onlineUserList.size() + "人");
    }



}
