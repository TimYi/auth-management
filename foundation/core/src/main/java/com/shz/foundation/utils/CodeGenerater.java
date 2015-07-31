package com.shz.foundation.utils;

import java.util.Random;

/**
 * 提供编码、随机码生成等服务
 * @author pc
 *
 */
public class CodeGenerater {
	private static Random random=new Random();
	public static String getRandomNumber(int charCount){
		StringBuilder builder=new StringBuilder();
		for (int i=0;i<charCount;i++) {
			Integer random=getRandomInt(0, 10);
			builder.append(random);
		}
		String value=builder.toString();
		return value;
	}
	
	public static int getRandomInt(int from, int to){
		int value=from+random.nextInt(to-from);
		return value;
	}
}
