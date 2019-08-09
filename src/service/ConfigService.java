package service;

import dao.ConfigDAO;
import entity.Config;

public class ConfigService {

	public static final String budget = "bugdet";
	public static final String mysqlPath = "mysqlPath";
	public static final String default_budget = "500";
	
	static ConfigDAO dao = new ConfigDAO();
	
	static{//jvm加载类的时候执行
		init();
	}
	
	public static void init(){
		init(budget,default_budget);
		init(mysqlPath,"");
	}
	
	//初始化
	private static void init(String key,String value){
		Config config = dao.getByKey(key);
		if(config == null){
			Config c = new Config();
			c.setKey(key);
			c.setValue(value);
			dao.add(c);
		}
	}
	
	public String get(String key){
		Config config = dao.getByKey(key);
		return config.getValue();
	}
	
	public void update(String key,String value){
		Config config = dao.getByKey(key);
		config.setValue(value);
		dao.update(config);
	}
	
	public double getIntBudget(){
		return Double.parseDouble(get(budget));
	}
}