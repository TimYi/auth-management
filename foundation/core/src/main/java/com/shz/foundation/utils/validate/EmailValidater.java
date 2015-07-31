package com.shz.foundation.utils.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidater {

	public final static String EMAIL_REGEX="^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";

	public static boolean isEmail(String email) {
		Pattern pattern=Pattern.compile(EMAIL_REGEX);
		Matcher matcher=pattern.matcher(email);
		return matcher.matches();
	}
}
