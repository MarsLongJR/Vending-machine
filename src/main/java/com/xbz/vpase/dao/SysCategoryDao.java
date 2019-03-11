package com.xbz.vpase.dao;

import com.xbz.vpase.dao.base.BaseDao;
import com.xbz.vpase.persistent.entity.SysCategory;

import java.util.List;

public interface SysCategoryDao extends BaseDao<SysCategory> {

    List<SysCategory> selectSysCategoryList(SysCategory sysCategory);
}
