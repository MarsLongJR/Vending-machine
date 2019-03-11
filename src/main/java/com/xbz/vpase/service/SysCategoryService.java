package com.xbz.vpase.service;


import com.xbz.vpase.persistent.entity.SysCategory;
import com.xbz.vpase.service.base.BaseService;

import java.util.List;

public interface SysCategoryService extends BaseService<SysCategory> {

    List<SysCategory> selectSysCategoryList(String code, String name, String parent, Boolean enable);
}
