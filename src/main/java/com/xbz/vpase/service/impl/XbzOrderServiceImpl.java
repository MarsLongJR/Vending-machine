package com.xbz.vpase.service.impl;

import com.xbz.vpase.dao.XbzOrderDao;
import com.xbz.vpase.persistent.entity.XbzOrder;
import com.xbz.vpase.request.XbzOrderRequest;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.service.XbzOrderService;
import com.xbz.vpase.service.base.impl.BaseServiceImpl;
import com.xbz.vpase.utils.TextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class XbzOrderServiceImpl extends BaseServiceImpl<XbzOrder> implements XbzOrderService {

    @Resource
    private XbzOrderDao xbzOrderDao;

    @Resource
    public void setBaseDao(XbzOrderDao xbzOrderDao) {
        super.setBaseDao(xbzOrderDao);
    }

    /**
     * 判断参数是否为空
     * @param id
     * @param orderCode
     * @param pickupCode
     * @param tel
     * @param linkMan
     * @param totalPrice
     * @param orderType
     * @param payPrice
     * @param userId
     * @param enable
     * @param distributionType
     * @param machineCode
     * @param payType
     * @param startDate
     * @param endDate
     * @param pageNum
     * @param pageSize
     * @return
     */
    private XbzOrderRequest paramIsNull(Long id, String orderCode, String pickupCode, String tel, String linkMan, String totalPrice, Integer orderType, String payPrice,
                                        Integer userId, Boolean enable, Short distributionType, String machineCode, Short payType,String startDate,String endDate,
                                        Integer pageNum,Integer pageSize){
        XbzOrderRequest request = new XbzOrderRequest();
        try {
            if(id!=null){
                request.setId(id);
            }
            if(!TextUtil.isEmpty(orderCode)){
                request.setOrderCode(orderCode);
            }
            if(!TextUtil.isEmpty(pickupCode)){
                request.setPickupCode(pickupCode);
            }
            if(!TextUtil.isEmpty(tel)){
                request.setTel(tel);
            }
            if(!TextUtil.isEmpty(linkMan)){
                request.setLinkMan(linkMan);
            }
            if(!TextUtil.isEmpty(totalPrice)){
                request.setTotalPrice(BigDecimal.valueOf(Double.valueOf(totalPrice)));
            }
            if(orderType!=null){
                request.setOrderType(orderType);
            }
            if(!TextUtil.isEmpty(payPrice)){
                request.setPayPrice(BigDecimal.valueOf(Double.valueOf(payPrice)));
            }
            if(userId!=null){
                request.setUserId(userId);
            }
            if(enable!=null){
                request.setEnable(enable);
            }
            if(distributionType!=null){
                request.setDistributionType(distributionType);
            }
            if(!TextUtil.isEmpty(machineCode)){
                request.setMachineCode(machineCode);
            }
            if(payType!=null){
                request.setPayType(payType);
            }
            if(!TextUtil.isEmpty(startDate)){
                request.setStartDate(startDate);
            }
            if(!TextUtil.isEmpty(endDate)){
                request.setEndDate(endDate);
            }
            if(pageNum!=null && pageSize!=null){
                request.setPage(pageNum,pageSize);
            }
            return request;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    /**
     * 新增订单
     * @param xbzOrder
     * @return
     */
    @Override
    public ResultJson insertXbzOrder(XbzOrder xbzOrder) {
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<XbzOrder> selectXbzOrderList(Long id, String orderCode, String pickupCode, String tel, String linkMan, String totalPrice, Integer orderType, String payPrice,
                                             Integer userId, Boolean enable, Short distributionType, String machineCode, Short payType,String startDate,String endDate,
                                             Integer pageNum,Integer pageSize) {
        try {

            XbzOrderRequest request = paramIsNull(id, orderCode, pickupCode, tel, linkMan, totalPrice, orderType, totalPrice, orderType, enable, distributionType, machineCode, payType, startDate, endDate, pageNum, pageSize);
            List<XbzOrder> orders = xbzOrderDao.selectXbzOrderList(request);
            return orders;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
