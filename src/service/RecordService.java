package service;

import java.util.Date;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class RecordService {

	RecordDAO recordDao = new RecordDAO();
	
	public void add(double spend,Category c,String comment,Date date){
		Record record = new Record();
		record.spend = spend;
		record.cid = c.id;
		record.comment = comment;
		record.date = date;
		recordDao.add(record);
	}
}