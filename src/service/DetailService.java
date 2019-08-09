package service;

import java.util.Date;
import java.util.List;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class DetailService {

	RecordDAO recordDao = new RecordDAO();
	CategoryDAO categoryDao = new CategoryDAO();
	
	public List<Record> listDate(Date dateStart,Date dateEnd){
		return recordDao.listDate(dateStart,dateEnd);
	}
	
	public String getCategoryName(int id){
		CategoryDAO categoryDao = new CategoryDAO();
		String categoryName = categoryDao.get(id).name;
		return categoryName;
	}
	
	public List<Record> listToday(){
		return recordDao.listToday();
	}
	
	public List<Category> listCategory(){
		List<Category> cs = new CategoryService().list();
		Category categoryAll = new Category();
		categoryAll.id = -1;
		categoryAll.name = "È«²¿";
		cs.add(0,categoryAll);
		return cs;
	}
	
	public void deleteRecord(int id){
		recordDao.delete(id);
	}
	
	public List<Record> list(Category category,Date dateStart,Date dateEnd){
		if(category.id == -1){
			return listDate(dateStart,dateEnd);
		}else{
			return recordDao.listCategory(category,dateStart,dateEnd);
		}
	}
}