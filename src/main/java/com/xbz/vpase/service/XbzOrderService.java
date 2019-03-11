package com.xbz.vpase.service;

import com.xbz.vpase.persistent.entity.XbzOrder;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.service.base.BaseService;

import java.util.List;

public interface XbzOrderService extends BaseService<XbzOrder>  {

    ResultJson insertXbzOrder(XbzOrder xbzOrder);

    List<XbzOrder> selectXbzOrderList(Long id, String orderCode, String pickupCode, String tel, String linkMan, String totalPrice, Integer orderType, String payPrice,
                                          Integer userId, Boolean enable, Short distributionType, String machineCode, Short payType,String startDate,String endDate,
                                          Integer pageNum,Integer pageSize);
}
