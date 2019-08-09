package gui.panel;

import javax.swing.JButton;

import gui.listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

public class BackupPanel extends WorkingPanel{

	static{
		GUIUtil.useLNF();
	}
	
	public static BackupPanel instance = new BackupPanel();
	
	public JButton bBackup = new JButton("±¸·Ý");
	
	private BackupPanel(){
		GUIUtil.setColor(ColorUtil.blueColor,bBackup);
		this.add(bBackup);
		
		addListener();
	}

	public void updateData() {}

	public void addListener() {
		BackupListener listener = new BackupListener();
		bBackup.addActionListener(listener);
	}
}