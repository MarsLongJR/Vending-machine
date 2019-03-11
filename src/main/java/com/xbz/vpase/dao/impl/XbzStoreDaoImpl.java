package com.xbz.vpase.dao.impl;

import com.xbz.vpase.dao.XbzStoreDao;
import com.xbz.vpase.dao.base.impl.BaseDaoImpl;
import com.xbz.vpase.persistent.entity.XbzStore;
import com.xbz.vpase.request.XbzStoreRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("xbzStoreDao")
public class XbzStoreDaoImpl extends BaseDaoImpl<XbzStore> implements XbzStoreDao {

    @Override
    public List<XbzStore> selectXbzStoreList(XbzStoreRequest request) {
        return sqlSessionTemplate.selectList("XbzStore.selectXbzStoreList",request);
    }

    @Override
    public Integer selectXbzStoreCount(XbzStoreRequest request) {
        return sqlSessionTemplate.selectOne("XbzStore.selectXbzStoreCount",request);
    }
}
