package com.xbz.vpase.dao;

import com.xbz.vpase.dao.base.BaseDao;
import com.xbz.vpase.persistent.entity.SysRole;

public interface SysRoleDao extends BaseDao<SysRole> {

    SysRole getByUserId(Integer id);
}
