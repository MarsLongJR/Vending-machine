package com.xbz.vpase.security;

import com.xbz.vpase.service.SysUserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ValidateCodeAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	
	@Resource
	private SysUserService sysUserService;
	private boolean postOnly = true;//只能使用post方式提交
	private boolean allowEmptyValidateCode = false;//验证码不能为空
	private String sessionvalidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;
	private String validateCodeParameter = DEFAULT_VALIDATE_CODE_PARAMETER;

	public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "random";//session中保存的验证码
	public static final String DEFAULT_VALIDATE_CODE_PARAMETER = "code";// 输入的验证码


	@Deprecated
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
	private SqlSessionFactory sqlSessionFactory;
    @Autowired
    @Qualifier("sqlSessionTemplate")
    protected transient SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws  AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}
//		System.out.println(obtainUsername(request).trim()+"=========================================================================");
		String username = obtainUsername(request).trim();
		String password = obtainPassword(request);
		if (password == null) {
			password = "";
		}

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);

		// Place the last username attempted into HttpSession for views
		HttpSession session = request.getSession(false);

		if (session != null || getAllowSessionCreation()) {
			request.getSession().setAttribute(
					SPRING_SECURITY_LAST_USERNAME_KEY,
					TextEscapeUtils.escapeEntities(username));
		}
		
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		// check validate code
		if (!isAllowEmptyValidateCode())
			checkValidateCode(request);
		updateLastLoginTime(username, password);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * 
	 * <li>比较session中的验证码和用户输入的验证码是否相等</li>
	 * 
	 */
	protected void checkValidateCode(HttpServletRequest request) {
		/*String sessionValidateCode = obtainSessionValidateCode(request);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		if (validateCodeParameter.isEmpty()
				|| !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			throw new AuthenticationServiceException(
					"验证码输入错误"
					messages.getMessage(VALIDATE_CODE_FAILED_MSG_KEY));
		}*/
	}
	
	private void updateLastLoginTime(String username,String password){
//		Session session= sessionFactory.openSession();
//		String sql="";
//		User user=userService.getUser(username);
//		if(user!=null){
//			if(password.equals(user.getPassword())){
//				user.setLastLogin(new Date());
//				userService.update(user);
//			}
//		}
		
	}
	
	private String obtainValidateCodeParameter(HttpServletRequest request) {
		return request.getParameter(validateCodeParameter);
	}

	protected String obtainSessionValidateCode(HttpServletRequest request) {
		Object obj = request.getSession()
				.getAttribute(sessionvalidateCodeField);
		return null == obj ? "" : obj.toString();
	}

	public boolean isPostOnly() {
		return postOnly;
	}

	@Override
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public String getValidateCodeName() {
		return sessionvalidateCodeField;
	}

	public void setValidateCodeName(String validateCodeName) {
		this.sessionvalidateCodeField = validateCodeName;
	}

	public boolean isAllowEmptyValidateCode() {
		return allowEmptyValidateCode;
	}

	public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {
		this.allowEmptyValidateCode = allowEmptyValidateCode;
	}


	public ValidateCodeAuthenticationFilter() {
		super();
	}

	public ValidateCodeAuthenticationFilter(SqlSessionFactory sqlSessionFactory) {
		super();
		this.sqlSessionFactory = sqlSessionFactory;
	}	
	
	
}