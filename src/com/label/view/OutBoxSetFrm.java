package com.label.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import com.label.util.Config;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 外箱备注显示框，位置及大小调整
 * @author 木易
 *
 */
public class OutBoxSetFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public OutBoxSetFrm() {
		setTitle("模板备注部分调整");
		setBounds(100, 100, 517, 345);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// 设置居中显示
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "外箱", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 491, 263);
		contentPane.add(panel);
		panel.setLayout(null);

		JSlider X_slider = new JSlider();
		X_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				OutPrintViewFrm.outOther_x = X_slider.getValue();
				OutPrintViewFrm.outBoxNum_label.setBounds(OutPrintViewFrm.outOther_x,OutPrintViewFrm.outOther_y,OutPrintViewFrm.outOther_width,OutPrintViewFrm.outOther_height);
			}
		});
		X_slider.setMinimum(200);
		X_slider.setMaximum(400);
		X_slider.setValue(Integer.valueOf(Config.getValues("outOther_x")));//配置文件中的值
		X_slider.setMajorTickSpacing(50);// 设置主刻度间隔
		X_slider.setMinorTickSpacing(1);// 设置次刻度间隔
		X_slider.setPaintTicks(true);
		X_slider.setPaintLabels(true);
		X_slider.setBounds(81, 22, 390, 39);
		panel.add(X_slider);
		
		JLabel label_2 = new JLabel("横坐标:");
		label_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		label_2.setBounds(10, 29, 51, 22);
		panel.add(label_2);
		
		JLabel label_1 = new JLabel("纵坐标:");
		label_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		label_1.setBounds(10, 82, 51, 22);
		panel.add(label_1);
		
		JSlider Y_slider = new JSlider();
		Y_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				OutPrintViewFrm.outOther_y = Y_slider.getValue();
				OutPrintViewFrm.outBoxNum_label.setBounds(OutPrintViewFrm.outOther_x,OutPrintViewFrm.outOther_y,OutPrintViewFrm.outOther_width,OutPrintViewFrm.outOther_height);
			}
		});
		
		Y_slider.setPaintTicks(true);
		Y_slider.setPaintLabels(true);
		Y_slider.setMinorTickSpacing(1);
		Y_slider.setMinimum(150);
		Y_slider.setMaximum(200);
		Y_slider.setValue(Integer.valueOf(Config.getValues("outOther_y")));
		Y_slider.setMajorTickSpacing(10);
		Y_slider.setBounds(81, 75, 390, 39);
		panel.add(Y_slider);
		
		JLabel label = new JLabel("宽:");
		label.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		label.setBounds(10, 142, 51, 22);
		panel.add(label);
		
		JSlider width_slider = new JSlider();
		width_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				OutPrintViewFrm.outOther_width = width_slider.getValue();
				OutPrintViewFrm.outBoxNum_label.setBounds(OutPrintViewFrm.outOther_x,OutPrintViewFrm.outOther_y,OutPrintViewFrm.outOther_width,OutPrintViewFrm.outOther_height);
			}
		});
		
		width_slider.setPaintTicks(true);
		width_slider.setPaintLabels(true);
		width_slider.setMinorTickSpacing(1);
		width_slider.setMinimum(50);
		width_slider.setMaximum(150);
		width_slider.setValue(Integer.valueOf(Config.getValues("outOther_width")));
		width_slider.setMajorTickSpacing(20);
		width_slider.setBounds(81, 142, 390, 39);
		panel.add(width_slider);
		
		JLabel label_3 = new JLabel("高:");
		label_3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		label_3.setBounds(10, 208, 51, 22);
		panel.add(label_3);
		
		JSlider height_slider = new JSlider();
		height_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				OutPrintViewFrm.outOther_height = height_slider.getValue();
				OutPrintViewFrm.outBoxNum_label.setBounds(OutPrintViewFrm.outOther_x,OutPrintViewFrm.outOther_y,OutPrintViewFrm.outOther_width,OutPrintViewFrm.outOther_height);
			}
		});
		
		height_slider.setPaintTicks(true);
		height_slider.setPaintLabels(true);
		height_slider.setMinorTickSpacing(1);
		height_slider.setMinimum(10);
		height_slider.setMaximum(50);
		height_slider.setValue(Integer.valueOf(Config.getValues("outOther_height")));
		height_slider.setMajorTickSpacing(10);
		height_slider.setBounds(81, 208, 390, 39);
		panel.add(height_slider);
		
		JButton button = new JButton("保存设置");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(Config.updateProperty("outOther_x",X_slider.getValue()+"")==true&&Config.updateProperty("outOther_y",Y_slider.getValue()+"")==true&&Config.updateProperty("outOther_width",width_slider.getValue()+"")==true&&Config.updateProperty("outOther_height",height_slider.getValue()+"")==true){
					 JOptionPane.showMessageDialog(null, "保存成功");
				 }else{
					 
				 }
			}
		});
		button.setBounds(408, 283, 93, 23);
		contentPane.add(button);
	}
}
