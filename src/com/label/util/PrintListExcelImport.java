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
 * ��ӡ�嵥ģ�嵼��
 * 
 * @author ľ��
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
		// ����Excel�ļ���ֻѰ����XLS��β��Excel�ļ�����������word�ĵ�Ҳ����д��doc
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xls .et", "xls", "et");
		fileChooser.setFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(null);
		// ����һ���ļ�ѡ����ʾ��
		if (returnValue == fileChooser.APPROVE_OPTION) {
			// ���û�ѡ���ļ����ȡ�ļ�·��
			chooseFile = fileChooser.getSelectedFile();

			WorkbookSettings wbs = new WorkbookSettings();
			wbs.setEncoding("GB2312");
			// �����ļ�·����ʼ��Excel������
			Workbook workBook = null;
			try {
				workBook = Workbook.getWorkbook(chooseFile, wbs);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

			// ��ȡ�ù������еĵ�һ��������
			Sheet sheet = workBook.getSheet(0);
			// ��ȡ�ù�������������Թ�����ѭ��ʹ��
			int rowSize = sheet.getRows();
			for (int i = 1; i < rowSize; i++)// �ӵ�1�п�ʼ�����˱�ͷ
			{
				// String
				// col1=sheet.getCell(0,i).getContents();//��ȡ��һ���ֶ����ݣ���A�е�i�У�ע��Excel�е��к��ж��Ǵ�0��ʼ��ȡ�ģ�A��Ϊ0��
				// col1=col1.replaceAll(" ", "");//ȥ�������пո�
				labelPrintLog.setQty1(sheet.getCell(1, i).getContents());// ��ȡ�ڶ����ֶ�����
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