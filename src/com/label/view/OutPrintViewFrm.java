package com.label.view;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.label.util.*;
import com.label.dao.LabelPrintLogDao;
import com.label.dao.PropertyValueDao;
import com.label.model.PropertyValue;
import com.label.model.LabelPrintLog;
import com.swetake.util.Qrcode;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class OutPrintViewFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField number1_textField;
	private JTextField number2_textField;
	private JTextField netWeigth_textField;
	private JTextField grossWeigth_textField;//354, 178, 80, 28
	public static int outOther_x = Integer.valueOf(Config.getValues("outOther_x"));
	public static int outOther_y = Integer.valueOf(Config.getValues("outOther_y"));
	public static int outOther_width = Integer.valueOf(Config.getValues("outOther_width"));
	public static int outOther_height = Integer.valueOf(Config.getValues("outOther_height"));
	JButton button;
	JLabel lblPo1;
	ImagePannel code_panel;
	JComboBox Unit1_comboBox;
	JComboBox Unit2_comboBox;
	JLabel vendorcode_Label;
	JLabel lblPo;
	JLabel label_lotno;
	JLabel po_label;
	JLabel Item_label;
	JLabel lblItem;
	JLabel PN_label;
	JLabel lblPn;
	JLabel AN_label;
	JLabel lblAn;
	JLabel qty1unit1_label;
	JLabel lblQty;
	JLabel pd_label;
	JLabel lblPd;
	JLabel vendor_label;
	JLabel month_lbl;
	JLabel qty2unit2_label;
	JLabel lblRemark;
	JLabel remark_label;
	JLabel netweigth_label;
	JLabel grossweigth_label;
	Font font = new Font("Microsoft YaHei", Font.BOLD, 14);

	List<String> n_n_Id = new ArrayList<String>();
	List<String> PO = new ArrayList<String>();
	List<String> VendorCode = new ArrayList<String>();
	List<String> Vendor = new ArrayList<String>();
	List<String> Item = new ArrayList<String>();
	List<String> MouthCode = new ArrayList<String>();
	List<String> PN = new ArrayList<String>();
	List<String> Remark = new ArrayList<String>();
	List<String> AN = new ArrayList<String>();
	List<String> LotNo = new ArrayList<String>();
	List<String> Qty1 = new ArrayList<String>();
	List<String> Unit1 = new ArrayList<String>();
	List<String> Qty2 = new ArrayList<String>();
	List<String> Unit2 = new ArrayList<String>();
	List<String> PD = new ArrayList<String>();
	List<String> NetWeigth = new ArrayList<String>();
	List<String> GrossWeigth = new ArrayList<String>();

	private String out_PO;
	private String out_VendorCode;
	private String out_Vendor;
	private String out_Item;
	private String out_MouthCode;
	private String out_PN;
	private String out_remark;
	private String out_AN;
	private String out_LotNo;
	private String out_Qty1;
	private String out_Unit1;
	private String out_Qty2;
	private String out_Unit2;
	private String out_PD;
	private String out_NetWeigth;
	private String out_GrossWeigth;
	private JPanel material_panel;
	private JComboBox material_comboBox;
	private JLabel property4_lbl;
	private JTextField property4_textField;
	private JComboBox property4_comboBox;
	private JLabel property1_lbl;
	private JTextField property1_textField;
	private JComboBox property1_comboBox;
	private JLabel property2_lbl;
	private JTextField property2_textField;
	private JComboBox property2_comboBox;
	private JLabel property3_lbl;
	private JTextField property3_textField;
	private JComboBox property3_comboBox;
	private JLabel property5_lbl;
	private JTextField property5_textField;
	private JComboBox property5_comboBox;
	private JLabel property6_lbl;
	private JTextField property6_textField;
	private JComboBox property6_comboBox;
	private JLabel property7_lbl;
	private JTextField property7_textField;
	private JComboBox property7_comboBox;
	private JLabel property9_lbl;
	private JTextField property9_textField;
	private JComboBox property9_comboBox;
	private JLabel property10_lbl;
	private JTextField property10_textField;
	private JComboBox property10_comboBox;
	private JLabel property11_lbl;
	private JTextField property11_textField;
	private JComboBox property11_comboBox;
	private JLabel property8_lbl;
	private JTextField property8_textField;
	private JComboBox property8_comboBox;
	private JPanel panel_2;
	private JTable table;
	private JButton delSelect;
	private JButton ImportExcel;
	private JButton TruncateTable;
	private JButton saveTotable;
	private JLabel lotno_label;
	private JTextField OutBoxNum_textField;
	private JLabel outBoxNum;
	public static JLabel outBoxNum_label;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;

	/**
	 * Create the frame.
	 * 
	 * @param outlabelPrintLog
	 */
	public OutPrintViewFrm(ArrayList<LabelPrintLog> outlabelPrintLog) {
		setResizable(false);
		setTitle("外箱打印预览");
		setBounds(100, 100, 1002, 541);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu = new JMenu("设置");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("外箱备注设置");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutBoxSetFrm outBoxSetFrm = new OutBoxSetFrm();
				outBoxSetFrm.setVisible(true);
			}
		});
		menu.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 准备数据
		for (LabelPrintLog labelPrintLog : outlabelPrintLog) {
			n_n_Id.add(labelPrintLog.getN_Id() + "");
			PO.add(labelPrintLog.getPO());
			VendorCode.add(labelPrintLog.getVendorCode());
			Vendor.add(labelPrintLog.getVendor());
			Item.add(labelPrintLog.getItem());
			MouthCode.add(labelPrintLog.getProduction_Date().substring(5, 7));
			PN.add(labelPrintLog.getPN());
			Remark.add(labelPrintLog.getRemarks());
			AN.add(labelPrintLog.getAISHINo());
			LotNo.add(labelPrintLog.getLot_No());
			Qty1.add(labelPrintLog.getQty1() + "");
			Unit1.add(labelPrintLog.getUnit1());
			Qty2.add(labelPrintLog.getQty2() + "");
			Unit2.add(labelPrintLog.getUnit2());
			PD.add(labelPrintLog.getProduction_Date());
			NetWeigth.add(labelPrintLog.getNet_Weight());
			GrossWeigth.add(labelPrintLog.getGross_Weight());
		}

		// 对外箱数据去重,数量相加
		List<String> insideBox_PO = new ArrayList<String>();
		List<String> insideBox_VendorCode = new ArrayList<String>();
		List<String> insideBox_Vendor = new ArrayList<String>();
		List<String> insideBox_Item = new ArrayList<String>();
		List<String> insideBox_MonthCode = new ArrayList<String>();
		List<String> insideBox_PN = new ArrayList<String>();
		List<String> insideBox_Remark = new ArrayList<String>();
		List<String> insideBox_AN = new ArrayList<String>();
		List<String> insideBox_LotNo = new ArrayList<String>();
		Float Count_Qty1 = 0f ;
		List<String> insideBox_Unit1 = new ArrayList<String>();
		Float Count_Qty2 =0f;
		List<String> insideBox_Unit2 = new ArrayList<String>();
		List<String> insideBox_PD = new ArrayList<String>();
		Float Count_NetWeigth =0f;
		Float Count_GrossWeigth = 0f ;

		for (String in : PO) {
			if (!insideBox_PO.contains(in)) {
				insideBox_PO.add(in);
			}
		}

		for (String in : VendorCode) {
			if (!insideBox_VendorCode.contains(in)) {
				insideBox_VendorCode.add(in);
			}
		}

		for (String in : Vendor) {
			if (!insideBox_Vendor.contains(in)) {
				insideBox_Vendor.add(in);
			}
		}

		for (String in : Item) {
			if (!insideBox_Item.contains(in)) {
				insideBox_Item.add(in);
			}
		}

		for (String in : MouthCode) {
			if (!insideBox_MonthCode.contains(in)) {
				insideBox_MonthCode.add(in);
			}
		}

		for (String in : PN) {
			if (!insideBox_PN.contains(in)) {
				insideBox_PN.add(in);
			}
		}

		for (String in : Remark) {
			if (!insideBox_Remark.contains(in)) {
				insideBox_Remark.add(in);
			}
		}

		for (String in : AN) {
			if (!insideBox_AN.contains(in)) {
				insideBox_AN.add(in);
			}
		}

		for (String in : LotNo) {
			if (!insideBox_LotNo.contains(in)) {
				insideBox_LotNo.add(in);
			}
		}

		for (String qty : Qty1) {
			if (StringUtil.isEmpty(qty)) {
				qty = "0";
			}
			Count_Qty1 =  Count_Qty1 + Float.parseFloat(qty);
		}

		for (String in : Unit1) {
			if (!insideBox_Unit1.contains(in)) {
				insideBox_Unit1.add(in);
			}
		}

		for (String qty : Qty2) {
			if (qty == null || qty.equals("") || qty.equals("null")) {
				qty = "0";
			}
			Count_Qty2 = Count_Qty2 + Float.parseFloat(qty);
		}

		for (String in : Unit2) {
			if (!insideBox_Unit2.contains(in) && in != null) {
				insideBox_Unit2.add(in);
			}
		}

		for (String in : PD) {
			if (!insideBox_PD.contains(in)) {
				insideBox_PD.add(in);
			}
		}

		for (String netWeigth : NetWeigth) {
			if (StringUtil.isEmpty(netWeigth)) {
				netWeigth = "0";
			}
			Count_NetWeigth = Count_NetWeigth + Float.parseFloat(netWeigth);
		}

		for (String grossWeigth : GrossWeigth) {
			if (StringUtil.isEmpty(grossWeigth)) {
				grossWeigth = "0";
			}
			Count_GrossWeigth = Count_GrossWeigth + Float.parseFloat(grossWeigth);
		}

		out_PO = insideBox_PO.get(0);
		out_VendorCode = insideBox_VendorCode.get(0);
		out_Vendor = insideBox_Vendor.get(0);
		out_Item = insideBox_Item.get(0);
		out_MouthCode = insideBox_MonthCode.get(0);
		out_PN = insideBox_PN.get(0);
		out_remark = insideBox_Remark.get(0);
		out_AN = insideBox_AN.get(0);
		out_LotNo = String.join("*", insideBox_LotNo);
		out_Qty1 = NumberUtil.removeZero(Count_Qty1);
		out_Unit1 = insideBox_Unit1.get(0);
		if (Count_Qty2 == 0) {
			out_Qty2 = "";
		} else {
			out_Qty2 = Count_Qty2 + "";
		}
		if (insideBox_Unit2.size() != 0) {
			out_Unit2 = insideBox_Unit2.get(0);
		} else {
			out_Unit2 = "";
		}
		out_PD = insideBox_PD.get(0);
		if(Count_NetWeigth==0){
			out_NetWeigth="";
		}else{
			out_NetWeigth = Count_NetWeigth + "";
		}
		if(Count_GrossWeigth==0){
			out_GrossWeigth="";
		}else{
			out_GrossWeigth = Count_GrossWeigth + "";
		}
		
		material_panel = new JPanel();
		material_panel.setLayout(null);
		material_panel.setBorder(new TitledBorder(null, "请选择材料", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		material_panel.setBounds(5, 72, 980, 132);
		contentPane.add(material_panel);
		
		property4_lbl = new JLabel("Y");
		property4_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property4_lbl.setBounds(775, 26, 76, 15);
		material_panel.add(property4_lbl);
		
		property4_textField = new JTextField();
		property4_textField.setColumns(10);
		property4_textField.setBounds(850, 26, 46, 21);
		material_panel.add(property4_textField);
		
		property4_comboBox = new JComboBox();
		property4_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm"}));
		property4_comboBox.setBounds(902, 26, 68, 21);
		material_panel.add(property4_comboBox);
		
		property1_lbl = new JLabel("A");
		property1_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property1_lbl.setBounds(140, 26, 76, 15);
		material_panel.add(property1_lbl);
		
		property1_textField = new JTextField();
		property1_textField.setColumns(10);
		property1_textField.setBounds(215, 26, 46, 21);
		material_panel.add(property1_textField);
		
		property1_comboBox = new JComboBox();
		property1_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm", "um", "μF/cm²", "g/cm³", "m"}));
		property1_comboBox.setBounds(267, 26, 68, 21);
		material_panel.add(property1_comboBox);
		
		property2_lbl = new JLabel("E");
		property2_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property2_lbl.setBounds(357, 26, 76, 15);
		material_panel.add(property2_lbl);
		
		property2_textField = new JTextField();
		property2_textField.setColumns(10);
		property2_textField.setBounds(432, 26, 46, 21);
		material_panel.add(property2_textField);
		
		property2_comboBox = new JComboBox();
		property2_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm", "um", "m"}));
		property2_comboBox.setBounds(484, 26, 68, 21);
		material_panel.add(property2_comboBox);
		
		property3_lbl = new JLabel("B");
		property3_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property3_lbl.setBounds(570, 26, 76, 15);
		material_panel.add(property3_lbl);
		
		property3_textField = new JTextField();
		property3_textField.setColumns(10);
		property3_textField.setBounds(645, 26, 46, 21);
		material_panel.add(property3_textField);
		
		property3_comboBox = new JComboBox();
		property3_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm", "m"}));
		property3_comboBox.setBounds(697, 26, 68, 21);
		material_panel.add(property3_comboBox);
		
		property5_lbl = new JLabel("T");
		property5_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property5_lbl.setBounds(140, 64, 76, 15);
		material_panel.add(property5_lbl);
		
		property5_textField = new JTextField();
		property5_textField.setColumns(10);
		property5_textField.setBounds(215, 64, 46, 21);
		material_panel.add(property5_textField);
		
		property5_comboBox = new JComboBox();
		property5_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm", "无碳化", "碳化"}));
		property5_comboBox.setBounds(267, 64, 68, 21);
		material_panel.add(property5_comboBox);
		
		property6_lbl = new JLabel("W");
		property6_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property6_lbl.setBounds(357, 64, 76, 15);
		material_panel.add(property6_lbl);
		
		property6_textField = new JTextField();
		property6_textField.setColumns(10);
		property6_textField.setBounds(432, 64, 46, 21);
		material_panel.add(property6_textField);
		
		property6_comboBox = new JComboBox();
		property6_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm"}));
		property6_comboBox.setBounds(484, 64, 68, 21);
		material_panel.add(property6_comboBox);
		
		property7_lbl = new JLabel("d");
		property7_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property7_lbl.setBounds(570, 64, 76, 15);
		material_panel.add(property7_lbl);
		
		property7_textField = new JTextField();
		property7_textField.setColumns(10);
		property7_textField.setBounds(645, 64, 46, 21);
		material_panel.add(property7_textField);
		
		property7_comboBox = new JComboBox();
		property7_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm"}));
		property7_comboBox.setBounds(697, 64, 68, 21);
		material_panel.add(property7_comboBox);
		
		property9_lbl = new JLabel("L1");
		property9_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property9_lbl.setBounds(140, 102, 76, 15);
		material_panel.add(property9_lbl);
		
		property9_textField = new JTextField();
		property9_textField.setColumns(10);
		property9_textField.setBounds(215, 102, 46, 21);
		material_panel.add(property9_textField);
		
		property9_comboBox = new JComboBox();
		property9_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm"}));
		property9_comboBox.setBounds(267, 102, 68, 21);
		material_panel.add(property9_comboBox);
		
		property10_lbl = new JLabel("L2");
		property10_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property10_lbl.setBounds(357, 102, 76, 15);
		material_panel.add(property10_lbl);
		
		property10_textField = new JTextField();
		property10_textField.setColumns(10);
		property10_textField.setBounds(432, 102, 46, 21);
		material_panel.add(property10_textField);
		
		property10_comboBox = new JComboBox();
		property10_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm"}));
		property10_comboBox.setBounds(484, 102, 68, 21);
		material_panel.add(property10_comboBox);
		
		property11_lbl = new JLabel("L");
		property11_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property11_lbl.setBounds(570, 102, 76, 15);
		material_panel.add(property11_lbl);
		
		property11_textField = new JTextField();
		property11_textField.setColumns(10);
		property11_textField.setBounds(645, 102, 46, 21);
		material_panel.add(property11_textField);
		
		property11_comboBox = new JComboBox();
		property11_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm"}));
		property11_comboBox.setBounds(697, 102, 68, 21);
		material_panel.add(property11_comboBox);
		
		property8_lbl = new JLabel("D");
		property8_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property8_lbl.setBounds(775, 64, 76, 15);
		material_panel.add(property8_lbl);
		
		property8_textField = new JTextField();
		property8_textField.setColumns(10);
		property8_textField.setBounds(850, 64, 46, 21);
		material_panel.add(property8_textField);
		
		property8_comboBox = new JComboBox();
		property8_comboBox.setModel(new DefaultComboBoxModel(new String[] {"mm"}));
		property8_comboBox.setBounds(902, 64, 68, 21);
		material_panel.add(property8_comboBox);
		
		property1_lbl.setVisible(false);
		property1_textField.setVisible(false);
		property1_comboBox.setVisible(false);
		
		property2_lbl.setVisible(false);
		property2_textField.setVisible(false);
		property2_comboBox.setVisible(false);
		
		property3_lbl.setVisible(false);
		property3_textField.setVisible(false);
		property3_comboBox.setVisible(false);
		
		property4_lbl.setVisible(false);
		property4_textField.setVisible(false);
		property4_comboBox.setVisible(false);
		
		property5_lbl.setVisible(false);
		property5_textField.setVisible(false);
		property5_comboBox.setVisible(false);
		
		property6_lbl.setVisible(false);
		property6_textField.setVisible(false);
		property6_comboBox.setVisible(false);
		
		property7_lbl.setVisible(false);
		property7_textField.setVisible(false);
		property7_comboBox.setVisible(false);
		
		property8_lbl.setVisible(false);
		property8_textField.setVisible(false);
		property8_comboBox.setVisible(false);
		
		property9_lbl.setVisible(false);
		property9_textField.setVisible(false);
		property9_comboBox.setVisible(false);
		
		property10_lbl.setVisible(false);
		property10_textField.setVisible(false);
		property10_comboBox.setVisible(false);
		
		property11_lbl.setVisible(false);
		property11_textField.setVisible(false);
		property11_comboBox.setVisible(false);
		
		material_comboBox = new JComboBox();
		material_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectProperty(e);
				flashOutBox(e);//更新二维码
			}
		});
		material_comboBox.setModel(new DefaultComboBoxModel(new String[] {"请选择材料", "正箔", "负箔", "电解纸", "正导针", "负导针", "胶带", "基座", "热封上带", "载带"}));
		material_comboBox.setBounds(10, 26, 108, 21);
		material_panel.add(material_comboBox);
		
		JLabel netWeigth_label = new JLabel("净重：");
		netWeigth_label.setBounds(435, 31, 49, 15);
		netWeigth_textField = new JTextField();
		netWeigth_textField.setBounds(468, 28, 94, 21);
		netWeigth_textField.setColumns(10);
		Document net_weight = netWeigth_textField.getDocument();
		net_weight.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(netWeigth_textField.getText())) {
					netweigth_label.setText("");
				} else {
					netweigth_label.setText("净重" + netWeigth_textField.getText()+"KG");
				}
				flashOutBox(e);
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(netWeigth_textField.getText())) {
					netweigth_label.setText("");
				} else {
					netweigth_label.setText("净重" + netWeigth_textField.getText()+"KG");
				}
				flashOutBox(e);
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(netWeigth_textField.getText())) {
					netweigth_label.setText("");
				} else {
					netweigth_label.setText("净重" + netWeigth_textField.getText()+"KG");
				}
				flashOutBox(e);
			}
		});

		netWeigth_textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String s = netWeigth_textField.getText();
				int keyChar = e.getKeyChar();
				if (s.length() >= 10)
					e.consume();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9
						|| keyChar == KeyEvent.getExtendedKeyCodeForChar('.')) {

				} else {
					e.consume();
				}
			}
		});

		JLabel grossWeigth_label = new JLabel("毛重：");
		grossWeigth_label.setBounds(584, 29, 52, 19);
		grossWeigth_textField = new JTextField();
		grossWeigth_textField.setBounds(619, 28, 100, 21);
		grossWeigth_textField.setColumns(10);
		Document gross_weight = grossWeigth_textField.getDocument();
		gross_weight.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(grossWeigth_textField.getText())) {
					grossweigth_label.setText("");
				} else {
					grossweigth_label.setText("毛重" + grossWeigth_textField.getText()+"KG");
				}
				flashOutBox(e);
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(grossWeigth_textField.getText())) {
					grossweigth_label.setText("");
				} else {
					grossweigth_label.setText("毛重" + grossWeigth_textField.getText()+"KG");
				}
				flashOutBox(e);
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(grossWeigth_textField.getText())) {
					grossweigth_label.setText("");
				} else {
					grossweigth_label.setText("毛重" + grossWeigth_textField.getText()+"KG");
				}
				flashOutBox(e);
			}
		});

		grossWeigth_textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String s = grossWeigth_textField.getText();
				int keyChar = e.getKeyChar();
				if (s.length() >= 10)
					e.consume();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9
						|| keyChar == KeyEvent.getExtendedKeyCodeForChar('.')) {

				} else {
					e.consume();
				}
			}
		});

		JLabel number1_label = new JLabel("数量1：");
		number1_label.setBounds(10, 29, 52, 19);
		number1_textField = new JTextField();
		number1_textField.setBounds(59, 28, 94, 21);
		number1_textField.setColumns(10);
		Document number1 = number1_textField.getDocument();
		number1.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number1_textField.getText())) {
					number2_textField.setText("");
					qty1unit1_label.setText("");
					qty2unit2_label.setText("");
				} else {
					qty1unit1_label.setText(number1_textField.getText() + Unit1_comboBox.getSelectedItem());
				}
				flashOutBox(e);
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number1_textField.getText())) {
					number2_textField.setText("");
					qty1unit1_label.setText("");
					qty2unit2_label.setText("");
				} else {
					qty1unit1_label.setText(number1_textField.getText() + Unit1_comboBox.getSelectedItem());
				}
				flashOutBox(e);
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number1_textField.getText())) {
					number2_textField.setText("");
					qty1unit1_label.setText("");
					qty2unit2_label.setText("");
				} else {
					qty1unit1_label.setText(number1_textField.getText() + Unit1_comboBox.getSelectedItem());
				}
				flashOutBox(e);
			}
		});
		number1_textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				String s = number1_textField.getText();
				if (s.length() >= 10)
					e.consume();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9
						|| keyChar == KeyEvent.getExtendedKeyCodeForChar('.')) {

				} else {
					e.consume();
				}
			}
		});

		Unit1_comboBox = new JComboBox();
		Unit1_comboBox.setBounds(154, 28, 46, 21);
		Unit1_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isNotEmpty(number1_textField.getText())) {
					qty1unit1_label.setText(number1_textField.getText() + Unit1_comboBox.getSelectedItem());
				}
			}
		});
		Unit1_comboBox.setModel(new DefaultComboBoxModel(new String[] { "pc", "kpc", "kg", "m", "m2" }));

		JLabel number2_label = new JLabel("数量2:");
		number2_label.setBounds(225, 29, 52, 19);
		number2_textField = new JTextField();
		number2_textField.setBounds(265, 28, 100, 21);
		number2_textField.setColumns(10);
		number2_textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				String s = number2_textField.getText();
				if (s.length() >= 10)
					e.consume();
				if (StringUtil.isEmpty(number1_textField.getText())) {
					e.consume(); // 关键，屏蔽掉非法输入
				} else if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9
						|| keyChar == KeyEvent.getExtendedKeyCodeForChar('.')) {

				} else {
					e.consume();
				}

			}
		});
		Document number2 = number2_textField.getDocument();
		number2.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number2_textField.getText())) {
					qty2unit2_label.setText("");
				} else {
					qty2unit2_label.setText(number2_textField.getText() + Unit2_comboBox.getSelectedItem());
				}
				flashOutBox(e);
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number2_textField.getText())) {
					qty2unit2_label.setText("");
				} else {
					qty2unit2_label.setText(number2_textField.getText() + Unit2_comboBox.getSelectedItem());
				}
				flashOutBox(e);
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number2_textField.getText())) {
					qty2unit2_label.setText("");
				} else {
					qty2unit2_label.setText(number2_textField.getText() + Unit2_comboBox.getSelectedItem());
				}
				flashOutBox(e);
			}
		});

		Unit2_comboBox = new JComboBox();
		Unit2_comboBox.setBounds(366, 28, 46, 21);
		Unit2_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isNotEmpty(number1_textField.getText())
						&& StringUtil.isNotEmpty(number2_textField.getText())) {
					qty2unit2_label.setText(number2_textField.getText() + Unit2_comboBox.getSelectedItem());
				}
			}
		});
		Unit2_comboBox.setModel(new DefaultComboBoxModel(new String[] { "m2" }));

		JPanel outBox_panel = new JPanel();
		outBox_panel.setBounds(524, 214, 450, 210);
		outBox_panel.setBackground(Color.WHITE);

		JButton btnNewButton = new JButton("打印外箱");
		btnNewButton.setBounds(345, 453, 325, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputActionformed(e)) {
					if (ComponenetPrintUtil.printComponenet(outBox_panel,1)) {
						LabelPrintLogDao labelPrintLogDao = new LabelPrintLogDao();
						LabelPrintLog labelPrintLog = new LabelPrintLog();
						labelPrintLog.setPO(out_PO);
						labelPrintLog.setVendorCode(out_VendorCode);
						if (insideBox_AN.size() == 2 && insideBox_LotNo.size() == 2) {
							labelPrintLog.setAISHINo(insideBox_AN.get(0) + '/' + insideBox_AN.get(1));
							labelPrintLog.setLot_No(out_LotNo);
						} else {
							labelPrintLog.setAISHINo(insideBox_AN.get(0));
							labelPrintLog.setLot_No(out_LotNo);
						}
						labelPrintLog.setPN(out_PN);
						labelPrintLog.setVendor(out_Vendor);
						labelPrintLog.setItem(out_Item);
						labelPrintLog.setQty1(number1_textField.getText());
						labelPrintLog.setQty2(number2_textField.getText());
						if (StringUtil.isNotEmpty(number2_textField.getText())) {
							labelPrintLog.setUnit2((String) Unit2_comboBox.getSelectedItem());
						}
						labelPrintLog.setUnit1((String) Unit1_comboBox.getSelectedItem());
						labelPrintLog.setNet_Weight(netWeigth_textField.getText());
						labelPrintLog.setGross_Weight(grossWeigth_textField.getText());
						labelPrintLog.setProduction_Date(out_PD);
						labelPrintLog.setRemarks(out_remark);
						labelPrintLog.setBoxType(1);
						labelPrintLog.setN_n_Id(String.join("/", n_n_Id));
						labelPrintLog.setSerialNumber(OutBoxNum_textField.getText());
						if(!"请选择材料".equals(material_comboBox.getSelectedItem().toString())){
							labelPrintLog.setMaterialName(material_comboBox.getSelectedItem().toString());
						}
						labelPrintLog.setMaterial_Property(GetMaterialPropertyToTable().toString());
						if (LabelPrintFrm.printLogSelFrm != null
								&& PrintLogSelFrm.BoxType_comboBox.getSelectedItem() == "内箱") {// 由内箱打印记录生成外箱
							if (labelPrintLogDao.insertPrintLog(labelPrintLog) == 1) {
								dispose();
								try {
									String[] id = String.join("/", n_n_Id).split("/");
									for (int i = 0; i < id.length; i++) {
									}
									LabelPrintLog labelPrintLog1 = new LabelPrintLog();
									labelPrintLog1.setBoxType(0);
									PrintLogSelFrm.fillTable(labelPrintLog1);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						} else {

						}
					}
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 980, 68);
		panel_1.setBorder(new TitledBorder(null, "编辑外箱数量", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		lblPo1 = new JLabel("");
		lblPo1.setBounds(10, 11, 37, 14);
		lblPo1.setFont(font);

		vendorcode_Label = new JLabel(out_VendorCode);
		vendorcode_Label.setBounds(57, 11, 130, 14);
		vendorcode_Label.setFont(font);

		label_lotno = new JLabel("Lot No:");
		label_lotno.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		label_lotno.setBounds(10, 34, 52, 14);
		outBox_panel.add(label_lotno);
		contentPane.add(panel_1);
		
		lblPo = new JLabel("PO:");
		lblPo.setBounds(10, 60, 38, 14);
		lblPo.setFont(font);

		po_label = new JLabel(out_PO);
		po_label.setBounds(57, 60, 200, 14);
		po_label.setFont(font);

		Item_label = new JLabel(out_Item);
		Item_label.setBounds(57, 86, 240, 14);
		Item_label.setFont(new Font("Microsoft YaHei", Font.BOLD, LabelPrintFrm.itemfontSize));

		lblItem = new JLabel("Item:");
		lblItem.setBounds(10, 86, 38, 14);
		lblItem.setFont(font);

		PN_label = new JLabel(out_PN);
		PN_label.setBounds(57, 111, 240, 14);
		PN_label.setFont(new Font("Microsoft YaHei", Font.BOLD, LabelPrintFrm.pnfontSize));
		if(StringUtil.isNotEmpty(out_remark)){
			PN_label.setText(out_PN+'/'+out_remark);
		}

		lblPn = new JLabel("P/N:");
		lblPn.setBounds(10, 111, 38, 14);
		lblPn.setFont(font);

		AN_label = new JLabel(out_AN);
		AN_label.setBounds(57, 136, 120, 14);
		AN_label.setFont(font);

		lblAn = new JLabel("A/N:");
		lblAn.setBounds(10, 136, 38, 14);
		lblAn.setFont(font);

		qty1unit1_label = new JLabel(out_Qty1 + out_Unit1);
		qty1unit1_label.setBounds(57, 161, 85, 17);
		qty1unit1_label.setFont(font);

		lblQty = new JLabel("Qty:");
		lblQty.setBounds(10, 161, 38, 14);
		lblQty.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));

		pd_label = new JLabel(out_PD);
		pd_label.setBounds(57, 186, 85, 14);
		pd_label.setFont(font);

		lblPd = new JLabel("PD:");
		lblPd.setBounds(10, 186, 38, 14);
		lblPd.setFont(font);
		
		JLabel lblRohs = new JLabel("RoHS2.0");
		lblRohs.setFont(new Font("Microsoft YaHei", Font.BOLD, 28));
		lblRohs.setBounds(307, 155, 120, 41);
		
		
		outBox_panel.setLayout(null);
		outBox_panel.add(lblRohs);
		outBox_panel.add(lblPo1);
		outBox_panel.add(vendorcode_Label);
		outBox_panel.add(lblPd);
		outBox_panel.add(pd_label);
		outBox_panel.add(lblQty);
		outBox_panel.add(qty1unit1_label);
		outBox_panel.add(lblAn);
		outBox_panel.add(AN_label);
		outBox_panel.add(lblPn);
		outBox_panel.add(PN_label);
		outBox_panel.add(lblItem);
		outBox_panel.add(Item_label);
		outBox_panel.add(lblPo);
		outBox_panel.add(po_label);

		vendor_label = new JLabel(out_Vendor);
		vendor_label.setFont(font);
		vendor_label.setBounds(194, 11, 130, 14);
		outBox_panel.add(vendor_label);

		month_lbl = new JLabel(out_MouthCode + "月");
		month_lbl.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
		month_lbl.setBounds(343, 10, 97, 16);
		outBox_panel.add(month_lbl);

		if (StringUtil.isEmpty(out_Qty2)) {
			qty2unit2_label = new JLabel("");
			qty2unit2_label.setFont(font);
			qty2unit2_label.setBounds(139, 161, 85, 17);
			outBox_panel.add(qty2unit2_label);
		} else {
			qty2unit2_label = new JLabel(out_Qty2 + out_Unit2);
			qty2unit2_label.setFont(font);
			qty2unit2_label.setBounds(139, 161, 85, 17);
			outBox_panel.add(qty2unit2_label);
		}
		
		netweigth_label = new JLabel("净重" + out_NetWeigth +"KG");
		netweigth_label.setFont(font);
		netweigth_label.setBounds(152, 186, 98, 14);
		outBox_panel.add(netweigth_label);
		if ("".equals(out_NetWeigth)) {
			netweigth_label.setText("");
		}

		grossweigth_label = new JLabel("毛重" + out_GrossWeigth+"KG");
		grossweigth_label.setFont(font);
		grossweigth_label.setBounds(252, 186, 98, 14);
		outBox_panel.add(grossweigth_label);
		if ("".equals(out_GrossWeigth)) {
			grossweigth_label.setText("");
		} 

		code_panel = new ImagePannel();
		code_panel.setBounds(307, 49, 115, 115);
		outBox_panel.add(code_panel);
		BufferedImage inside_bi = new BufferedImage(115, 115, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 115; i++)
			for (int j = 0; j < 115; j++)
				inside_bi.setRGB(j, i, Color.WHITE.getRGB());

		Graphics2D inside_gs = inside_bi.createGraphics();
		inside_gs.setColor(Color.BLACK);

		Qrcode inside_qrcode = new Qrcode();
		inside_qrcode.setQrcodeEncodeMode('B');// 存储英文
		inside_qrcode.setQrcodeVersion(10);

		// 二维码生成规则
		String inside_code = out_PO + '|' + out_VendorCode + '|' + out_PN + '|' +
		 out_LotNo+
				'|' + out_remark + '|' + out_AN + '|' + out_PD + '|' + out_Qty1 + '|' + out_Qty2 + '|' + out_NetWeigth
				+ '|' + out_GrossWeigth;

		try {
			boolean[][] inside_rest = inside_qrcode.calQrcode(inside_code.getBytes("UTF-8"));
			for (int i = 0; i < inside_rest.length; i++) {
				for (int j = 0; j < inside_rest.length; j++) {
					if (inside_rest[j][i])
						inside_gs.fillRect(j * 2, i * 2, 2, 2);
				}
			}

			code_panel.setBufferedImage(inside_bi);
			code_panel.repaint();
		} catch (HeadlessException | UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		button = new JButton("清空");
		button.setBounds(883, 26, 81, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetInput(e);
			}
		});

		// 将数据加入到输入框
		if(out_Qty1=="0"){
			number1_textField.setText("");
		}else{
			number1_textField.setText(out_Qty1 + "");
		}
		if(Count_Qty2==0){
			number2_textField.setText("");
		}else{
			number2_textField.setText(NumberUtil.removeZero(Count_Qty2));
		}
		
		if(Count_NetWeigth==0){
			netWeigth_textField.setText("");
		}else{
			netWeigth_textField.setText(NumberUtil.removeZero(Count_NetWeigth));
		}
		if(Count_GrossWeigth==0){
			grossWeigth_textField.setText("");
		}else{
			grossWeigth_textField.setText(NumberUtil.removeZero(Count_GrossWeigth));
		}
		
		Unit1_comboBox.setSelectedItem(insideBox_Unit1.get(0));
		Unit2_comboBox.setSelectedItem(out_Unit2);
		panel_1.setLayout(null);
		panel_1.add(number1_label);
		panel_1.add(number1_textField);
		panel_1.add(Unit1_comboBox);
		panel_1.add(number2_label);
		panel_1.add(number2_textField);
		panel_1.add(Unit2_comboBox);
		panel_1.add(netWeigth_label);
		panel_1.add(netWeigth_textField);
		panel_1.add(grossWeigth_label);
		panel_1.add(grossWeigth_textField);
		panel_1.add(button);
		
		OutBoxNum_textField = new JTextField();
		OutBoxNum_textField.setColumns(10);
		OutBoxNum_textField.setBounds(773, 29, 100, 21);
		panel_1.add(OutBoxNum_textField);
		Document OutBoxNum = OutBoxNum_textField.getDocument();
		OutBoxNum.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(OutBoxNum_textField.getText())) {
					outBoxNum_label.setText("");
				} else {
//					outBoxNum_label.setText("箱号"+OutBoxNum_textField.getText());
					try {
						JLabelUtil.JlabelSetText(outBoxNum_label, OutBoxNum_textField.getText());
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(OutBoxNum_textField.getText())) {
					outBoxNum_label.setText("");
				} else {
//					outBoxNum_label.setText("箱号"+OutBoxNum_textField.getText());
					try {
						JLabelUtil.JlabelSetText(outBoxNum_label, OutBoxNum_textField.getText());
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(OutBoxNum_textField.getText())) {
					outBoxNum_label.setText("");
				} else {
//					outBoxNum_label.setText("箱号"+OutBoxNum_textField.getText());
					try {
						JLabelUtil.JlabelSetText(outBoxNum_label, OutBoxNum_textField.getText());
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		outBoxNum = new JLabel("备注：");
		outBoxNum.setBounds(738, 30, 52, 19);
		panel_1.add(outBoxNum);
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		contentPane.add(outBox_panel);
		
		lotno_label = new JLabel(out_LotNo.replace('*', '/'));
		if(out_LotNo.replace('*', '/').split("/").length>1){
			lotno_label.setFont(new Font("Microsoft YaHei", Font.BOLD, 10));
		}else{
			lotno_label.setFont(font);
		}
		lotno_label.setBounds(57, 34, 383, 14);
		outBox_panel.add(lotno_label);
		
		outBoxNum_label = new JLabel("");
		outBoxNum_label.setFont(new Font("Microsoft YaHei", Font.BOLD, 10));
//		outBoxNum_label.setBounds(320, 186, 120, 20);//354, 178, 80, 28
		outBoxNum_label.setBounds(outOther_x, outOther_y, outOther_width, outOther_height);//354, 178, 80, 28
		outBox_panel.add(outBoxNum_label);
		
		JLabel label = new JLabel("外");
		label.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
		label.setBounds(275, 49, 24, 31);
		outBox_panel.add(label);
		
		Document property1 = property1_textField.getDocument();
		property1.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
            	flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
            	flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
            	flashOutBox(e);
            }
        });
		
		Document property2 = property2_textField.getDocument();
		property2.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
        });
		
		Document property3 = property3_textField.getDocument();
		property3.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
        });
		
		Document property4 = property4_textField.getDocument();
		property4.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
        });
		
		Document property5 = property5_textField.getDocument();
		property5.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
        });
		
		Document property6 = property6_textField.getDocument();
		property6.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
        });
		
		
		Document property7 = property7_textField.getDocument();
		property7.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
        });
		
		Document property8 = property8_textField.getDocument();
		property8.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
        });
		
		Document property9 = property9_textField.getDocument();
		property9.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
        });
		
		Document property10 = property10_textField.getDocument();
		property10.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
        });
		
		Document property11 = property11_textField.getDocument();
		property11.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void removeUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
            public void changedUpdate(DocumentEvent e) {
				flashOutBox(e);
            }
        });
		property1_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashOutBox(e);
			}
		});
		
		property2_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashOutBox(e);
			}
		});
		
		property3_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashOutBox(e);
			}
		});
		
		property4_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashOutBox(e);
			}
		});
		
		property5_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(material_comboBox.getSelectedItem()=="电解纸"){
					property5_textField.setText((String) property5_comboBox.getSelectedItem());
				}
				flashOutBox(e);
			}
		});
		property6_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashOutBox(e);
			}
		});
		property7_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashOutBox(e);
			}
		});
		property8_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashOutBox(e);
			}
		});
		property9_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashOutBox(e);
			}
		});
		property10_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashOutBox(e);
			}
		});
		property11_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashOutBox(e);
			}
		});
		
		
		saveTotable = new JButton("保存到模板");
		saveTotable.setVisible(false);
		saveTotable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MaterialPropertyInputCheck()){
					SaveToModel(e);//保存标准值到标准值模板
				}else{
					JOptionPane.showMessageDialog(null, "确保所有属性输入不为空！");
				}
				try {
					fillTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		saveTotable.setBounds(850, 98, 120, 23);
		material_panel.add(saveTotable);
		if(PrintLogSelFrm.BoxType_comboBox.getSelectedItem()=="外箱"){
			Material_Property_loadTo_textFiled();//加载补打数据到输入框
		}
		
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "选择标准值模板", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(5, 209, 495, 231);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 17, 483, 169);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5E8F\u53F7", "\u6750\u6599\u540D", "\u5B57\u6BB5\u4E00", "\u5355\u4F4D1", "\u5B57\u6BB5\u4E8C", "\u5355\u4F4D2", "\u5B57\u6BB5\u4E09", "\u5355\u4F4D3", "\u5B57\u6BB5\u56DB", "\u5355\u4F4D4", "\u5B57\u6BB5\u4E94", "\u5355\u4F4D5", "\u5B57\u6BB5\u516D", "\u5355\u4F4D6", "\u5B57\u6BB5\u4E03", "\u5355\u4F4D7", "\u5B57\u6BB5\u516B", "\u5355\u4F4D8", "\u5B57\u6BB5\u4E5D", "\u5355\u4F4D9", "\u5B57\u6BB5\u5341", "\u5355\u4F4D10", "\u5B57\u6BB5\u5341\u4E00", "\u5355\u4F4D11"
			}
		){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//单选
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(43);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setPreferredWidth(50);
		table.getColumnModel().getColumn(9).setPreferredWidth(50);
		table.getColumnModel().getColumn(10).setPreferredWidth(50);
		table.getColumnModel().getColumn(11).setPreferredWidth(50);
		table.getColumnModel().getColumn(12).setPreferredWidth(43);
		table.getColumnModel().getColumn(13).setPreferredWidth(50);
		table.getColumnModel().getColumn(14).setPreferredWidth(50);
		table.getColumnModel().getColumn(15).setPreferredWidth(50);
		table.getColumnModel().getColumn(16).setPreferredWidth(50);
		table.getColumnModel().getColumn(17).setPreferredWidth(50);
		table.getColumnModel().getColumn(18).setPreferredWidth(50);
		table.getColumnModel().getColumn(19).setPreferredWidth(50);
		table.getColumnModel().getColumn(20).setPreferredWidth(50);
		table.getColumnModel().getColumn(21).setPreferredWidth(50);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				int Row = table.getSelectedRow();
				if(Row != -1)
				{
					Material_Property_load(Row);
				}
			}
		});
		scrollPane.setViewportView(table);
		panel_2.add(scrollPane);
		
		delSelect = new JButton("删除选定记录");
		delSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1){
					int id = Integer.valueOf(table.getValueAt(row, 0)+"");
					deleteTableRow(e,id);
				}
				try {
					fillTable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		delSelect.setBounds(368, 196, 117, 23);
		panel_2.add(delSelect);
		
		ImportExcel = new JButton("Excel导入");
		ImportExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MaterialPropertyExcelImport();
				try {
					fillTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ImportExcel.setBounds(241, 196, 117, 23);
		panel_2.add(ImportExcel);
		
		TruncateTable = new JButton("清空列表");
		TruncateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否清空?");
				if(result == 0){
					truncateTable(e);
				}
				try {
					fillTable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		TruncateTable.setBounds(106, 196, 117, 23);
		panel_2.add(TruncateTable);

		/**
		 * 补打
		 */
		if (LabelPrintFrm.printLogSelFrm != null && PrintLogSelFrm.PrintLog_table.getSelectedRow() != -1
				&& PrintLogSelFrm.BoxType_comboBox.getSelectedItem() == "外箱") {
			btnNewButton.setText("补打外箱");
		}

		// 设置居中显示
		this.setLocationRelativeTo(null);
		try {
			this.fillTable();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * 加载补打数据到输入框()
	 */
	private void Material_Property_loadTo_textFiled() {
		// TODO Auto-generated method stub
		int Row =PrintLogSelFrm.PrintLog_table.getSelectedRow();
		OutBoxNum_textField.setText((String) PrintLogSelFrm.PrintLog_table.getValueAt(Row, 21));
		if(StringUtil.isEmpty((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19))||"".equals((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,20))){
			material_comboBox.setSelectedItem("请选择材料");
		}else{
			material_comboBox.setSelectedItem((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19));
		}
		if("正箔".equals(((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19)))&&!"".equals(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString())){//只能用equqls
			property1_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[1]);
			property2_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[3]);
			property3_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[5]);
			property4_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[7]);
		}else if("负箔".equals(((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19)))&&!"".equals(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString())){
			property1_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[1]);
			property2_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[3]);
			property3_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[5]);
			property4_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[7]);
		}else if("电解纸".equals(((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19)))&&!"".equals(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString())){
			property1_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[1]);
			property2_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[3]);
			property3_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[5]);
			property4_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[7]);
			property5_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[8]);
			property5_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[8]);
		}else if("正导针".equals(((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19)))&&!"".equals(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString())){
			property1_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[1]);
			property2_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[3]);
			property3_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[5]);
			property4_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[7]);
			property5_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[8]);
			property5_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[9]);
			property6_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[10]);
			property6_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[11]);
			property7_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[12]);
			property7_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[13]);
			property8_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[14]);
			property8_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[15]);
			property9_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[16]);
			property9_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[17]);
			property10_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[18]);
			property10_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[19]);
			property11_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[20]);
			property11_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[21]);
		}else if("负导针".equals(((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19)))&&!"".equals(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString())){
			property1_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[1]);
			property2_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[3]);
			property3_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[5]);
			property4_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[7]);
			property5_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[8]);
			property5_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[9]);
			property6_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[10]);
			property6_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[11]);
			property7_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[12]);
			property7_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[13]);
			property8_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[14]);
			property8_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[15]);
			property9_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[16]);
			property9_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[17]);
			property10_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[18]);
			property10_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[19]);
			property11_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[20]);
			property11_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[21]);
		}else if("胶带".equals(((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19)))&&!"".equals(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString())){
			property1_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[1]);
			property2_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[3]);
			property3_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[5]);
		}else if("基座".equals(((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19)))&&!"".equals(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString())){
			property1_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[1]);
		}else if("热封上带".equals(((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19)))&&!"".equals(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString())){
			property1_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[1]);
		}else if("载带".equals(((String)PrintLogSelFrm.PrintLog_table.getValueAt(Row,19)))&&!"".equals(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString())){
			property1_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[1]);
			property2_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[3]);
			property3_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[5]);
			property4_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[7]);
			property5_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[8]);
			property5_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[9]);
			property6_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[10]);
			property6_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[11]);
			property7_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[12]);
			property7_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[13]);
			property8_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[14]);
			property8_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[15]);
			property9_textField.setText(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[16]);
			property9_comboBox.setSelectedItem(PrintLogSelFrm.PrintLog_table.getValueAt(Row,20).toString().split("\\|")[17]);
		}
	}

	/**
	 * 点击表格行，加载行数据到输入框 
	 * @param row
	 */
	protected void Material_Property_load(int row) {
		if(StringUtil.isEmpty((String)table.getValueAt(row,1))||"".equals((String)table.getValueAt(row,1))){
			material_comboBox.setSelectedItem("请选择材料");
		}else{
			material_comboBox.setSelectedItem((String)table.getValueAt(row,1));
		}
		if("正箔".equals(((String)table.getValueAt(row,1)))||"负箔".equals(((String)table.getValueAt(row,1)))){//只能用equqls
			property1_textField.setText((String)table.getValueAt(row,2));
			property1_comboBox.setSelectedItem((String)table.getValueAt(row,3));
			property2_textField.setText((String)table.getValueAt(row,4));
			property2_comboBox.setSelectedItem((String)table.getValueAt(row,5));
			property3_textField.setText((String)table.getValueAt(row,6));
			property3_comboBox.setSelectedItem((String)table.getValueAt(row,7));
			property4_textField.setText((String)table.getValueAt(row,8));
			property4_comboBox.setSelectedItem((String)table.getValueAt(row,9));
		}else if("电解纸".equals(((String)table.getValueAt(row,1)))){
			property1_textField.setText((String)table.getValueAt(row,2));
			property1_comboBox.setSelectedItem((String)table.getValueAt(row,3));
			property2_textField.setText((String)table.getValueAt(row,4));
			property2_comboBox.setSelectedItem((String)table.getValueAt(row,5));
			property3_textField.setText((String)table.getValueAt(row,6));
			property3_comboBox.setSelectedItem((String)table.getValueAt(row,7));
			property4_textField.setText((String)table.getValueAt(row,8));
			property4_comboBox.setSelectedItem((String)table.getValueAt(row,9));
			property5_textField.setText((String)table.getValueAt(row,10));
			property5_comboBox.setSelectedItem((String)table.getValueAt(row,11));
		}else if("正导针".equals(((String)table.getValueAt(row,1)))||"负导针".equals(((String)table.getValueAt(row,1)))){
			property1_textField.setText((String)table.getValueAt(row,2));
			property1_comboBox.setSelectedItem((String)table.getValueAt(row,3));
			property2_textField.setText((String)table.getValueAt(row,4));
			property2_comboBox.setSelectedItem((String)table.getValueAt(row,5));
			property3_textField.setText((String)table.getValueAt(row,6));
			property3_comboBox.setSelectedItem((String)table.getValueAt(row,7));
			property4_textField.setText((String)table.getValueAt(row,8));
			property4_comboBox.setSelectedItem((String)table.getValueAt(row,9));
			property5_textField.setText((String)table.getValueAt(row,10));
			property5_comboBox.setSelectedItem((String)table.getValueAt(row,11));
			property6_textField.setText((String)table.getValueAt(row,12));
			property6_comboBox.setSelectedItem((String)table.getValueAt(row,13));
			property7_textField.setText((String)table.getValueAt(row,14));
			property7_comboBox.setSelectedItem((String)table.getValueAt(row,15));
			property8_textField.setText((String)table.getValueAt(row,16));
			property8_comboBox.setSelectedItem((String)table.getValueAt(row,17));
			property9_textField.setText((String)table.getValueAt(row,18));
			property9_comboBox.setSelectedItem((String)table.getValueAt(row,19));
			property10_textField.setText((String)table.getValueAt(row,20));
			property10_comboBox.setSelectedItem((String)table.getValueAt(row,21));
			property11_textField.setText((String)table.getValueAt(row,22));
			property11_comboBox.setSelectedItem((String)table.getValueAt(row,23));
		}else if("胶带".equals(((String)table.getValueAt(row,1)))){
			property1_textField.setText((String)table.getValueAt(row,2));
			property1_comboBox.setSelectedItem((String)table.getValueAt(row,3));
			property2_textField.setText((String)table.getValueAt(row,4));
			property2_comboBox.setSelectedItem((String)table.getValueAt(row,5));
			property3_textField.setText((String)table.getValueAt(row,6));
			property3_comboBox.setSelectedItem((String)table.getValueAt(row,7));
		}else if("基座".equals(((String)table.getValueAt(row,1)))||"热封上带".equals(((String)table.getValueAt(row,1)))){
			property1_textField.setText((String)table.getValueAt(row,2));
			property1_comboBox.setSelectedItem((String)table.getValueAt(row,3));
		}else if("载带".equals(((String)table.getValueAt(row,1)))){
			property1_textField.setText((String)table.getValueAt(row,2));
			property1_comboBox.setSelectedItem((String)table.getValueAt(row,3));
			property2_textField.setText((String)table.getValueAt(row,4));
			property2_comboBox.setSelectedItem((String)table.getValueAt(row,5));
			property3_textField.setText((String)table.getValueAt(row,6));
			property3_comboBox.setSelectedItem((String)table.getValueAt(row,7));
			property4_textField.setText((String)table.getValueAt(row,8));
			property4_comboBox.setSelectedItem((String)table.getValueAt(row,9));
			property5_textField.setText((String)table.getValueAt(row,10));
			property5_comboBox.setSelectedItem((String)table.getValueAt(row,11));
			property6_textField.setText((String)table.getValueAt(row,12));
			property6_comboBox.setSelectedItem((String)table.getValueAt(row,13));
			property7_textField.setText((String)table.getValueAt(row,14));
			property7_comboBox.setSelectedItem((String)table.getValueAt(row,15));
			property8_textField.setText((String)table.getValueAt(row,16));
			property8_comboBox.setSelectedItem((String)table.getValueAt(row,17));
			property9_textField.setText((String)table.getValueAt(row,18));
			property9_comboBox.setSelectedItem((String)table.getValueAt(row,19));
		}
		
	}

	/**
	 * 删除指定标准值列表指定行
	 * @param e
	 * @param id
	 */
	protected void deleteTableRow(ActionEvent e, int id) {
		// TODO Auto-generated method stub
		PropertyValueDao propertyValueDao = new PropertyValueDao();
		int flag = propertyValueDao.deleteFxstandardValueRow(id);
		if(flag==1){
			JOptionPane.showMessageDialog(null, "删除成功！");
		}
	}
	/**
	 * 清空标准值列表
	 * @param e
	 */

	protected void truncateTable(ActionEvent e) {
		// TODO Auto-generated method stub
		PropertyValueDao propertyValueDao = new PropertyValueDao();
		propertyValueDao.truncateFxstandardValue();
	}

	/**
	 * 将输入的标准值保存到标准值模板
	 * @param e
	 */
	protected void SaveToModel(ActionEvent e) {
		PropertyValue fxstandardValue = new PropertyValue();
		PropertyValueDao propertyValueDao = new PropertyValueDao();
		if(material_comboBox.getSelectedItem()!="请选择材料"&&StringUtil.isNotEmpty((String) material_comboBox.getSelectedItem())&&!"".equals(material_comboBox.getSelectedItem())){
			fxstandardValue.setMaterialName((String) material_comboBox.getSelectedItem());
			fxstandardValue.setProperty1(property1_textField.getText());
			fxstandardValue.setUnit1((String) property1_comboBox.getSelectedItem());
			fxstandardValue.setProperty2(property2_textField.getText());
			if(StringUtil.isEmpty(property2_textField.getText())||"".equals(property2_textField.getText())){
				fxstandardValue.setUnit2("");
			}else{
				fxstandardValue.setUnit2((String) property2_comboBox.getSelectedItem());
			}
			fxstandardValue.setProperty3(property3_textField.getText());
			if(StringUtil.isEmpty(property3_textField.getText())||"".equals(property3_textField.getText())){
				fxstandardValue.setUnit3("");
			}else{
				fxstandardValue.setUnit3((String) property3_comboBox.getSelectedItem());
			}
			fxstandardValue.setProperty4(property4_textField.getText());
			if(StringUtil.isEmpty(property4_textField.getText())||"".equals(property4_textField.getText())){
				fxstandardValue.setUnit4("");
			}else{
				fxstandardValue.setUnit4((String) property4_comboBox.getSelectedItem());
			}
			fxstandardValue.setProperty5(property5_textField.getText());
			if(StringUtil.isEmpty(property5_textField.getText())||"".equals(property5_textField.getText())){
				fxstandardValue.setUnit5("");
			}else{
				fxstandardValue.setUnit5((String) property5_comboBox.getSelectedItem());
			}
			fxstandardValue.setProperty6(property6_textField.getText());
			if(StringUtil.isEmpty(property6_textField.getText())||"".equals(property6_textField.getText())){
				fxstandardValue.setUnit6("");
			}else{
				fxstandardValue.setUnit6((String) property6_comboBox.getSelectedItem());
			}
			fxstandardValue.setProperty7(property7_textField.getText());
			if(StringUtil.isEmpty(property7_textField.getText())||"".equals(property7_textField.getText())){
				fxstandardValue.setUnit7("");
			}else{
				fxstandardValue.setUnit7((String) property7_comboBox.getSelectedItem());
			}
			fxstandardValue.setProperty8(property8_textField.getText());
			if(StringUtil.isEmpty(property8_textField.getText())||"".equals(property8_textField.getText())){
				fxstandardValue.setUnit8("");
			}else{
				fxstandardValue.setUnit8((String) property8_comboBox.getSelectedItem());
			}
			fxstandardValue.setProperty9(property9_textField.getText());
			if(StringUtil.isEmpty(property9_textField.getText())||"".equals(property9_textField.getText())){
				fxstandardValue.setUnit9("");
			}else{
				fxstandardValue.setUnit9((String) property9_comboBox.getSelectedItem());
			}
			fxstandardValue.setProperty10(property10_textField.getText());
			if(StringUtil.isEmpty(property10_textField.getText())||"".equals(property10_textField.getText())){
				fxstandardValue.setUnit10("");
			}else{
				fxstandardValue.setUnit10((String) property10_comboBox.getSelectedItem());
			}
			fxstandardValue.setProperty11(property11_textField.getText());
			if(StringUtil.isEmpty(property11_textField.getText())||"".equals(property11_textField.getText())){
				fxstandardValue.setUnit11("");
			}else{
				fxstandardValue.setUnit11((String) property11_comboBox.getSelectedItem());
			}
			if(propertyValueDao.insertToFxstandardValue(fxstandardValue)==1){
//				System.out.println("存储成功");
			}
		}
		
	}

	/**
	 * 刷新外箱标签二维码
	 * @param e
	 */
	protected void flashOutBox(ActionEvent e) {
		// TODO Auto-generated method stub
		//材料名
				String MaterialName = material_comboBox.getSelectedItem().toString();
				if (material_comboBox.getSelectedItem() == "请选择材料") {
					MaterialName = "";
				}
				//标准值
				StringBuffer Material_Property = GetMaterialProperty();
				
				
				BufferedImage inside_bi = new BufferedImage(115, 115, BufferedImage.TYPE_INT_RGB);
				for (int i = 0; i < 115; i++)
					for (int j = 0; j < 115; j++)
						inside_bi.setRGB(j, i, Color.WHITE.getRGB());

				Graphics2D inside_gs = inside_bi.createGraphics();
				inside_gs.setColor(Color.BLACK);

				Qrcode inside_qrcode = new Qrcode();
				inside_qrcode.setQrcodeEncodeMode('B');// 存储英文
				inside_qrcode.setQrcodeVersion(10);

				// 二维码生成规则
				String inside_code = out_PO + '|' + out_VendorCode + '|' + out_PN + '|' +
				 out_LotNo+
						'|' + out_remark + '|' + out_AN + '|' + out_PD + '|' + number1_textField.getText().trim() + '|'
						+ number2_textField.getText().trim() + '|' + netWeigth_textField.getText() + '|' + grossWeigth_textField.getText() +'|' +MaterialName+ '|' +Material_Property;

				try {
					boolean[][] inside_rest = inside_qrcode.calQrcode(inside_code.getBytes("UTF-8"));
					for (int i = 0; i < inside_rest.length; i++) {
						for (int j = 0; j < inside_rest.length; j++) {
							if (inside_rest[j][i])
								inside_gs.fillRect(j * 2, i * 2, 2, 2);
						}
					}

					code_panel.setBufferedImage(inside_bi);
					code_panel.repaint();
				} catch (HeadlessException | UnsupportedEncodingException ex) {
					ex.printStackTrace();
				}
	}

	/**
	 * 选择材料,输入框自动根据选择得材料变化
	 * @param e
	 */
	protected void SelectProperty(ActionEvent e) {
		if(material_comboBox.getSelectedItem()=="正箔"){
			material_panel.setBorder(new TitledBorder(null, "正箔标准值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			saveTotable.setVisible(true);
			property1_lbl.setText("正箔比容");
			property1_textField.setText("");
			property1_comboBox.setSelectedItem("μF/cm²");
			property1_lbl.setVisible(true);
			property1_textField.setVisible(true);
			property1_comboBox.setVisible(true);
			
			property2_lbl.setText("正箔厚度");
			property2_textField.setText("");
			property2_comboBox.setSelectedItem("um");
			property2_lbl.setVisible(true);
			property2_textField.setVisible(true);
			property2_comboBox.setVisible(true);
			
			property3_lbl.setText("正箔长度");
			property3_textField.setText("");
			property3_comboBox.setSelectedItem("m");
			property3_lbl.setVisible(true);
			property3_textField.setVisible(true);
			property3_comboBox.setVisible(true);
			
			property4_lbl.setText("正箔宽度");
			property4_textField.setText("");
			property4_comboBox.setSelectedItem("mm");
			property4_lbl.setVisible(true);
			property4_textField.setVisible(true);
			property4_comboBox.setVisible(true);
			
			property5_lbl.setText("");
			property5_textField.setText("");
			property5_lbl.setVisible(false);
			property5_textField.setVisible(false);
			property5_comboBox.setVisible(false);
			property5_textField.setEnabled(true);
			
			property6_lbl.setText("");
			property6_textField.setText("");
			property6_comboBox.setSelectedItem("");
			property6_lbl.setVisible(false);
			property6_textField.setVisible(false);
			property6_comboBox.setVisible(false);
			
			property7_lbl.setText("");
			property7_textField.setText("");
			property7_comboBox.setSelectedItem("");
			property7_lbl.setVisible(false);
			property7_textField.setVisible(false);
			property7_comboBox.setVisible(false);
			
			property8_lbl.setText("");
			property8_textField.setText("");
			property8_comboBox.setSelectedItem("");
			property8_lbl.setVisible(false);
			property8_textField.setVisible(false);
			property8_comboBox.setVisible(false);
			
			property9_lbl.setText("");
			property9_textField.setText("");
			property9_comboBox.setSelectedItem("");
			property9_lbl.setVisible(false);
			property9_textField.setVisible(false);
			property9_comboBox.setVisible(false);
			
			property10_lbl.setText("");
			property10_textField.setText("");
			property10_comboBox.setSelectedItem("");
			property10_lbl.setVisible(false);
			property10_textField.setVisible(false);
			property10_comboBox.setVisible(false);
			
			property11_lbl.setText("");
			property11_textField.setText("");
			property11_comboBox.setSelectedItem("");
			property11_lbl.setVisible(false);
			property11_textField.setVisible(false);
			property11_comboBox.setVisible(false);
			
		}else if(material_comboBox.getSelectedItem()=="负箔"){
			material_panel.setBorder(new TitledBorder(null, "负箔标准值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			saveTotable.setVisible(true);
			property1_lbl.setText("负箔比容");
			property1_textField.setText("");
			property1_comboBox.setSelectedItem("μF/cm²");
			property1_lbl.setVisible(true);
			property1_textField.setVisible(true);
			property1_comboBox.setVisible(true);
			
			property2_lbl.setText("负箔厚度");
			property2_textField.setText("");
			property2_comboBox.setSelectedItem("um");
			property2_lbl.setVisible(true);
			property2_textField.setVisible(true);
			property2_comboBox.setVisible(true);
			
			property3_lbl.setText("负箔长度");
			property3_textField.setText("");
			property3_comboBox.setSelectedItem("m");
			property3_lbl.setVisible(true);
			property3_textField.setVisible(true);
			property3_comboBox.setVisible(true);
			
			property4_lbl.setText("负箔宽度");
			property4_textField.setText("");
			property4_comboBox.setSelectedItem("mm");
			property4_lbl.setVisible(true);
			property4_textField.setVisible(true);
			property4_comboBox.setVisible(true);
			
			property5_lbl.setText("");
			property5_textField.setText("");
			property5_lbl.setVisible(false);
			property5_textField.setVisible(false);
			property5_comboBox.setVisible(false);
			property5_textField.setEnabled(true);
			
			property6_lbl.setText("");
			property6_textField.setText("");
			property6_comboBox.setSelectedItem("");
			property6_lbl.setVisible(false);
			property6_textField.setVisible(false);
			property6_comboBox.setVisible(false);
			
			property7_lbl.setText("");
			property7_textField.setText("");
			property7_comboBox.setSelectedItem("");
			property7_lbl.setVisible(false);
			property7_textField.setVisible(false);
			property7_comboBox.setVisible(false);
			
			property8_lbl.setText("");
			property8_textField.setText("");
			property8_comboBox.setSelectedItem("");
			property8_lbl.setVisible(false);
			property8_textField.setVisible(false);
			property8_comboBox.setVisible(false);
			
			property9_lbl.setText("");
			property9_textField.setText("");
			property9_comboBox.setSelectedItem("");
			property9_lbl.setVisible(false);
			property9_textField.setVisible(false);
			property9_comboBox.setVisible(false);
			
			property10_lbl.setText("");
			property10_textField.setText("");
			property10_comboBox.setSelectedItem("");
			property10_lbl.setVisible(false);
			property10_textField.setVisible(false);
			property10_comboBox.setVisible(false);
			
			property11_lbl.setText("");
			property11_textField.setText("");
			property11_comboBox.setSelectedItem("");
			property11_lbl.setVisible(false);
			property11_textField.setVisible(false);
			property11_comboBox.setVisible(false);
		}
		else if(material_comboBox.getSelectedItem()=="电解纸"){
			material_panel.setBorder(new TitledBorder(null, "电解纸标准值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			saveTotable.setVisible(true);
			property1_lbl.setText("电解纸密度");
			property1_textField.setText("");
			property1_comboBox.setSelectedItem("g/cm³");
			property1_lbl.setVisible(true);
			property1_textField.setVisible(true);
			property1_comboBox.setVisible(true);
			
			property2_lbl.setText("电解纸厚度");
			property2_textField.setText("");
			property2_comboBox.setSelectedItem("um");
			property2_lbl.setVisible(true);
			property2_textField.setVisible(true);
			property2_comboBox.setVisible(true);
			
			property3_lbl.setText("电解纸长度");
			property3_textField.setText("");
			property3_comboBox.setSelectedItem("m");
			property3_lbl.setVisible(true);
			property3_textField.setVisible(true);
			property3_comboBox.setVisible(true);
			
			property4_lbl.setText("电解纸宽度");
			property4_textField.setText("");
			property4_comboBox.setSelectedItem("mm");
			property4_lbl.setVisible(true);
			property4_textField.setVisible(true);
			property4_comboBox.setVisible(true);
			
			property5_lbl.setText("电解纸类型");
			property5_textField.setText((String) property5_comboBox.getSelectedItem());
			property5_comboBox.setSelectedItem("无碳化");
			property5_lbl.setVisible(true);
			property5_textField.setVisible(true);
			property5_comboBox.setVisible(true);
			property5_textField.setEnabled(false);
			
			property6_lbl.setText("");
			property6_textField.setText("");
			property6_comboBox.setSelectedItem("");
			property6_lbl.setVisible(false);
			property6_textField.setVisible(false);
			property6_comboBox.setVisible(false);
			
			property7_lbl.setText("");
			property7_textField.setText("");
			property7_comboBox.setSelectedItem("");
			property7_lbl.setVisible(false);
			property7_textField.setVisible(false);
			property7_comboBox.setVisible(false);
			
			property8_lbl.setText("");
			property8_textField.setText("");
			property8_comboBox.setSelectedItem("");
			property8_lbl.setVisible(false);
			property8_textField.setVisible(false);
			property8_comboBox.setVisible(false);
			
			property9_lbl.setText("");
			property9_textField.setText("");
			property9_comboBox.setSelectedItem("");
			property9_lbl.setVisible(false);
			property9_textField.setVisible(false);
			property9_comboBox.setVisible(false);
			
			property10_lbl.setText("");
			property10_textField.setText("");
			property10_comboBox.setSelectedItem("");
			property10_lbl.setVisible(false);
			property10_textField.setVisible(false);
			property10_comboBox.setVisible(false);
			
			property11_lbl.setText("");
			property11_textField.setText("");
			property11_comboBox.setSelectedItem("");
			property11_lbl.setVisible(false);
			property11_textField.setVisible(false);
			property11_comboBox.setVisible(false);
		}else if(material_comboBox.getSelectedItem()=="正导针"){
			material_panel.setBorder(new TitledBorder(null, "正导针标准值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			saveTotable.setVisible(true);
			property1_lbl.setText("A");
			property1_textField.setText("");
			property1_comboBox.setSelectedItem("mm");
			property1_lbl.setVisible(true);
			property1_textField.setVisible(true);
			property1_comboBox.setVisible(true);
			
			property2_lbl.setText("E");
			property2_textField.setText("");
			property2_comboBox.setSelectedItem("mm");
			property2_lbl.setVisible(true);
			property2_textField.setVisible(true);
			property2_comboBox.setVisible(true);
			
			property3_lbl.setText("B");
			property3_textField.setText("");
			property3_comboBox.setSelectedItem("mm");
			property3_lbl.setVisible(true);
			property3_textField.setVisible(true);
			property3_comboBox.setVisible(true);
			
			property4_lbl.setText("Y");
			property4_textField.setText("");
			property4_comboBox.setSelectedItem("mm");
			property4_lbl.setVisible(true);
			property4_textField.setVisible(true);
			property4_comboBox.setVisible(true);
			
			property5_lbl.setText("T");
			property5_textField.setText("");
			property5_comboBox.setSelectedItem("mm");
			property5_lbl.setVisible(true);
			property5_textField.setVisible(true);
			property5_comboBox.setVisible(true);
			property5_textField.setEnabled(true);
			
			property6_lbl.setText("W");
			property6_textField.setText("");
			property6_comboBox.setSelectedItem("mm");
			property6_lbl.setVisible(true);
			property6_textField.setVisible(true);
			property6_comboBox.setVisible(true);
			
			property7_lbl.setText("d");
			property7_textField.setText("");
			property7_comboBox.setSelectedItem("mm");
			property7_lbl.setVisible(true);
			property7_textField.setVisible(true);
			property7_comboBox.setVisible(true);
			
			property8_lbl.setText("D");
			property8_textField.setText("");
			property8_comboBox.setSelectedItem("mm");
			property8_lbl.setVisible(true);
			property8_textField.setVisible(true);
			property8_comboBox.setVisible(true);
			
			property9_lbl.setText("L1");
			property9_textField.setText("");
			property9_comboBox.setSelectedItem("mm");
			property9_lbl.setVisible(true);
			property9_textField.setVisible(true);
			property9_comboBox.setVisible(true);
			
			property10_lbl.setText("L2");
			property10_textField.setText("");
			property10_comboBox.setSelectedItem("mm");
			property10_lbl.setVisible(true);
			property10_textField.setVisible(true);
			property10_comboBox.setVisible(true);
			
			property11_lbl.setText("L");
			property11_textField.setText("");
			property11_comboBox.setSelectedItem("mm");
			property11_lbl.setVisible(true);
			property11_textField.setVisible(true);
			property11_comboBox.setVisible(true);
		}
		else if(material_comboBox.getSelectedItem()=="负导针"){
			material_panel.setBorder(new TitledBorder(null, "负导针标准值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			saveTotable.setVisible(true);
			property1_lbl.setText("A");
			property1_textField.setText("");
			property1_comboBox.setSelectedItem("mm");
			property1_lbl.setVisible(true);
			property1_textField.setVisible(true);
			property1_comboBox.setVisible(true);
			
			property2_lbl.setText("E");
			property2_textField.setText("");
			property2_comboBox.setSelectedItem("mm");
			property2_lbl.setVisible(true);
			property2_textField.setVisible(true);
			property2_comboBox.setVisible(true);
			
			property3_lbl.setText("B");
			property3_textField.setText("");
			property3_comboBox.setSelectedItem("mm");
			property3_lbl.setVisible(true);
			property3_textField.setVisible(true);
			property3_comboBox.setVisible(true);
			
			property4_lbl.setText("Y");
			property4_textField.setText("");
			property4_comboBox.setSelectedItem("mm");
			property4_lbl.setVisible(true);
			property4_textField.setVisible(true);
			property4_comboBox.setVisible(true);
			
			property5_lbl.setText("T");
			property5_textField.setText("");
			property5_comboBox.setSelectedItem("mm");
			property5_lbl.setVisible(true);
			property5_textField.setVisible(true);
			property5_comboBox.setVisible(true);
			property5_textField.setEnabled(true);
			
			property6_lbl.setText("W");
			property6_textField.setText("");
			property6_comboBox.setSelectedItem("mm");
			property6_lbl.setVisible(true);
			property6_textField.setVisible(true);
			property6_comboBox.setVisible(true);
			
			property7_lbl.setText("d");
			property7_textField.setText("");
			property7_comboBox.setSelectedItem("mm");
			property7_lbl.setVisible(true);
			property7_textField.setVisible(true);
			property7_comboBox.setVisible(true);
			
			property8_lbl.setText("D");
			property8_textField.setText("");
			property8_comboBox.setSelectedItem("mm");
			property8_lbl.setVisible(true);
			property8_textField.setVisible(true);
			property8_comboBox.setVisible(true);
			
			property9_lbl.setText("L1");
			property9_textField.setText("");
			property9_comboBox.setSelectedItem("mm");
			property9_lbl.setVisible(true);
			property9_textField.setVisible(true);
			property9_comboBox.setVisible(true);
			
			property10_lbl.setText("L2");
			property10_textField.setText("");
			property10_comboBox.setSelectedItem("mm");
			property10_lbl.setVisible(true);
			property10_textField.setVisible(true);
			property10_comboBox.setVisible(true);
			
			property11_lbl.setText("L");
			property11_textField.setText("");
			property11_comboBox.setSelectedItem("mm");
			property11_lbl.setVisible(true);
			property11_textField.setVisible(true);
			property11_comboBox.setVisible(true);
		}else if(material_comboBox.getSelectedItem()=="胶带"){
			material_panel.setBorder(new TitledBorder(null, "胶带标准值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			saveTotable.setVisible(true);
			property1_lbl.setText("胶带厚度");
			property1_textField.setText("");
			property1_comboBox.setSelectedItem("um");
			property1_lbl.setVisible(true);
			property1_textField.setVisible(true);
			property1_comboBox.setVisible(true);
			
			property2_lbl.setText("胶带长度");
			property2_textField.setText("");
			property2_comboBox.setSelectedItem("m");
			property2_lbl.setVisible(true);
			property2_textField.setVisible(true);
			property2_comboBox.setVisible(true);
			
			property3_lbl.setText("胶带宽度");
			property3_textField.setText("");
			property3_comboBox.setSelectedItem("mm");
			property3_lbl.setVisible(true);
			property3_textField.setVisible(true);
			property3_comboBox.setVisible(true);
			
			property4_lbl.setText("");
			property4_textField.setText("");
			property4_comboBox.setSelectedItem("");
			property4_lbl.setVisible(false);
			property4_textField.setVisible(false);
			property4_comboBox.setVisible(false);
			
			property5_lbl.setText("");
			property5_textField.setText("");
			property5_lbl.setVisible(false);
			property5_textField.setVisible(false);
			property5_comboBox.setVisible(false);
			property5_textField.setEnabled(true);
			
			property6_lbl.setText("");
			property6_textField.setText("");
			property6_comboBox.setSelectedItem("");
			property6_lbl.setVisible(false);
			property6_textField.setVisible(false);
			property6_comboBox.setVisible(false);
			
			property7_lbl.setText("");
			property7_textField.setText("");
			property7_comboBox.setSelectedItem("");
			property7_lbl.setVisible(false);
			property7_textField.setVisible(false);
			property7_comboBox.setVisible(false);
			
			property8_lbl.setText("");
			property8_textField.setText("");
			property8_comboBox.setSelectedItem("");
			property8_lbl.setVisible(false);
			property8_textField.setVisible(false);
			property8_comboBox.setVisible(false);
			
			property9_lbl.setText("");
			property9_textField.setText("");
			property9_comboBox.setSelectedItem("");
			property9_lbl.setVisible(false);
			property9_textField.setVisible(false);
			property9_comboBox.setVisible(false);
			
			property10_lbl.setText("");
			property10_textField.setText("");
			property10_comboBox.setSelectedItem("");
			property10_lbl.setVisible(false);
			property10_textField.setVisible(false);
			property10_comboBox.setVisible(false);
			
			property11_lbl.setText("");
			property11_textField.setText("");
			property11_comboBox.setSelectedItem("");
			property11_lbl.setVisible(false);
			property11_textField.setVisible(false);
			property11_comboBox.setVisible(false);
		}else if(material_comboBox.getSelectedItem()=="基座"){
			material_panel.setBorder(new TitledBorder(null, "基座标准值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			saveTotable.setVisible(true);
			property1_lbl.setText("基座厚度");
			property1_textField.setText("");
			property1_comboBox.setSelectedItem("mm");
			property1_lbl.setVisible(true);
			property1_textField.setVisible(true);
			property1_comboBox.setVisible(true);
			
			property2_lbl.setText("");
			property2_textField.setText("");
			property2_comboBox.setSelectedItem("");
			property2_lbl.setVisible(false);
			property2_textField.setVisible(false);
			property2_comboBox.setVisible(false);
			
			property3_lbl.setText("");
			property3_textField.setText("");
			property3_comboBox.setSelectedItem("");
			property3_lbl.setVisible(false);
			property3_textField.setVisible(false);
			property3_comboBox.setVisible(false);
			
			property4_lbl.setText("");
			property4_textField.setText("");
			property4_comboBox.setSelectedItem("");
			property4_lbl.setVisible(false);
			property4_textField.setVisible(false);
			property4_comboBox.setVisible(false);
			
			property5_lbl.setText("");
			property5_textField.setText("");
			property5_lbl.setVisible(false);
			property5_textField.setVisible(false);
			property5_comboBox.setVisible(false);
			property5_textField.setEnabled(true);
			
			property6_lbl.setText("");
			property6_textField.setText("");
			property6_comboBox.setSelectedItem("");
			property6_lbl.setVisible(false);
			property6_textField.setVisible(false);
			property6_comboBox.setVisible(false);
			
			property7_lbl.setText("");
			property7_textField.setText("");
			property7_comboBox.setSelectedItem("");
			property7_lbl.setVisible(false);
			property7_textField.setVisible(false);
			property7_comboBox.setVisible(false);
			
			property8_lbl.setText("");
			property8_textField.setText("");
			property8_comboBox.setSelectedItem("");
			property8_lbl.setVisible(false);
			property8_textField.setVisible(false);
			property8_comboBox.setVisible(false);
			
			property9_lbl.setText("");
			property9_textField.setText("");
			property9_comboBox.setSelectedItem("");
			property9_lbl.setVisible(false);
			property9_textField.setVisible(false);
			property9_comboBox.setVisible(false);
			
			property10_lbl.setText("");
			property10_textField.setText("");
			property10_comboBox.setSelectedItem("");
			property10_lbl.setVisible(false);
			property10_textField.setVisible(false);
			property10_comboBox.setVisible(false);
			
			property11_lbl.setText("");
			property11_textField.setText("");
			property11_comboBox.setSelectedItem("");
			property11_lbl.setVisible(false);
			property11_textField.setVisible(false);
			property11_comboBox.setVisible(false);
		}else if(material_comboBox.getSelectedItem()=="热封上带"){
			material_panel.setBorder(new TitledBorder(null, "热封上带标准值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			saveTotable.setVisible(true);
			property1_lbl.setText("热封上带长度");
			property1_lbl.setBounds(135, 26, 81, 15);
			property1_textField.setText("");
			property1_comboBox.setSelectedItem("m");
			property1_lbl.setVisible(true);
			property1_textField.setVisible(true);
			property1_comboBox.setVisible(true);
			
			property2_lbl.setText("");
			property2_textField.setText("");
			property2_comboBox.setSelectedItem("");
			property2_lbl.setVisible(false);
			property2_textField.setVisible(false);
			property2_comboBox.setVisible(false);
			
			property3_lbl.setText("");
			property3_textField.setText("");
			property3_comboBox.setSelectedItem("");
			property3_lbl.setVisible(false);
			property3_textField.setVisible(false);
			property3_comboBox.setVisible(false);
			
			property4_lbl.setText("");
			property4_textField.setText("");
			property4_comboBox.setSelectedItem("");
			property4_lbl.setVisible(false);
			property4_textField.setVisible(false);
			property4_comboBox.setVisible(false);
			
			property5_lbl.setText("");
			property5_textField.setText("");
			property5_lbl.setVisible(false);
			property5_textField.setVisible(false);
			property5_comboBox.setVisible(false);
			property5_textField.setEnabled(true);
			
			property6_lbl.setText("");
			property6_textField.setText("");
			property6_comboBox.setSelectedItem("");
			property6_lbl.setVisible(false);
			property6_textField.setVisible(false);
			property6_comboBox.setVisible(false);
			
			property7_lbl.setText("");
			property7_textField.setText("");
			property7_comboBox.setSelectedItem("");
			property7_lbl.setVisible(false);
			property7_textField.setVisible(false);
			property7_comboBox.setVisible(false);
			
			property8_lbl.setText("");
			property8_textField.setText("");
			property8_comboBox.setSelectedItem("");
			property8_lbl.setVisible(false);
			property8_textField.setVisible(false);
			property8_comboBox.setVisible(false);
			
			property9_lbl.setText("");
			property9_textField.setText("");
			property9_comboBox.setSelectedItem("");
			property9_lbl.setVisible(false);
			property9_textField.setVisible(false);
			property9_comboBox.setVisible(false);
			
			property10_lbl.setText("");
			property10_textField.setText("");
			property10_comboBox.setSelectedItem("");
			property10_lbl.setVisible(false);
			property10_textField.setVisible(false);
			property10_comboBox.setVisible(false);
			
			property11_lbl.setText("");
			property11_textField.setText("");
			property11_comboBox.setSelectedItem("");
			property11_lbl.setVisible(false);
			property11_textField.setVisible(false);
			property11_comboBox.setVisible(false);
		}else if(material_comboBox.getSelectedItem()=="载带"){
			material_panel.setBorder(new TitledBorder(null, "载带标准值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			saveTotable.setVisible(true);
			property1_lbl.setText("载带长度");
			property1_textField.setText("");
			property1_comboBox.setSelectedItem("m");
			property1_lbl.setVisible(true);
			property1_textField.setVisible(true);
			property1_comboBox.setVisible(true);
			
			property2_lbl.setText("W");
			property2_textField.setText("");
			property2_comboBox.setSelectedItem("mm");
			property2_lbl.setVisible(true);
			property2_textField.setVisible(true);
			property2_comboBox.setVisible(true);
			
			property3_lbl.setText("A0");
			property3_textField.setText("");
			property3_comboBox.setSelectedItem("mm");
			property3_lbl.setVisible(true);
			property3_textField.setVisible(true);
			property3_comboBox.setVisible(true);
			
			property4_lbl.setText("B0");
			property4_textField.setText("");
			property4_comboBox.setSelectedItem("mm");
			property4_lbl.setVisible(true);
			property4_textField.setVisible(true);
			property4_comboBox.setVisible(true);
			
			property5_lbl.setText("P");
			property5_textField.setText("");
			property5_comboBox.setSelectedItem("mm");
			property5_lbl.setVisible(true);
			property5_textField.setVisible(true);
			property5_comboBox.setVisible(true);
			property5_textField.setEnabled(true);
			
			property6_lbl.setText("K0");
			property6_textField.setText("");
			property6_comboBox.setSelectedItem("mm");
			property6_lbl.setVisible(true);
			property6_textField.setVisible(true);
			property6_comboBox.setVisible(true);
			
			property7_lbl.setText("F");
			property7_textField.setText("");
			property7_comboBox.setSelectedItem("mm");
			property7_lbl.setVisible(true);
			property7_textField.setVisible(true);
			property7_comboBox.setVisible(true);
			
			property8_lbl.setText("T");
			property8_textField.setText("");
			property8_comboBox.setSelectedItem("mm");
			property8_lbl.setVisible(true);
			property8_textField.setVisible(true);
			property8_comboBox.setVisible(true);
			
			property9_lbl.setText("上带宽");
			property9_textField.setText("");
			property9_comboBox.setSelectedItem("mm");
			property9_lbl.setVisible(true);
			property9_textField.setVisible(true);
			property9_comboBox.setVisible(true);
			
			property10_lbl.setText("");
			property10_textField.setText("");
			property10_comboBox.setSelectedItem("");
			property10_lbl.setVisible(false);
			property10_textField.setVisible(false);
			property10_comboBox.setVisible(false);
			
			property11_lbl.setText("");
			property11_textField.setText("");
			property11_comboBox.setSelectedItem("");
			property11_lbl.setVisible(false);
			property11_textField.setVisible(false);
			property11_comboBox.setVisible(false);
		}else if(material_comboBox.getSelectedItem()=="请选择材料"){
			material_panel.setBorder(new TitledBorder(null, "请选择材料", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			saveTotable.setVisible(false);
			property1_lbl.setText("");
			property1_lbl.setBounds(135, 26, 81, 15);
			property1_textField.setText("");
			property1_comboBox.setSelectedItem("");
			property1_lbl.setVisible(false);
			property1_textField.setVisible(false);
			property1_comboBox.setVisible(false);
			
			property2_lbl.setText("");
			property2_textField.setText("");
			property2_comboBox.setSelectedItem("");
			property2_lbl.setVisible(false);
			property2_textField.setVisible(false);
			property2_comboBox.setVisible(false);
			
			property3_lbl.setText("");
			property3_textField.setText("");
			property3_comboBox.setSelectedItem("");
			property3_lbl.setVisible(false);
			property3_textField.setVisible(false);
			property3_comboBox.setVisible(false);
			
			property4_lbl.setText("");
			property4_textField.setText("");
			property4_comboBox.setSelectedItem("");
			property4_lbl.setVisible(false);
			property4_textField.setVisible(false);
			property4_comboBox.setVisible(false);
			
			property5_lbl.setText("");
			property5_textField.setText("");
			property5_lbl.setVisible(false);
			property5_textField.setVisible(false);
			property5_comboBox.setVisible(false);
			property5_textField.setEnabled(true);
			
			property6_lbl.setText("");
			property6_textField.setText("");
			property6_comboBox.setSelectedItem("");
			property6_lbl.setVisible(false);
			property6_textField.setVisible(false);
			property6_comboBox.setVisible(false);
			
			property7_lbl.setText("");
			property7_textField.setText("");
			property7_comboBox.setSelectedItem("");
			property7_lbl.setVisible(false);
			property7_textField.setVisible(false);
			property7_comboBox.setVisible(false);
			
			property8_lbl.setText("");
			property8_textField.setText("");
			property8_comboBox.setSelectedItem("");
			property8_lbl.setVisible(false);
			property8_textField.setVisible(false);
			property8_comboBox.setVisible(false);
			
			property9_lbl.setText("");
			property9_textField.setText("");
			property9_comboBox.setSelectedItem("");
			property9_lbl.setVisible(false);
			property9_textField.setVisible(false);
			property9_comboBox.setVisible(false);
			
			property10_lbl.setText("");
			property10_textField.setText("");
			property10_comboBox.setSelectedItem("");
			property10_lbl.setVisible(false);
			property10_textField.setVisible(false);
			property10_comboBox.setVisible(false);
			
			property11_lbl.setText("");
			property11_textField.setText("");
			property11_comboBox.setSelectedItem("");
			property11_lbl.setVisible(false);
			property11_textField.setVisible(false);
			property11_comboBox.setVisible(false);
		}
	}
	
	//刷新外箱标签二维码
	protected void flashOutBox(DocumentEvent e) {
		//材料名
		String MaterialName = material_comboBox.getSelectedItem().toString();
		if (material_comboBox.getSelectedItem() == "请选择材料") {
			MaterialName = "";
		}
		//标准值
		StringBuffer Material_Property = GetMaterialProperty();
		
		
		BufferedImage inside_bi = new BufferedImage(115, 115, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 115; i++)
			for (int j = 0; j < 115; j++)
				inside_bi.setRGB(j, i, Color.WHITE.getRGB());

		Graphics2D inside_gs = inside_bi.createGraphics();
		inside_gs.setColor(Color.BLACK);

		Qrcode inside_qrcode = new Qrcode();
		inside_qrcode.setQrcodeEncodeMode('B');// 存储英文
		inside_qrcode.setQrcodeVersion(10);

		// 二维码生成规则
		String inside_code = out_PO + '|' + out_VendorCode + '|' + out_PN + '|' +
		 out_LotNo +
				'|' + out_remark + '|' + out_AN + '|' + out_PD + '|' + number1_textField.getText().trim() + '|'
				+ number2_textField.getText().trim() + '|' + netWeigth_textField.getText() + '|' + grossWeigth_textField.getText() +'|' +MaterialName+ '|' +Material_Property;
		
		try {
			boolean[][] inside_rest = inside_qrcode.calQrcode(inside_code.getBytes("UTF-8"));
			for (int i = 0; i < inside_rest.length; i++) {
				for (int j = 0; j < inside_rest.length; j++) {
					if (inside_rest[j][i])
						inside_gs.fillRect(j * 2, i * 2, 2, 2);
				}
			}

			code_panel.setBufferedImage(inside_bi);
			code_panel.repaint();
		} catch (HeadlessException | UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 二维码获取标准值
	 * @return
	 */
	private StringBuffer GetMaterialProperty() {
		StringBuffer result = new StringBuffer("");
		if(StringUtil.isNotEmpty(property1_textField.getText())){
			result.append(property1_textField.getText()+property1_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property2_textField.getText())){
			result.append("|"+property2_textField.getText()+property2_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property3_textField.getText())){
			result.append("|"+property3_textField.getText()+property3_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property4_textField.getText())){
			result.append("|"+property4_textField.getText()+property4_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property5_textField.getText())&&material_comboBox.getSelectedItem()!="电解纸"){
			result.append("|"+property5_textField.getText()+property5_comboBox.getSelectedItem());
		}else if(material_comboBox.getSelectedItem()=="电解纸"){
			result.append("|"+property5_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property6_textField.getText())){
			result.append("|"+property6_textField.getText()+property6_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property7_textField.getText())){
			result.append("|"+property7_textField.getText()+property7_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property8_textField.getText())){
			result.append("|"+property8_textField.getText()+property8_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property9_textField.getText())){
			result.append("|"+property9_textField.getText()+property9_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property10_textField.getText())){
			result.append("|"+property10_textField.getText()+property10_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property11_textField.getText())){
			result.append("|"+property11_textField.getText()+property11_comboBox.getSelectedItem());
		}
		return result;
	}

	/*
	 * 获取材料的实测值to存储
	 */
	protected StringBuffer GetMaterialPropertyToTable() {
		StringBuffer result = new StringBuffer("");
		if(StringUtil.isNotEmpty(property1_textField.getText())){
			result.append(property1_textField.getText()+"|"+property1_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property2_textField.getText())){
			result.append("|"+property2_textField.getText()+"|"+property2_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property3_textField.getText())){
			result.append("|"+property3_textField.getText()+"|"+property3_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property4_textField.getText())){
			result.append("|"+property4_textField.getText()+"|"+property4_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property5_textField.getText())&&material_comboBox.getSelectedItem()!="电解纸"){
			result.append("|"+property5_textField.getText()+property5_comboBox.getSelectedItem());
		}else if(material_comboBox.getSelectedItem()=="电解纸"){
			result.append("|"+property5_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property6_textField.getText())){
			result.append("|"+property6_textField.getText()+"|"+property6_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property7_textField.getText())){
			result.append("|"+property7_textField.getText()+"|"+property7_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property8_textField.getText())){
			result.append("|"+property8_textField.getText()+"|"+property8_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property9_textField.getText())){
			result.append("|"+property9_textField.getText()+"|"+property9_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property10_textField.getText())){
			result.append("|"+property10_textField.getText()+"|"+property10_comboBox.getSelectedItem());
		}
		if(StringUtil.isNotEmpty(property11_textField.getText())){
			result.append("|"+property11_textField.getText()+"|"+property11_comboBox.getSelectedItem());
		}
		return result;
	}
	
	//重置输入
	protected void ResetInput(ActionEvent e) {
		// TODO Auto-generated method stub
		number1_textField.setText("");
		number2_textField.setText("");
		netWeigth_textField.setText("");
		grossWeigth_textField.setText("");
		Unit1_comboBox.setSelectedIndex(0);
		OutBoxNum_textField.setText("");
	}

	/**
	 * 预览以及打印前，标签内容完整性检查
	 * 
	 * @param e
	 */
	private Boolean inputActionformed(ActionEvent e) {

		if (StringUtil.isEmpty(this.number1_textField.getText())) {
			JOptionPane.showMessageDialog(null, "单箱数量不能为空！");
			return false;
		}else if(material_comboBox.getSelectedItem()=="正箔"||material_comboBox.getSelectedItem()=="负箔"){
			if(StringUtil.isEmpty(property1_textField.getText())||StringUtil.isEmpty(property2_textField.getText())||
					StringUtil.isEmpty(property3_textField.getText())||StringUtil.isEmpty(property4_textField.getText())){
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem()+"标准值不能为空！");
				return false;
			}else{
				return true;
			}
		}else if(material_comboBox.getSelectedItem()=="电解纸"){
			if(StringUtil.isEmpty(property1_textField.getText())||StringUtil.isEmpty(property2_textField.getText())||
					StringUtil.isEmpty(property3_textField.getText())||StringUtil.isEmpty(property4_textField.getText())||StringUtil.isEmpty(property5_textField.getText())){
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem()+"标准值不能为空！");
				return false;
			}else{
				return true;
			}
		}else if(material_comboBox.getSelectedItem()=="正导针"||material_comboBox.getSelectedItem()=="负导针"){
			if(StringUtil.isEmpty(property1_textField.getText())||StringUtil.isEmpty(property2_textField.getText())||
					StringUtil.isEmpty(property3_textField.getText())||StringUtil.isEmpty(property4_textField.getText())||StringUtil.isEmpty(property5_textField.getText())
					||StringUtil.isEmpty(property6_textField.getText())||StringUtil.isEmpty(property7_textField.getText())||StringUtil.isEmpty(property8_textField.getText())
					||StringUtil.isEmpty(property9_textField.getText())||StringUtil.isEmpty(property10_textField.getText())||StringUtil.isEmpty(property11_textField.getText())){
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem()+"标准值不能为空！");
				return false;
			}else{
				return true;
			}
		}else if(material_comboBox.getSelectedItem()=="胶带"){
			if(StringUtil.isEmpty(property1_textField.getText())||StringUtil.isEmpty(property2_textField.getText())||
					StringUtil.isEmpty(property3_textField.getText())){
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem()+"标准值不能为空！");
				return false;
			}else{
				return true;
			}
		}else if(material_comboBox.getSelectedItem()=="基座"||material_comboBox.getSelectedItem()=="热封上带"){
			if(StringUtil.isEmpty(property1_textField.getText())){
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem()+"标准值不能为空！");
				return false;
			}else{
				return true;
			}
		}else if(material_comboBox.getSelectedItem()=="载带"){
			if(StringUtil.isEmpty(property1_textField.getText())||StringUtil.isEmpty(property2_textField.getText())||
					StringUtil.isEmpty(property3_textField.getText())||StringUtil.isEmpty(property4_textField.getText())||StringUtil.isEmpty(property5_textField.getText())
					||StringUtil.isEmpty(property6_textField.getText())||StringUtil.isEmpty(property7_textField.getText())||StringUtil.isEmpty(property8_textField.getText())
					||StringUtil.isEmpty(property9_textField.getText())){
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem()+"标准值不能为空！");
				return false;
			}else{
				return true;
			}
		} else {
			return true;
		}
	}

	/**
	 * 材料标准值输入检查
	 * @return
	 */
	private Boolean MaterialPropertyInputCheck(){
		if(material_comboBox.getSelectedItem()=="正箔"||material_comboBox.getSelectedItem()=="负箔"){
			if(StringUtil.isEmpty(property1_textField.getText())||StringUtil.isEmpty(property2_textField.getText())||
					StringUtil.isEmpty(property3_textField.getText())||StringUtil.isEmpty(property4_textField.getText())){
				return false;
			}else{
				return true;
			}
		}else if(material_comboBox.getSelectedItem()=="电解纸"){
			if(StringUtil.isEmpty(property1_textField.getText())||StringUtil.isEmpty(property2_textField.getText())||
					StringUtil.isEmpty(property3_textField.getText())||StringUtil.isEmpty(property4_textField.getText())||StringUtil.isEmpty(property5_textField.getText())){
				return false;
			}else{
				return true;
			}
		}else if(material_comboBox.getSelectedItem()=="正导针"||material_comboBox.getSelectedItem()=="负导针"){
			if(StringUtil.isEmpty(property1_textField.getText())||StringUtil.isEmpty(property2_textField.getText())||
					StringUtil.isEmpty(property3_textField.getText())||StringUtil.isEmpty(property4_textField.getText())||StringUtil.isEmpty(property5_textField.getText())
					||StringUtil.isEmpty(property6_textField.getText())||StringUtil.isEmpty(property7_textField.getText())||StringUtil.isEmpty(property8_textField.getText())
					||StringUtil.isEmpty(property9_textField.getText())||StringUtil.isEmpty(property10_textField.getText())||StringUtil.isEmpty(property11_textField.getText())){
				return false;
			}else{
				return true;
			}
		}else if(material_comboBox.getSelectedItem()=="胶带"){
			if(StringUtil.isEmpty(property1_textField.getText())||StringUtil.isEmpty(property2_textField.getText())||
					StringUtil.isEmpty(property3_textField.getText())){
				return false;
			}else{
				return true;
			}
		}else if(material_comboBox.getSelectedItem()=="基座"||material_comboBox.getSelectedItem()=="热封上带"){
			if(StringUtil.isEmpty(property1_textField.getText())){
				return false;
			}else{
				return true;
			}
		}else if(material_comboBox.getSelectedItem()=="载带"){
			if(StringUtil.isEmpty(property1_textField.getText())||StringUtil.isEmpty(property2_textField.getText())||
					StringUtil.isEmpty(property3_textField.getText())||StringUtil.isEmpty(property4_textField.getText())||StringUtil.isEmpty(property5_textField.getText())
					||StringUtil.isEmpty(property6_textField.getText())||StringUtil.isEmpty(property7_textField.getText())||StringUtil.isEmpty(property8_textField.getText())
					||StringUtil.isEmpty(property9_textField.getText())){
				return false;
			}else{
				return true;
			}
		}
		else{
			return true;
		}
	}
	
	/**
	 * 初始化标准值模板表的数据
	 * @param BoxType
	 * @throws SQLException 
	 */
	private void fillTable() throws SQLException{
		PropertyValueDao propertyValueDao = new PropertyValueDao();
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		ResultSet rs = propertyValueDao.listFxstandardValue();
		while(rs.next()){
			Vector v = new Vector();
			v.add(rs.getString("n_Id"));
			v.add(rs.getString("MaterialName"));
			v.add(rs.getString("Property1"));
			v.add(rs.getString("Unit1"));
			v.add(rs.getString("Property2"));
			v.add(rs.getString("Unit2"));
			v.add(rs.getString("Property3"));
			v.add(rs.getString("Unit3"));
			v.add(rs.getString("Property4"));
			v.add(rs.getString("Unit4"));
			v.add(rs.getString("Property5"));
			v.add(rs.getString("Unit5"));
			v.add(rs.getString("Property6"));
			v.add(rs.getString("Unit6"));
			v.add(rs.getString("Property7"));
			v.add(rs.getString("Unit7"));
			v.add(rs.getString("Property8"));
			v.add(rs.getString("Unit8"));
			v.add(rs.getString("Property9"));
			v.add(rs.getString("Unit9"));
			v.add(rs.getString("Property10"));
			v.add(rs.getString("Unit10"));
			v.add(rs.getString("Property11"));
			v.add(rs.getString("Unit11"));
			dtm.addRow(v);
		}
	}
}
