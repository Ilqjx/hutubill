package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import gui.panel.ReportPanel;

public class ReportListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		ReportPanel p = ReportPanel.instance;
		
		try{
			p.update();
		}catch(ParseException e1){
			e1.printStackTrace();
		}
	}
}
