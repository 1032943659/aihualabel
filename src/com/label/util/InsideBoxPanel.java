package com.label.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.label.model.LabelPrintLog;
import com.label.view.LabelPrintFrm;
import com.swetake.util.Qrcode;

/**
 * 内箱标签批量打印模板
 * @author 木易
 *
 */
public class InsideBoxPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel vendorcode_label;
	JLabel lotno_label;
	JLabel po_label;
	JLabel item_label;
	JLabel pn_label;
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
	JLabel remark_label;
	JLabel netweigth_label;
	JLabel grossweith_label;
	ImagePannel imagePannel;
	JLabel lblPo;
	JLabel label_5;
	JLabel label_3;
	JLabel label_7;
	JLabel label_9;
	JLabel lblLotno;
	JLabel lblItem;
	JLabel lblRohs;
	Qrcode inside_qrcode;
	Graphics2D inside_gs;
	String inside_code;
	BufferedImage inside_bi;
	JLabel label_18;
	JLabel TagboxNum_label;
	Font font = new Font("Microsoft YaHei", Font.BOLD, 14);
	
	public InsideBoxPanel(LabelPrintLog labelPrintLog) {

		this.setPreferredSize(new Dimension(450, 210));
		this.setBackground(Color.WHITE);
		this.setLayout(null);

		lblPo = new JLabel("PO:");
		lblPo.setFont(font);
		lblPo.setBounds(10, 14, 37, 14);
		this.add(lblPo);

		po_label = new JLabel(labelPrintLog.getPO());
		po_label.setFont(font);
		po_label.setBounds(57, 14, 130, 14);
		this.add(po_label);

		label_3 = new JLabel("PD:");
		label_3.setFont(font);
		label_3.setBounds(10, 182, 38, 14);
		this.add(label_3);

		pd_label = new JLabel(labelPrintLog.getProduction_Date());
		pd_label.setFont(font);
		pd_label.setBounds(57, 182, 85, 14);
		this.add(pd_label);

		label_5 = new JLabel("Qty:");
		label_5.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		label_5.setBounds(10, 154, 38, 14);
		this.add(label_5);

		qty1unit1_label = new JLabel(labelPrintLog.getQty1()+labelPrintLog.getUnit1());
		qty1unit1_label.setFont(font);
		qty1unit1_label.setBounds(57, 154, 85, 17);
		this.add(qty1unit1_label);

		label_7 = new JLabel("A/N:");
		label_7.setFont(font);
		label_7.setBounds(10, 126, 38, 14);
		this.add(label_7);

		an_label = new JLabel(labelPrintLog.getAISHINo());
		an_label.setFont(font);
		an_label.setBounds(57, 126, 120, 14);
		this.add(an_label);

		label_9 = new JLabel("P/N:");
		label_9.setFont(font);
		label_9.setBounds(10, 98, 38, 14);
		this.add(label_9);

		pn_label = new JLabel(labelPrintLog.getPN());
		pn_label.setFont(new Font("Microsoft YaHei", Font.BOLD, LabelPrintFrm.pnfontSize));
		if(StringUtil.isNotEmpty(labelPrintLog.getRemarks())){
			pn_label.setText(labelPrintLog.getPN()+'/'+labelPrintLog.getRemarks());
		}
		pn_label.setBounds(57, 98, 240, 14);
		this.add(pn_label);

		lblLotno = new JLabel("Lot No:");
		lblLotno.setFont(font);
		lblLotno.setBounds(10, 70, 52, 14);
		this.add(lblLotno);

		lotno_label = new JLabel(labelPrintLog.getLot_No());
		lotno_label.setFont(font);
		lotno_label.setBounds(67, 70, 200, 14);
		this.add(lotno_label);

		lblItem = new JLabel("Item:");
		lblItem.setFont(font);
		lblItem.setBounds(10, 42, 38, 14);
		this.add(lblItem);

		JLabel lblNewLabel = new JLabel("内");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
		lblNewLabel.setBounds(277, 57, 24, 31);
		this.add(lblNewLabel);
		
		item_label = new JLabel(labelPrintLog.getItem());
		item_label.setFont(new Font("Microsoft YaHei", Font.BOLD, LabelPrintFrm.itemfontSize));
		item_label.setBounds(57, 42, 240, 14);
		this.add(item_label);

		vendorcode_label = new JLabel(labelPrintLog.getVendorCode());
		vendorcode_label.setFont(font);
		vendorcode_label.setBounds(194, 14, 130, 14);
		this.add(vendorcode_label);

		vendor_label = new JLabel(labelPrintLog.getVendor());
		vendor_label.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		vendor_label.setBounds(343, 12, 97, 14);
		this.add(vendor_label);

		if(labelPrintLog.getQty2()=="0"||StringUtil.isEmpty(labelPrintLog.getQty2())){
			qty2unit2_label = new JLabel("");
		}else{
			qty2unit2_label = new JLabel(labelPrintLog.getQty2()+labelPrintLog.getUnit2());
			qty2unit2_label.setFont(font);
			qty2unit2_label.setBounds(139, 154, 85, 17);
			this.add(qty2unit2_label);
		}

		TagboxNum_label = new JLabel();
		TagboxNum_label.setFont(new Font("Microsoft YaHei", Font.BOLD, 10));
		TagboxNum_label.setBounds(354, 175, 80, 28);
		this.add(TagboxNum_label);
		
		lblRohs = new JLabel("RoHS2.0");
		lblRohs.setFont(new Font("Microsoft YaHei", Font.BOLD, 28));
		lblRohs.setBounds(307, 140, 120, 41);
		this.add(lblRohs);
		
		if(StringUtil.isEmpty(labelPrintLog.getSerialNumber())){
			TagboxNum_label.setText("");
		}else{
			try {
				JLabelUtil.JlabelSetText(TagboxNum_label, labelPrintLog.getSerialNumber());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(StringUtil.isEmpty(labelPrintLog.getNet_Weight())){
			netweigth_label = new JLabel("");
		}else{
			netweigth_label = new JLabel("净重"+labelPrintLog.getNet_Weight()+"KG");
			netweigth_label.setFont(font);
			netweigth_label.setBounds(152, 182, 98, 14);
			this.add(netweigth_label);
		}

		if(StringUtil.isEmpty(labelPrintLog.getGross_Weight())){
			grossweith_label = new JLabel("");
		}else{
			grossweith_label = new JLabel("毛重"+labelPrintLog.getGross_Weight()+"KG");
			grossweith_label.setFont(font);
			grossweith_label.setBounds(252, 182, 98, 14);
			this.add(grossweith_label);
		}
		

		imagePannel = new ImagePannel();
		imagePannel.setBounds(307, 29, 115, 115);
		this.add(imagePannel);
		inside_bi = new BufferedImage(115, 115, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 115; i++)
			for (int j = 0; j < 115; j++)
				inside_bi.setRGB(j, i, Color.WHITE.getRGB());

		inside_gs = inside_bi.createGraphics();
		inside_gs.setColor(Color.BLACK);

		inside_qrcode = new Qrcode();
		inside_qrcode.setQrcodeEncodeMode('B');// 存储英文
		inside_qrcode.setQrcodeVersion(10);
		
		if(labelPrintLog.getQty2()=="0"||StringUtil.isEmpty(labelPrintLog.getQty2())){
			inside_code = labelPrintLog.getPO() + '|' + labelPrintLog.getVendorCode() + '|'
					+ labelPrintLog.getPN() + '|' + labelPrintLog.getLot_No() + '|' + labelPrintLog.getRemarks() + '|'
					+ labelPrintLog.getAISHINo() + '|' + labelPrintLog.getProduction_Date() + '|'
					+ labelPrintLog.getQty1() + '|' + '|'
					+ labelPrintLog.getNet_Weight()+ '|' + labelPrintLog.getGross_Weight() + '|'+labelPrintLog.getMaterialName() +'|'+StringUtil.getMaterialProperty(labelPrintLog);
		}else{
			inside_code = labelPrintLog.getPO() + '|' + labelPrintLog.getVendorCode() + '|'
					+ labelPrintLog.getPN() + '|' + labelPrintLog.getLot_No() + '|' + labelPrintLog.getRemarks() + '|'
					+ labelPrintLog.getAISHINo() + '|' + labelPrintLog.getProduction_Date() + '|'
					+ labelPrintLog.getQty1() + '|' + labelPrintLog.getQty2() + '|'
					+ labelPrintLog.getNet_Weight()+ '|' + labelPrintLog.getGross_Weight() + '|'+labelPrintLog.getMaterialName() +'|'+StringUtil.getMaterialProperty(labelPrintLog);
		}
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
	
}
