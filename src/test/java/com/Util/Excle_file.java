package com.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excle_file {
	 private static XSSFWorkbook workbook;
	 private static XSSFSheet sheet;
	 private static  CellType cell;
	 private static int lastrow;
	
	public Excle_file(String filepath, String name) {
		FileInputStream file;
		try {
			file = new FileInputStream(filepath);
			workbook=new XSSFWorkbook(file);
			sheet=workbook.getSheet(name);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public  Object[][] readexcle(int rowstart, String testname) throws Exception{
		
		 Object[][] data=null;
		  lastrow =sheet.getPhysicalNumberOfRows();
		  
		  int lastcell=sheet.getRow(0).getLastCellNum();
		  data=new Object[lastrow-1][lastcell];
		  
		 System.out.println("last row "+ lastrow); 
		  System.out.println("last cell "+ lastcell);
		 System.out.println(getstrval(rowstart,0));
		  
		 if (testname.equalsIgnoreCase(getstrval(rowstart,0))) {
			 
		 int ci=0;
		  for (int a=rowstart;a<lastrow;a++,ci++) { 
			  
				 int cj=0;
		for (int b=0;b<lastcell;b++,cj++) {
			
			cell=sheet.getRow(a).getCell(b).getCellType();
		  
			switch(cell) {
			case STRING : data[ci][cj]=getstrval(a,b); break;
			case NUMERIC:data[ci][cj]= getNurval(a,b); break;
			
			}
		}
		}
		  		 
		  }
		 else {
			  throw new Exception ("test name not specified");
		 }
		  
		  return data;
		  
	}
		  
		  
		  
		  public String getstrval(int i,int j) {
			  String str=sheet.getRow(i).getCell(j).getStringCellValue();
			  return str;
		  }
		  public Double getNurval(int i,int j) {
			  double Nur=sheet.getRow(i).getCell(j).getNumericCellValue();
			  return Nur;
		  }
		  
		  
		  
		  
		  public static void creatdata(int row,String filepath,String status,String placeid, String scope,String ref,String id) {
			  FileInputStream file=null;
			try {
				file = new FileInputStream(filepath);
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			}
			  XSSFWorkbook workbook=null;
			try {
				workbook = new XSSFWorkbook(file);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			 
			 XSSFSheet sheet =workbook.getSheet("Sheet2");
			 
			  for (int i=row;i<=lastrow;i++) {
			//	  int lastcell=sheet.getRow(0).getLastCellNum();
				  
			  sheet.createRow(row);
			  sheet.getRow(row).createCell(1).setCellValue(status);
			  sheet.getRow(row).createCell(2).setCellValue(placeid);
			  sheet.getRow(row).createCell(3).setCellValue(scope);
			  sheet.getRow(row).createCell(4).setCellValue(ref);
			  sheet.getRow(row).createCell(5).setCellValue(id);
			  File file_1=new File(filepath);
			  try {
				FileOutputStream output=new FileOutputStream(file_1);
				workbook.write(output);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			 
			  }
			  
			  
		  }
		  
		  
}
