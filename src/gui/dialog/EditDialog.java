package gui.dialog;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import entity.Category;
import entity.Record;
import gui.listener.EditListener;
import gui.model.CategoryComboBoxModel;
import gui.panel.DetailPanel;
import service.EditService;
import util.ColorUtil;
import util.GUIUtil;

public class EditDialog extends JDialog{

	static{
		GUIUtil.useLNF();
	}
	
	public static EditDialog instance = new EditDialog();
	
	public JLabel lSpend = new JLabel("花费(￥)");
	public JLabel lCategory = new JLabel("分类");
	public JLabel lComment = new JLabel("备注");
	public JLabel lDate = new JLabel("日期");
	
	public JTextField tfSpend = new JTextField("0");
	public JTextField tfComment = new JTextField();
	
	public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
	
	public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
	
	public JXDatePicker datepick = new JXDatePicker(new Date());
	
	public JButton bSubmit = new JButton("提交");
	
	private EditDialog() {
		GUIUtil.setColor(ColorUtil.grayColor,lSpend,lCategory,lComment,lDate);
		GUIUtil.setColor(ColorUtil.blueColor,bSubmit);
		
		lSpend.setBounds(20,20,60,25);
		lCategory.setBounds(20,65,60,25);
		lComment.setBounds(20,110,60,25);
		lDate.setBounds(20,155,60,25);
		tfSpend.setBounds(165,20,130,25);
		cbCategory.setBounds(165,65,130,25);
		tfComment.setBounds(165,110,130,25);
		datepick.setBounds(165,155,130,25);
		bSubmit.setBounds(135,190,60,25);
		
		this.add(lSpend);
		this.add(tfSpend);
		this.add(lCategory);
		this.add(cbCategory);
		this.add(lComment);
		this.add(tfComment);
		this.add(lDate);
		this.add(datepick);
		this.add(bSubmit);
		
		this.setLayout(null);
		this.setSize(320,260);
		this.setModal(true);
		this.setTitle("主人 请您修改记录^_^");
		this.setResizable(false);
		
		addListener();
	}
	
	public Category getSelectedCategory(){
		return (Category) cbCategory.getSelectedItem();
	}
	
	public void addListener() {
		EditListener listener = new EditListener();
		bSubmit.addActionListener(listener);
	}
	
	public void resetInput() {
		Record record = DetailPanel.instance.getSelectedRecord();
		tfSpend.setText(String.valueOf(record.spend));
		int index = new EditService().getIndex(record);
		cbCategory.setSelectedIndex(index);
		tfComment.setText(record.comment);
		datepick.setDate(record.date);
		tfSpend.grabFocus();//?????
	}
}