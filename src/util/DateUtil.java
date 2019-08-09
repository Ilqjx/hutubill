package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	static long millisecondsOfOneDay = 24*60*60*1000;
	
	public static java.sql.Date util2sql(java.util.Date d){
		return new java.sql.Date(d.getTime());
	}
	
	public static Date today(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,0);
		return c.getTime();//返回Date对象
	}
	
	//获取月初
	public static Date monthBegin(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE,1);

		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,0);
		
		return c.getTime();
	}
	
	public static Date monthEnd(){
		Calendar c = Calendar.getInstance();
		c.setTime(monthBegin());
		c.add(Calendar.MONTH,1);
		
		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,-1);
		
		return c.getTime();
	}
	
	public static int thisMonthTotalDay(){
		long lastDayMilliSeconds = monthEnd().getTime();
        long firstDayMilliSeconds = monthBegin().getTime();
        return (int) ((lastDayMilliSeconds-firstDayMilliSeconds + 1)/millisecondsOfOneDay);
	}
	
	public static int thisMonthLeftDay(){
		long lastDayMilliSeconds = monthEnd().getTime();
        long toDayMilliSeconds = today().getTime();
        return (int) ((lastDayMilliSeconds - toDayMilliSeconds + 1)/millisecondsOfOneDay) - 1;
	}
	
	public static int getCurrentMonth(){
		SimpleDateFormat sdf = new SimpleDateFormat("M");
		String thisMonth = sdf.format(new Date());
		return Integer.parseInt(thisMonth);
	}
	
	public static int getCurrentYear(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String thisYear = sdf.format(new Date());
		return Integer.parseInt(thisYear);
	}
	
}