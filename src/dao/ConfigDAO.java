package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Config;
import util.DBUtil;

public class ConfigDAO {

	public int getTotal(){
		int total = 0;
		try(
			Connection c = DBUtil.getConnection();
			Statement s = c.createStatement();
		){
			String sql = "select count(*) from config";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt(1);
			}
			System.out.println("total: "+total);//
		}catch(SQLException e){
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(Config config){
		String sql = "insert into config values(null,?,?)";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setString(1,config.key);
			ps.setString(2,config.value);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();//获取主键
			if(rs.next()){
				config.id = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void update(Config config){
		String sql = "update config set key_ = ?,value = ? where id = ?";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setString(1,config.key);
			ps.setString(2,config.value);
			ps.setInt(3,config.id);
			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void delete(int id){
		String sql = "delete from config where id = ?";
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
	
	public Config get(int id){
		Config config = null;
		String sql = "select * from config where id = ?";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				config = new Config();
				config.id = id;
				config.key = rs.getString("key_");
				config.value = rs.getString("value");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return config;
	}
	
	public List<Config> list(){
		return list(0,Integer.MAX_VALUE);
	}
	
	public List<Config> list(int start,int count){
		List<Config> configs = new ArrayList<Config>();
		String sql = "select * from config order by id desc limit ?,?";//id倒序查询
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setInt(1,start);
			ps.setInt(2,count);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Config config = new Config();
				config.id = rs.getInt(1);
				config.key  =rs.getString("key_");
				config.value = rs.getString("value");
				configs.add(config);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return configs;
	}
	
	public Config getByKey(String key){
		Config config = null;
		String sql = "select * from config where key_ = ?";
		try(
			Connection c = DBUtil.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
		){
			ps.setString(1,key);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				config = new Config();
				config.id = rs.getInt("id");
				config.key = key;
				config.value = rs.getString("value");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return config;
	}
}