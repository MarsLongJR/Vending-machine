package com.xbz.vpase.dao.impl;

import com.xbz.vpase.dao.SysAreaDao;
import com.xbz.vpase.dao.base.impl.BaseDaoImpl;
import com.xbz.vpase.persistent.entity.SysArea;
import com.xbz.vpase.utils.TextUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sysAreaDao")
public class SysAreaDaoImpl extends BaseDaoImpl<SysArea> implements SysAreaDao {

    @Override
    public List<SysArea> selectSysAreaList(String parentCode, String areaName, String areaCode) {
        try {
            SysArea sysArea = new SysArea();
            if(!TextUtil.isEmpty(areaCode)){
                sysArea.setAreaCode(areaCode);
            }
            if(!TextUtil.isEmpty(areaName)){
                sysArea.setAreaName(areaName);
            }
            if(TextUtil.isEmpty(parentCode)){
                parentCode ="0";
            }
            sysArea.setParent(parentCode);
            sysArea.setEnable(true);
            return sqlSessionTemplate.selectList("SysArea.selectSysAreaList",sysArea);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
