package com.restapi_testng;
import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Util.Excle_file;
import com.Util.Jsonpath;
import com.Util.Object_Mapper;
import com.Util.Propertiesfile;
import com.Util.Spark_reportor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pojo.Location;
import com.pojo.POJO;
import com.pojo.PassData_pojo;
import com.pojo.Place_id;

import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Listeners(com.restapi_testng.Listners.class)
public class test extends Pre_conditions { 

	
	public static String placeid;
	public static String respo_postresp;
	public static String respo_getresp;
	public static String respo_deleteresp;
	public static Response respo;


	@DataProvider (name = "excle")
	public Object[][] Authentication(){ 
		Excle_file abc=new Excle_file(prop.getProperty("Exclepath"),prop.getProperty("Post_Sheet"));
		Object[][] data=null;
		try {
			data = abc.readexcle(1,"Post");
		} catch (Exception e) {
			
			e.printStackTrace();
		}


		return data; 
	}

	@Test (priority = 1, testName="CREAT NEW DATA ON RESOURCES", dataProvider = "excle")
	public void posttest(String testname,String path,double lat,double lng, double acc,String name, String phone,String address, String typ1, String typ2, String web, String lang) throws IOException {


		PrintStream addfile=Propertiesfile.responcefile("Result/POST");
		
		POJO pojo =PassData_pojo.addvalue(lat,lng,acc,name, phone,address,typ1,typ2,web,lang);		
		String payload= Object_Mapper.mapper(pojo);

	      respo = given().spec(Pre_conditions.req).
				body(payload)                 //new String (Files.readAllBytes(Paths.get(Pre_conditions.prop.getProperty("jsonpath"))))
				.filter(ResponseLoggingFilter.logResponseTo(addfile))				
				.when().post(path).
				then().log().all().spec(Pre_conditions.res).extract().response();
          respo_postresp =respo.asString();
		JsonPath js1 = Jsonpath.parsjson(respo_postresp);
		placeid = js1.getString("place_id");
        String status= js1.getString("status");
        String scop=js1.getString("scope");
        String ref=js1.getString("reference");
        String id =js1.getString("id");
		Spark_reportor.info(testname,payload);
		
		
		
	//Spark_reportor.info(postresp);
		
       
		//System.out.println(payload);
		
		Excle_file.creatdata(1, "src/test/resources/Resources/Api_testing.xlsx",status, placeid,scop,ref,id);
	}

	@Test(priority = 2,testName="GET DATA FROM RESOURCES", dependsOnMethods = "posttest" )
	public void gettest() throws FileNotFoundException {
		PrintStream addfile=Propertiesfile.responcefile("Result/GET");

		  respo = given().spec(Pre_conditions.req)
				.queryParam("place_id", placeid).filter(ResponseLoggingFilter.logResponseTo(addfile)).
				when().get(Pre_conditions.prop.getProperty("Resources1")).
				then().log().all().spec(Pre_conditions.res).extract().response();
		 respo_getresp= respo.asString();
		        Spark_reportor.info("get",respo_postresp);

	}

	@Test(priority = 3,testName="UPDATE DATA ON RESOURCES", enabled = false)
	public void puttest() throws FileNotFoundException, JsonProcessingException {
		PrintStream addfile=Propertiesfile.responcefile("Result/PUT");

		Place_id id=new Place_id();
		id.setPlace_id(placeid);
		id.setAddress("10,jsonroad, UK");
		id.setKey("qaclick123");
		String putload=Object_Mapper.mapper(id);


		 respo = given().spec(Pre_conditions.req)
				.queryParam("place_id", placeid).body(putload).
				filter(ResponseLoggingFilter.logResponseTo(addfile)).
				when().put(Pre_conditions.prop.getProperty("Resources2"))
				.then().log().all().spec(Pre_conditions.res).extract().response();
                  String respo_putresp =respo.asString();
		JsonPath js2 = Jsonpath.parsjson(respo_putresp);

		Spark_reportor.info("put",putload);


	}

	@Test(priority = 4,testName="DELETE DATA FROM RESOURCES")
	public void deletetest() throws FileNotFoundException, JsonProcessingException {
		PrintStream addfile=Propertiesfile.responcefile("Result/DELETE");

		Place_id id=new Place_id();
		id.setPlace_id(placeid);

		String deletepayload =Object_Mapper.mapper(id);

		 respo =	given().spec(Pre_conditions.req).
				body(deletepayload).filter(ResponseLoggingFilter.logResponseTo(addfile)).		
				when().delete(Pre_conditions.prop.getProperty("Resources3")).
				then().log().all().spec(Pre_conditions.res).extract().response();	

		respo_deleteresp =respo.asString();
		         Spark_reportor.info("Delete",deletepayload);


	}



	//sapret method for given (log all data ) for report 
	//using pojo class  //data provider excle integration 

}
