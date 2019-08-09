package gui.model;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import service.ReportService;
import util.DateUtil;

public class MonthComboBoxModel implements ComboBoxModel<Integer>{
	
	public List<Integer> is = new ReportService().getMonths();
	
	Integer c;
	
	public MonthComboBoxModel(){
		if(is.size() != 0){
			c = is.get(DateUtil.getCurrentMonth()-1);
		}
	}
	
	public int getSize() {
		return is.size();
	}

	public Integer getElementAt(int index) {
		return is.get(index);
	}

	public void addListDataListener(ListDataListener l) {}

	public void removeListDataListener(ListDataListener l) {}

	public void setSelectedItem(Object anItem) {
		c = (Integer) anItem;
	}

	public Object getSelectedItem() {
		return c;
	}

}
