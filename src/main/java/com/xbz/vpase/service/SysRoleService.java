package com.xbz.vpase.service;

import com.xbz.vpase.persistent.entity.SysRole;
import com.xbz.vpase.service.base.BaseService;

public interface SysRoleService extends BaseService<SysRole> {

    SysRole getByUserId(Integer id);
}
