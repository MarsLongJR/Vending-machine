package com.xbz.vpase.service.impl;

import com.xbz.vpase.dao.SysUserDao;
import com.xbz.vpase.persistent.entity.SysUser;
import com.xbz.vpase.service.SysUserService;
import com.xbz.vpase.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    public void setBaseDao(SysUserDao sysUserDao) {
        super.setBaseDao(sysUserDao);
    }
}
