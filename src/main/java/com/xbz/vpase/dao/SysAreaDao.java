package com.xbz.vpase.dao;


import com.xbz.vpase.dao.base.BaseDao;
import com.xbz.vpase.persistent.entity.SysArea;

import java.util.List;

public interface SysAreaDao extends BaseDao<SysArea> {

    List<SysArea> selectSysAreaList(String parentCode, String areaName, String areaCode);
}
