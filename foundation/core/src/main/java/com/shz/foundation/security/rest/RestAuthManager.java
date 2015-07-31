package com.shz.foundation.security.rest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface RestAuthManager extends UserDetailsService {

	/**
	 * 获取验证身份所需凭据
	 * @param principal
	 * @param credential
	 * @return
	 */
	Token login(String principal, String credential);
	
	/**注销*/
	void logout(String token);
	
	/**
	 * 根据验证凭据进行身份识别
	 * @param token
	 * @return 如果没有找到对应身份，返回空
	 */
	UserDetails authenticate(String token);
}
