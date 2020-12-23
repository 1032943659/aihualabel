package com.label.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.label.util.*;
import com.label.dao.LabelPrintLogDao;
import com.label.model.LabelPrintLog;
import com.label.util.StringUtil;

/**
 * 批量打印界面
 * 
 * @author 木易
 */
public class BatchPrintViewFrm extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static PrinterJob job;
	static Book printBook;
	static PageFormat pf;
	JPanel jpc;// 存放组件的面板
	JScrollPane jsp;// 滚动面板
	int[] Row = LabelPrintFrm.printList_table.getSelectedRows();
	JButton jbAdd;
	public static ArrayList<JPanel> TagList;
	public static InsideBoxPanel InsideBox;
	public static Boolean printResult = true;
	public String box;
	public JFrame f_Jframe;

	/**
	 * Create the frame.
	 */
	public BatchPrintViewFrm(JFrame This,String s) {
		setResizable(false);
		jpc = new JPanel();
		jpc.setLayout(new BoxLayout(jpc, BoxLayout.Y_AXIS));// 盒子布局.从上到下
		jsp = new JScrollPane(jpc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(jsp);
		box = s;
		f_Jframe = This;
		
		TagList = new ArrayList<JPanel>();
		LabelPrintLog labelPrintLog = new LabelPrintLog();

		// 当选择补打的时候
		if (Row.length == 0) {
			if (LabelPrintFrm.printLogSelFrm != null && PrintLogSelFrm.insideToOutFlag == false) {
				System.out.println("进入补打");
				int row = PrintLogSelFrm.PrintLog_table.getSelectedRow();
				labelPrintLog.setPO((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 2));
				labelPrintLog.setVendorCode((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 3));
				labelPrintLog.setAISHINo((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 5));
				labelPrintLog.setPN((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 4));
				labelPrintLog.setVendor((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 15));
				labelPrintLog.setLot_No((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 6));
				labelPrintLog.setItem((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 7));
				labelPrintLog.setQty1((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 8));

				if (StringUtil.isNotEmpty(PrintLogSelFrm.PrintLog_table.getValueAt(row, 10).toString())) {
					labelPrintLog.setQty2(PrintLogSelFrm.PrintLog_table.getValueAt(row, 10) + "");
					labelPrintLog.setUnit2((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 11));
				}
				labelPrintLog.setUnit1((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 9));
				labelPrintLog.setNet_Weight((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 12));
				labelPrintLog.setGross_Weight((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 13));
				labelPrintLog.setProduction_Date((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 14));
				labelPrintLog.setRemarks((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 16));
				if (StringUtil.isEmpty((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 19))
						|| "".equals((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 19))) {
					labelPrintLog.setMaterialName("");
				} else {
					labelPrintLog.setMaterialName((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 19));
				}
				labelPrintLog.setMaterial_Property((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 20));
				labelPrintLog.setSerialNumber((String) PrintLogSelFrm.PrintLog_table.getValueAt(row, 21));

				InsideBoxPanel InsideBox = new InsideBoxPanel(labelPrintLog);
				TagList.add(InsideBox);
				jpc.add(new JLabel("标签" + 1));
				jpc.add(TagList.get(0));

			} else {
				System.out.println("退出补打");
			}
		}

		// 进入内箱批量生成单个外箱
		if (LabelPrintFrm.printLogSelFrm != null && PrintLogSelFrm.insideToOutFlag) {
			int[] row = PrintLogSelFrm.PrintLog_table.getSelectedRows();
			System.out.println("进入批量生成外箱" + row.length);
			for (int i = 0; i < row.length; i++) {
				labelPrintLog.setPO((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 2));
				labelPrintLog.setVendorCode((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 3));
				labelPrintLog.setAISHINo((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 5));
				labelPrintLog.setPN((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 4));
				labelPrintLog.setVendor((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 15));
				labelPrintLog.setLot_No((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 6));
				labelPrintLog.setItem((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 7));
				labelPrintLog.setQty1((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 8));

				if (StringUtil.isNotEmpty(PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 10).toString())) {
					labelPrintLog.setQty2(PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 10) + "");
					labelPrintLog.setUnit2((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 11));
				}
				labelPrintLog.setUnit1((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 9));
				labelPrintLog.setNet_Weight((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 12));
				labelPrintLog.setGross_Weight((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 13));
				labelPrintLog.setProduction_Date((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 14));
				labelPrintLog.setRemarks((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 16));
				if (StringUtil.isEmpty((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 19))
						|| "".equals((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 19))) {
					labelPrintLog.setMaterialName("");
				} else {
					labelPrintLog.setMaterialName((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 19));
				}
				labelPrintLog.setMaterial_Property((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 20));
				labelPrintLog.setSerialNumber((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 21));

				OutsideBoxPanel outTag = new OutsideBoxPanel(labelPrintLog);
				TagList.add(outTag);
				int j = i + 1;
				jpc.add(new JLabel("外箱标签:" + j));
				jpc.add(TagList.get(i));
			}
		}

		// 内箱打印--选择的是打印清单
		for (int i = 0; i < Row.length; i++) {
			// 打印记录，打印清单的选择
			System.out.println("进入打印清单打印");
			labelPrintLog.setPO((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 7));
			labelPrintLog.setVendorCode((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 8));
			labelPrintLog.setAISHINo((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 13));
			labelPrintLog.setPN((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 12));
			labelPrintLog.setVendor((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 9));
			labelPrintLog.setLot_No((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 11));
			labelPrintLog.setItem((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 10));
			labelPrintLog.setQty1(LabelPrintFrm.printList_table.getValueAt(Row[i], 1) + "");
			labelPrintLog.setQty2(LabelPrintFrm.printList_table.getValueAt(Row[i], 3) + "");
			labelPrintLog.setUnit2((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 4));
			labelPrintLog.setUnit1((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 2));
			labelPrintLog.setNet_Weight((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 5));
			labelPrintLog.setGross_Weight((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 6));
			labelPrintLog.setProduction_Date((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 14));
			labelPrintLog.setRemarks((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 15));
			if (StringUtil.isEmpty((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 16))
					|| "".equals((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 16))) {
				labelPrintLog.setMaterialName("");
			} else {
				labelPrintLog.setMaterialName((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 16));
			}
			labelPrintLog.setMaterial_Property((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 17));
			labelPrintLog.setSerialNumber((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 18));

			InsideBox = new InsideBoxPanel(labelPrintLog);
			TagList.add(InsideBox);
			int j = i + 1;
			jpc.add(new JLabel("内箱标签:" + j));
			jpc.add(TagList.get(i));
			myUpdateUI();

		}
		jbAdd = new JButton("打印");
		jbAdd.addActionListener(this);
		JPanel jps = new JPanel();
		jps.add(jbAdd);

		getContentPane().add(jps, BorderLayout.SOUTH);
		setTitle("打印预览");
		setSize(480, 534);// 大小
		setLocationRelativeTo(null);// 居中

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				This.setEnabled(true);
			}
		});
	}

	/**
	 * 按钮响应事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton jb = (JButton) e.getSource();
		int flag = 0;
		if (jb == jbAdd) {// 当点击打印按钮时,触发批量打印
			if (box == "内箱") {
				if (LabelPrintFrm.printStatus == 1) {// 当未勾选仅保存打印记录时
					printResult = ComponenetPrintUtil.printComponenet(TagList);// 调用swing组件批量打印
				}
				if (printResult == true && Row.length != 0) {// 内箱批量打印
					LabelPrintLogDao labelPrintLogDao = new LabelPrintLogDao();
					for (int i = 0; i < Row.length; i++) {
						LabelPrintLog labelPrintLog = new LabelPrintLog();
						labelPrintLog.setPO((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 7));
						labelPrintLog.setVendorCode((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 8));
						labelPrintLog.setAISHINo((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 13));
						labelPrintLog.setPN((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 12));
						labelPrintLog.setVendor((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 9));
						labelPrintLog.setLot_No((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 11));
						labelPrintLog.setItem((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 10));
						labelPrintLog.setQty1(LabelPrintFrm.printList_table.getValueAt(Row[i], 1) + "");
						labelPrintLog.setQty2(LabelPrintFrm.printList_table.getValueAt(Row[i], 3) + "");
						labelPrintLog.setUnit2((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 4));
						labelPrintLog.setUnit1((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 2));
						labelPrintLog.setNet_Weight((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 5));
						labelPrintLog.setGross_Weight((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 6));
						labelPrintLog.setProduction_Date((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 14));
						labelPrintLog.setRemarks((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 15));
						labelPrintLog.setMaterialName((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 16));
						labelPrintLog
								.setMaterial_Property((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 17));
						labelPrintLog.setSerialNumber((String) LabelPrintFrm.printList_table.getValueAt(Row[i], 18));
						flag = labelPrintLogDao.insertPrintLog(labelPrintLog);// MYSQL存储数据
					}
					if (flag != 1 && LabelPrintFrm.printStatus == 0) {// 存储失败的时候
						JOptionPane.showMessageDialog(null, "内箱标签打印记录存储失败！");
					} else if (LabelPrintFrm.printStatus == 0) {// 如果不需要打印出来，就必须提示是否保存成功
						JOptionPane.showMessageDialog(null, "内箱标签打印记录存储成功！");
					}
					this.dispose();
					f_Jframe.setEnabled(true);
				} else if (printResult == true && Row.length == 0) {

				}
			}else if(box=="外箱"){
				printResult = ComponenetPrintUtil.printComponenet(TagList);// 调用swing组件批量打印
				if(printResult==true){
					LabelPrintLog labelPrintLog = new LabelPrintLog();
					LabelPrintLogDao labelPrintLogDao = new LabelPrintLogDao();
					int[] row = PrintLogSelFrm.PrintLog_table.getSelectedRows();//选中打印记录
					for (int i = 0; i < row.length; i++) {
						labelPrintLog.setPO((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 2));
						labelPrintLog.setVendorCode((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 3));
						labelPrintLog.setAISHINo((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 5));
						labelPrintLog.setPN((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 4));
						labelPrintLog.setVendor((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 15));
						labelPrintLog.setLot_No((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 6));
						labelPrintLog.setItem((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 7));
						labelPrintLog.setQty1((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 8));

						if (StringUtil.isNotEmpty(PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 10).toString())) {
							labelPrintLog.setQty2((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 10));
							labelPrintLog.setUnit2((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 11));
						}else{
							labelPrintLog.setQty2("");
							labelPrintLog.setUnit2("");
						}
						labelPrintLog.setUnit1((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 9));
						labelPrintLog.setNet_Weight((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 12));
						labelPrintLog.setGross_Weight((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 13));
						labelPrintLog.setProduction_Date((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 14));
						labelPrintLog.setRemarks((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 16));
						labelPrintLog.setBoxType(1);
						labelPrintLog.setN_n_Id((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 0));
						if (StringUtil.isEmpty((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 19))
								|| "".equals((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 19))) {
							labelPrintLog.setMaterialName("");
						} else {
							labelPrintLog
									.setMaterialName((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 19));
						}
						labelPrintLog.setMaterial_Property((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 20));
						labelPrintLog.setSerialNumber((String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 21));
						if(labelPrintLogDao.insertPrintLog(labelPrintLog)==1){
							try {
								String id = (String) PrintLogSelFrm.PrintLog_table.getValueAt(row[i], 0);//选中的内箱id
								int n_id = labelPrintLogDao.SelBoxNidByNnId(labelPrintLog);//当前的外箱id
								labelPrintLogDao.UpdateInsideBoxNnId(Integer.valueOf(id), n_id);//将每个绑定了外箱的内箱，更新n_n_Id
								LabelPrintLog labelPrintLog1 = new LabelPrintLog();
								labelPrintLog1.setBoxType(0);
								PrintLogSelFrm.fillTable(labelPrintLog1);
							} catch (SQLException e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null,e1);
							}
						}
						this.dispose();
						f_Jframe.setEnabled(true);
					}
				}
			}
		}
	}

	/**
	 * 刷新界面函数
	 */
	private void myUpdateUI() {
		SwingUtilities.updateComponentTreeUI(this);// 添加或删除组件后,更新窗口
		jsp.getVerticalScrollBar();
	}

}
