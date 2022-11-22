package com.Util;

import java.io.*;
import java.util.Properties;

import io.restassured.internal.support.FileReader;

public class Propertiesfile {
	
	
	public static Properties loadprop(String path) throws IOException {
		
		FileInputStream file=new FileInputStream(path);
		
	   Properties prop=new Properties();
	   prop.load(file);
	   return prop;
	}
	
	public static PrintStream responcefile(String path) throws FileNotFoundException {
		
		PrintStream pr=new PrintStream(new FileOutputStream(path));
		return pr;
	}

	

}
