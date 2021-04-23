package com.ssafy.util;

import java.util.Calendar;

public class DateUtil {
	public static String getToday(){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
 		Calendar c = Calendar.getInstance();
 		return formatter.format(c.getTime());
 	}
	public static String getCurMonday(){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
 		Calendar c = Calendar.getInstance();
 		
 		int dayOfWeek=c.get(Calendar.DAY_OF_WEEK);
 		if(dayOfWeek==1) c.set(Calendar.DATE, c.get(Calendar.DATE)-1);
 		c.setTime(c.getTime());

 		c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
 		return formatter.format(c.getTime());
 	}
	public static String getCurMonth(){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
 		Calendar c = Calendar.getInstance();
 		c.set(Calendar.MONTH,c.get(Calendar.MONTH));
 		c.set(Calendar.DATE, 1);
 		return formatter.format(c.getTime());
 	}
	public static String getCurYear(){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
 		Calendar c = Calendar.getInstance();
 		c.set(Calendar.YEAR,c.get(Calendar.YEAR));
 		c.set(Calendar.MONTH,0);
 		c.set(Calendar.DATE, 1);
 		return formatter.format(c.getTime());
	}
}


