package com.Util;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.internal.TestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.AbstractReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Spark_reportor {
	
	 public ExtentSparkReporter spark;
	 public static ExtentReports extent;
	 public static ExtentTest test;
	
	public Spark_reportor() {
		
		spark=new ExtentSparkReporter("Result/Report.html");
		extent=new ExtentReports();
		extent.attachReporter(spark); 
		spark.config().setTheme(Theme.DARK);
		
				
		
	}
	public void givename(String Doc_name, String name) {
		spark.config().setDocumentTitle(Doc_name);
		spark.config().setReportName(name);
		
	}
	
	public void creattest(String name) {
		test=extent.createTest(name).assignAuthor("Vrushabh");
	}
	public static void info(String testname,String data) {
		test.log(Status.INFO, testname);
		test.log(Status.INFO, MarkupHelper.createCodeBlock(data, CodeLanguage.JSON));
	}
	public static void Pass(String pass){
		test.log(Status.PASS, MarkupHelper.createCodeBlock(pass, CodeLanguage.JSON));
	}
	public static void Fail(String fail){
		test.log(Status.FAIL, fail);
		
	}
	public static void flush() {
		extent.flush();
	}
	

}
