package com.shz.foundation.security.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

public class AuthUtils {

	/**检查id是否是当前用户的id*/
	public static void checkUser(String userId) {
		Subject subject=SecurityUtils.getSubject();
		if(!subject.isAuthenticated()) {
			throw new AuthenticationException("请登录！");
		}
		ShiroUser user=(ShiroUser)subject.getPrincipal();
		String id=user.getId();
		if(!id.equals(userId)) {
			throw new RuntimeException("您只能访问您自己的个人信息");
		}
	}
}
