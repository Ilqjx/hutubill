package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdesktop.swingx.JXDatePicker;

import entity.Category;
import entity.Record;
import gui.listener.DetailListener;
import gui.model.DetailComboBoxModel;
import gui.model.DetailTableModel;
import service.DetailService;
import util.ColorUtil;
import util.GUIUtil;

public class DetailPanel extends WorkingPanel{

	static{
		GUIUtil.useLNF();
	}
	
	public static DetailPanel instance = new DetailPanel();
	
	public JLabel lCategory = new JLabel("分类");
	public JLabel lDate = new JLabel("日期");
	public JLabel lSymbol = new JLabel("—");
	
	public JButton bCheck = new JButton("查询");
	public JButton bAdd = new JButton("新增");
	public JButton bEdit = new JButton("编辑");
	public JButton bDelete = new JButton("删除");
	public JButton bAll = new JButton("全部");
	
	public JXDatePicker datepickStart = new JXDatePicker();
	public JXDatePicker datepickEnd = new JXDatePicker();
	
	public DetailComboBoxModel dbm = new DetailComboBoxModel();
	
	public JComboBox<Category> cbCategory = new JComboBox<>(dbm);
	
	public DetailTableModel dtm = new DetailTableModel();
	
	public JTable t = new JTable(dtm);
	
	public JScrollPane sp = new JScrollPane(t);
	
	public boolean boolCheck = false;
	
	private DetailPanel(){
		GUIUtil.setColor(ColorUtil.blueColor,bAdd,bEdit,bDelete,bCheck,bAll);
		GUIUtil.setColor(Color.BLUE,lCategory,lDate);
		
		JPanel pNorth = new JPanel();
		JPanel pSouth = new JPanel();
		
		cbCategory.setPreferredSize(new Dimension(60,25));
		
		pNorth.add(lCategory);
		pNorth.add(cbCategory);
		pNorth.add(lDate);
		pNorth.add(datepickStart);
		pNorth.add(lSymbol);
		pNorth.add(datepickEnd);
		pNorth.add(bCheck);
		
		pSouth.add(bAdd);
		pSouth.add(bEdit);
		pSouth.add(bDelete);
		pSouth.add(bAll);
		
		this.setLayout(new BorderLayout());
		this.add(pNorth,BorderLayout.NORTH);
		this.add(sp,BorderLayout.CENTER);
		this.add(pSouth,BorderLayout.SOUTH);
		
		addListener();
	}
	
	public void update(){
		if(!boolCheck){
			dtm.rs = new DetailService().listToday();
		}else{
			dtm.rs = new DetailService().list(DetailListener.category,DetailListener.dateStart,DetailListener.dateEnd);
		}
		t.updateUI();
		t.getSelectionModel().setSelectionInterval(0,0);
		
		if(dtm.rs.size() == 0){
			bEdit.setEnabled(false);
			bDelete.setEnabled(false);
		}else{
			bEdit.setEnabled(true);
			bDelete.setEnabled(true);
		}
	}

	public void updateData(){
		datepickStart.setDate(new Date());
		datepickEnd.setDate(new Date());
		
		//comboBox初始化
		dbm.cs = new DetailService().listCategory();
		if(dbm.cs.size() != 0) {
			cbCategory.setSelectedIndex(0);
		}
		cbCategory.updateUI();
		
		//table初始化
		dtm.rs = new DetailService().listToday();
		t.updateUI();
		t.getSelectionModel().setSelectionInterval(0,0);
		
		if(dtm.rs.size() == 0){
			bEdit.setEnabled(false);
			bDelete.setEnabled(false);
		}else{
			bEdit.setEnabled(true);
			bDelete.setEnabled(true);
		}
		
		boolCheck = false;
	}

	public void addListener() {
		DetailListener listener = new DetailListener();
		bAdd.addActionListener(listener);
		bEdit.addActionListener(listener);
		bDelete.addActionListener(listener);
		bCheck.addActionListener(listener);
		bAll.addActionListener(listener);
	}
	
	public Category getSelectedCategory(){//JComboBox
		return (Category) cbCategory.getSelectedItem();
	}
	
	public Record getSelectedRecord() {//JTable
		int index = t.getSelectedRow();
		return dtm.rs.get(index);
	}
}