package a8.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import a8.exceptions.EightaException;

public class JsonUtils {

	public static byte [] convertFromBean(Object bean){
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsBytes(bean);
		} catch (JsonProcessingException e) {
			throw new EightaException(e);
		}
	}
	
	public static <T> T convertToBean(byte [] raw, Class<T> clazz) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(raw, clazz);
		} catch (IOException e) {
			throw new EightaException(e);
		}
	}
}
