package com.xbz.vpase.dao.impl;


import com.xbz.vpase.dao.SysUserDao;

import com.xbz.vpase.dao.base.impl.BaseDaoImpl;
import com.xbz.vpase.persistent.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDaoImpl<SysUser> implements SysUserDao {
}
