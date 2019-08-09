package gui.panel;

import javax.swing.JButton;

import gui.listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

public class RecoverPanel extends WorkingPanel{

	static{
		GUIUtil.useLNF();
	}
	
	public static RecoverPanel instance = new RecoverPanel();
	
	public JButton bRecover = new JButton("»Ö¸´");
	
	private RecoverPanel(){
		GUIUtil.setColor(ColorUtil.blueColor,bRecover);
		this.add(bRecover);
		
		addListener();
	}

	public void updateData() {}

	public void addListener() {
		RecoverListener listener = new RecoverListener();
		bRecover.addActionListener(listener);
	}
}