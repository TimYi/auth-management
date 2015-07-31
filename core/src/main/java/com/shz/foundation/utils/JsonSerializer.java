package com.shz.foundation.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer {
	protected static ObjectMapper mapper=new ObjectMapper();
	
	static {
		mapper.findAndRegisterModules();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(df);
	}
	
	public static <T> T DeSerialize(String json,Class<T> type) throws IOException,JsonParseException,JsonMappingException {
		T value=mapper.readValue(json, type);
		return value;
	}
	
	public static String Serialize(Object object) throws IOException,JsonParseException,JsonMappingException {
		String value=mapper.writeValueAsString(object);
		return value;
	}
	
	public static JsonNode readTree(String json) throws IOException,JsonProcessingException {
		JsonNode node=mapper.readTree(json);
		return node;
	}
	
	public static <T>  T DeSerialize(String json,TypeReference<T> type) throws IOException,JsonParseException,JsonMappingException {
		T value=mapper.readValue(json, type);
		return value;
	}
}
