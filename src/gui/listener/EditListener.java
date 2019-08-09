package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import entity.Category;
import entity.Record;
import gui.dialog.EditDialog;
import gui.panel.DetailPanel;
import service.EditService;
import util.GUIUtil;

public class EditListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		EditDialog d = EditDialog.instance;
		Record record = DetailPanel.instance.getSelectedRecord();
		
		if(!GUIUtil.checkZero(d,d.tfSpend,"花费金额")) {
			return;
		}
		double spend = Double.parseDouble(d.tfSpend.getText());
		Category category = d.getSelectedCategory();
		String comment = d.tfComment.getText();
		Date date = d.datepick.getDate();
		
		record.spend = spend;
		record.cid = category.id;
		record.comment = comment;
		record.date = date;
		
		new EditService().updateRecord(record);
		
		JOptionPane.showMessageDialog(d,"修改成功");
		d.setVisible(false);
	}
}