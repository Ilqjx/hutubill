package entity;

public class Category {

	public int id;
	public String name;
	public int recordNumber;
	public double spendAll;
	
	public double getSpendAll(){
		return spendAll;
	}
	
	public void setSpendAll(double spendAll){
		this.spendAll = spendAll;
	}
	
	public int getRecordNumber(){
		return recordNumber;
	}
	
	public void setRecordNumber(int recordNumber){
		this.recordNumber = recordNumber;
	}
	
	public int getId(){
        return id;
    }
	
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String toString(){
    	return name;
    }
}