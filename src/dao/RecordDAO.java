package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Category;
import entity.Record;
import util.DBUtil;
import util.DateUtil;

public class RecordDAO {

	public int getTotal(){
		int total = 0;
		try(
			Connection c = DBUtil.getConnection();
			Statement s = c.createStatement();
		){
			String sql = "select count(*) from record";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(Record record){
		String sql = "insert into record values(null,?,?,?,?)";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setDouble(1,record.spend);
			ps.setInt(2,record.cid);
			ps.setString(3,record.comment);
			ps.setDate(4,DateUtil.util2sql(record.date));
			
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();//获取主键
			if(rs.next()){
				record.id = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(Record record){
		String sql = "update record set spend = ?,cid = ?,comment = ?,date = ? where id = ?";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setDouble(1,record.spend);
			ps.setInt(2,record.cid);
			ps.setString(3,record.comment);
			ps.setDate(4,DateUtil.util2sql(record.date));
			ps.setInt(5,record.id);
			
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void delete(int id){
		String sql = "delete from record where id = ?";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setInt(1,id);
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteRecord(int cid){
		String sql = "delete from record where cid = ?";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setInt(1,cid);
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Record get(int id){
		Record record = null;
		String sql = "select * from record where id = ?";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				record = new Record();
				record.id = id;
				record.spend = rs.getDouble("spend");
				record.cid = rs.getInt("cid");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return record;
	}
	
	public List<Record> list(){
		return list(0,Integer.MAX_VALUE);
	}
	
	public List<Record> list(int start,int count){
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record order by id desc limit ?,?";//id倒序查询
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setInt(1,start);
			ps.setInt(2,count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.id = rs.getInt("id");
				record.spend = rs.getDouble("spend");
				record.cid = rs.getInt("cid");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return records;
	}
	
	
	//获取某个时间范围内的消费记录信息
	public List<Record> list(Date start,Date end){
		List<Record> records = new ArrayList<>();
		String sql = "select * from record where date >= ? and date <= ?";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setDate(1,DateUtil.util2sql(start));
			ps.setDate(2,DateUtil.util2sql(end));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.id = rs.getInt("id");
				record.cid = rs.getInt("cid");
				record.spend = rs.getDouble("spend");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				records.add(record);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return records;
	}
	
	//获取某段时间范围内按时间倒序排序的消费记录信息
	public List<Record> listDate(Date start,Date end){
		List<Record> records = new ArrayList<>();
		String sql = "select * from record where date >= ? and date <= ? order by date desc";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setDate(1,DateUtil.util2sql(start));
			ps.setDate(2,DateUtil.util2sql(end));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.id = rs.getInt("id");
				record.cid = rs.getInt("cid");
				record.spend = rs.getDouble("spend");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				records.add(record);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Record> listDateAsc(Date start,Date end){
		List<Record> records = new ArrayList<>();
		String sql = "select * from record where date >= ? and date <= ? order by date";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setDate(1,DateUtil.util2sql(start));
			ps.setDate(2,DateUtil.util2sql(end));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.id = rs.getInt("id");
				record.cid = rs.getInt("cid");
				record.spend = rs.getDouble("spend");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				records.add(record);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return records;
	}
	
	public List<Record> listCategory(Category category,Date start,Date end){
		List<Record> records = new ArrayList<>();
		String sql = "select * from record where date >= ? and date <= ? and cid = ? order by date desc";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setDate(1,DateUtil.util2sql(start));
			ps.setDate(2,DateUtil.util2sql(end));
			ps.setInt(3,category.id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.id = rs.getInt("id");
				record.cid = rs.getInt("cid");
				record.spend = rs.getDouble("spend");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				records.add(record);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return records;
	}
	
	//获取本月消费记录信息
	public List<Record> listThisMonth(){
		return list(DateUtil.monthBegin(),DateUtil.monthEnd());
	}
	
	//获取某天的消费记录信息
	public List<Record> list(Date day){
		List<Record> records = new ArrayList<>();
		String sql = "select * from record where date = ?";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setDate(1,DateUtil.util2sql(day));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.id = rs.getInt("id");
				record.cid = rs.getInt("cid");
				record.spend = rs.getDouble("spend");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				records.add(record);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return records;
	}
	
	//获取今天的消费记录信息
	public List<Record> listToday(){
		return list(DateUtil.today());
	}
	
	public List<Record> list(int cid){
		List<Record> records = new ArrayList<>();
		String sql = "select * from record where cid = ?";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setInt(1,cid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.id = rs.getInt("id");
				record.cid = cid;
				record.spend = rs.getDouble("spend");
				record.comment = rs.getString("comment");
				record.date = rs.getDate("date");
				records.add(record);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return records;
	}
}