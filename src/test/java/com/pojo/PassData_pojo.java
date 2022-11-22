package com.pojo;

import java.util.ArrayList;
import java.util.List;

public class PassData_pojo {
	
	
	public static POJO addvalue(double lat,double lng, double acc,String name, String phone,String address, String typ1, String typ2, String web, String lang) {
		
		POJO pojo=new POJO();
		Location loc=new Location();
		loc.setLat(lat);
		loc.setLng(lng);
		pojo.setLocation(loc);
		pojo.setAccuracy(acc);
		pojo.setAddress(address);
		pojo.setName(name);
		pojo.setPhone_number(phone);
		List<String> mylist=new ArrayList<String>();
		mylist.add(typ1);
		mylist.add(typ2);
		pojo.setTypes(mylist);
		pojo.setWebsite(web);
		pojo.setLanguage(lang);
		
		return pojo;
	}

}
