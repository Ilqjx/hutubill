package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import entity.Category;
import entity.Record;
import gui.dialog.AddDialog;
import gui.dialog.EditDialog;
import gui.panel.DetailPanel;
import service.DetailService;

public class DetailListener implements ActionListener{

	public static Date dateStart;
	public static Date dateEnd;
	public static Category category;
	
	public void actionPerformed(ActionEvent e) {
		DetailPanel p = DetailPanel.instance;
		JButton b = (JButton) e.getSource();
		DetailService ds = new DetailService();
		
		if(b == p.bDelete){
			Record record = p.getSelectedRecord();
			if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p,"确定要删除?")){
				return;
			}
			ds.deleteRecord(record.id);
			JOptionPane.showMessageDialog(p,"删除成功");
		}
		
		if(b == p.bAdd){
			AddDialog.instance.resetInput();
			AddDialog.instance.setLocationRelativeTo(p);
			AddDialog.instance.setVisible(true);
		}
		
		if(b == p.bEdit){
			EditDialog.instance.resetInput();
			EditDialog.instance.setLocationRelativeTo(p);
			EditDialog.instance.setVisible(true);
		}
		
		if(b == p.bCheck){
			p.boolCheck = true;
			dateStart = p.datepickStart.getDate();
			dateEnd = p.datepickEnd.getDate();
			category = p.getSelectedCategory();
		}
		
		if(b == p.bAll){
			p.boolCheck = true;
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR,2018);
			category = new Category();
			category.id = p.getSelectedCategory().id;
			dateStart = c.getTime();
			dateEnd = new Date();
		}

		p.update();
	}
}