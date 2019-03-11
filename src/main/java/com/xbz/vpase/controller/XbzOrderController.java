package com.xbz.vpase.controller;

import com.xbz.vpase.persistent.entity.XbzOrder;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.response.RetCode;
import com.xbz.vpase.service.XbzOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class XbzOrderController {

    @Autowired
    private XbzOrderService xbzOrderService;

    /**
     * 新增订单
     * @param xbzOrder
     * @return
     */
    @RequestMapping("insertXbzOrder_allow")
    public ResultJson insertXbzOrder(@RequestBody XbzOrder xbzOrder){
        try {
            ResultJson resultJson = new ResultJson();
            if(xbzOrder!=null){
                resultJson = xbzOrderService.insertXbzOrder(xbzOrder);
            return resultJson;
            }else{
                resultJson.ResultJson(RetCode.ERROR);
                return resultJson;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
