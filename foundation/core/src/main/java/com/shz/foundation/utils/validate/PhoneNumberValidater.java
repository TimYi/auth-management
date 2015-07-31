package com.shz.foundation.utils.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidater {
	public final static String MOBILE_REGEX="1[3|5|7|8|][0-9]{9}";
	/**
	 * 检验是否是标准的11位手机号码
	 * @param number
	 * @return 手机号码正确返回true
	 */
	public static boolean isMobile(String number) {
		Pattern pattern=Pattern.compile(MOBILE_REGEX);
		Matcher matcher=pattern.matcher(number);
		return matcher.matches();
	}
}
