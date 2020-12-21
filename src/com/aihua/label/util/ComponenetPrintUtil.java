package com.aihua.label.util;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ComponenetPrintUtil {
	
	/**
	 * ������ӡ���
	 * @param List
	 * @return
	 */
	public static Boolean printComponenet(ArrayList<JPanel> tagList) {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setJobName(" ��ǩ��ӡ ");
		Book printBook = new Book();
		Paper paper = new Paper();
		PageFormat pf = new PageFormat();
		paper.setSize(340, 140);// ����һҳֽ�Ĵ�С
		paper.setImageableArea(20, 5, 340, 140);// ���ô�ӡ����Ĵ�С
		pf.setPaper(paper);
		job.setPageable(printBook);
		if (job.printDialog() == false)
			return false;
		for (JPanel tag : tagList) {
			printBook.append(new Printable() {
				public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

					pageFormat.getImageableY();

					Graphics2D g2 = (Graphics2D) graphics;
					g2.translate(pf.getImageableX(), pf.getImageableY());
					g2.scale(0.6, 0.6);
					tag.paint(g2);
					return Printable.PAGE_EXISTS; // ����PAGE_EXISTS
				}

			}, pf, 1);
		}

		job.setPageable(printBook);
		try {
			job.print();
			return true;
		} catch (PrinterException ex) {
			// handle exception
		}
		return true;

	}

	/**
	 * �����������ӡ
	 * @param component 
	 * @param num	��Ҫ��ӡ������
	 * @return
	 */
	public static Boolean printComponenet(Component component,int num) {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setJobName(" ��ǩ��ӡ ");
		
		Book printBook = new Book();
		
		Paper paper=new Paper();
		PageFormat pf = new PageFormat();
		paper.setSize(340,140);//����һҳֽ�Ĵ�С
		paper.setImageableArea(20,5,340,140);//���ô�ӡ����Ĵ�С
		pf.setPaper(paper);
		
        job.setPageable(printBook);
			
		if (job.printDialog() == false)
			return false;

		printBook.append(new Printable() {
			public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) throws PrinterException {
				
				Graphics2D g2 = (Graphics2D) graphics;
				g2.translate(pf.getImageableX(), pf.getImageableY());
				g2.scale(0.6, 0.6);
				component.paint(g2);
				return Printable.PAGE_EXISTS; // ����PAGE_EXISTS
			}
		}, pf,num);
		job.setPageable(printBook);
		
		try {
			job.print();
			return true;
		} catch (PrinterException ex) {
			// handle exception
		}
		return true;
		
	}
}
