package com.shz.foundation.utils.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidater {

	public static final String USERNAME_REGEX="^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{2,10}$";
	/**
	 * 检验是否是2~10位字母或数字组成的用户名
	 * @param number
	 * @return 手机号码正确返回true
	 */
	public static boolean isUsername(String username) {
		Pattern pattern=Pattern.compile(USERNAME_REGEX);
		Matcher matcher=pattern.matcher(username);
		if(matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
