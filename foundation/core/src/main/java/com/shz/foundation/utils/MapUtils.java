package com.shz.foundation.utils;

import java.text.MessageFormat;
import java.util.Map;

public class MapUtils {
	
	
	private static final String NullArgumentErrorPattern="参数{0}不能为空";

	/**
	 * 如果value为空，则不能添加到map中，并且报错
	 * @param map
	 * @param key
	 * @param value
	 */
	public static <K,V> void putNotNullKeyValues(Map<K, V> map ,K key, V value) {
		if(value==null) throw new IllegalArgumentException(MessageFormat.format(NullArgumentErrorPattern, key));
		map.put(key, value);
	}
	
	/**
	 * 如果value不为空，则添加到map中
	 * @param map
	 * @param key
	 * @param value
	 */
	public static <K,V> void put(Map<K, V> map ,K key, V value) {
		if(value==null) return;
		map.put(key, value);
	}
}
