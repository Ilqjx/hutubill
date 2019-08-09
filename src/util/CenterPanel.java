package util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.panel.WorkingPanel;

public class CenterPanel extends JPanel{
	
	private double rate;//拉伸比例
	private JComponent c;//显示的组件
	private boolean stretch;//是否拉伸
	
	public CenterPanel(double rate,boolean stretch){
		this.setLayout(null);
		this.rate = rate;
		this.stretch = stretch;
	}
	
	public CenterPanel(double rate){
		this(rate,true);
	}
	
	public void show(JComponent p){
		this.c = p;
		Component[] cs = getComponents();//获取Panel所有组件
		for(Component c : cs){
			remove(c);
		}
		
		add(p);
			
		if(p instanceof WorkingPanel){//向上转型 父类引用指向子类对象
			((WorkingPanel) p).updateData();//调用子类方法
		}
		
		this.updateUI();
	}
	
	public void repaint(){
		if(c != null){
			Dimension containerSize = this.getSize();//JPanel大小
			Dimension componentSize = c.getPreferredSize();//默认大小
			if(stretch){
				c.setSize((int)(containerSize.width*rate),(int)(containerSize.height*rate));
			}else{
				c.setSize(componentSize);
			}
			c.setLocation(containerSize.width/2-c.getSize().width/2,containerSize.height/2-c.getSize().height/2);
		}
		super.repaint();//本来要重绘的地方还是要做
	}	
}
