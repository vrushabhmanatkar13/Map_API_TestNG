package com.Util;

import org.asynchttpclient.RequestBuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Req_Res_Specification {
	
	public static RequestSpecification Req_Spe(String uri, String key, String value) {
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri(uri).addQueryParam(key, value).build();
		return req;
	}
	
	public static ResponseSpecification Res_Spe(int code) {
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(code).expectContentType(ContentType.JSON).build();
		return res;
	}
	

}
