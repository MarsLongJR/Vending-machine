package com.xbz.vpase.service.impl;

import com.xbz.vpase.dao.XbzOrderDetailDao;
import com.xbz.vpase.dao.XbzOrderLineDao;
import com.xbz.vpase.persistent.entity.XbzOrderLine;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.service.XbzOrderLineService;
import com.xbz.vpase.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class XbzOrderLineServiceImpl extends BaseServiceImpl<XbzOrderLine> implements XbzOrderLineService {

    @Override
    public ResultJson selectXbzStoreLineByOrderId(Long orderId) {
        return null;
    }
}
