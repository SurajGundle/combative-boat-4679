package com.utility;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class IDUtil {
	
	public static String generateTenderId(){
		String tid = null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		tid=sdf.format(new java.util.Date());
		
		tid = "T"+tid;
		
		return tid;
	}
	public static String generateVendorId(){
		String vid = null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		vid=sdf.format(new java.util.Date());
		vid = "V"+vid;
		
		return vid;
	}
	public static String generateApplicationId(){
		return generateBidderId();
	}
	
	public static String generateBidderId(){
		String bidderId = null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		bidderId =sdf.format(new java.util.Date());
		bidderId = "B"+bidderId;
		
		return bidderId;
	}

}
