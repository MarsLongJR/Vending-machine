package com.xbz.vpase.service.base.impl;


import com.xbz.vpase.dao.base.BaseDao;
import com.xbz.vpase.service.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class BaseServiceImpl<T>implements BaseService<T> {
    private BaseDao<T> baseDao;

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }
    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }
	public T get(Integer id) {
		return baseDao.get(id);
	}
	
	public Integer save(T entity) {
		return baseDao.save(entity);
	}

	@Transactional
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void updateByConditon(T entity) {
		baseDao.updateByConditon(entity);
	}

	public void delete(String id) {
		baseDao.delete(id);
	}
	
	public void delete(String[] ids) {
		baseDao.delete(ids);
	}
	
	public List<T> selectListByEntity(T entity) {
		return baseDao.selectListByEntity(entity);
	}
}
