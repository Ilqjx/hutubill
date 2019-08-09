package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

public class ConfigListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		ConfigPanel p = ConfigPanel.instance;
		if(!GUIUtil.checkDoubleNumber(p,p.tfBudget,"本月预算")){
			return;
		}
		String mysqlPath = p.tfMysqlPath.getText();
		if(mysqlPath.length() != 0){// =0的时候???
			File commandFile = new File(mysqlPath,"bin/mysql.exe");
			if(!commandFile.exists()){
				JOptionPane.showMessageDialog(p,"mysql路径不正确");
				p.tfMysqlPath.grabFocus();
				return;
			}
		}
		
		ConfigService cs = new ConfigService();
		cs.update(ConfigService.budget,p.tfBudget.getText());
		cs.update(ConfigService.mysqlPath,mysqlPath);
		
		JOptionPane.showMessageDialog(p,"设置修改成功");
		
		p.updateData();
	}
}