package service;

import java.util.List;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

public class SpendService {

	public SpendPage getSpendPage(){
		RecordDAO dao = new RecordDAO();
		
		List<Record> thisMonthRecords = dao.listThisMonth();
		List<Record> toDayRecords = dao.listToday();
		
		int thisMonthTotalDay = DateUtil.thisMonthTotalDay();
		
		double monthSpend = 0;
        double todaySpend = 0;
        double avgSpendPerDay = 0;
        double monthAvailable = 0;
        double dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;
        
        double monthBudget = new ConfigService().getIntBudget();
        
        for(Record record : thisMonthRecords){
        	monthSpend += record.getSpend();
        }
        
        for(Record record : toDayRecords){
        	todaySpend += record.getSpend();
        }
        
        monthLeftDay = DateUtil.thisMonthLeftDay();
        
        avgSpendPerDay = monthSpend/(thisMonthTotalDay-monthLeftDay);
        monthAvailable = monthBudget-monthSpend;
        usagePercentage = (int)(monthSpend*100/monthBudget);
        dayAvgAvailable = monthAvailable/monthLeftDay;
        
        return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthAvailable, dayAvgAvailable, monthLeftDay,
                usagePercentage);
	}
}