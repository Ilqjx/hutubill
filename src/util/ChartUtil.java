package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;

import entity.Record;

public class ChartUtil {

	public static int max(double[] sampleValues){
		int max = 0;
		for(double v : sampleValues){
			if(v > max){
				max = (int)v;
			}
		}
		return max;
	}
	
	private static String[] sampleLabels(List<Record> rs){
		String[] sampleLabels = new String[rs.size()];
		for(int i=0;i<sampleLabels.length;i++){
			if(i%5 == 0){
				sampleLabels[i] = String.valueOf(i+1+"日");
			}
		}
		return sampleLabels;
	}
	
	public static double[] sampleValues(List<Record> rs){
		double[] result = new double[rs.size()];
		for(int i=0;i<result.length;i++){
			result[i] = rs.get(i).spend;
		}
		return result;
	}
	
	public static Image getImage(List<Record> rs,int width,int height){
		double[] sampleValues = sampleValues(rs);
		String[] sampleLabels = sampleLabels(rs);
		int max = max(sampleValues);
		Color[] sampleColors = new Color[]{ColorUtil.blueColor};
		BarChart chart = new BarChart();//柱状图
		chart.setSampleCount(sampleValues.length);
		chart.setSampleValues(0,sampleValues);//?
		chart.setSampleLabels(sampleLabels);
		chart.setSampleColors(sampleColors);
		chart.setRange(0,max*1.2);
		chart.setValueLinesOn(true);
		chart.setSampleLabelsOn(true);
		chart.setSampleLabelStyle(Chart.BELOW);
		chart.setFont("rangeLabelFont",new Font("Arial",Font.BOLD,12));//?
		chart.setLegendOn(true);
		chart.setLegendPosition(Chart.TOP);
		chart.setLegendLabels(new String[]{"月消费报表"});
		chart.setFont("legendFont",new Font("Dialog",Font.BOLD,13));
		chart.setFont("sampleLabelFont",new Font("Dialog",Font.BOLD,13));
		chart.setChartBackground(Color.white);
		chart.setBackground(ColorUtil.backgroundColor);
		Image im = chart.getImage(width,height);
		return im;
	}
}