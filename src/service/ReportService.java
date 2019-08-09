package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.RecordDAO;
import entity.Record;

public class ReportService {

	//一个月的消费记录
	public List<Record> listMonthRecords(Date monthBegin,int monthTotalDay){
		List<Record> result = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		for(int i=0;i<monthTotalDay;i++){
			Record record = new Record();
			c.setTime(monthBegin);
			c.add(Calendar.DATE,i);
			Date date = c.getTime();
			record.spend = getDaySpend(date);
			result.add(record);
		}
		return result;
	}
	
	//某一天的消费记录
	public int getDaySpend(Date date){
		RecordDAO dao = new RecordDAO();
		List<Record> rs = dao.list(date);
		double daySpend = 0;
		for(Record record : rs){
			daySpend += record.spend;
		}
		return (int) daySpend;
	}
	
	public List<Integer> getMonths(){
		List<Integer> is = new ArrayList<>();
		for(int i=1;i<=12;i++){
			is.add(i);
		}
		return is;
	}
	
	public List<Integer> getYears(){
		List<Integer> is = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int yearStart = Integer.parseInt(sdf.format(firstDate()));
		int yearEnd = Integer.parseInt(sdf.format(new Date()));
		for(int i=yearEnd;i>=yearStart;i--){
			is.add(i);
		}
		return is;
	}
	
	public Date firstDate(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.YEAR,2015);// 2015
		Date dateStart = c.getTime();
		Date dateEnd = new Date();
		List<Record> rs = new RecordDAO().listDateAsc(dateStart,dateEnd);
		if(rs.size() == 0){
			return new Date();
		}else{
			return rs.get(0).date;
		}
	}
	
	//一个月多少天
	public int getMonthDays(int year,int month){
		if(month==4 || month==6 || month==9 || month==11){
			return 30;
		}
		if(month == 2){
			if((year%400==0) || (year%4==0 && year%100!=0)){
				return 29;
			}else{
				return 28;
			}
		}
		return 31;
	}
	
}