package entity;

public class Config {
	//Config表是用于存放配置信息的，key表示关键字，value表示对应的值
	
	public int id;
	public String key;
	public String value;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getKey(){
		return key;
	}
	
	public void setKey(String key){
		this.key = key;
	}
	
	public String getValue(){
		return value;
	}
	
	public void setValue(String value){
		this.value = value;
	}
}