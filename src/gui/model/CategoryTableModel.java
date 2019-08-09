package gui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Category;
import service.CategoryService;
import util.GUIUtil;

public class CategoryTableModel extends AbstractTableModel{

	String[] columnNames = new String[]{"分类名称","消费次数","消费总额"};
	
	public List<Category> cs = new CategoryService().list();
	
	public int getRowCount() {
		return cs.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Category h = cs.get(rowIndex);
		if(columnIndex == 0){
			return h.name;
		}
		if(columnIndex == 1){
			return h.recordNumber;
		}
		if(columnIndex == 2){
			return GUIUtil.double2(h.spendAll);
		}
		return null;
	}
}