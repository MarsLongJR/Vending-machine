package com.xbz.vpase.service.impl;

import com.xbz.vpase.authorities.UserInfo;
import com.xbz.vpase.dao.XbzStorePriceLineDao;
import com.xbz.vpase.persistent.entity.XbzStorePriceLine;
import com.xbz.vpase.service.XbzStorePriceLineService;
import com.xbz.vpase.service.base.impl.BaseServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class XbzStorePriceLineServiceImpl extends BaseServiceImpl<XbzStorePriceLine> implements XbzStorePriceLineService {

    @Resource
    private XbzStorePriceLineDao xbzStorePriceLineDao;

    @Resource
    public void setBaseDao(XbzStorePriceLineDao xbzStorePriceLineDao) {
        super.setBaseDao(xbzStorePriceLineDao);
    }


    @Override
    public void insetXbzStorePrice(BigDecimal price,Integer storeId) {
        try {
            UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            将上次的价格记录作废
            XbzStorePriceLine line = xbzStorePriceLineDao.selectLastOfXbzStorePrice(storeId);
            if(line!=null){
                line.setEnable(false);
                line.setModificationTime(new Date());
                line.setModifierId(userInfo.getId());
                xbzStorePriceLineDao.updateByConditon(line);
            }
//            插入一条新的商品价格记录
            XbzStorePriceLine priceLine = new XbzStorePriceLine();
            priceLine.setCreateTime(new Date());
            priceLine.setPrice(priceLine.getPrice());
            priceLine.setCreatorId(userInfo.getId());
            priceLine.setStoreId((long)storeId);
            priceLine.setEnable(true);
            xbzStorePriceLineDao.save(priceLine);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<XbzStorePriceLine> selectXbzStorePriceLineByStore(Integer storeId) {
        try {
            List<XbzStorePriceLine> lines = xbzStorePriceLineDao.selectXbzStorePriceLineByStore(storeId);
            return lines;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
