package com.xbz.vpase.controller;

import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.service.SysAreaService;
import com.xbz.vpase.utils.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("area")
public class SysAreaController {

    @Autowired
    private SysAreaService sysAreaService;

    /**
     * 根据上一级的编号查询下一级区域
     * @param
     * @return
     */
    @RequestMapping("selectSysAreaList_allow")
    public  @ResponseBody ResultJson selectSysAreaList(String parentCode, String areaName,String areaCode){
        try {
            ResultJson resultJson = sysAreaService.selectSysAreaList(parentCode, areaName, areaCode);
            return resultJson;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
