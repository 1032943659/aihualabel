package com.aihua.label.util;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.aihua.label.dao.MaterialPropertyDao;
import com.aihua.label.model.MaterialProperty;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

/**
 * ��������ģ�嵼��
 * @author ľ��
 *
 */
public class MaterialPropertyExcelImport {
	public static File chooseFile;
	private static JFileChooser fileChooser;
	private static MaterialPropertyDao propertyValueDao = new MaterialPropertyDao();
	private static MaterialProperty fxstandardValue = new MaterialProperty();

	@SuppressWarnings("static-access")
	public MaterialPropertyExcelImport() {

		fileChooser = new JFileChooser();
		// ����Excel�ļ���ֻѰ����XLS��β��Excel�ļ�����������word�ĵ�Ҳ����д��doc
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xls .et", "xls","et");
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
				JOptionPane.showMessageDialog(null,e);
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

				fxstandardValue.setMaterialName(sheet.getCell(1, i).getContents());
				fxstandardValue.setProperty1(sheet.getCell(2, i).getContents());
				fxstandardValue.setUnit1(sheet.getCell(3, i).getContents());
				fxstandardValue.setProperty2(sheet.getCell(4, i).getContents());
				if (StringUtil.isEmpty(sheet.getCell(4, i).getContents())
						|| "".equals(sheet.getCell(4, i).getContents())) {
					fxstandardValue.setUnit2("");
				} else {
					fxstandardValue.setUnit2(sheet.getCell(5, i).getContents());
				}
				fxstandardValue.setProperty3(sheet.getCell(6, i).getContents());
				if (StringUtil.isEmpty(sheet.getCell(6, i).getContents())
						|| "".equals(sheet.getCell(6, i).getContents())) {
					fxstandardValue.setUnit3("");
				} else {
					fxstandardValue.setUnit3(sheet.getCell(7, i).getContents());
				}
				fxstandardValue.setProperty4(sheet.getCell(8, i).getContents());
				if (StringUtil.isEmpty(sheet.getCell(8, i).getContents())
						|| "".equals(sheet.getCell(8, i).getContents())) {
					fxstandardValue.setUnit4("");
				} else {
					fxstandardValue.setUnit4(sheet.getCell(9, i).getContents());
				}
				fxstandardValue.setProperty5(sheet.getCell(10, i).getContents());
				if (StringUtil.isEmpty(sheet.getCell(10, i).getContents())
						|| "".equals(sheet.getCell(10, i).getContents())) {
					fxstandardValue.setUnit5("");
				} else {
					fxstandardValue.setUnit5(sheet.getCell(11, i).getContents());
				}
				fxstandardValue.setProperty6(sheet.getCell(12, i).getContents());
				if (StringUtil.isEmpty(sheet.getCell(12, i).getContents())
						|| "".equals(sheet.getCell(12, i).getContents())) {
					fxstandardValue.setUnit6("");
				} else {
					fxstandardValue.setUnit6(sheet.getCell(13, i).getContents());
				}
				fxstandardValue.setProperty7(sheet.getCell(14, i).getContents());
				if (StringUtil.isEmpty(sheet.getCell(14, i).getContents())
						|| "".equals(sheet.getCell(14, i).getContents())) {
					fxstandardValue.setUnit7("");
				} else {
					fxstandardValue.setUnit7(sheet.getCell(15, i).getContents());
				}
				fxstandardValue.setProperty8(sheet.getCell(16, i).getContents());
				if (StringUtil.isEmpty(sheet.getCell(16, i).getContents())
						|| "".equals(sheet.getCell(16, i).getContents())) {
					fxstandardValue.setUnit8("");
				} else {
					fxstandardValue.setUnit8(sheet.getCell(17, i).getContents());
				}
				fxstandardValue.setProperty9(sheet.getCell(18, i).getContents());
				if (StringUtil.isEmpty(sheet.getCell(18, i).getContents())
						|| "".equals(sheet.getCell(18, i).getContents())) {
					fxstandardValue.setUnit9("");
				} else {
					fxstandardValue.setUnit9(sheet.getCell(19, i).getContents());
				}
				fxstandardValue.setProperty10(sheet.getCell(20, i).getContents());
				if (StringUtil.isEmpty(sheet.getCell(20, i).getContents())
						|| "".equals(sheet.getCell(20, i).getContents())) {
					fxstandardValue.setUnit10("");
				} else {
					fxstandardValue.setUnit10(sheet.getCell(21, i).getContents());
				}
				fxstandardValue.setProperty11(sheet.getCell(22, i).getContents());
				if (StringUtil.isEmpty(sheet.getCell(22, i).getContents())
						|| "".equals(sheet.getCell(22, i).getContents())) {
					fxstandardValue.setUnit11("");
				} else {
					fxstandardValue.setUnit11(sheet.getCell(23, i).getContents());
				}
				if (propertyValueDao.insertToFxstandardValue(fxstandardValue) == 1) {
					System.out.println("�洢�ɹ�");
				}
			}

		}
	}

}