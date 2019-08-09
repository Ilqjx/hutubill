package gui.model;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import service.ReportService;

public class YearComboBoxModel implements ComboBoxModel<Integer>{

	public List<Integer> is = new ReportService().getYears();
	
	Integer c;
	
	public YearComboBoxModel(){
		if(is.size() != 0){
			c = is.get(0);
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
