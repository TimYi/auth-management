package com.shz.foundation.test;

import java.io.IOException;

import com.shz.foundation.utils.JsonSerializer;

public class TestUtils {

	public static void printJson(Object obj) {
		try {
			System.out.println(JsonSerializer.Serialize(obj));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
