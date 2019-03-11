package com.xbz.vpase.security;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.*;


public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	 private transient SqlSessionTemplate sqlSessionTemplate;
	
	//private RequestMatcher requestMatcher = new AntPathRequestMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;//resourceMap是所有权限与之相应资源的键值对
	
	//在服务器启动的时候，读取所有的权限和相应的路径资源
	public MyInvocationSecurityMetadataSource(){
		loadResourceDefine();
	}
	
	@SuppressWarnings("unchecked")
	private void loadResourceDefine(){
		List<String> urlList = null;//权限资源的访问地址
		resourceMap=new HashMap<String, Collection<ConfigAttribute>>();
		//Collection<ConfigAttribute> attr=new ArrayList<ConfigAttribute>();
//		List<String>list=sqlSessionTemplate.selectList("tbl_resource.getAuthoritys");
//		for(String authority:list){
//			ConfigAttribute ca = new SecurityConfig(authority);
//			urlList=sqlSessionTemplate.selectList("tbl_resource.getUrl",authority);
//			for(String url:urlList){
//				if(resourceMap.containsKey(url)){
//					Collection<ConfigAttribute> attrs=resourceMap.get(url);
//					attrs.add(ca);
//					resourceMap.put(url, attrs);
//				}else {
//					Collection<ConfigAttribute> attrs =new ArrayList<ConfigAttribute>();
//					attrs.add(ca);
//					resourceMap.put(url, attrs);
//				}
//			}
//		}
	}
	
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// According to a URL, Find out permission configuration of this URL.
	
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// guess object is a URL.
		String url = ((FilterInvocation) object).getRequestUrl();//获得请求路径
		Iterator<String> ite = resourceMap.keySet().iterator();//得到所有路径和权限集合的键值对
		while (ite.hasNext()) {
			String resURL = ite.next();
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);//创建路径比对器
			if (requestMatcher.matches(((FilterInvocation) object).getHttpRequest())) {
				return resourceMap.get(resURL);//如果访问的路径与map中的路径相同，返回相应的权限集合
			}
		}
		return null;//如果始终都没有匹配的路径，返回null
	}

	
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public MyInvocationSecurityMetadataSource(
			SqlSessionTemplate sqlSessionTemplate) {
		super();
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	
	
	
}
