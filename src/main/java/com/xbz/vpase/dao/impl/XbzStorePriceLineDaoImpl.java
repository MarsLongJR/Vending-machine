package com.xbz.vpase.dao.impl;

import com.xbz.vpase.dao.XbzStorePriceLineDao;
import com.xbz.vpase.dao.base.impl.BaseDaoImpl;
import com.xbz.vpase.persistent.entity.XbzStorePriceLine;
import org.springframework.stereotype.Repository;

@Repository("xbzStorePriceLineDao")
public class XbzStorePriceLineDaoImpl extends BaseDaoImpl<XbzStorePriceLine> implements XbzStorePriceLineDao {
    @Override
    public XbzStorePriceLine selectLastOfXbzStorePrice(Integer storeId) {
        XbzStorePriceLine line = new XbzStorePriceLine();
        line.setEnable(true);
        line.setStoreId((long)storeId);
        return sqlSessionTemplate.selectOne("XbzStorePriceLine.selectLastOfXbzStorePrice",line);
    }
}
