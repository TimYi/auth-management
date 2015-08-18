package com.shz.foundation.security.shiro;

import java.util.function.Consumer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 定义权限字符规则，解析用户是否具有指定权限
 * 除了特殊处理字符之外，其它代码都只进行权限检查
 * @author pc
 *
 */
public class AuthDecesider {

	private static final String IS_USER="user";
	private static final String ANON="anon";
	
	public static void permissionDeside(String permission, Consumer<String> isUser, Consumer<String> anon, Consumer<String> perms) {
		if(permission.equals(IS_USER)) {
			isUser.accept(permission);
		} else if(permission.equals(ANON)) {
			anon.accept(permission);
		} else {
			perms.accept(permission);
		}
	}
	
	/**
	 * 用户是否具有访问权限
	 * @param permission 权限代码
	 * @return
	 */
	public static boolean couldAccess(String permission) {
		Subject subject=SecurityUtils.getSubject();
		try {
			permissionDeside(permission, p->subject.isAuthenticated(), p->{},
					p->subject.checkPermission(p));
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
}
