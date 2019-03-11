package com.xbz.vpase.service;

import com.xbz.vpase.persistent.entity.XbzStorePriceLine;
import com.xbz.vpase.service.base.BaseService;

import java.math.BigDecimal;
import java.util.List;

public interface XbzStorePriceLineService extends BaseService<XbzStorePriceLine> {

    void insetXbzStorePrice(BigDecimal price,Integer storeId);

    List<XbzStorePriceLine> selectXbzStorePriceLineByStore(Integer storeId);
}
