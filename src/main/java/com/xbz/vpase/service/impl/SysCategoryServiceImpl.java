package com.xbz.vpase.service.impl;

import com.xbz.vpase.dao.SysCategoryDao;
import com.xbz.vpase.persistent.entity.SysCategory;
import com.xbz.vpase.service.SysCategoryService;
import com.xbz.vpase.service.base.impl.BaseServiceImpl;
import com.xbz.vpase.utils.TextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysCategoryServiceImpl extends BaseServiceImpl<SysCategory> implements SysCategoryService {

    @Resource
    private SysCategoryDao sysCategoryDao;

    @Resource
    public void setBaseDao(SysCategoryDao sysCategoryDao) {
        super.setBaseDao(sysCategoryDao);
    }

    @Override
    public List<SysCategory> selectSysCategoryList(String code, String name, String parent, Boolean enable) {
        try {
            SysCategory sysCategory = new SysCategory();
            if(!TextUtil.isEmpty(code)){
                sysCategory.setCode(code);
            }
            if(!TextUtil.isEmpty(name)){
                sysCategory.setName(name);
            }
            if(!TextUtil.isEmpty(parent)){
                sysCategory.setParent(parent);
            }
            if(enable!=null){
                sysCategory.setEnable(enable);
            }
            List<SysCategory> categories = sysCategoryDao.selectSysCategoryList(sysCategory);
            return categories;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
