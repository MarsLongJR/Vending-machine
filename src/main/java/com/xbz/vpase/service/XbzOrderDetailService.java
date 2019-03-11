package com.xbz.vpase.service;

import com.xbz.vpase.persistent.entity.XbzOrderDetail;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.service.base.BaseService;

public interface XbzOrderDetailService extends BaseService<XbzOrderDetail> {

    ResultJson selectXbzStoreDetailByOrderId(Long orderId);

    ResultJson selectXbzStoreLineByOrderId(Long orderId);
}
