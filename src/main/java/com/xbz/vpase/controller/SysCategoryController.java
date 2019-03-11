package com.xbz.vpase.controller;

import com.xbz.vpase.persistent.entity.SysCategory;
import com.xbz.vpase.response.ResultJson;
import com.xbz.vpase.response.RetCode;
import com.xbz.vpase.service.SysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("category")
public class SysCategoryController {

    @Autowired
    private SysCategoryService sysCategoryService;

    /**
     *
     * 根据编号，名称，上级类别查询类别
     * @param code
     * @param name
     * @param parent
     * @param enable
     * @return
     */
    @RequestMapping("selectSysCategoryList_allow")
    public @ResponseBody ResultJson selectSysCategoryList(String code, String name , String parent, Boolean enable){
        ResultJson resultJson = new ResultJson();
        try {
            List<SysCategory> categoryList= sysCategoryService.selectSysCategoryList(code,name,parent,enable);
            resultJson.ResultJson(RetCode.OK);
            resultJson.setData(categoryList);
        }catch (Exception e){
            e.printStackTrace();
            resultJson.ResultJson(RetCode.ERROR);
        }
        return resultJson;

    }
}
