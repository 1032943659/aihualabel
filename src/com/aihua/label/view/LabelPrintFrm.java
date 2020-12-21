package com.aihua.label.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import com.aihua.label.dao.LabelDao;
import com.aihua.label.model.AiHuaLabel;
import com.aihua.label.util.ComponenetPrintUtil;
import com.aihua.label.util.DBHelper;
import com.aihua.label.util.DateChooser;
import com.aihua.label.util.ImagePannel;
import com.aihua.label.util.JLabelUtil;
import com.aihua.label.util.LogUtil;
import com.aihua.label.util.PrintListExcelImport;
import com.aihua.label.util.PropertiesConfig;
import com.aihua.label.util.StringUtil;
import com.aihua.label.util.UpgraderUtil;
import com.swetake.util.Qrcode;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class LabelPrintFrm extends JFrame {

	private static final long serialVersionUID = 1L;
	public static JTextField PO_textField;
	public static int printStatus = 1;// 打印状态：1保存打印记录并打印，0仅保存打印记录
	public static JTextField VendorCode_textField;
	public static JTextField Item_textField;
	public static JTextField Vendor_textField;
	public static JTextField PrintDate_textField;
	public static JTextField Lot_No_textField;
	public static JTextField AISHINo_textField;
	public static JTextField number1_textField;
	public static JTextField PN_textField;
	public static JTextField net_weight_textField;
	public static JTextField gross_weight_textField;
	public static JComboBox unit1_comboBox;
	public static JComboBox unit2_comboBox;
	public static JTextField insideBox_textField;
	public static JTextField number2_textField;
	public static JTable printList_table;
	public static PrintLogSelFrm printLogSelFrm;
	public static JButton PrintBtn;
	public static JTextField remake_textField;
	private BatchPrintViewFrm printViewFrm;
	private Font font = new Font("Microsoft YaHei", Font.BOLD, 14);
	public static int itemfontSize = Integer.valueOf(PropertiesConfig.getFontValues("ItemFontSize"));// 型号规格字体大小
	public static int otherfontSize = Integer.valueOf(PropertiesConfig.getFontValues("otherFontSize"));// 备注字体大小
	public static int pnfontSize = Integer.valueOf(PropertiesConfig.getFontValues("PNFontSize"));//PN字体大小
	JLabel vendorcode_label;
	JLabel lotno_label;
	JLabel po_label;
	public static JLabel item_label;
	JLabel lblItem;
	public static JLabel pn_label;
	JLabel lblPn;
	JLabel an_label;
	JLabel lblAn;
	JLabel qty1unit1_label;
	JLabel lblQty;
	JLabel pd_label;
	JLabel lblPd;
	JLabel vendor_label;
	JLabel qty2unit2_label;
	JLabel lblRemark;
	JLabel netweigth_label;
	JLabel grossweith_label;
	ImagePannel imagePannel;
	JPanel insideBox_panel;
	JComboBox material_comboBox;
	JLabel property4_lbl;
	JLabel property1_lbl;
	JComboBox property4_comboBox;
	JComboBox property1_comboBox;
	JLabel property2_lbl;
	JComboBox property2_comboBox;
	JLabel property3_lbl;
	JComboBox property3_comboBox;
	JLabel property5_lbl;
	JComboBox property5_comboBox;
	JLabel property6_lbl;
	JComboBox property6_comboBox;
	JLabel property7_lbl;
	JComboBox property7_comboBox;
	JLabel property9_lbl;
	JComboBox property9_comboBox;
	JLabel property10_lbl;
	JComboBox property10_comboBox;
	JLabel property11_lbl;
	JComboBox property11_comboBox;
	JLabel property8_lbl;
	JComboBox property8_comboBox;
	JPanel material_panel;
	private JTextField property4_textField;
	private JTextField property1_textField;
	private JTextField property2_textField;
	private JTextField property3_textField;
	private JTextField property5_textField;
	private JTextField property6_textField;
	private JTextField property7_textField;
	private JTextField property9_textField;
	private JTextField property10_textField;
	private JTextField property11_textField;
	private JTextField property8_textField;
	private JButton excelInput_Button;
	private JButton updateList;
	private JTextField boxNum_textField;
	public static JLabel TagboxNum_label;
	private String REMARK;
	private String PN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabelPrintFrm frame = new LabelPrintFrm();
					frame.setVisible(true);
					new Thread(new Runnable() {
				        @Override
				        public void run() {
				        	DBHelper.getConnection(); //数据库连接测试
				        	UpgraderUtil.autoupgrade(frame);// 更新包连接测试
				        }
				    }).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public LabelPrintFrm() throws SQLException {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		setResizable(false);

		JFrame This = this;

		new ImagePannel();
		new ImagePannel();

		setTitle("标签打印v" + UpgraderUtil.currentversion);
		setBounds(100, 100, 1017, 757);

		JPanel panel = new JPanel();
		panel.setBounds(15, 10, 980, 212);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "输入区", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setToolTipText("");

		JPanel outLabel_panel = new JPanel();
		outLabel_panel.setBounds(15, 379, 486, 264);
		outLabel_panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "打印清单",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel insideLabel_panel = new JPanel();
		insideLabel_panel.setLocation(511, 379);
		insideLabel_panel.setBorder(
				new TitledBorder(null, "内箱标签预览", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
		insideLabel_panel.setSize(484, 264);

		PrintBtn = new JButton("内箱打印");
		PrintBtn.setBounds(304, 661, 407, 23);
		PrintBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 置空
				if (StringUtil.isEmpty(net_weight_textField.getText())) {
					netweigth_label.setText("");
				}
				if (StringUtil.isEmpty(gross_weight_textField.getText())) {
					grossweith_label.setText("");
				}
				if (StringUtil.isEmpty(number2_textField.getText())) {
					qty2unit2_label.setText("");
				}
				int[] Row = printList_table.getSelectedRows();// 打印清单选取的记录
				int flag = 0;// 判断是否存储成功
				if (inputActionformed(e)) {// 检查输入和检查是否是补打
					if (Row.length != 0 && Row[0] != -1 && Row.length != 1) {// 选择打印清单打印
						This.setEnabled(false);
						printViewFrm = new BatchPrintViewFrm(This, "内箱");// 生成一个隐藏的窗口，用于批量打印内箱
						printViewFrm.setVisible(true);
					} else if (Row.length == 0 || Row.length == 1) {// 当客户未选择打印记录的时候,或者选择一条打印清单记录时
						// 开始内箱打印，打印成功后添加打印信息到数据库（默认）
						Boolean bool = true;
						if (printStatus == 1) {
							bool = ComponenetPrintUtil.printComponenet(insideBox_panel, 1);
						}
						if (bool) {
							AiHuaLabel InsidelabelPrintList = new AiHuaLabel();
							InsidelabelPrintList.setPO(PO_textField.getText());
							InsidelabelPrintList.setVendorCode(VendorCode_textField.getText());
							InsidelabelPrintList.setVendor(Vendor_textField.getText());
							InsidelabelPrintList.setItem(Item_textField.getText());
							InsidelabelPrintList.setLot_No(Lot_No_textField.getText());
							InsidelabelPrintList.setPN(PN_textField.getText());
							InsidelabelPrintList.setAISHINo(AISHINo_textField.getText());
							InsidelabelPrintList.setQty1(number1_textField.getText());
							InsidelabelPrintList.setQty2(number2_textField.getText());
							if (StringUtil.isNotEmpty(number2_textField.getText())) {
								InsidelabelPrintList.setUnit2(unit2_comboBox.getSelectedItem().toString());
							}
							InsidelabelPrintList.setUnit1(unit1_comboBox.getSelectedItem().toString());
							InsidelabelPrintList.setProductionDate(PrintDate_textField.getText());
							InsidelabelPrintList.setNetWeight(net_weight_textField.getText());
							InsidelabelPrintList.setGrossWeight(gross_weight_textField.getText());
							InsidelabelPrintList.setBoxType(0);
							InsidelabelPrintList.setRemarks(remake_textField.getText());
							InsidelabelPrintList.setSerialNumber(boxNum_textField.getText());
							if (!"请选择材料".equals(material_comboBox.getSelectedItem().toString())) {
								InsidelabelPrintList.setMaterialName(material_comboBox.getSelectedItem().toString());
							}
							InsidelabelPrintList.setMaterial_Property(GetMaterialPropertyToTable().toString());
							LabelDao labelPrintLogDao = new LabelDao();
							flag = labelPrintLogDao.insertPrintLog(InsidelabelPrintList);
						}
						if (flag != 1 && printStatus == 0) {
							JOptionPane.showMessageDialog(null, "内箱标签打印记录存储失败！");
						} else if (printStatus == 0) {// 如果不需要打印出来，就必须提示是否保存成功
							JOptionPane.showMessageDialog(null, "内箱标签打印记录存储成功！");
						}
					}
				}
			}
		});

		insideBox_panel = new JPanel();
		insideBox_panel.setLayout(null);
		insideBox_panel.setBackground(Color.WHITE);

		JLabel lblPo = new JLabel("PO:");
		lblPo.setFont(font);
		lblPo.setBounds(10, 14, 37, 14);
		insideBox_panel.add(lblPo);

		po_label = new JLabel("采购订单号");
		po_label.setFont(font);
		po_label.setBounds(57, 14, 130, 14);
		insideBox_panel.add(po_label);

		JLabel label_3 = new JLabel("PD:");
		label_3.setFont(font);
		label_3.setBounds(10, 182, 24, 14);
		insideBox_panel.add(label_3);

		pd_label = new JLabel("生产日期");
		pd_label.setFont(font);
		pd_label.setBounds(57, 182, 85, 14);
		insideBox_panel.add(pd_label);

		JLabel label_5 = new JLabel("Qty:");
		label_5.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		label_5.setBounds(10, 154, 38, 14);
		insideBox_panel.add(label_5);

		qty1unit1_label = new JLabel("数量1单位1");
		qty1unit1_label.setFont(font);
		qty1unit1_label.setBounds(57, 154, 85, 17);
		insideBox_panel.add(qty1unit1_label);

		JLabel label_7 = new JLabel("A/N:");
		label_7.setFont(font);
		label_7.setBounds(10, 126, 38, 14);
		insideBox_panel.add(label_7);

		an_label = new JLabel("艾华批次号");
		an_label.setFont(font);
		an_label.setBounds(57, 126, 120, 14);
		insideBox_panel.add(an_label);

		JLabel label_9 = new JLabel("P/N:");
		label_9.setFont(font);
		label_9.setBounds(10, 98, 38, 14);
		insideBox_panel.add(label_9);

		pn_label = new JLabel("艾华料号&REMARK");
		pn_label.setFont(new Font("Microsoft YaHei", Font.BOLD, pnfontSize));
		pn_label.setBounds(57, 98, 240, 14);
		insideBox_panel.add(pn_label);

		JLabel lblLotno = new JLabel("Lot No:");
		lblLotno.setFont(font);
		lblLotno.setBounds(10, 70, 52, 14);
		insideBox_panel.add(lblLotno);

		lotno_label = new JLabel("供应商生产批号");
		lotno_label.setFont(font);
		lotno_label.setBounds(67, 70, 200, 14);
		insideBox_panel.add(lotno_label);

		JLabel lblItem = new JLabel("Item:");
		lblItem.setFont(font);
		lblItem.setBounds(10, 42, 38, 14);
		insideBox_panel.add(lblItem);

		item_label = new JLabel("型号规格");
		item_label.setFont(new Font("Microsoft YaHei", Font.BOLD, itemfontSize));
		item_label.setBounds(57, 42, 240, 14);
		insideBox_panel.add(item_label);

		vendorcode_label = new JLabel("供应商代码");
		vendorcode_label.setFont(font);
		vendorcode_label.setBounds(194, 14, 130, 14);
		insideBox_panel.add(vendorcode_label);

		vendor_label = new JLabel("供应商简称");
		vendor_label.setFont(font);
		vendor_label.setBounds(343, 12, 97, 14);
		insideBox_panel.add(vendor_label);

		qty2unit2_label = new JLabel("数量2单位2");
		qty2unit2_label.setFont(font);
		qty2unit2_label.setBounds(139, 154, 85, 17);
		insideBox_panel.add(qty2unit2_label);

		netweigth_label = new JLabel("净重");
		netweigth_label.setFont(font);
		netweigth_label.setBounds(152, 182, 98, 14);
		insideBox_panel.add(netweigth_label);

		grossweith_label = new JLabel("毛重");
		grossweith_label.setFont(font);
		grossweith_label.setBounds(252, 182, 98, 14);
		insideBox_panel.add(grossweith_label);

		imagePannel = new ImagePannel();
		imagePannel.setBounds(307, 29, 115, 115);
		insideBox_panel.add(imagePannel);
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
		String inside_code = po_label.getText().trim() + '|' + vendorcode_label.getText().trim() + '|' + PN + '|'
				+ lotno_label.getText().trim() + '|' + REMARK + '|' + an_label.getText().trim() + '|'
				+ pd_label.getText().trim() + '|' + qty1unit1_label.getText().substring(0, 3) + '|'
				+ qty2unit2_label.getText().substring(0, 3) + '|' + netweigth_label.getText() + '|'
				+ grossweith_label.getText();
		try {
			boolean[][] inside_rest = inside_qrcode.calQrcode(inside_code.getBytes("UTF-8"));
			for (int i = 0; i < inside_rest.length; i++) {
				for (int j = 0; j < inside_rest.length; j++) {
					if (inside_rest[j][i])
						inside_gs.fillRect(j * 2, i * 2, 2, 2);
				}
			}

			imagePannel.setBufferedImage(inside_bi);
			imagePannel.repaint();
		} catch (HeadlessException | UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		GroupLayout gl_insideLabel_panel = new GroupLayout(insideLabel_panel);
		gl_insideLabel_panel.setHorizontalGroup(gl_insideLabel_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_insideLabel_panel.createSequentialGroup().addContainerGap()
						.addComponent(insideBox_panel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(12, Short.MAX_VALUE)));
		gl_insideLabel_panel.setVerticalGroup(gl_insideLabel_panel.createParallelGroup(Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				gl_insideLabel_panel.createSequentialGroup().addContainerGap()
						.addComponent(insideBox_panel, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(102, Short.MAX_VALUE)));

		TagboxNum_label = new JLabel("");
		TagboxNum_label.setFont(new Font("Microsoft YaHei", Font.BOLD, 10));
		TagboxNum_label.setBounds(354, 175, 80, 28);
		insideBox_panel.add(TagboxNum_label);

		JLabel lblRohs = new JLabel("RoHS2.0");
		lblRohs.setFont(new Font("Microsoft YaHei", Font.BOLD, 28));
		lblRohs.setBounds(307, 140, 120, 41);
		insideBox_panel.add(lblRohs);
		insideLabel_panel.setLayout(gl_insideLabel_panel);

		JLabel lblNewLabel = new JLabel("内");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
		lblNewLabel.setBounds(277, 57, 24, 31);
		insideBox_panel.add(lblNewLabel);
		insideLabel_panel.setLayout(gl_insideLabel_panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 466, 193);

		JLabel insideBox_label = new JLabel("清单总数:");
		insideBox_label.setBounds(10, 228, 72, 19);
		insideBox_textField = new JTextField();
		insideBox_textField.setBounds(74, 227, 29, 21);
		insideBox_textField.setEditable(false);
		insideBox_textField.setColumns(10);
		insideBox_textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});
		
		JButton resetList_Button = new JButton("清空打印清单");
		resetList_Button.setBounds(113, 226, 115, 23);
		resetList_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否清空打印清单?");
				if (result == 0) {
					truncateList(e);
				}
				AiHuaLabel labelPrintLog = new AiHuaLabel();
				try {
					fillTable(labelPrintLog);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnNewButton_1 = new JButton("删除选定记录");
		btnNewButton_1.setBounds(361, 226, 115, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int row = printList_table.getSelectedRow();
				if (row != -1) {
					int id = Integer.valueOf(printList_table.getValueAt(row, 0) + "");
					deleteListRow(evt, id);
				}
				AiHuaLabel labelPrintLog = new AiHuaLabel();
				try {
					fillTable(labelPrintLog);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		printList_table = new JTable();

		printList_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int Row = printList_table.getSelectedRow();
				if (Row != -1) {
					if (printLogSelFrm != null) {
						PrintLogSelFrm.PrintLog_table.clearSelection();
					}
					PrintBtn.setText("打印");
					number1_textField.setText(printList_table.getValueAt(Row, 1) + "");
					unit1_comboBox.setSelectedItem((String) printList_table.getValueAt(Row, 2));
					number2_textField.setText(printList_table.getValueAt(Row, 3) + "");
					unit2_comboBox.setSelectedItem((String) printList_table.getValueAt(Row, 4));
					net_weight_textField.setText((String) printList_table.getValueAt(Row, 5));
					gross_weight_textField.setText((String) printList_table.getValueAt(Row, 6));
					PO_textField.setText((String) printList_table.getValueAt(Row, 7));
					VendorCode_textField.setText((String) printList_table.getValueAt(Row, 8));
					Vendor_textField.setText((String) printList_table.getValueAt(Row, 9));
					Item_textField.setText((String) printList_table.getValueAt(Row, 10));
					Lot_No_textField.setText((String) printList_table.getValueAt(Row, 11));
					PN_textField.setText((String) printList_table.getValueAt(Row, 12));
					AISHINo_textField.setText((String) printList_table.getValueAt(Row, 13));
					PrintDate_textField.setText((String) printList_table.getValueAt(Row, 14));
					remake_textField.setText((String) printList_table.getValueAt(Row, 15));
					boxNum_textField.setText((String) printList_table.getValueAt(Row, 18));
					Material_Property_load(Row);
				}
			}
		});

		printList_table.getTableHeader().setReorderingAllowed(false);

		printList_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		printList_table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u7F16\u53F7", "\u6570\u91CF1", "\u5355\u4F4D1", "\u6570\u91CF2", "\u5355\u4F4D2",
						"\u51C0\u91CD", "\u6BDB\u91CD", "\u91C7\u8D2D\u8BA2\u5355\u53F7",
						"\u4F9B\u5E94\u5546\u4EE3\u7801", "\u4F9B\u5E94\u5546\u540D\u79F0", "\u578B\u53F7\u89C4\u683C",
						"\u4F9B\u5E94\u5546\u751F\u4EA7\u6279\u53F7", "\u827E\u534E\u6599\u53F7",
						"\u827E\u534E\u6279\u6B21\u53F7", "\u751F\u4EA7\u65E5\u671F", "REMARK",
						"\u6750\u6599\u540D\u79F0", "\u5B9E\u6D4B\u503C", "备注" }) {
			/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false, false, false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		printList_table.getColumnModel().getColumn(0).setPreferredWidth(43);
		printList_table.getColumnModel().getColumn(1).setPreferredWidth(50);
		printList_table.getColumnModel().getColumn(2).setPreferredWidth(50);
		printList_table.getColumnModel().getColumn(3).setPreferredWidth(50);
		printList_table.getColumnModel().getColumn(4).setPreferredWidth(50);
		printList_table.getColumnModel().getColumn(5).setPreferredWidth(50);
		printList_table.getColumnModel().getColumn(6).setPreferredWidth(50);
		printList_table.getColumnModel().getColumn(10).setPreferredWidth(180);
		printList_table.getColumnModel().getColumn(11).setPreferredWidth(140);
		printList_table.getColumnModel().getColumn(12).setPreferredWidth(120);
		printList_table.getColumnModel().getColumn(13).setPreferredWidth(120);
		printList_table.getColumnModel().getColumn(15).setPreferredWidth(120);
		outLabel_panel.setLayout(null);

		scrollPane.setViewportView(printList_table);
		outLabel_panel.add(scrollPane);
		outLabel_panel.add(insideBox_label);
		outLabel_panel.add(insideBox_textField);
		outLabel_panel.add(resetList_Button);
		outLabel_panel.add(btnNewButton_1);

		// 初始化打印清单
		AiHuaLabel labelPrintLog = new AiHuaLabel();
		this.fillTable(labelPrintLog);

		JLabel VendorCode_label = new JLabel("供应商代码：");
		VendorCode_label.setBounds(345, 28, 89, 19);
		VendorCode_textField = new JTextField();
		VendorCode_textField.setBounds(435, 28, 157, 21);
		VendorCode_textField.setColumns(10);
		Document VendorCode = VendorCode_textField.getDocument();
		VendorCode.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				vendorcode_label.setText(VendorCode_textField.getText());
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				vendorcode_label.setText(VendorCode_textField.getText());
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				vendorcode_label.setText(VendorCode_textField.getText());
				flashTable(e);
			}
		});

		JLabel Item_label = new JLabel("型号规格：");
		Item_label.setBounds(16, 59, 108, 19);
		Item_textField = new JTextField();
		Item_textField.setBounds(128, 58, 464, 21);
		Item_textField.setColumns(10);
		Document Item = Item_textField.getDocument();
		Item.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				item_label.setText(Item_textField.getText());
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				item_label.setText(Item_textField.getText());
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				item_label.setText(Item_textField.getText());
				flashTable(e);
			}
		});

		JLabel Vendor_label = new JLabel("供应商简称：");
		Vendor_label.setBounds(677, 28, 89, 19);
		Vendor_textField = new JTextField();
		Vendor_textField.setBounds(771, 28, 152, 21);
		Vendor_textField.setColumns(10);
		Document Vendor = Vendor_textField.getDocument();
		Vendor.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				vendor_label.setText(Vendor_textField.getText());
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				vendor_label.setText(Vendor_textField.getText());
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				vendor_label.setText(Vendor_textField.getText());
				flashTable(e);
			}
		});

		JLabel PrinteDate_label = new JLabel("生产日期：");
		PrinteDate_label.setBounds(677, 58, 72, 19);
		PrintDate_textField = new JTextField();
		PrintDate_textField.setBounds(771, 58, 152, 21);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy.MM.dd");
		dateChooser1.register(PrintDate_textField);
		PrintDate_textField.setColumns(10);
		Document PrintDate = PrintDate_textField.getDocument();
		PrintDate.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				pd_label.setText(PrintDate_textField.getText());
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				pd_label.setText(PrintDate_textField.getText());
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				pd_label.setText(PrintDate_textField.getText());
				flashTable(e);
			}
		});

		JLabel PO_label = new JLabel("采购订单号：");
		PO_label.setBounds(16, 28, 108, 19);
		PO_textField = new JTextField();
		PO_textField.setBounds(128, 28, 148, 21);
		PO_textField.setColumns(10);
		Document PO = PO_textField.getDocument();
		PO.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				po_label.setText(PO_textField.getText());
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				po_label.setText(PO_textField.getText());
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				po_label.setText(PO_textField.getText());
				flashTable(e);
			}
		});

		JLabel Lot_No_label = new JLabel("供应商生产批号：");
		Lot_No_label.setBounds(16, 88, 108, 19);
		Lot_No_textField = new JTextField();
		Lot_No_textField.setBounds(128, 88, 148, 21);
		Lot_No_textField.setColumns(10);
		Document Lot_No = Lot_No_textField.getDocument();
		Lot_No.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				lotno_label.setText(Lot_No_textField.getText());
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				lotno_label.setText(Lot_No_textField.getText());
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				lotno_label.setText(Lot_No_textField.getText());
				flashTable(e);
			}
		});

		JLabel AISHINo_label = new JLabel("艾华批次号：");
		AISHINo_label.setBounds(16, 148, 108, 19);
		AISHINo_textField = new JTextField();
		AISHINo_textField.setBounds(128, 148, 148, 21);
		AISHINo_textField.setColumns(10);
		Document AISHINo = AISHINo_textField.getDocument();
		AISHINo.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				an_label.setText(AISHINo_textField.getText());
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				an_label.setText(AISHINo_textField.getText());
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				an_label.setText(AISHINo_textField.getText());
				flashTable(e);
			}
		});
		AISHINo_textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String s = AISHINo_textField.getText();
				if (s.length() >= 10)
					e.consume();
			}
		});

		JLabel number1_label = new JLabel("数量1：");
		number1_label.setBounds(345, 88, 90, 19);
		number1_textField = new JTextField();
		number1_textField.setBounds(435, 88, 101, 21);
		number1_textField.setColumns(10);
		Document number1 = number1_textField.getDocument();
		number1.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number1_textField.getText())) {
					number2_textField.setText("");
					qty1unit1_label.setText("");
					qty2unit2_label.setText("");
				} else {
					qty1unit1_label.setText(number1_textField.getText() + unit1_comboBox.getSelectedItem());
				}
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number1_textField.getText())) {
					number2_textField.setText("");
					qty1unit1_label.setText("");
					qty2unit2_label.setText("");
				} else {
					qty1unit1_label.setText(number1_textField.getText() + unit1_comboBox.getSelectedItem());
				}
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number1_textField.getText())) {
					number2_textField.setText("");
					qty1unit1_label.setText("");
					qty2unit2_label.setText("");
				} else {
					qty1unit1_label.setText(number1_textField.getText() + unit1_comboBox.getSelectedItem());
				}
				flashTable(e);
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

		JLabel PN_label = new JLabel("艾华料号：");
		PN_label.setBounds(16, 118, 108, 19);
		PN_textField = new JTextField();
		PN_textField.setBounds(128, 118, 148, 21);
		PN_textField.setColumns(10);
		Document PN_ = PN_textField.getDocument();
		PN_.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (StringUtil.isNotEmpty(remake_textField.getText())) {
					pn_label.setText(PN_textField.getText() + '/' + remake_textField.getText());
				} else {
					pn_label.setText(PN_textField.getText());
				}
				PN = PN_textField.getText();
				if (StringUtil.isEmpty(PN_textField.getText())) {
					pn_label.setText("");
				}
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isNotEmpty(remake_textField.getText())) {
					pn_label.setText(PN_textField.getText() + '/' + remake_textField.getText());
				} else {
					pn_label.setText(PN_textField.getText());
				}
				PN = PN_textField.getText();
				if (StringUtil.isEmpty(PN_textField.getText())) {
					pn_label.setText("");
				}
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isNotEmpty(remake_textField.getText())) {
					pn_label.setText(PN_textField.getText() + '/' + remake_textField.getText());
				} else {
					pn_label.setText(PN_textField.getText());
				}
				PN = PN_textField.getText();
				if (StringUtil.isEmpty(PN_textField.getText())) {
					pn_label.setText("");
				}
				flashTable(e);
			}
		});
		PN_textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String s = PN_textField.getText();
				if (s.length() >= 12)
					e.consume();
			}
		});

		JLabel number2_label = new JLabel("数量2:");
		number2_label.setBounds(677, 88, 72, 19);
		number2_textField = new JTextField();
		number2_textField.setBounds(771, 88, 100, 21);
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
					qty2unit2_label.setText(number2_textField.getText() + unit2_comboBox.getSelectedItem());
				}
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number2_textField.getText())) {
					qty2unit2_label.setText("");
				} else {
					qty2unit2_label.setText(number2_textField.getText() + unit2_comboBox.getSelectedItem());
				}
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(number2_textField.getText())) {
					qty2unit2_label.setText("");
				} else {
					qty2unit2_label.setText(number2_textField.getText() + unit2_comboBox.getSelectedItem());
				}
				flashTable(e);
			}
		});

		JLabel net_weight_label = new JLabel("净重：");
		net_weight_label.setBounds(345, 118, 73, 19);
		net_weight_textField = new JTextField();
		net_weight_textField.setBounds(435, 118, 157, 21);
		net_weight_textField.setColumns(10);
		Document net_weight = net_weight_textField.getDocument();
		net_weight.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(net_weight_textField.getText())) {
					netweigth_label.setText("");
				} else {
					netweigth_label.setText("净重" + net_weight_textField.getText() + "KG");
				}
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(net_weight_textField.getText())) {
					netweigth_label.setText("");
				} else {
					netweigth_label.setText("净重" + net_weight_textField.getText() + "KG");
				}
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(net_weight_textField.getText())) {
					netweigth_label.setText("");
				} else {
					netweigth_label.setText("净重" + net_weight_textField.getText() + "KG");
				}
				flashTable(e);
			}
		});
		net_weight_textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String s = net_weight_textField.getText();
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

		JLabel gross_weight_label = new JLabel("毛重：");
		gross_weight_label.setBounds(677, 118, 72, 19);
		gross_weight_textField = new JTextField();
		gross_weight_textField.setBounds(771, 118, 152, 21);
		gross_weight_textField.setColumns(10);
		Document gross_weight = gross_weight_textField.getDocument();
		gross_weight.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(gross_weight_textField.getText())) {
					grossweith_label.setText("");
				} else {
					grossweith_label.setText("毛重" + gross_weight_textField.getText() + "KG");
				}
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(gross_weight_textField.getText())) {
					grossweith_label.setText("");
				} else {
					grossweith_label.setText("毛重" + gross_weight_textField.getText() + "KG");
				}
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(gross_weight_textField.getText())) {
					grossweith_label.setText("");
				} else {
					grossweith_label.setText("毛重" + gross_weight_textField.getText() + "KG");
				}
				flashTable(e);
			}
		});
		gross_weight_textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String s = gross_weight_textField.getText();
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

		unit1_comboBox = new JComboBox();
		unit1_comboBox.setBounds(546, 87, 46, 21);
		unit1_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isNotEmpty(number1_textField.getText())) {
					qty1unit1_label.setText(number1_textField.getText() + unit1_comboBox.getSelectedItem());
				}
			}
		});
		unit1_comboBox.setModel(new DefaultComboBoxModel(new String[] { "pc", "kpc", "kg", "m", "m2" }));

		unit2_comboBox = new JComboBox();
		unit2_comboBox.setBounds(877, 88, 46, 21);
		unit2_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isNotEmpty(number1_textField.getText())
						&& StringUtil.isNotEmpty(number2_textField.getText())) {
					qty2unit2_label.setText(number2_textField.getText() + unit2_comboBox.getSelectedItem());
				}
			}
		});
		unit2_comboBox.setModel(new DefaultComboBoxModel(new String[] { "m2" }));

		JButton button_1 = new JButton("重置");
		button_1.setBounds(499, 179, 116, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
				printList_table.clearSelection();
				if (PrintLogSelFrm.PrintLog_table != null) {
					PrintLogSelFrm.PrintLog_table.clearSelection();
				}
			}
		});

		JButton btnNewButton = new JButton("添加到清单");
		btnNewButton.setBounds(355, 179, 122, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputActionformed(e)) {
					AiHuaLabel InsidelabelPrintList = new AiHuaLabel();
					InsidelabelPrintList.setPO(PO_textField.getText());
					InsidelabelPrintList.setVendorCode(VendorCode_textField.getText());
					InsidelabelPrintList.setVendor(Vendor_textField.getText());
					InsidelabelPrintList.setItem(Item_textField.getText());
					InsidelabelPrintList.setLot_No(Lot_No_textField.getText());
					InsidelabelPrintList.setPN(PN_textField.getText());
					InsidelabelPrintList.setAISHINo(AISHINo_textField.getText());
					InsidelabelPrintList.setQty1(number1_textField.getText());
					InsidelabelPrintList.setQty2(number2_textField.getText());
					if (StringUtil.isEmpty(number2_textField.getText())) {
						InsidelabelPrintList.setUnit2("");
					} else {
						InsidelabelPrintList.setUnit2(unit2_comboBox.getSelectedItem().toString());
					}
					InsidelabelPrintList.setUnit1(unit1_comboBox.getSelectedItem().toString());
					InsidelabelPrintList.setProductionDate(PrintDate_textField.getText());
					InsidelabelPrintList.setNetWeight(net_weight_textField.getText());
					InsidelabelPrintList.setGrossWeight(gross_weight_textField.getText());
					InsidelabelPrintList.setBoxType(0);
					InsidelabelPrintList.setRemarks(remake_textField.getText());
					InsidelabelPrintList.setSerialNumber(boxNum_textField.getText());

					LabelDao labelPrintLogDao = new LabelDao();
					int flag = labelPrintLogDao.insertList(InsidelabelPrintList);
					if (flag != 1) {
						JOptionPane.showMessageDialog(null, "内箱打印数据存储失败！");
					}
					try {
						fillTable(InsidelabelPrintList);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		JLabel label = new JLabel("REMARK:");
		label.setBounds(345, 148, 89, 15);

		remake_textField = new JTextField();
		remake_textField.setBounds(435, 148, 157, 21);
		remake_textField.setColumns(10);
		Document remake = remake_textField.getDocument();
		remake_textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (StringUtil.isEmpty(PN_textField.getText())) {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});
		remake.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				REMARK = remake_textField.getText();
				pn_label.setText(PN + '/' + REMARK);
				if (StringUtil.isEmpty(remake_textField.getText())) {
					pn_label.setText(PN);
				}
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				REMARK = remake_textField.getText();
				pn_label.setText(PN + '/' + REMARK);
				if (StringUtil.isEmpty(remake_textField.getText())) {
					pn_label.setText(PN);
				}
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				REMARK = remake_textField.getText();
				pn_label.setText(PN + '/' + REMARK);
				if (StringUtil.isEmpty(remake_textField.getText())) {
					pn_label.setText(PN);
				}
				flashTable(e);
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(PrintBtn);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.add(Lot_No_label);
		panel.add(Item_label);
		panel.add(PO_label);
		panel.add(PN_label);
		panel.add(AISHINo_label);
		panel.add(label);
		panel.add(PO_textField);
		panel.add(VendorCode_label);
		panel.add(remake_textField);
		panel.add(PN_textField);
		panel.add(Lot_No_textField);
		panel.add(AISHINo_textField);
		panel.add(net_weight_label);
		panel.add(number1_label);
		panel.add(net_weight_textField);
		panel.add(number1_textField);
		panel.add(unit1_comboBox);
		panel.add(VendorCode_textField);
		panel.add(btnNewButton);
		panel.add(Item_textField);
		panel.add(number2_label);
		panel.add(gross_weight_label);
		panel.add(number2_textField);
		panel.add(unit2_comboBox);
		panel.add(gross_weight_textField);
		panel.add(PrinteDate_label);
		panel.add(Vendor_label);
		panel.add(PrintDate_textField);
		panel.add(Vendor_textField);
		panel.add(button_1);

		JLabel boxNum_label = new JLabel("备注：");
		boxNum_label.setBounds(677, 148, 72, 19);
		panel.add(boxNum_label);

		boxNum_textField = new JTextField();
		boxNum_textField.setColumns(10);
		boxNum_textField.setBounds(771, 148, 152, 21);
		panel.add(boxNum_textField);
		Document boxNumm = boxNum_textField.getDocument();
		boxNumm.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(boxNum_textField.getText())) {
					TagboxNum_label.setText("");
				} else {
					try {
						JLabelUtil.JlabelSetText(TagboxNum_label, boxNum_textField.getText());
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(boxNum_textField.getText())) {
					TagboxNum_label.setText("");
				} else {
					try {
						JLabelUtil.JlabelSetText(TagboxNum_label, boxNum_textField.getText());
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				if (StringUtil.isEmpty(boxNum_textField.getText())) {
					TagboxNum_label.setText("");
				} else {
					try {
						JLabelUtil.JlabelSetText(TagboxNum_label, boxNum_textField.getText());
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				flashTable(e);
			}
		});
		getContentPane().add(outLabel_panel);

		excelInput_Button = new JButton("Excel导入");
		excelInput_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PrintListExcelImport();
				try {
					fillTable(null);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		excelInput_Button.setBounds(238, 226, 113, 23);
		outLabel_panel.add(excelInput_Button);
		getContentPane().add(insideLabel_panel);

		material_panel = new JPanel();
		material_panel.setBorder(new TitledBorder(null, "请选择材料", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		material_panel.setBounds(15, 232, 980, 137);
		getContentPane().add(material_panel);
		material_panel.setLayout(null);

		material_comboBox = new JComboBox();
		material_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectProperty(e);
				flashTable(e);
			}
		});
		material_comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "请选择材料", "正箔", "负箔", "电解纸", "正导针", "负导针", "胶带", "基座", "热封上带", "载带" }));
		material_comboBox.setBounds(10, 26, 108, 21);
		material_panel.add(material_comboBox);

		property4_lbl = new JLabel("Y");
		property4_lbl.setVisible(false);
		property4_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		property4_lbl.setBounds(775, 26, 76, 15);
		material_panel.add(property4_lbl);

		property4_textField = new JTextField();
		property4_textField.setVisible(false);
		property4_textField.setColumns(10);
		property4_textField.setBounds(850, 26, 46, 21);
		material_panel.add(property4_textField);
		Document property4 = property4_textField.getDocument();
		property4.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});

		property4_comboBox = new JComboBox();
		property4_comboBox.setVisible(false);
		property4_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm" }));
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
		Document property1 = property1_textField.getDocument();
		property1.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});

		property1_comboBox = new JComboBox();
		property1_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm", "um", "μF/cm²", "g/cm³", "m" }));
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
		property2_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm", "um", "m" }));
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
		property3_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm", "m" }));
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
		property5_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm", "无碳化", "碳化" }));
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
		property6_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm" }));
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
		property7_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm" }));
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
		property9_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm" }));
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
		property10_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm" }));
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
		property11_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm" }));
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
		property8_comboBox.setModel(new DefaultComboBoxModel(new String[] { "mm" }));
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

		updateList = new JButton("更新到打印清单");
		updateList.setVisible(false);
		updateList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkMaterialPropertyInput(e)) {
					updateMaterialPropertyToList(e);
				} else {
					JOptionPane.showMessageDialog(null, "确保所有属性输入不为空！");
				}
			}
		});
		updateList.setBounds(808, 102, 162, 23);
		material_panel.add(updateList);

		JRadioButton SavaDataRadioButton = new JRadioButton("仅保存打印记录");
		SavaDataRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton temp = (JRadioButton) e.getSource();
				if (temp.isSelected()) {
					printStatus = 0;
				} else {
					printStatus = 1;
				}
			}
		});

		SavaDataRadioButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		SavaDataRadioButton.setBounds(747, 661, 121, 23);
		getContentPane().add(SavaDataRadioButton);

		Document property2 = property2_textField.getDocument();
		property2.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});

		Document property3 = property3_textField.getDocument();
		property3.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});

		Document property5 = property5_textField.getDocument();
		property5.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});

		Document property6 = property6_textField.getDocument();
		property6.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});

		Document property7 = property7_textField.getDocument();
		property7.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});

		Document property8 = property8_textField.getDocument();
		property8.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});

		Document property9 = property9_textField.getDocument();
		property9.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});

		Document property10 = property10_textField.getDocument();
		property10.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});

		Document property11 = property11_textField.getDocument();
		property11.addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void removeUpdate(DocumentEvent e) {
				flashTable(e);
			}

			public void changedUpdate(DocumentEvent e) {
				flashTable(e);
			}
		});
		property1_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashTable(e);
			}
		});

		property2_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashTable(e);
			}
		});

		property3_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashTable(e);
			}
		});

		property4_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashTable(e);
			}
		});

		property5_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (material_comboBox.getSelectedItem() == "电解纸") {
					property5_textField.setText((String) property5_comboBox.getSelectedItem());
				}
				flashTable(e);
			}
		});
		property6_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashTable(e);
			}
		});
		property7_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashTable(e);
			}
		});
		property8_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashTable(e);
			}
		});
		property9_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashTable(e);
			}
		});
		property10_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashTable(e);
			}
		});
		property11_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flashTable(e);
			}
		});

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("菜单");
		menuBar.add(mnNewMenu);

		JMenuItem menuItem = new JMenuItem("打印记录");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printLogSelFrm = new PrintLogSelFrm();
				printLogSelFrm.setVisible(true);
			}
		});
		mnNewMenu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("退出");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否退出?");
				if (result == 0) {
					dispose();
					System.exit(1);
				}
			}
		});

		JMenuItem mntmNewMenuItem = new JMenuItem("字体调整");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FontSetFrm fontSetFrm = new FontSetFrm(This);
				fontSetFrm.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("清空打印记录");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否清空内外箱打印记录?");
				if (result == 0) {
					LabelDao labelPrintLogDao = new LabelDao();
					labelPrintLogDao.truncatePrintLog();
				}
				if (printLogSelFrm != null) {//清空后进行打印记录刷新
					AiHuaLabel labelPrintLog = new AiHuaLabel();
					try {
						if (PrintLogSelFrm.BoxType_comboBox.getSelectedIndex() == 0) {
							labelPrintLog.setBoxType(0);
						} else if (PrintLogSelFrm.BoxType_comboBox.getSelectedIndex() == 1) {
							labelPrintLog.setBoxType(1);
						}
						PrintLogSelFrm.fillTable(labelPrintLog);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		mnNewMenu.add(menuItem_1);

		JMenu menu = new JMenu("帮助");
		menu.setEnabled(true);
		menuBar.add(menu);

		JMenuItem menuItem_2 = new JMenuItem("操作说明");
		menuItem_2.setEnabled(false);
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// HelpInterFrm help = new HelpInterFrm();
				// help.setVisible(true);
			}
		});

		JMenuItem menuItem_3 = new JMenuItem("检查新版本");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!UpgraderUtil.autoupgrade(This)) {
					JOptionPane.showMessageDialog(null, "暂无新版本!");
				}
			}
		});
		menu.add(menuItem_3);

		JMenuItem menuItem_4 = new JMenuItem("相关文件下载");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SourceDownloadFrm SourceDownloadFrm = new SourceDownloadFrm(This);
				SourceDownloadFrm.setVisible(true);
				This.setEnabled(false);
			}
		});
		menu.add(menuItem_4);
		menu.add(menuItem_2);
		// 设置居中显示
		this.setLocationRelativeTo(null);
	}

	/**
	 * 追加实测值时，检查实测值的输入
	 * 
	 * @param e
	 * @return
	 */
	protected Boolean checkMaterialPropertyInput(ActionEvent e) {
		// TODO Auto-generated method stub
		if (material_comboBox.getSelectedItem() == "正箔" || material_comboBox.getSelectedItem() == "负箔") {
			if (StringUtil.isEmpty(property1_textField.getText()) || StringUtil.isEmpty(property2_textField.getText())
					|| StringUtil.isEmpty(property3_textField.getText())
					|| StringUtil.isEmpty(property4_textField.getText())) {
				return false;
			} else {
				return true;
			}
		} else if (material_comboBox.getSelectedItem() == "电解纸") {
			if (StringUtil.isEmpty(property1_textField.getText()) || StringUtil.isEmpty(property2_textField.getText())
					|| StringUtil.isEmpty(property3_textField.getText())
					|| StringUtil.isEmpty(property4_textField.getText())
					|| StringUtil.isEmpty(property5_textField.getText())) {
				return false;
			} else {
				return true;
			}
		} else if (material_comboBox.getSelectedItem() == "正导针" || material_comboBox.getSelectedItem() == "负导针") {
			if (StringUtil.isEmpty(property1_textField.getText()) || StringUtil.isEmpty(property2_textField.getText())
					|| StringUtil.isEmpty(property3_textField.getText())
					|| StringUtil.isEmpty(property4_textField.getText())
					|| StringUtil.isEmpty(property5_textField.getText())
					|| StringUtil.isEmpty(property6_textField.getText())
					|| StringUtil.isEmpty(property7_textField.getText())
					|| StringUtil.isEmpty(property8_textField.getText())
					|| StringUtil.isEmpty(property9_textField.getText())
					|| StringUtil.isEmpty(property10_textField.getText())
					|| StringUtil.isEmpty(property11_textField.getText())) {
				return false;
			} else {
				return true;
			}
		} else if (material_comboBox.getSelectedItem() == "胶带") {
			if (StringUtil.isEmpty(property1_textField.getText()) || StringUtil.isEmpty(property2_textField.getText())
					|| StringUtil.isEmpty(property3_textField.getText())) {
				return false;
			} else {
				return true;
			}
		} else if (material_comboBox.getSelectedItem() == "基座" || material_comboBox.getSelectedItem() == "热封上带") {
			if (StringUtil.isEmpty(property1_textField.getText())) {
				return false;
			} else {
				return true;
			}
		} else if (material_comboBox.getSelectedItem() == "载带") {
			if (StringUtil.isEmpty(property1_textField.getText()) || StringUtil.isEmpty(property2_textField.getText())
					|| StringUtil.isEmpty(property3_textField.getText())
					|| StringUtil.isEmpty(property4_textField.getText())
					|| StringUtil.isEmpty(property5_textField.getText())
					|| StringUtil.isEmpty(property6_textField.getText())
					|| StringUtil.isEmpty(property7_textField.getText())
					|| StringUtil.isEmpty(property8_textField.getText())
					|| StringUtil.isEmpty(property9_textField.getText())) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	/**
	 * 将材料及属性值追加到选定的打印清单
	 * 
	 * @param e
	 */
	protected void updateMaterialPropertyToList(ActionEvent e) {
		// TODO Auto-generated method stub
		StringBuffer MaterialProperty = GetMaterialPropertyToTable();
		AiHuaLabel InsidelabelPrintList = new AiHuaLabel();
		if (material_comboBox.getSelectedItem().toString() == "请选择材料") {
			InsidelabelPrintList.setMaterialName("");
		} else {
			InsidelabelPrintList.setMaterialName(material_comboBox.getSelectedItem().toString());
		}
		InsidelabelPrintList.setMaterial_Property(MaterialProperty.toString());
		LabelDao labelPrintLogDao = new LabelDao();
		if (printList_table.getSelectedRows().length != 0) {
			
			int[] rows = printList_table.getSelectedRows();
			
			for(int i = 0 ;i<rows.length;i++){
				int flag = labelPrintLogDao.updateToList(InsidelabelPrintList,
						Integer.valueOf((String) printList_table.getValueAt(rows[i], 0)));
				if (flag != 1) {
					JOptionPane.showMessageDialog(null, "打印清单数据更新失败！");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "请在打印清单中选择需要覆盖的记录！");
		}

		try {
			fillTable(InsidelabelPrintList);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 点击表格的行后将属性值加载到输入区
	 * 
	 * @param row
	 */
	protected void Material_Property_load(int Row) {

		if (StringUtil.isEmpty((String) printList_table.getValueAt(Row, 16))
				|| "".equals((String) printList_table.getValueAt(Row, 16))) {
			material_comboBox.setSelectedItem("请选择材料");
		} else {
			material_comboBox.setSelectedItem((String) printList_table.getValueAt(Row, 16));
		}
		if ("正箔".equals(((String) printList_table.getValueAt(Row, 16)))
				&& !"".equals(printList_table.getValueAt(Row, 17).toString())) {// 只能用equqls
			property1_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[1]);
			property2_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[3]);
			property3_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[5]);
			property4_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[7]);
		} else if ("负箔".equals(((String) printList_table.getValueAt(Row, 16)))
				&& !"".equals(printList_table.getValueAt(Row, 17).toString())) {
			property1_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[1]);
			property2_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[3]);
			property3_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[5]);
			property4_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[7]);
		} else if ("电解纸".equals(((String) printList_table.getValueAt(Row, 16)))
				&& !"".equals(printList_table.getValueAt(Row, 17).toString())) {
			property1_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[1]);
			property2_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[3]);
			property3_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[5]);
			property4_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[7]);
			property5_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[8]);
			property5_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[8]);
		} else if ("正导针".equals(((String) printList_table.getValueAt(Row, 16)))
				&& !"".equals(printList_table.getValueAt(Row, 17).toString())) {
			property1_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[1]);
			property2_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[3]);
			property3_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[5]);
			property4_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[7]);
			property5_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[8]);
			property5_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[9]);
			property6_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[10]);
			property6_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[11]);
			property7_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[12]);
			property7_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[13]);
			property8_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[14]);
			property8_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[15]);
			property9_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[16]);
			property9_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[17]);
			property10_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[18]);
			property10_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[19]);
			property11_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[20]);
			property11_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[21]);
		} else if ("负导针".equals(((String) printList_table.getValueAt(Row, 16)))
				&& !"".equals(printList_table.getValueAt(Row, 17).toString())) {
			property1_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[1]);
			property2_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[3]);
			property3_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[5]);
			property4_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[7]);
			property5_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[8]);
			property5_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[9]);
			property6_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[10]);
			property6_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[11]);
			property7_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[12]);
			property7_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[13]);
			property8_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[14]);
			property8_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[15]);
			property9_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[16]);
			property9_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[17]);
			property10_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[18]);
			property10_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[19]);
			property11_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[20]);
			property11_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[21]);
		} else if ("胶带".equals(((String) printList_table.getValueAt(Row, 16)))
				&& !"".equals(printList_table.getValueAt(Row, 17).toString())) {
			property1_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[1]);
			property2_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[3]);
			property3_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[5]);
		} else if ("基座".equals(((String) printList_table.getValueAt(Row, 16)))
				&& !"".equals(printList_table.getValueAt(Row, 17).toString())) {
			property1_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[1]);
		} else if ("热封上带".equals(((String) printList_table.getValueAt(Row, 16)))
				&& !"".equals(printList_table.getValueAt(Row, 17).toString())) {
			property1_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[1]);
		} else if ("载带".equals(((String) printList_table.getValueAt(Row, 16)))
				&& !"".equals(printList_table.getValueAt(Row, 17).toString())) {
			property1_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[0]);
			property1_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[1]);
			property2_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[2]);
			property2_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[3]);
			property3_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[4]);
			property3_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[5]);
			property4_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[6]);
			property4_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[7]);
			property5_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[8]);
			property5_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[9]);
			property6_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[10]);
			property6_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[11]);
			property7_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[12]);
			property7_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[13]);
			property8_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[14]);
			property8_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[15]);
			property9_textField.setText(printList_table.getValueAt(Row, 17).toString().split("\\|")[16]);
			property9_comboBox.setSelectedItem(printList_table.getValueAt(Row, 17).toString().split("\\|")[17]);
		}

	}

	/**
	 * 更新二维码
	 * 
	 * @param e
	 */
	protected void flashTable(ActionEvent e) {
		// 材料名
		String MaterialName = material_comboBox.getSelectedItem().toString();
		if (material_comboBox.getSelectedItem() == "请选择材料") {
			MaterialName = "";
		}
		// 实测值
		StringBuffer Material_Property = GetMaterialProperty();

		BufferedImage inside_bi = new BufferedImage(140, 120, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 120; i++)
			for (int j = 0; j < 140; j++)
				inside_bi.setRGB(j, i, Color.WHITE.getRGB());

		Graphics2D inside_gs = inside_bi.createGraphics();
		inside_gs.setColor(Color.BLACK);

		Qrcode inside_qrcode = new Qrcode();
		inside_qrcode.setQrcodeEncodeMode('B');// 存储英文
		inside_qrcode.setQrcodeVersion(10);

		// 二维码生成规则
		String inside_code = PO_textField.getText().trim() + '|' + VendorCode_textField.getText().trim() + '|'
				+ PN_textField.getText().trim() + '|' + Lot_No_textField.getText().trim() + '|'
				+ remake_textField.getText() + '|' + AISHINo_textField.getText().trim() + '|'
				+ PrintDate_textField.getText().trim() + '|' + number1_textField.getText().trim() + '|'
				+ number2_textField.getText().trim() + '|' + net_weight_textField.getText() + '|'
				+ gross_weight_textField.getText() + '|' + MaterialName + '|' + Material_Property;
		try {
			boolean[][] inside_rest = inside_qrcode.calQrcode(inside_code.getBytes("UTF-8"));
			for (int i = 0; i < inside_rest.length; i++) {
				for (int j = 0; j < inside_rest.length; j++) {
					if (inside_rest[j][i])
						inside_gs.fillRect(j * 2, i * 2, 2, 2);
				}
			}

			imagePannel.setBufferedImage(inside_bi);
			imagePannel.repaint();
		} catch (HeadlessException | UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * 获取材料的实测值to二维码
	 */
	protected StringBuffer GetMaterialProperty() {
		StringBuffer result = new StringBuffer("");
		if (StringUtil.isNotEmpty(property1_textField.getText())) {
			result.append(property1_textField.getText() + property1_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property2_textField.getText())) {
			result.append("|" + property2_textField.getText() + property2_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property3_textField.getText())) {
			result.append("|" + property3_textField.getText() + property3_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property4_textField.getText())) {
			result.append("|" + property4_textField.getText() + property4_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property5_textField.getText()) && material_comboBox.getSelectedItem() != "电解纸") {
			result.append("|" + property5_textField.getText() + property5_comboBox.getSelectedItem());
		} else if (material_comboBox.getSelectedItem() == "电解纸") {
			result.append("|" + property5_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property6_textField.getText())) {
			result.append("|" + property6_textField.getText() + property6_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property7_textField.getText())) {
			result.append("|" + property7_textField.getText() + property7_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property8_textField.getText())) {
			result.append("|" + property8_textField.getText() + property8_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property9_textField.getText())) {
			result.append("|" + property9_textField.getText() + property9_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property10_textField.getText())) {
			result.append("|" + property10_textField.getText() + property10_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property11_textField.getText())) {
			result.append("|" + property11_textField.getText() + property11_comboBox.getSelectedItem());
		}
		return result;
	}

	/*
	 * 获取材料的实测值to存储
	 */
	protected StringBuffer GetMaterialPropertyToTable() {
		StringBuffer result = new StringBuffer("");
		if (StringUtil.isNotEmpty(property1_textField.getText())) {
			result.append(property1_textField.getText() + "|" + property1_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property2_textField.getText())) {
			result.append("|" + property2_textField.getText() + "|" + property2_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property3_textField.getText())) {
			result.append("|" + property3_textField.getText() + "|" + property3_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property4_textField.getText())) {
			result.append("|" + property4_textField.getText() + "|" + property4_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property5_textField.getText()) && material_comboBox.getSelectedItem() != "电解纸") {
			result.append("|" + property5_textField.getText() + '|' + property5_comboBox.getSelectedItem());
		} else if (material_comboBox.getSelectedItem() == "电解纸") {
			result.append("|" + property5_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property6_textField.getText())) {
			result.append("|" + property6_textField.getText() + "|" + property6_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property7_textField.getText())) {
			result.append("|" + property7_textField.getText() + "|" + property7_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property8_textField.getText())) {
			result.append("|" + property8_textField.getText() + "|" + property8_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property9_textField.getText())) {
			result.append("|" + property9_textField.getText() + "|" + property9_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property10_textField.getText())) {
			result.append("|" + property10_textField.getText() + "|" + property10_comboBox.getSelectedItem());
		}
		if (StringUtil.isNotEmpty(property11_textField.getText())) {
			result.append("|" + property11_textField.getText() + "|" + property11_comboBox.getSelectedItem());
		}
		return result;
	}

	/**
	 * 选择材料
	 * 
	 * @param e
	 */
	protected void SelectProperty(ActionEvent e) {
		if (material_comboBox.getSelectedItem() == "正箔") {
			material_panel
					.setBorder(new TitledBorder(null, "正箔实测值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			property1_lbl.setText("正箔比容");
			updateList.setVisible(true);
			;
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

		} else if (material_comboBox.getSelectedItem() == "负箔") {
			material_panel
					.setBorder(new TitledBorder(null, "负箔实测值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			property1_lbl.setText("负箔比容");
			updateList.setVisible(true);
			;
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
		} else if (material_comboBox.getSelectedItem() == "电解纸") {
			material_panel
					.setBorder(new TitledBorder(null, "电解纸实测值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			property1_lbl.setText("电解纸密度");
			property1_textField.setText("");
			updateList.setVisible(true);
			;
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
		} else if (material_comboBox.getSelectedItem() == "正导针") {
			material_panel
					.setBorder(new TitledBorder(null, "正导针实测值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			property1_lbl.setText("A");
			updateList.setVisible(true);
			;
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
		} else if (material_comboBox.getSelectedItem() == "负导针") {
			material_panel
					.setBorder(new TitledBorder(null, "负导针实测值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			property1_lbl.setText("A");
			updateList.setVisible(true);
			;
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
		} else if (material_comboBox.getSelectedItem() == "胶带") {
			material_panel
					.setBorder(new TitledBorder(null, "胶带实测值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			property1_lbl.setText("胶带厚度");
			property1_textField.setText("");
			updateList.setVisible(true);
			;
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
		} else if (material_comboBox.getSelectedItem() == "基座") {
			material_panel
					.setBorder(new TitledBorder(null, "基座实测值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			property1_lbl.setText("基座厚度");
			property1_textField.setText("");
			updateList.setVisible(true);
			;
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
		} else if (material_comboBox.getSelectedItem() == "热封上带") {
			material_panel
					.setBorder(new TitledBorder(null, "热封上带实测值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			property1_lbl.setText("热封上带长度");
			updateList.setVisible(true);
			;
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
		} else if (material_comboBox.getSelectedItem() == "载带") {
			material_panel
					.setBorder(new TitledBorder(null, "载带实测值", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			property1_lbl.setText("载带长度");
			updateList.setVisible(true);
			;
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
		} else if (material_comboBox.getSelectedItem() == "请选择材料") {
			material_panel
					.setBorder(new TitledBorder(null, "请选择材料", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			property1_lbl.setText("");
			updateList.setVisible(false);
			;
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

	/**
	 * 更新二维码
	 * 
	 * @param e
	 */
	protected void flashTable(DocumentEvent e) {
		// 材料名
		String MaterialName = material_comboBox.getSelectedItem().toString();
		if (material_comboBox.getSelectedItem() == "请选择材料") {
			MaterialName = "";
		}
		// 实测值
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
		String inside_code = PO_textField.getText().trim() + '|' + VendorCode_textField.getText().trim() + '|'
				+ PN_textField.getText().trim() + '|' + Lot_No_textField.getText().trim() + '|'
				+ remake_textField.getText() + '|' + AISHINo_textField.getText().trim() + '|'
				+ PrintDate_textField.getText().trim() + '|' + number1_textField.getText().trim() + '|'
				+ number2_textField.getText().trim() + '|' + net_weight_textField.getText() + '|'
				+ gross_weight_textField.getText() + '|' + MaterialName + '|' + Material_Property;
		try {
			boolean[][] inside_rest = inside_qrcode.calQrcode(inside_code.getBytes("UTF-8"));
			for (int i = 0; i < inside_rest.length; i++) {
				for (int j = 0; j < inside_rest.length; j++) {
					if (inside_rest[j][i])
						inside_gs.fillRect(j * 2, i * 2, 2, 2);
				}
			}

			imagePannel.setBufferedImage(inside_bi);
			imagePannel.repaint();
		} catch (HeadlessException | UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 重置打印输入信息，重置标签预览
	 * 
	 * @param e
	 */
	protected void resetValueActionPerformed(ActionEvent e) {
		LabelPrintFrm.PO_textField.setText("");
		LabelPrintFrm.VendorCode_textField.setText("");
		LabelPrintFrm.AISHINo_textField.setText("");
		LabelPrintFrm.PN_textField.setText("");
		LabelPrintFrm.Vendor_textField.setText("");
		LabelPrintFrm.Lot_No_textField.setText("");
		LabelPrintFrm.Item_textField.setText("");
		LabelPrintFrm.number1_textField.setText("");
		LabelPrintFrm.number2_textField.setText("");
		LabelPrintFrm.unit1_comboBox.setSelectedIndex(0);
		LabelPrintFrm.unit2_comboBox.setSelectedIndex(0);
		LabelPrintFrm.net_weight_textField.setText("");
		LabelPrintFrm.gross_weight_textField.setText("");
		LabelPrintFrm.PrintDate_textField.setText("");
		LabelPrintFrm.remake_textField.setText("");
		this.qty1unit1_label.setText("");
		this.qty2unit2_label.setText("");
		this.netweigth_label.setText("");
		this.grossweith_label.setText("");
		this.boxNum_textField.setText("");
	}

	/**
	 * 删除选定打印清单
	 * 
	 * @param id
	 */
	protected void deleteListRow(ActionEvent e, int id) {
		LabelDao labelPrintLogDao = new LabelDao();
		int flag = labelPrintLogDao.deleteListRow(id);
		if (flag == 1) {
			JOptionPane.showMessageDialog(null, "删除成功！");
		}
	}

	/**
	 * 清空打印清单
	 * 
	 * @param e
	 */
	protected void truncateList(ActionEvent e) {
		LabelDao labelPrintLogDao = new LabelDao();
		labelPrintLogDao.truncateList();
	}

	/**
	 * 打印前，所有的标签内容完整性检查
	 * 
	 * @param e
	 */
	private Boolean inputActionformed(ActionEvent e) {

		if (StringUtil.isEmpty(LabelPrintFrm.PO_textField.getText())) {
			JOptionPane.showMessageDialog(null, "采购订单号不能为空！");
			return false;
		} else if (StringUtil.isEmpty(LabelPrintFrm.VendorCode_textField.getText())) {
			JOptionPane.showMessageDialog(null, "供应商代码不能为空！");
			return false;
		} else if (StringUtil.isEmpty(LabelPrintFrm.Vendor_textField.getText())) {
			JOptionPane.showMessageDialog(null, "供应商简称不能为空！");
			return false;
		} else if (StringUtil.isEmpty(LabelPrintFrm.number1_textField.getText())) {
			JOptionPane.showMessageDialog(null, "单箱数量不能为空！");
			return false;
		} else if (StringUtil.isEmpty(LabelPrintFrm.Item_textField.getText())) {
			JOptionPane.showMessageDialog(null, "型号规格不能为空！");
			return false;
		} else if (StringUtil.isEmpty(LabelPrintFrm.Lot_No_textField.getText())) {
			JOptionPane.showMessageDialog(null, "供应商生产批次号不能为空！");
			return false;
		} else if (StringUtil.isEmpty(LabelPrintFrm.PN_textField.getText())) {
			JOptionPane.showMessageDialog(null, "艾华料号不能为空！");
			return false;
		} else if (StringUtil.isEmpty(LabelPrintFrm.AISHINo_textField.getText())) {
			JOptionPane.showMessageDialog(null, "艾华批次号不能为空！");
			return false;
		} else if (StringUtil.isEmpty(LabelPrintFrm.PrintDate_textField.getText())) {
			JOptionPane.showMessageDialog(null, "生产日期不能为空！");
			return false;
		} else if (material_comboBox.getSelectedItem() == "正箔" || material_comboBox.getSelectedItem() == "负箔") {
			if (StringUtil.isEmpty(property1_textField.getText()) || StringUtil.isEmpty(property2_textField.getText())
					|| StringUtil.isEmpty(property3_textField.getText())
					|| StringUtil.isEmpty(property4_textField.getText())) {
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem() + "实测值不能为空！");
				return false;
			} else {
				return true;
			}
		} else if (material_comboBox.getSelectedItem() == "电解纸") {
			if (StringUtil.isEmpty(property1_textField.getText()) || StringUtil.isEmpty(property2_textField.getText())
					|| StringUtil.isEmpty(property3_textField.getText())
					|| StringUtil.isEmpty(property4_textField.getText())
					|| StringUtil.isEmpty(property5_textField.getText())) {
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem() + "实测值不能为空！");
				return false;
			} else {
				return true;
			}
		} else if (material_comboBox.getSelectedItem() == "正导针" || material_comboBox.getSelectedItem() == "负导针") {
			if (StringUtil.isEmpty(property1_textField.getText()) || StringUtil.isEmpty(property2_textField.getText())
					|| StringUtil.isEmpty(property3_textField.getText())
					|| StringUtil.isEmpty(property4_textField.getText())
					|| StringUtil.isEmpty(property5_textField.getText())
					|| StringUtil.isEmpty(property6_textField.getText())
					|| StringUtil.isEmpty(property7_textField.getText())
					|| StringUtil.isEmpty(property8_textField.getText())
					|| StringUtil.isEmpty(property9_textField.getText())
					|| StringUtil.isEmpty(property10_textField.getText())
					|| StringUtil.isEmpty(property11_textField.getText())) {
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem() + "实测值不能为空！");
				return false;
			} else {
				return true;
			}
		} else if (material_comboBox.getSelectedItem() == "胶带") {
			if (StringUtil.isEmpty(property1_textField.getText()) || StringUtil.isEmpty(property2_textField.getText())
					|| StringUtil.isEmpty(property3_textField.getText())) {
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem() + "实测值不能为空！");
				return false;
			} else {
				return true;
			}
		} else if (material_comboBox.getSelectedItem() == "基座" || material_comboBox.getSelectedItem() == "热封上带") {
			if (StringUtil.isEmpty(property1_textField.getText())) {
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem() + "实测值不能为空！");
				return false;
			} else {
				return true;
			}
		} else if (material_comboBox.getSelectedItem() == "载带") {
			if (StringUtil.isEmpty(property1_textField.getText()) || StringUtil.isEmpty(property2_textField.getText())
					|| StringUtil.isEmpty(property3_textField.getText())
					|| StringUtil.isEmpty(property4_textField.getText())
					|| StringUtil.isEmpty(property5_textField.getText())
					|| StringUtil.isEmpty(property6_textField.getText())
					|| StringUtil.isEmpty(property7_textField.getText())
					|| StringUtil.isEmpty(property8_textField.getText())
					|| StringUtil.isEmpty(property9_textField.getText())) {
				JOptionPane.showMessageDialog(null, material_comboBox.getSelectedItem() + "实测值不能为空！");
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	/**
	 * 初始化表格数据
	 * 
	 * @param BoxType
	 * @throws SQLException
	 */
	private void fillTable(AiHuaLabel labelPrintLog) throws SQLException {
		LabelDao labelPrintLogDao = new LabelDao();
		DefaultTableModel dtm = (DefaultTableModel) printList_table.getModel();
		dtm.setRowCount(0);
		ResultSet rs = labelPrintLogDao.listSel(labelPrintLog);
		int count = 0;
		try {
		while (rs.next()) {
			Vector<String> v = new Vector<String>();
			v.add(rs.getString("n_Id"));
			v.add(rs.getString("Qty1"));
			v.add(rs.getString("Unit1"));
			if (rs.getString("Qty2") == null) {
				v.add("");
				v.add("");
			} else {
				v.add(rs.getString("Qty2"));
				v.add(rs.getString("Unit2"));
			}
			v.add(rs.getString("net_weight"));
			v.add(rs.getString("gross_weight"));
			v.add(rs.getString("PO"));
			v.add(rs.getString("VendorCode"));
			v.add(rs.getString("Vendor"));
			v.add(rs.getString("Item"));
			v.add(rs.getString("Lot_No"));
			v.add(rs.getString("PN"));
			v.add(rs.getString("AISHINo"));
			v.add(rs.getString("production_date"));
			v.add(rs.getString("remarks"));
			v.add(rs.getString("MaterialName"));
			v.add(rs.getString("Material_Property"));
			v.add(rs.getString("SerialNumber"));
			dtm.addRow(v);
			count++;
		}
		}catch(Exception e){
			LogUtil.exception(e);
		}
		LabelPrintFrm.insideBox_textField.setText(count + "");
	}
}
