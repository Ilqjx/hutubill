package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Record;
import gui.listener.ReportListener;
import gui.model.MonthComboBoxModel;
import gui.model.YearComboBoxModel;
import service.ReportService;
import util.ChartUtil;
import util.ColorUtil;
import util.DateUtil;
import util.GUIUtil;

public class ReportPanel extends WorkingPanel{

	static{
		GUIUtil.useLNF();
	}
	
	public static ReportPanel instance = new ReportPanel();
	
	public JLabel l = new JLabel();
	public JLabel lYear = new JLabel("年份");
	public JLabel lMonth = new JLabel("月份");
	public JLabel lText = new JLabel("   ");
	
	public JButton bCheck = new JButton("查询");

	public MonthComboBoxModel mcb = new MonthComboBoxModel();
	public YearComboBoxModel ycb = new YearComboBoxModel();
	
	public JComboBox<Integer> cbMonth = new JComboBox<>(mcb);
	public JComboBox<Integer> cbYear = new JComboBox<>(ycb);
	
	private ReportPanel(){
		GUIUtil.setColor(Color.blue,lYear,lMonth);
		GUIUtil.setColor(ColorUtil.blueColor,bCheck);
		
		this.setLayout(new BorderLayout());
		JPanel pNorth = new JPanel();
		JPanel pCenter = new JPanel();
		
		pNorth.add(lYear);
		pNorth.add(cbYear);
		pNorth.add(lMonth);
		pNorth.add(cbMonth);
		pNorth.add(lText);
		pNorth.add(bCheck);
		
		pCenter.add(l);
		
		this.add(pNorth,BorderLayout.NORTH);
		this.add(pCenter,BorderLayout.CENTER);
		
		addListener();
	}
	
	public void updateData(){
		List<Record> rs = new ReportService().listMonthRecords(DateUtil.monthBegin(),DateUtil.thisMonthTotalDay());
		Image i = ChartUtil.getImage(rs,400,230);
		ImageIcon icon = new ImageIcon(i);
		l.setIcon(icon);
		
		mcb.is =  new ReportService().getMonths();
		ycb.is =  new ReportService().getYears();
		
		cbYear.setSelectedItem(DateUtil.getCurrentYear());
		cbMonth.setSelectedItem(DateUtil.getCurrentMonth());
	}

	public void update() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		int year = getSelectedYear();
		int month = getSelectedMonth();
		int monthDays = new ReportService().getMonthDays(year,month);
		String dateStart = year+"/"+month+"/"+1+" 00:00:00";
		Date monthBegin = sdf.parse(dateStart);
		
		List<Record> rs = new ReportService().listMonthRecords(monthBegin,monthDays);
		Image i = ChartUtil.getImage(rs,400,230);
		ImageIcon icon = new ImageIcon(i);
		l.setIcon(icon);
	}
	
	public int getSelectedYear(){
		return (int) cbYear.getSelectedItem();
	}
	
	public int getSelectedMonth(){
		return (int) cbMonth.getSelectedItem();
	}

	public void addListener() {
		ReportListener listener = new ReportListener();
		bCheck.addActionListener(listener);
	}
}