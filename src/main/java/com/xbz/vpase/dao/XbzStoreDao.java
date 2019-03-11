package com.xbz.vpase.dao;

import com.xbz.vpase.dao.base.BaseDao;
import com.xbz.vpase.persistent.entity.XbzStore;
import com.xbz.vpase.request.XbzStoreRequest;

import java.util.List;

public interface XbzStoreDao extends BaseDao<XbzStore> {

    List<XbzStore> selectXbzStoreList(XbzStoreRequest request);

    Integer selectXbzStoreCount(XbzStoreRequest request);
}
