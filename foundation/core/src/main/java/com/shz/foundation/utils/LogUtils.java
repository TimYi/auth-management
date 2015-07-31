package com.shz.foundation.utils;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public class LogUtils {

	public static Logger logger=LoggerFactory.getLogger(LogUtils.class);
	
	public static void error(String msg) {
		logger.error(msg);
	}
	
	public static void error(Throwable ex) {
		logger.error(ex.getMessage(), ex);
	}
	
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void debug(String message) {
		logger.debug(message);
	}
}
