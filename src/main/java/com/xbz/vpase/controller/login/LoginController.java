package com.xbz.vpase.controller.login;

import com.xbz.vpase.authorities.UserInfo;
import com.xbz.vpase.persistent.entity.SysRole;
import com.xbz.vpase.persistent.entity.SysUser;
import com.xbz.vpase.service.SysRoleService;
import com.xbz.vpase.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("getLoginPage_allow")
    public ModelAndView getLoginPage(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();

        ModelAndView modelAndView = new ModelAndView();
        String ua = request.getHeader("user-agent")
                .toLowerCase();
        if (ua.indexOf("micromessenger") <= 0) {// 不是微信浏览器
            modelAndView.setViewName("login");
        } else {//// 是微信浏览器
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    /**
     * 用户登录成功后跳转的方法
     *
     * @return
     */
    @RequestMapping("userLogin")
    public ModelAndView userLogin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.get(userInfo.getId());
        sysUser.setLastTime(new Date());
        sysUserService.update(sysUser);
        SysRole sysRole = sysRoleService.getByUserId(userInfo.getId());
        modelAndView.addObject("role",sysRole);
        String ua = request.getHeader("user-agent")
                .toLowerCase();
        String sessionId = request.getSession().getId();
        if (ua.indexOf("miniprogram") != -1) {//小程序
            modelAndView.addObject("cookie",sessionId);
            modelAndView.setViewName("miniProgram/miniProgram");
            return modelAndView;
        } else if (ua.indexOf("micromessenger") <= 0) {// 不是微信浏览器

        } else {//// 是微信浏览器 if(ua.indexOf("micromessenger")!=0)

        }
        modelAndView.addObject("user",userInfo);

        return modelAndView;
    }
}
