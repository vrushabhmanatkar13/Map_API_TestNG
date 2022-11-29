package com.restapi_testng;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Util.Spark_reportor;

public class Listners implements ITestListener{

	public void onTestStart(ITestResult result) {
		Pre_conditions.sp.creattest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		/*
		 * String respo_string=null; try { respo_string =(String)
		 * result.getTestClass().getRealClass().getDeclaredField("respo").get(result.
		 * getInstance()); } catch (Exception e) {
		 * 
		 * e.printStackTrace(); }
		 */
		
		
		Spark_reportor.Pass(test.respo.asString());
	}

	public void onTestFailure(ITestResult result) {
		 Spark_reportor.Fail(result.getTestName()+result.getThrowable());
		
		
	}

	public void onTestSkipped(ITestResult result) {
		
		
	}
	

}
