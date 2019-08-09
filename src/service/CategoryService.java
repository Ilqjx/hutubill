package service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class CategoryService {

	CategoryDAO categoryDao = new CategoryDAO();
	RecordDAO recordDao = new RecordDAO();
	
	public List<Category> listAll(){
		List<Category> cs = list();
		Category category = new Category();
		category.name = "È«²¿";
		category.recordNumber = 0;
		category.spendAll = 0;
		for(Category c : cs){
			category.recordNumber += c.recordNumber;
			category.spendAll += c.spendAll;
		}
		cs.add(0,category);
		return cs;
	}
	
	public List<Category> list(){
		List<Category> cs = categoryDao.list();
		for(Category c : cs){
			List<Record> rs = recordDao.list(c.id);
			c.recordNumber = rs.size();
			c.spendAll = 0;
			for(Record record : rs){
				c.spendAll += record.spend;
			}
		}
		Collections.sort(cs,(c1,c2)->c2.recordNumber-c1.recordNumber);//lambdaµ¹ÅÅÐò
		return cs;
	}
	
	public List<Category> list(Date start,Date end){
		List<Category> cs = categoryDao.list();
		for(Category c : cs){
			List<Record> rs = recordDao.listCategory(c,start,end);
			c.recordNumber = rs.size();
			c.spendAll = 0;
			for(Record record : rs){
				c.spendAll += record.spend;
			}
		}
		Collections.sort(cs,(c1,c2)->c2.recordNumber-c1.recordNumber);//lambdaµ¹ÅÅÐò
		return cs;
	}
	
	public void add(String name){
		Category c = new Category();
		c.setName(name);
		categoryDao.add(c);
	}
	
	public void update(int id,String name){
		Category c = new Category();
		c.setId(id);
		c.setName(name);
		categoryDao.update(c);
	}
	
	public void delete(int id){
		categoryDao.delete(id);
	}
}