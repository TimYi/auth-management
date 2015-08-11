package com.shz.pay.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class OrderNumberGenerater {	
	
	private static final SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 
	 * @param prefix
	 * @return
	 */
	public static String generateOrderNumber(String code) {
		if(code.length()>3) throw new IllegalArgumentException("操作代码长度不能大于3");
		StringBuilder sb=new StringBuilder();
		sb.append(code);
		sb.append(DATE_FORMAT.format(new Date()));
		String sufix=RandomStringUtils.random(3,true,true);
		sb.append(sufix);
		return sb.toString();
	}
}
