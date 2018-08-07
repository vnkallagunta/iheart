package com.iheart.challenge.io;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iheart.challenge.bean.Advertiser;

public class TestDataUtils {
	public static <T> T read(final String absoluteFilePath, Class<T> clazz) {
		
		try {
		    final String json = IOUtils.toString(TestDataUtils.class.getClassLoader().getResourceAsStream(absoluteFilePath));
		    return unmarshall(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static final <T> T unmarshall(final String json, final Class<T> clazz) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			Object o = mapper.readValue(json, clazz);
			return clazz.cast(o);
		}catch(IOException  jpe) {
			throw new RuntimeException("Error during json to java conversion", jpe);
		}
	}
	
	public static void main(String args[]) {
		read("advertiser.json", Advertiser.class);
	}
}
