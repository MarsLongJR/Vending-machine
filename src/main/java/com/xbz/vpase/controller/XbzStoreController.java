package com.xbz.vpase.controller;

import com.xbz.vpase.persistent.entity.XbzStore;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.response.RetCode;
import com.xbz.vpase.service.XbzStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("store")
public class XbzStoreController {

    @Autowired
    private XbzStoreService xbzStoreService;


    /**
     * 添加商品
     * @param xbzStore
     * @return
     */
    @RequestMapping("insetSysStore")
    public @ResponseBody ResultJson insetSysStore(@RequestBody XbzStore xbzStore){
        ResultJson resultJson = new ResultJson();
        try {
            if(xbzStore!=null&&xbzStore.getBarcode()!=null&&xbzStore.getCategoryId()!=null&&xbzStore.getStoreName()!=null){
                resultJson = xbzStoreService.insetSysStore(xbzStore);
            }else{
                resultJson.ResultJson(RetCode.PARAM_NULL);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return resultJson;
    }

    /**
     * 分页查询商品
     * @param id 商品id
     * @param storeName 商品名称
     * @param barCode 商品条码
     * @param price 商品价格
     * @param startDate 商品添加时间
     * @param endDate 商品添加时间
     * @param enable 是否存在
     * @param  categoryId 商品类别id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("selectXbzStorePage")
    public @ResponseBody Map<String ,Object> selectXbzStorePage(Integer id, String storeName, String barCode,String sysName, String sysCode, BigDecimal price, String startDate, String endDate, Boolean enable, Integer categoryId, Integer pageNum, Integer pageSize){
        Map<String ,Object> objectMap = new HashMap<>();
        try {
            List<XbzStore> xbzStores = xbzStoreService.selectXbzStoreList(id,storeName,barCode,sysName,sysCode,price,startDate,endDate,enable,categoryId,pageNum,pageSize);
            Integer totalCount = xbzStoreService.selectXbzStoreCount(id,storeName,barCode,sysName,sysCode,price,startDate,endDate,enable,categoryId);
            objectMap.put("list",xbzStores);
            objectMap.put("count",totalCount);
            return objectMap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping("modifyXbzStore")
    public @ResponseBody ResultJson modifyXbzStore(Integer id, String storeName, String barCode, String sysName, String sysCode,BigDecimal price, Boolean enable, Integer categoryId){
        ResultJson resultJson = new ResultJson();
        try {
            resultJson = xbzStoreService.modifyXbzStore(id,storeName,barCode,sysName,sysCode,price,enable,categoryId);
            return resultJson;
        }catch (Exception e){
            e.printStackTrace();
            resultJson.ResultJson(RetCode.ERROR);
        }
        return resultJson;

    }



}
