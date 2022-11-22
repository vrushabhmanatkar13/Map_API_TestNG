package com.Util;

import io.restassured.path.json.JsonPath;

public class Jsonpath {
	
	public static JsonPath parsjson(String responce) {
		
		JsonPath js=new JsonPath(responce);
		return js;
	}

}
