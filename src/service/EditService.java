package service;

import java.util.List;

import dao.RecordDAO;
import entity.Category;
import entity.Record;
import gui.model.CategoryComboBoxModel;

public class EditService {

	RecordDAO recordDao = new RecordDAO();
	
	public void updateRecord(Record record){
		recordDao.update(record);
	}
	
	public int getIndex(Record record){
		List<Category> cs = new CategoryComboBoxModel().cs;
		int index = -1;
		for(int i=0;i<cs.size();i++){
			Category c = cs.get(i);
			if(record.cid == c.id){
				index = i;
				break;
			}
		}
		return index;
	}
}