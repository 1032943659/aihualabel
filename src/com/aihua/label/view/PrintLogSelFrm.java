package com.aihua.label.view;

import java.awt.FileDialog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

import com.aihua.label.dao.LabelDao;
import com.aihua.label.model.AiHuaLabel;
import com.aihua.label.util.ComponenetPrintUtil;
import com.aihua.label.util.DateChooser;
import com.aihua.label.util.ExcelUtil;
import com.aihua.label.util.StringUtil;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PrintLogSelFrm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Boolean insideToOutFlag = false;
	private JPanel contentPane;
	public static JTable PrintLog_table;
	private JLabel PO_label;
	private JTextField PO_textField;
	private JTextField VendorCode_textField;
	private JLabel LotNo_label;
	private JTextField LotNo_textField;
	private JLabel BoxType_Label;
	public static JComboBox BoxType_comboBox;
	public static AiHuaLabel outlabelPrintLogSelect = new AiHuaLabel();//用于存放选定的外箱打印记录
	OutPrintViewFrm outPrintViewFrm = null;
	private JTextField PN_textField;
	private JTextField AN_textField;
	private JLabel PD_label;
	private JTextField PD_textField;
	JButton createOutBoxOneByOne_button;
	private JButton delete_button;
	/**
	 * Create the frame.
	 */
	public PrintLogSelFrm() {
		setTitle("打印记录");
		setResizable(false);
		setBounds(100, 100, 1051, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JScrollPane scrollPane = new JScrollPane();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				PrintLog_table.clearSelection();
				if(outPrintViewFrm!=null){
					outPrintViewFrm.dispose();
				}
				LabelPrintFrm.PrintBtn.setText("打印");
			}
		});
		
		JFrame This = this;
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton createOutBox_Button = new JButton("合批生成外箱");
		createOutBox_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<AiHuaLabel> insideBox_labelPrintLog = new ArrayList<AiHuaLabel>();// 外箱获取内箱打印数据
				if(BoxType_comboBox.getSelectedItem()=="内箱"){
					int[] Row =  PrintLog_table.getSelectedRows();	
					//存放选择外箱的指定信息
					List<String> PN = new ArrayList<String>();
					List<String> AN = new ArrayList<String>();
					List<String> LotNo = new ArrayList<String>();
					List<String> N_N_Id = new ArrayList<String>();
					//存放去重后的信息
					List<String> def_PN = new ArrayList<String>();
					List<String> def_AN = new ArrayList<String>();
					List<String> def_LotNo = new ArrayList<String>();
					List<String> def_N_N_Id = new ArrayList<String>();
					for (int i = 0; i < Row.length; i++) {
						AiHuaLabel labelPrintLog = new AiHuaLabel();
						labelPrintLog.setLabelId(Integer.valueOf(PrintLog_table.getValueAt(Row[i], 0)+""));
						labelPrintLog.setPO((String) PrintLog_table.getValueAt(Row[i], 2));
						labelPrintLog.setVendorCode((String) PrintLog_table.getValueAt(Row[i], 3));
						labelPrintLog.setAISHINo((String) PrintLog_table.getValueAt(Row[i], 5));
						labelPrintLog.setPN((String) PrintLog_table.getValueAt(Row[i], 4));
						labelPrintLog.setVendor((String) PrintLog_table.getValueAt(Row[i], 15));
						labelPrintLog.setLot_No((String) PrintLog_table.getValueAt(Row[i], 6));
						labelPrintLog.setItem((String) PrintLog_table.getValueAt(Row[i], 7));
						labelPrintLog.setQty1((String) PrintLog_table.getValueAt(Row[i], 8));
						if (StringUtil.isNotEmpty(PrintLog_table.getValueAt(Row[i], 10).toString())) {
							labelPrintLog.setQty2((String) PrintLog_table.getValueAt(Row[i], 10));
							labelPrintLog.setUnit2((String) PrintLog_table.getValueAt(Row[i], 11));
						}
						labelPrintLog.setUnit1((String) PrintLog_table.getValueAt(Row[i], 9));
						labelPrintLog.setNetWeight((String) PrintLog_table.getValueAt(Row[i], 12));
						labelPrintLog.setGrossWeight((String) PrintLog_table.getValueAt(Row[i], 13));
						labelPrintLog.setProductionDate((String) PrintLog_table.getValueAt(Row[i], 14));
						labelPrintLog.setRemarks((String) PrintLog_table.getValueAt(Row[i], 16));
						labelPrintLog.setSerialNumber((String) PrintLog_table.getValueAt(Row[i], 21));
						//需要打印外箱的内箱记录添加到list
						insideBox_labelPrintLog.add(labelPrintLog);
						
						AN.add((String) PrintLog_table.getValueAt(Row[i], 5));
						PN.add((String) PrintLog_table.getValueAt(Row[i], 4));
						LotNo.add((String) PrintLog_table.getValueAt(Row[i], 6));
						N_N_Id.add((String)PrintLog_table.getValueAt(Row[i], 1));
						
						for (String in : PN) {
							if (!def_PN.contains(in)) {
								def_PN.add(in);
							}
						}
						
						for (String in : AN) {
							if (!def_AN.contains(in)) {
								def_AN.add(in);
							}
						}
						
						for (String in : LotNo) {
							if (!def_LotNo.contains(in)) {
								def_LotNo.add(in);
							}
						}
						
						for (String in : N_N_Id) {
							if (!def_N_N_Id.contains(in)&&StringUtil.isNotEmpty(in)) {
								def_N_N_Id.add(in);
							}
						}
						
					}
					if(def_PN.size()==1&&def_AN.size()==1&&def_LotNo.size()<=3){//筛选生成内箱的条件
						outPrintViewFrm = new OutPrintViewFrm(insideBox_labelPrintLog);
						outPrintViewFrm.setVisible(true);
					}else{
//						JOptionPane.showMessageDialog(null, "");
					}
					
				}
			}
		});
		
		JButton TagPrinted_Button = new JButton("补打选中记录");
		TagPrinted_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = PrintLog_table.getSelectedRow();
				int[] rows = PrintLog_table.getSelectedRows();
				if (PrintLogSelFrm.BoxType_comboBox.getSelectedItem() == "内箱"&&rows.length!=0) {
					int result = JOptionPane.showConfirmDialog(null, "是否补打选中内箱打印记录?");
					if (result == 0) {
						BatchPrintViewFrm printViewFrm = new BatchPrintViewFrm(This,"内箱补打");
						ComponenetPrintUtil.printComponenet(BatchPrintViewFrm.TagList);
						printViewFrm.dispose();
					}
				} else if (PrintLogSelFrm.BoxType_comboBox.getSelectedItem() == "外箱"&&row!=-1) {
					int result = JOptionPane.showConfirmDialog(null, "是否补打选中外箱打印记录?");
					if (result == 0) {
						ArrayList<AiHuaLabel> outPrinted = new ArrayList<AiHuaLabel>();
						outPrinted.add(PrintLogSelFrm.outlabelPrintLogSelect);
						outPrintViewFrm = new OutPrintViewFrm(outPrinted);
						outPrintViewFrm.setVisible(true);
					}
				}
			}
		});
		
		
		PrintLog_table = new JTable();
		scrollPane.setViewportView(PrintLog_table);

		PrintLog_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\u5173\u8054ID", "\u91C7\u8D2D\u8BA2\u5355\u53F7", "\u4F9B\u5E94\u5546\u4EE3\u7801", "\u827E\u534E\u6599\u53F7", "\u827E\u534E\u6279\u6B21\u53F7", "\u4F9B\u5E94\u5546\u751F\u4EA7\u6279\u53F7", "\u578B\u53F7\u89C4\u683C", "\u6570\u91CF1", "\u5355\u4F4D1", "\u6570\u91CF2", "\u5355\u4F4D2", "\u51C0\u91CD", "\u6BDB\u91CD", "\u751F\u4EA7\u65E5\u671F", "\u4F9B\u5E94\u5546\u540D\u79F0", "REMARK", "\u6253\u5370\u65E5\u671F", "\u8BB0\u5F55\u66F4\u65B0\u65F6\u95F4", "\u6750\u6599\u540D\u79F0", "\u5B9E\u6D4B\u503C/\u6807\u51C6\u503C", "备注"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		PrintLog_table.getColumnModel().getColumn(0).setPreferredWidth(42);
		PrintLog_table.getColumnModel().getColumn(1).setPreferredWidth(42);
		PrintLog_table.getColumnModel().getColumn(2).setPreferredWidth(94);
		PrintLog_table.getColumnModel().getColumn(4).setPreferredWidth(98);
		PrintLog_table.getColumnModel().getColumn(5).setPreferredWidth(124);
		PrintLog_table.getColumnModel().getColumn(6).setPreferredWidth(135);
		PrintLog_table.getColumnModel().getColumn(7).setPreferredWidth(180);
		PrintLog_table.getColumnModel().getColumn(8).setPreferredWidth(57);
		PrintLog_table.getColumnModel().getColumn(9).setPreferredWidth(56);
		PrintLog_table.getColumnModel().getColumn(10).setPreferredWidth(52);
		PrintLog_table.getColumnModel().getColumn(11).setPreferredWidth(54);
		PrintLog_table.getColumnModel().getColumn(12).setPreferredWidth(56);
		PrintLog_table.getColumnModel().getColumn(13).setPreferredWidth(54);
		PrintLog_table.getColumnModel().getColumn(14).setPreferredWidth(70);
		PrintLog_table.getColumnModel().getColumn(16).setPreferredWidth(110);
		PrintLog_table.getColumnModel().getColumn(17).setPreferredWidth(130);
		PrintLog_table.getColumnModel().getColumn(18).setPreferredWidth(130);
		PrintLog_table.getColumnModel().getColumn(19).setPreferredWidth(90);
		PrintLog_table.getColumnModel().getColumn(20).setPreferredWidth(250);
		
		BoxType_comboBox = new JComboBox();
		BoxType_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(BoxType_comboBox.getSelectedItem()=="外箱"){
						createOutBoxOneByOne_button.setEnabled(false);
						createOutBox_Button.setEnabled(false);
					}else{
						createOutBoxOneByOne_button.setEnabled(true);
						createOutBox_Button.setEnabled(true);
					}
					
					LabelPringLogSearchActionPerformed(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		BoxType_comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5185\u7BB1", "\u5916\u7BB1"}));
		
		AiHuaLabel labelPrintLog = new AiHuaLabel();
		try {
			if(PrintLogSelFrm.BoxType_comboBox.getSelectedIndex()==0){
				labelPrintLog.setBoxType(0);
			}else if(PrintLogSelFrm.BoxType_comboBox.getSelectedIndex()==1){
				labelPrintLog.setBoxType(1);
			}
			PrintLogSelFrm.fillTable(labelPrintLog);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PO_label = new JLabel("采购订单号：");
		
		PO_textField = new JTextField();
		PO_textField.setColumns(10);
		
		VendorCode_textField = new JTextField();
		VendorCode_textField.setColumns(10);
		
		JLabel VendorCode_label = new JLabel("供应商代码：");
		
		LotNo_label = new JLabel("供应商生产批号：");
		
		LotNo_textField = new JTextField();
		LotNo_textField.setColumns(10);
		
		BoxType_Label = new JLabel("装箱类型：");
		

		
		
		JButton Sel_btnNewButton = new JButton("查询");
		Sel_btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LabelPringLogSearchActionPerformed(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel PN_label = new JLabel("艾华料号：");
		
		PN_textField = new JTextField();
		PN_textField.setColumns(10);
		
		JLabel AN_label = new JLabel("艾华批次号：");
		
		AN_textField = new JTextField();
		AN_textField.setColumns(10);
		
		PD_label = new JLabel("生产日期：");
		
		PD_textField = new JTextField();
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy.MM.dd");
		dateChooser1.register(PD_textField);
		PD_textField.setColumns(10);
		Document PrintDate = PD_textField.getDocument();
		PrintDate.addDocumentListener(new javax.swing.event.DocumentListener(){                  
            public void insertUpdate(DocumentEvent e) {
            	LabelPringLogSearchActionPerformedByPD(e);
            } 
            public void removeUpdate(DocumentEvent e) {
            	LabelPringLogSearchActionPerformedByPD(e);
            }
            public void changedUpdate(DocumentEvent e) {
            	LabelPringLogSearchActionPerformedByPD(e); 
            }
        });
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(PO_label)
							.addGap(12)
							.addComponent(PO_textField, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(PN_label, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(PN_textField, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(VendorCode_label, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(VendorCode_textField, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(AN_label, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(AN_textField, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(38)
							.addComponent(PD_label, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(PD_textField, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(14)
							.addComponent(LotNo_label, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(LotNo_textField, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
					.addGap(79)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(BoxType_Label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(BoxType_comboBox, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addComponent(Sel_btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(72))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(BoxType_Label)
							.addComponent(BoxType_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(PO_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(PO_label)
								.addComponent(LotNo_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(VendorCode_label)
								.addComponent(VendorCode_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(LotNo_label))
							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(PN_label)
								.addComponent(PN_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(AN_label)
								.addComponent(AN_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(PD_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(PD_label)
								.addComponent(Sel_btnNewButton))
							.addGap(31))))
		);
		panel.setLayout(gl_panel);
		
		
		if(BoxType_comboBox.getSelectedItem()=="外箱"){
			PrintLog_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		PrintLog_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		PrintLog_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = PrintLog_table.getSelectedRow();
				if (row != -1&&BoxType_comboBox.getSelectedItem()=="内箱"){
					LabelPrintFrm.printList_table.clearSelection();
				}else if(row != -1&&BoxType_comboBox.getSelectedItem()=="外箱"){				
					outlabelPrintLogSelect.setPO((String) PrintLog_table.getValueAt(row, 2));
					outlabelPrintLogSelect.setVendorCode((String) PrintLog_table.getValueAt(row, 3));
					outlabelPrintLogSelect.setAISHINo((String) PrintLog_table.getValueAt(row, 5));
					outlabelPrintLogSelect.setPN((String) PrintLog_table.getValueAt(row, 4));
					outlabelPrintLogSelect.setVendor((String) PrintLog_table.getValueAt(row, 15));
					outlabelPrintLogSelect.setLot_No((String) PrintLog_table.getValueAt(row, 6));
					outlabelPrintLogSelect.setItem((String) PrintLog_table.getValueAt(row, 7));
					outlabelPrintLogSelect.setQty1((String) PrintLog_table.getValueAt(row, 8));

					if (StringUtil.isNotEmpty(PrintLog_table.getValueAt(row, 10).toString())) {
						outlabelPrintLogSelect.setQty2((String) PrintLog_table.getValueAt(row, 10));
						outlabelPrintLogSelect.setUnit2((String) PrintLog_table.getValueAt(row, 11));
					}
					outlabelPrintLogSelect.setUnit1((String) PrintLog_table.getValueAt(row, 9));
					outlabelPrintLogSelect.setNetWeight((String) PrintLog_table.getValueAt(row, 12));
					outlabelPrintLogSelect.setGrossWeight((String) PrintLog_table.getValueAt(row, 13));
					outlabelPrintLogSelect.setProductionDate((String) PrintLog_table.getValueAt(row, 14));
					outlabelPrintLogSelect.setRemarks((String) PrintLog_table.getValueAt(row, 16));
					outlabelPrintLogSelect.setMaterialName((String) PrintLog_table.getValueAt(row, 19));
					outlabelPrintLogSelect.setMaterial_Property((String) PrintLog_table.getValueAt(row, 20));
					outlabelPrintLogSelect.setSerialNumber((String) PrintLog_table.getValueAt(row, 21));
				}
			}
		});
		
		//设置居中显示
		this.setLocationRelativeTo(null);
		
		JButton btnexcel = new JButton("\u5BFC\u51FAExcel");
		
		createOutBoxOneByOne_button = new JButton("批量生成对应外箱");
		createOutBoxOneByOne_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insideToOutFlag = true;
				LabelPrintFrm.printList_table.clearSelection();
				if (PrintLog_table.getSelectedRowCount() != 0) {
					This.setEnabled(false);
					BatchPrintViewFrm printViewFrm = new BatchPrintViewFrm(This,"外箱");// 生成一个隐藏的窗口，用于批量打印内箱
					printViewFrm.setVisible(true);
					insideToOutFlag = false;
				} else {

				}
			}
		});
		
		delete_button = new JButton("删除选中记录");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] rows = PrintLogSelFrm.PrintLog_table.getSelectedRows();
				if(rows.length>0){
					int result = JOptionPane.showConfirmDialog(null, "是否删除指定打印记录?");
					if(result == 0){
						LabelDao labelPrintLogDao = new LabelDao();
						for (int i = 0; i < rows.length; i++) {
							labelPrintLogDao.deletePrintLog(Integer.valueOf((String)PrintLogSelFrm.PrintLog_table.getValueAt(rows[i], 0)));
						}
					}
					
					try {
						LabelPringLogSearchActionPerformed(e);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,e1);
					}
				}
				
			}
		});
		delete_button.setForeground(Color.RED);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addComponent(createOutBoxOneByOne_button, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(62)
							.addComponent(createOutBox_Button, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(60)
							.addComponent(TagPrinted_Button, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
							.addComponent(delete_button, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(btnexcel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnexcel)
						.addComponent(createOutBoxOneByOne_button)
						.addComponent(createOutBox_Button)
						.addComponent(TagPrinted_Button)
						.addComponent(delete_button))
					.addGap(19))
		);
		
		contentPane.setLayout(gl_contentPane);
		btnexcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jButtonActionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
	}
	
	/**
	 * 根据生产日期查询
	 * @param e
	 */
	protected void LabelPringLogSearchActionPerformedByPD(DocumentEvent e) {
		// TODO Auto-generated method stub
		AiHuaLabel labelPrintLog = new AiHuaLabel();
		labelPrintLog.setProductionDate(this.PD_textField.getText());
		if(PrintLogSelFrm.BoxType_comboBox.getSelectedIndex()==0){
			labelPrintLog.setBoxType(0);
		}else if(PrintLogSelFrm.BoxType_comboBox.getSelectedIndex()==1){
			labelPrintLog.setBoxType(1);
		}
		try {
			PrintLogSelFrm.fillTable(labelPrintLog);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,e1);
		}
	}

	/**
	 * 数据导出
	 * @param e
	 */
	protected void jButtonActionPerformed(ActionEvent e) throws IOException {
		// TODO Auto-generated method stub
		FileDialog fd = new FileDialog(this, "打印记录导出", FileDialog.SAVE);
		 fd.setLocation(500, 350);
	     fd.setVisible(true);  
	     String stringfile = fd.getDirectory()+fd.getFile()+".xls";
        try {
       	 ExcelUtil export = new ExcelUtil();
       	 export.exportTable(PrintLogSelFrm.PrintLog_table,new File(stringfile));
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,ex);
        }
	}

	/**
	 * 查询事件
	 * @param e
	 * @throws SQLException 
	 */
	protected void LabelPringLogSearchActionPerformed(ActionEvent evt) throws SQLException {
		// TODO Auto-generated method stub
		AiHuaLabel labelPrintLog = new AiHuaLabel();
		labelPrintLog.setPO(this.PO_textField.getText());
		labelPrintLog.setVendorCode(this.VendorCode_textField.getText());
		labelPrintLog.setLot_No(this.LotNo_textField.getText());
		labelPrintLog.setPN(this.PN_textField.getText());
		labelPrintLog.setAISHINo(this.AN_textField.getText());
		labelPrintLog.setProductionDate(this.PD_textField.getText());
		if(PrintLogSelFrm.BoxType_comboBox.getSelectedIndex()==0){
			labelPrintLog.setBoxType(0);
		}else if(PrintLogSelFrm.BoxType_comboBox.getSelectedIndex()==1){
			labelPrintLog.setBoxType(1);
		}
		PrintLogSelFrm.fillTable(labelPrintLog);
	} 

	/**
	 * 初始化表格数据
	 * @param BoxType
	 * @throws SQLException 
	 */
	public static void fillTable(AiHuaLabel labelPrintLog) throws SQLException{
		LabelDao labelPrintLogDao = new LabelDao();
		DefaultTableModel  dtm = (DefaultTableModel)PrintLog_table.getModel();
		dtm.setRowCount(0);
		ResultSet rs = labelPrintLogDao.list(labelPrintLog);
		while(rs.next()){
			Vector v = new Vector();
			v.add(rs.getString("n_Id"));
			v.add(rs.getString("n_n_Id"));
			v.add(rs.getString("PO"));
			v.add(rs.getString("VendorCode"));
			v.add(rs.getString("PN"));
			v.add(rs.getString("AISHINo"));
			v.add(rs.getString("Lot_No"));
			v.add(rs.getString("Item"));
			v.add(rs.getString("Qty1"));
			v.add(rs.getString("Unit1"));
			if(rs.getString("Qty2")==null){
				v.add("");
				v.add("");
			}else{
				v.add(rs.getString("Qty2"));
				v.add(rs.getString("Unit2"));
			}
			v.add(rs.getString("net_weight"));
			v.add(rs.getString("gross_weight"));
			v.add(rs.getString("production_date"));
			v.add(rs.getString("Vendor"));
			v.add(rs.getString("remarks"));
			v.add(rs.getString("create_time"));
			v.add(rs.getString("update_time"));
			v.add(rs.getString("MaterialName"));
			v.add(rs.getString("Material_Property"));
			v.add(rs.getString("SerialNumber"));
			dtm.addRow(v);
		}
	}
	
}
