package com.xbz.vpase.service.impl;

import com.xbz.vpase.dao.SysAreaDao;
import com.xbz.vpase.persistent.entity.SysArea;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.response.RetCode;
import com.xbz.vpase.service.SysAreaService;
import com.xbz.vpase.service.base.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SysAreaServiceImpl extends BaseServiceImpl<SysArea> implements SysAreaService {

    @Resource
    private SysAreaDao sysAreaDao;

    @Resource
    public void setBaseDao(SysAreaDao sysAreaDao) {
        super.setBaseDao(sysAreaDao);
    }

    /**
     * 区域查询，不进行分页
     * @param parentCode
     * @param areaName
     * @param areaCode
     * @return
     */
    @Override
    public ResultJson selectSysAreaList(String parentCode, String areaName, String areaCode) {

            ResultJson resultJson = new ResultJson();
        try {
            List<SysArea> sysAreas = sysAreaDao.selectSysAreaList(parentCode, areaName, areaCode);
            resultJson.ResultJson(RetCode.OK);
            resultJson.setData(sysAreas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }
}