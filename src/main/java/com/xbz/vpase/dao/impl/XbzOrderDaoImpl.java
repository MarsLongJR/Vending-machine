package com.xbz.vpase.dao.impl;

import com.xbz.vpase.dao.XbzOrderDao;
import com.xbz.vpase.dao.base.impl.BaseDaoImpl;
import com.xbz.vpase.persistent.entity.XbzOrder;
import org.springframework.stereotype.Repository;

@Repository("xbzOrderDao")
public class XbzOrderDaoImpl extends BaseDaoImpl<XbzOrder> implements XbzOrderDao {
}
