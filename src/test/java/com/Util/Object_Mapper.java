package com.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Object_Mapper {

	
	public static String mapper(Object obj) {
		
		ObjectMapper mapper=new ObjectMapper();
		String string=null;
		try {
			string = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return string;
	}
	
}
