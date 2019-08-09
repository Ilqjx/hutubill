package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import gui.panel.RecoverPanel;
import service.ConfigService;
import util.MysqlUtil;

public class RecoverListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		RecoverPanel p = RecoverPanel.instance;
		ConfigService cs = new ConfigService();
		String mysqlPath = cs.get(ConfigService.mysqlPath);
		if(mysqlPath.length() == 0) {
			JOptionPane.showMessageDialog(p,"还原前请事先配置mysql的路径");
			MainPanel.instance.workingPanel.show(ConfigPanel.instance);
			ConfigPanel.instance.tfMysqlPath.grabFocus();
			return;
		}
		
		JFileChooser fc = new JFileChooser();
//		fc.setSelectedFile(new File("hutubill.sql"));
		
		fc.setFileFilter(new FileFilter() {
			
			public String getDescription() {
				return ".sql";
			}
			
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".sql");
			}
		});
		
		int returnVal = fc.showOpenDialog(p);
		File file = fc.getSelectedFile();
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			if(!file.getName().toLowerCase().endsWith(".sql")) {
				JOptionPane.showMessageDialog(p,"文件格式错误");
				return;
			}
			try{
				MysqlUtil.recover(mysqlPath,file.getAbsolutePath());
				JOptionPane.showMessageDialog(p,"还原成功");
			}catch(IOException e1){
				e1.printStackTrace();
				JOptionPane.showMessageDialog(p,"还原失败\r\n错误：\r\n"+e1.getMessage());
			}
		}
	}
}