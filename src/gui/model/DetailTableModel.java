package gui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Record;
import service.DetailService;
import util.GUIUtil;

public class DetailTableModel extends AbstractTableModel{

	String[] columnNames = new String[]{"分类","金额","日期","备注"};
	
	DetailService ds =  new DetailService();
	
	public List<Record> rs = ds.listToday();

	public int getRowCount() {
		return rs.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Record record = rs.get(rowIndex);
		if(columnIndex == 0) {
			int id = record.cid;
			return ds.getCategoryName(id);
		}
		if(columnIndex == 1) {
			return GUIUtil.double2(record.spend);
		}
		if(columnIndex == 2) {
			return record.date;
		}
		if(columnIndex == 3) {
			return record.comment;
		}
		return null;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
	
	public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}