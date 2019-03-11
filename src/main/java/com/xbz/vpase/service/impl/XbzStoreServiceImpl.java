package com.xbz.vpase.service.impl;

import com.xbz.vpase.authorities.UserInfo;
import com.xbz.vpase.dao.XbzStoreDao;
import com.xbz.vpase.dao.XbzStorePriceLineDao;
import com.xbz.vpase.persistent.entity.XbzStore;
import com.xbz.vpase.persistent.entity.XbzStorePriceLine;
import com.xbz.vpase.request.XbzStoreRequest;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.response.RetCode;
import com.xbz.vpase.service.XbzStorePriceLineService;
import com.xbz.vpase.service.XbzStoreService;
import com.xbz.vpase.service.base.impl.BaseServiceImpl;
import com.xbz.vpase.utils.TextUtil;
import org.apache.http.impl.execchain.TunnelRefusedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class XbzStoreServiceImpl extends BaseServiceImpl<XbzStore> implements XbzStoreService {

    @Resource
    private XbzStoreDao xbzStoreDao;

    @Autowired
    private XbzStorePriceLineService xbzStorePriceLineService;

    @Resource
    public void setBaseDao(XbzStoreDao xbzStoreDao) {
        super.setBaseDao(xbzStoreDao);
    }

    /**
     * 判断参数是否为空并set
     * @param id
     * @param storeName
     * @param barCode
     * @param sysName
     * @param sysCode
     * @param price
     * @param startDate
     * @param endDate
     * @param enable
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    private XbzStoreRequest paramIsNull(Integer id, String storeName, String barCode, String sysName,String sysCode,String price, String startDate, String endDate, Boolean enable,Integer categoryId, Integer pageNum, Integer pageSize){
        try {
            XbzStoreRequest request = new XbzStoreRequest();
            if(id!=null){
                request.setId(id);
            }
            if(!TextUtil.isEmpty(storeName)){
                request.setStoreName(storeName);
            }
            if(!TextUtil.isEmpty(barCode)){
                request.setBarcode(barCode);
            }
            if(!TextUtil.isEmpty(sysCode)){
                request.setSysCode(sysCode);
            }
            if(!TextUtil.isEmpty(sysName)){
                request.setSysName(sysName);
            }
            if(price!=null&&Double.valueOf(price)>0.0){
                request.setPrice(BigDecimal.valueOf(Double.valueOf(price)));
            }
            if(!TextUtil.isEmpty(startDate)){
                request.setStartDate(startDate+"00:00:00");
            }
            if(!TextUtil.isEmpty(endDate)){
                request.setEndDate(endDate+"23:59:59");
            }
            if(enable!=null){
                request.setEnable(enable);
            }
            if(pageNum!=null && pageSize!=null){
                request.setPage(pageNum,pageSize);
            }
            if(categoryId!=null){
                request.setCategoryId(categoryId);
            }
            return request;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 添加商品，如果商品添加时给了价格，添加商品价格记录
     * @param xbzStore
     * @return
     */
    @Override
    public ResultJson insetSysStore(XbzStore xbzStore) {
        ResultJson resultJson = new ResultJson();
        try {
            UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            xbzStore.setCreateTime(new Date());
            xbzStore.setCreateId(userInfo.getId());
            xbzStoreDao.save(xbzStore);
            if(xbzStore.getPrice()!=null && xbzStore.getPrice().doubleValue()>0.0){
                xbzStorePriceLineService.insetXbzStorePrice(xbzStore.getPrice(),xbzStore.getId());
            }
            resultJson.ResultJson(RetCode.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultJson;
    }

    @Override
    public List<XbzStore> selectXbzStoreList(Integer id, String storeName, String barCode, String sysName,String sysCode,String price, String startDate, String endDate, Boolean enable,Integer categoryId, Integer pageNum, Integer pageSize) {
        try {
            XbzStoreRequest request = paramIsNull(id,storeName,barCode,sysName,sysCode,price,startDate,endDate,enable,categoryId,pageNum,pageSize);
            List<XbzStore> xbzStores =  xbzStoreDao.selectXbzStoreList(request);
            return xbzStores;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer selectXbzStoreCount(Integer id, String storeName, String barCode,String sysName,String sysCode, String price, String startDate, String endDate, Boolean enable, Integer categoryId) {
        try {
            XbzStoreRequest request =  paramIsNull(id,storeName,barCode,sysName,sysCode,price,startDate,endDate,enable,categoryId,null,null);
            Integer totalCount=  xbzStoreDao.selectXbzStoreCount(request);
            return totalCount;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultJson modifyXbzStore(Integer id,String storeName, String barCode,String sysName,String sysCode, String price, Boolean enable, Integer categoryId) {
        ResultJson resultJson = new ResultJson();
        try {
            XbzStore store = xbzStoreDao.get(id);
            if(store!=null){
                if(!TextUtil.isEmpty(storeName)){
                    store.setStoreName(storeName);
                }
                if(!TextUtil.isEmpty(storeName)){
                    store.setStoreName(storeName);
                }
                if(!TextUtil.isEmpty(sysCode)){
                    store.setSysCode(sysCode);
                }
                if(!TextUtil.isEmpty(sysName)){
                    store.setSysName(sysName);
                }
                if(!TextUtil.isEmpty(barCode)){
                    store.setBarcode(barCode);
                }
                if(!TextUtil.isEmpty(barCode)){
                    store.setBarcode(barCode);
                }
                if(price!=null&&Double.valueOf(price)!=store.getPrice().doubleValue()){
                    xbzStorePriceLineService.insetXbzStorePrice(BigDecimal.valueOf(Double.valueOf(price)),store.getId());
                }
                if(enable!=null){
                    store.setEnable(enable);
                }
                if(categoryId!=null){
                    store.setCategoryId(categoryId);
                }
                xbzStoreDao.save(store);
                resultJson.ResultJson(RetCode.OK);
            }else{
                resultJson.ResultJson(RetCode.NO_CONTENT);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultJson.ResultJson(RetCode.ERROR);
        }
        return resultJson;
    }


}
