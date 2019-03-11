package com.xbz.vpase.service;

import com.xbz.vpase.persistent.entity.XbzOrderLine;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.service.base.BaseService;

public interface XbzOrderLineService extends BaseService<XbzOrderLine> {

    ResultJson selectXbzStoreLineByOrderId(Long orderId);
}
