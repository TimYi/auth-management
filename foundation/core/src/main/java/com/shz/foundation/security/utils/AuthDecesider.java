package com.shz.foundation.security.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 定义权限字符规则，解析用户是否具有指定权限
 * 除了特殊处理字符之外，其它代码都只进行权限检查
 * @author pc
 *
 */
public class AuthDecesider {

	/**用于表示只有用户才能访问*/
	private static final String SHOULD_LOGIN="isAuthenticated";
	
	/**
	 * 用户是否具有访问权限
	 * @param code 权限代码
	 * @return
	 */
	public static boolean couldAccess(String code) {
		if(StringUtils.isBlank(code)) {
			return true;
		}
		
		//匹配特殊权限代码
		Subject subject=SecurityUtils.getSubject();
		if(code.equals(SHOULD_LOGIN)) {
			return subject.isAuthenticated();
		}
		
		return subject.isPermitted(code);
	}
}
