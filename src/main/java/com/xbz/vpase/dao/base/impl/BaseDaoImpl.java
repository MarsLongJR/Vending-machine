package com.xbz.vpase.dao.base.impl;


import com.xbz.vpase.dao.base.BaseDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


@Repository
public class BaseDaoImpl<T>  implements BaseDao<T> {
	private Class<T> entityClass;   
	private String className="";
/*    protected transient XLogger log = XLoggerFactory.getXLogger(getClass());*/
    
    /**
    * sql session
    */
    @Autowired
    @Qualifier("sqlSessionTemplate")
    protected transient SqlSessionTemplate sqlSessionTemplate;

    @SuppressWarnings({ "unchecked", "rawtypes" })
   	public BaseDaoImpl() {
           this.entityClass = null;
           Class c = getClass();
           Type type = c.getGenericSuperclass();
           if (type instanceof ParameterizedType) {
               Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
               this.entityClass = (Class<T>) parameterizedType[0];
               String name=entityClass.getName();
               this.className=name.substring(name.lastIndexOf(".")+1);
           }
       }

	@Override
	public T get(Integer id) {
		return sqlSessionTemplate.selectOne(className+".selectByPrimaryKey", id);
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public Integer save(T entity) {
		return sqlSessionTemplate.insert(className+".insertSelective",entity);
	}

	@Override
	public void update(T entity) {
		sqlSessionTemplate.update(this.className+".updateByPrimaryKey",entity);
	}

	@Override
	public void updateByConditon(T entity) {
		sqlSessionTemplate.update(className+".updateByPrimaryKeySelective",entity);
	}


	@Override
	public void delete(String id) {
		sqlSessionTemplate.delete(this.className+".deleteByPrimaryKey", id);
	}

	@Override
	public void delete(String[] ids) {
		for(String id:ids){
			sqlSessionTemplate.delete(this.className+".deleteByPrimaryKey", id);
		}
	}


	@Override
	public List<T> selectListByEntity(T entity) {
		return sqlSessionTemplate.selectList(this.className+".selectListByEntity", entity);
	}
}
