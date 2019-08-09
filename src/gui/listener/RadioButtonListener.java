package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.panel.CategoryPanel;

public class RadioButtonListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		CategoryPanel p = CategoryPanel.instance;
		p.bAllbool = true;
		p.update();
	}
}
