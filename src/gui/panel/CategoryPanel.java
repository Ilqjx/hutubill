package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entity.Category;
import gui.listener.CategoryListener;
import gui.listener.RadioButtonListener;
import gui.model.CategoryTableModel;
import gui.model.MonthComboBoxModel;
import gui.model.YearComboBoxModel;
import service.CategoryService;
import service.ReportService;
import util.ColorUtil;
import util.DateUtil;
import util.GUIUtil;

public class CategoryPanel extends WorkingPanel{

	static{
		GUIUtil.useLNF();
	}
	
	public boolean bCheckbool = false;
	public boolean bAllbool = false;
	
	public static CategoryPanel instance = new CategoryPanel();
	
	public CategoryTableModel ctm = new CategoryTableModel();
	
	public JTable t = new JTable(ctm);
	
	public JButton bAdd = new JButton("新增");
	public JButton bEdit = new JButton("编辑");
	public JButton bDelete = new JButton("删除");
	public JButton bCheck = new JButton("查询");
	
	public JLabel lYear = new JLabel("年份");
	public JLabel lMonth = new JLabel("月份");
	public JLabel lText1 = new JLabel("   ");
	public JLabel lText2 = new JLabel("   ");
	
	public MonthComboBoxModel mcb = new MonthComboBoxModel();
	public YearComboBoxModel ycb = new YearComboBoxModel();
	
	public JComboBox<Integer> cbMonth = new JComboBox<>(mcb);
	public JComboBox<Integer> cbYear = new JComboBox<>(ycb);
	
	public JRadioButton bAll = new JRadioButton("全部");
	
	private CategoryPanel(){//实例化时调用构造方法
		GUIUtil.setColor(ColorUtil.blueColor,bAdd,bEdit,bDelete,bCheck,bAll);
		GUIUtil.setColor(Color.blue,lYear,lMonth);

		JScrollPane sp = new JScrollPane(t);
		JPanel pNorth = new JPanel();
		JPanel pSubmit = new JPanel();
		
		pNorth.add(lYear);
		pNorth.add(cbYear);
		pNorth.add(lMonth);
		pNorth.add(cbMonth);
		pNorth.add(lText1);
		pNorth.add(bCheck);
		pNorth.add(lText2);
		pNorth.add(bAll);
		
		pSubmit.add(bAdd);
		pSubmit.add(bEdit);
		pSubmit.add(bDelete);
		
		this.setLayout(new BorderLayout());
		this.add(pNorth,BorderLayout.NORTH);
		this.add(sp,BorderLayout.CENTER);
		this.add(pSubmit,BorderLayout.SOUTH);
		
		addListener();
	}
	
	public Category getSelectedCategory(){
		int index = t.getSelectedRow();
		return ctm.cs.get(index);
	}
	
	public void updateData(){
		ctm.cs = new CategoryService().list(DateUtil.monthBegin(),DateUtil.monthEnd());
		t.updateUI();
		t.getSelectionModel().setSelectionInterval(0,0);
		
		if(ctm.cs.size() == 0){
			bDelete.setEnabled(false);
			bEdit.setEnabled(false);
		}else{
			bDelete.setEnabled(true);
			bEdit.setEnabled(true);
		}
		
		bCheckbool = false;
		bAllbool = false;
		
		bAll.setSelected(false);
		
		mcb.is =  new ReportService().getMonths();
		ycb.is =  new ReportService().getYears();
		
		cbYear.setSelectedItem(DateUtil.getCurrentYear());
		cbMonth.setSelectedItem(DateUtil.getCurrentMonth());
	}
	
	public void addListener(){
		CategoryListener listener = new CategoryListener();
		RadioButtonListener rbListener = new RadioButtonListener();
	
		bAdd.addActionListener(listener);
		bDelete.addActionListener(listener);
		bEdit.addActionListener(listener);
		bCheck.addActionListener(listener);
		
		bAll.addActionListener(rbListener);
	}
	
	public int getSelectedYear(){
		return (int) cbYear.getSelectedItem();
	}
	
	public int getSelectedMonth(){
		return (int) cbMonth.getSelectedItem();
	}
	
	public void update(){
		if(bAllbool){
			ctm.cs = new CategoryService().listAll();
		}else if(bCheckbool){
			ctm.cs = new CategoryService().list(CategoryListener.dateStart,CategoryListener.dateEnd);
		}else{
			ctm.cs = new CategoryService().list(DateUtil.monthBegin(),DateUtil.monthEnd());
		}
		
		t.updateUI();
		t.getSelectionModel().setSelectionInterval(0,0);
		
		if(ctm.cs.size() == 0){
			bDelete.setEnabled(false);
			bEdit.setEnabled(false);
		}else{
			bDelete.setEnabled(true);
			bEdit.setEnabled(true);
		}
	}
	
}