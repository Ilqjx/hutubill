package gui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

public class ConfigPanel extends WorkingPanel{

	static{
		GUIUtil.useLNF();
	}
	
	public static ConfigPanel instance = new ConfigPanel();
	
	public JLabel lBudget = new JLabel("本月预算(￥)");
	public JLabel lMysql = new JLabel("Mysql安装目录");
	
	public JTextField tfBudget = new JTextField("0");
	public JTextField tfMysqlPath = new JTextField("");
	
	public JButton bSubmit = new JButton("更新");
	
	private ConfigPanel(){
		GUIUtil.setColor(ColorUtil.grayColor,lBudget,lMysql);
		GUIUtil.setColor(ColorUtil.blueColor,bSubmit);
		
		JPanel pInput = new JPanel();
		JPanel pSubmit = new JPanel();
		
		int gap =  40;
		pInput.setLayout(new GridLayout(4,1,gap,gap));
		
		pInput.add(lBudget);
		pInput.add(tfBudget);
		pInput.add(lMysql);
		pInput.add(tfMysqlPath);
		
		pSubmit.add(bSubmit);
		
		this.setLayout(new BorderLayout());
		this.add(pInput,BorderLayout.NORTH);
		this.add(pSubmit,BorderLayout.CENTER);
	
		updateData();
		addListener();
	}
	
	public void addListener(){
		ConfigListener cl = new ConfigListener();;
		bSubmit.addActionListener(cl);
	}

	public void updateData(){
		String budget = new ConfigService().get(ConfigService.budget);
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		tfBudget.setText(budget);
		tfMysqlPath.setText(mysqlPath);
		tfBudget.grabFocus();
	}
}