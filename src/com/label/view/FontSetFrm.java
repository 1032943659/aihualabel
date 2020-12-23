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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FontSetFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public FontSetFrm(JFrame This) {
		setTitle("�����С����");
		setBounds(100, 100, 517, 302);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// ���þ�����ʾ
		this.setLocationRelativeTo(null);
		
		//��������״̬��Ϊ�ɲ���
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				This.setEnabled(true);
			}
		});
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "������", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 491, 220);
		contentPane.add(panel);
		panel.setLayout(null);

		JSlider Item_slider = new JSlider();
		Item_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				LabelPrintFrm.itemfontSize = Item_slider.getValue();
				LabelPrintFrm.item_label.setFont(new Font("Microsoft YaHei", Font.BOLD, LabelPrintFrm.itemfontSize));
			}
		});
		Item_slider.setMinimum(9);
		Item_slider.setMaximum(15);
		Item_slider.setValue(Integer.valueOf(Config.getValues("ItemFontSize")));//�����ļ��е�ֵ
		Item_slider.setMajorTickSpacing(1);// �������̶ȼ��
		Item_slider.setMinorTickSpacing(1);// ���ôο̶ȼ��
		Item_slider.setPaintTicks(true);
		Item_slider.setPaintLabels(true);
		Item_slider.setBounds(81, 22, 390, 39);
		panel.add(Item_slider);
		
		JLabel label_2 = new JLabel("�ͺŹ��:");
		label_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		label_2.setBounds(10, 29, 51, 22);
		panel.add(label_2);
		
		JLabel label_1 = new JLabel("��ע:");
		label_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		label_1.setBounds(10, 82, 51, 22);
		panel.add(label_1);
		
		JSlider other_slider = new JSlider();
		other_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				LabelPrintFrm.otherfontSize = other_slider.getValue();
				LabelPrintFrm.TagboxNum_label.revalidate();
			}
		});
		other_slider.setValue(Integer.valueOf(Config.getValues("otherFontSize")));
		other_slider.setPaintTicks(true);
		other_slider.setPaintLabels(true);
		other_slider.setMinorTickSpacing(1);
		other_slider.setMinimum(9);
		other_slider.setMaximum(15);
		other_slider.setMajorTickSpacing(1);
		other_slider.setBounds(81, 75, 390, 39);
		panel.add(other_slider);
		
		JLabel label = new JLabel("�����Ϻ�:");
		label.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		label.setBounds(10, 144, 51, 22);
		panel.add(label);
		
		JSlider pn_slider = new JSlider();
		pn_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				LabelPrintFrm.pnfontSize = pn_slider.getValue();
				LabelPrintFrm.pn_label.setFont(new Font("Microsoft YaHei", Font.BOLD, LabelPrintFrm.pnfontSize));
			}
		});
		pn_slider.setValue(Integer.valueOf(Config.getValues("PNFontSize")));
		pn_slider.setPaintTicks(true);
		pn_slider.setPaintLabels(true);
		pn_slider.setMinorTickSpacing(1);
		pn_slider.setMinimum(9);
		pn_slider.setMaximum(15);
		pn_slider.setMajorTickSpacing(1);
		pn_slider.setBounds(81, 128, 390, 39);
		panel.add(pn_slider);
		
		
		JButton button = new JButton("��������");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(Config.updateProperty("ItemFontSize",Item_slider.getValue()+"")==true&&Config.updateProperty("otherFontSize",other_slider.getValue()+"")==true&&Config.updateProperty("PNFontSize",pn_slider.getValue()+"")==true){
					 JOptionPane.showMessageDialog(null, "����ɹ�");
				 }else{
					 
				 }
			}
		});
		button.setBounds(408, 240, 93, 23);
		contentPane.add(button);
	}
}
