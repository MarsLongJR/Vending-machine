package com.xbz.vpase.service.impl;


import com.xbz.vpase.dao.SysRoleDao;
import com.xbz.vpase.persistent.entity.SysRole;
import com.xbz.vpase.service.SysRoleService;
import com.xbz.vpase.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    public void setBaseDao(SysRoleDao sysRoleDao) {
        super.setBaseDao(sysRoleDao);
    }

    @Override
    public SysRole getByUserId(Integer id) {
        try {
            SysRole sysRole = sysRoleDao.getByUserId(id);
            return sysRole;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
