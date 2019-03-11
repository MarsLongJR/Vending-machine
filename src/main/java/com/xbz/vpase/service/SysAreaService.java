package com.xbz.vpase.service;

import com.xbz.vpase.persistent.entity.SysArea;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.service.base.BaseService;

public interface SysAreaService extends BaseService<SysArea> {

    ResultJson selectSysAreaList(String parentCode,String areaName,String areaCode);


}
