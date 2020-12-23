package com.label.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.label.dao.LabelPrintLogDao;
import com.label.model.LabelPrintLog;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

/**
 * 打印清单模板导入
 * 
 * @author 木易
 *
 */
public class PrintListExcelImport {
	public static File chooseFile;
	private static JFileChooser fileChooser;
	private static LabelPrintLogDao labelPrintLogDao = new LabelPrintLogDao();
	private static LabelPrintLog labelPrintLog = new LabelPrintLog();

	@SuppressWarnings("static-access")
	public PrintListExcelImport() {

		fileChooser = new JFileChooser();
		// 过滤Excel文件，只寻找以XLS结尾的Excel文件，如果想过滤word文档也可以写上doc
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xls .et", "xls", "et");
		fileChooser.setFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(null);
		// 弹出一个文件选择提示框
		if (returnValue == fileChooser.APPROVE_OPTION) {
			// 当用户选择文件后获取文件路径
			chooseFile = fileChooser.getSelectedFile();

			WorkbookSettings wbs = new WorkbookSettings();
			wbs.setEncoding("GB2312");
			// 根据文件路径初始化Excel工作簿
			Workbook workBook = null;
			try {
				workBook = Workbook.getWorkbook(chooseFile, wbs);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

			// 获取该工作表中的第一个工作表
			Sheet sheet = workBook.getSheet(0);
			// 获取该工作表的行数，以供下面循环使用
			int rowSize = sheet.getRows();
			for (int i = 1; i < rowSize; i++)// 从第1行开始，过滤表头
			{
				// String
				// col1=sheet.getCell(0,i).getContents();//获取第一列字段数据，第A列第i行，注意Excel中的行和列都是从0开始获取的，A列为0列
				// col1=col1.replaceAll(" ", "");//去掉该所有空格
				labelPrintLog.setQty1(sheet.getCell(1, i).getContents());// 获取第二列字段数据
				labelPrintLog.setUnit1(sheet.getCell(2, i).getContents());
				labelPrintLog.setQty2(sheet.getCell(3, i).getContents());
				labelPrintLog.setUnit2(sheet.getCell(4, i).getContents());
				labelPrintLog.setNet_Weight(sheet.getCell(5, i).getContents());
				labelPrintLog.setGross_Weight(sheet.getCell(6, i).getContents());
				labelPrintLog.setPO(sheet.getCell(7, i).getContents());
				labelPrintLog.setVendorCode(sheet.getCell(8, i).getContents());
				labelPrintLog.setVendor(sheet.getCell(9, i).getContents());
				labelPrintLog.setItem(sheet.getCell(10, i).getContents());
				labelPrintLog.setLot_No(sheet.getCell(11, i).getContents());
				labelPrintLog.setPN(sheet.getCell(12, i).getContents());
				labelPrintLog.setAISHINo(sheet.getCell(13, i).getContents());
				labelPrintLog.setProduction_Date(sheet.getCell(14, i).getContents());
				labelPrintLog.setRemarks(sheet.getCell(15, i).getContents());
				labelPrintLog.setSerialNumber(sheet.getCell(18, i).getContents());
				labelPrintLogDao.insertList(labelPrintLog);
			}
		}

	}

}