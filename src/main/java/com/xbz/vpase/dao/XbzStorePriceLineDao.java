package com.xbz.vpase.dao;

import com.xbz.vpase.dao.base.BaseDao;
import com.xbz.vpase.persistent.entity.XbzStorePriceLine;

public interface XbzStorePriceLineDao extends BaseDao<XbzStorePriceLine> {

    XbzStorePriceLine selectLastOfXbzStorePrice(Integer storeId);
}
