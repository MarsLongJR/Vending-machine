package com.xbz.vpase.service;

import com.xbz.vpase.persistent.entity.XbzStore;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.service.base.BaseService;

import java.math.BigDecimal;
import java.util.List;

public interface XbzStoreService extends BaseService<XbzStore> {

    ResultJson insetSysStore(XbzStore xbzStore);

    List<XbzStore> selectXbzStoreList(Integer id, String storeName, String barCode, String sysName,String sysCode,String price, String startDate, String endDate, Boolean enable,Integer categoryId, Integer pageNum, Integer pageSize);

    Integer selectXbzStoreCount(Integer id, String storeName, String barCode,String sysName,String sysCode, String price, String startDate, String endDate, Boolean enable, Integer categoryId);

    ResultJson modifyXbzStore(Integer id ,String storeName, String barCode,String sysName,String sysCode, String price, Boolean enable, Integer categoryId);


}
