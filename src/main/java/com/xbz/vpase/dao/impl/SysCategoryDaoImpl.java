package com.xbz.vpase.dao.impl;

import com.xbz.vpase.dao.SysCategoryDao;
import com.xbz.vpase.dao.base.impl.BaseDaoImpl;
import com.xbz.vpase.persistent.entity.SysCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sysCategoryDao")
public class SysCategoryDaoImpl extends BaseDaoImpl<SysCategory> implements SysCategoryDao {

    @Override
    public List<SysCategory> selectSysCategoryList(SysCategory sysCategory) {
        return sqlSessionTemplate.selectList("SysCategory.selectSysCategoryList",sysCategory);
    }
}
