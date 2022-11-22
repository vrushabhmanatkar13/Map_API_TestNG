package com.restapi_testng;

import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.Util.Excle_file;
import com.Util.Propertiesfile;
import com.Util.Req_Res_Specification;
import com.Util.Spark_reportor;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Pre_conditions {
	public static Properties prop;
	public static Spark_reportor sp;
	public static RequestSpecification req; 
	public static ResponseSpecification res;
	
	
	@BeforeSuite(alwaysRun = true,enabled = true)
	public void propfile() throws Exception {
		prop=Propertiesfile.loadprop("src/test/resources/Resources/Config.properties");
	  
	  
	   req=Req_Res_Specification.Req_Spe(prop.getProperty("BaseURI"), prop.getProperty("key"), prop.getProperty("value"));
		res =Req_Res_Specification.Res_Spe(200);
		 sp=new Spark_reportor();
		 
		 sp.givename("Map_API","Vrushabh");
		
		
	}
	/*
	 * @BeforeMethod public void beforeMethod(ITestResult result) {
	 * sp.creattest(result.getAttribute(null)); }
	 */
	
	
	
	

	@AfterMethod(alwaysRun = true)
	public void flush() {
		Spark_reportor.flush();
	}

}
