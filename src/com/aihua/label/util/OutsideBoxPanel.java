package com.aihua.label.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.aihua.label.model.AiHuaLabel;
import com.aihua.label.view.LabelPrintFrm;
import com.swetake.util.Qrcode;

/**
 * 外箱标签批量打印模板
 * @author 木易
 */
public class OutsideBoxPanel extends JPanel{
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
	JLabel Lotno_label;
	JLabel label_7;
	JLabel label_9;
	JLabel lblLotno;
	JLabel lblItem;
	Qrcode inside_qrcode;
	Graphics2D inside_gs;
	String inside_code;
	BufferedImage inside_bi;
	JLabel label_18;
	JLabel label_lotno;
	JLabel TagboxNum_label;
	Font font = new Font("Microsoft YaHei", Font.BOLD, 14);
	
	public OutsideBoxPanel(AiHuaLabel labelPrintLog) {

		this.setPreferredSize(new Dimension(450, 210));
		this.setBackground(Color.WHITE);
		this.setLayout(null);

		lblPo = new JLabel("");
		lblPo.setFont(font);
		lblPo.setBounds(10, 11, 37, 14);
		this.add(lblPo);

		po_label = new JLabel(labelPrintLog.getVendorCode());
		po_label.setFont(font);
		po_label.setBounds(57, 11, 130, 14);
		this.add(po_label);

		label_lotno = new JLabel("Lot No:");
		label_lotno.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		label_lotno.setBounds(10, 34, 52, 14);
		this.add(label_lotno);
		
		Lotno_label = new JLabel(labelPrintLog.getLot_No().replace('*', '/'));
		if(labelPrintLog.getLot_No().split("/").length>1){
			Lotno_label.setFont(new Font("Microsoft YaHei", Font.BOLD, 10));
		}else{
			Lotno_label.setFont(font);
		}
		Lotno_label.setBounds(57, 34, 383, 14);
		this.add(Lotno_label);
		
		label_3 = new JLabel("PD:");
		label_3.setFont(font);
		label_3.setBounds(10, 186, 38, 14);
		this.add(label_3);

		pd_label = new JLabel(labelPrintLog.getProductionDate());
		pd_label.setFont(font);
		pd_label.setBounds(57, 186, 85, 14);
		this.add(pd_label);

		label_5 = new JLabel("Qty:");
		label_5.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		label_5.setBounds(10, 161, 38, 14);
		this.add(label_5);

		qty1unit1_label = new JLabel(labelPrintLog.getQty1()+labelPrintLog.getUnit1());
		qty1unit1_label.setFont(font);
		qty1unit1_label.setBounds(57, 161, 85, 17);
		this.add(qty1unit1_label);

		label_7 = new JLabel("A/N:");
		label_7.setFont(font);
		label_7.setBounds(10, 136, 38, 14);
		this.add(label_7);

		an_label = new JLabel(labelPrintLog.getAISHINo());
		an_label.setFont(font);
		an_label.setBounds(57, 136, 120, 14);
		this.add(an_label);

		label_9 = new JLabel("P/N:");
		label_9.setFont(font);
		label_9.setBounds(10, 111, 38, 14);
		this.add(label_9);

		pn_label = new JLabel(labelPrintLog.getPN());
		pn_label.setFont(new Font("Microsoft YaHei", Font.BOLD, LabelPrintFrm.pnfontSize));
		if(StringUtil.isNotEmpty(labelPrintLog.getRemarks())){
			pn_label.setText(labelPrintLog.getPN()+'/'+labelPrintLog.getRemarks());
		}
		pn_label.setBounds(57, 111, 240, 14);
		this.add(pn_label);

		lblLotno = new JLabel("Item:");
		lblLotno.setFont(font);
		lblLotno.setBounds(10, 86, 38, 14);
//		lblLotno.setBounds(10, 86, 52, 14);
		this.add(lblLotno);

		lotno_label = new JLabel(labelPrintLog.getItem());
		lotno_label.setFont(new Font("Microsoft YaHei", Font.BOLD, LabelPrintFrm.itemfontSize));
		lotno_label.setBounds(57, 86, 240, 14);
//		lotno_label.setBounds(67, 86, 240, 14);
		this.add(lotno_label);

		lblItem = new JLabel("PO:");
		lblItem.setFont(font);
		lblItem.setBounds(10, 60, 38, 14);
		this.add(lblItem);

		item_label = new JLabel(labelPrintLog.getPO());
		item_label.setFont(font);
		item_label.setBounds(57, 60, 200, 14);
		this.add(item_label);

		JLabel label = new JLabel("外");
		label.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
		label.setBounds(275, 49, 24, 31);
		this.add(label);
		
		vendorcode_label = new JLabel(labelPrintLog.getVendor());
		vendorcode_label.setFont(font);
		vendorcode_label.setBounds(194, 11, 130, 14);
//		vendorcode_label.setBounds(57, 11, 130, 14);
		this.add(vendorcode_label);

		vendor_label = new JLabel(labelPrintLog.getProductionDate().substring(5, 7)+"月");
		vendor_label.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
		vendor_label.setBounds(343, 10, 97, 16);
		this.add(vendor_label);

		if(labelPrintLog.getQty2()=="0"||StringUtil.isEmpty(labelPrintLog.getQty2())){
			qty2unit2_label = new JLabel("");
		}else{
			qty2unit2_label = new JLabel(labelPrintLog.getQty2()+labelPrintLog.getUnit2());
			qty2unit2_label.setFont(font);
			qty2unit2_label.setBounds(139, 161, 85, 17);
			this.add(qty2unit2_label);
		}

		TagboxNum_label = new JLabel();
		TagboxNum_label.setFont(new Font("Microsoft YaHei", Font.BOLD, 10));
		TagboxNum_label.setBounds(Integer.valueOf(PropertiesConfig.getFontValues("outOther_x")), Integer.valueOf(PropertiesConfig.getFontValues("outOther_y")), Integer.valueOf(PropertiesConfig.getFontValues("outOther_width")), Integer.valueOf(PropertiesConfig.getFontValues("outOther_height")));
		this.add(TagboxNum_label);
		
		JLabel lblRohs = new JLabel("RoHS2.0");
		lblRohs.setFont(new Font("Microsoft YaHei", Font.BOLD, 28));
		lblRohs.setBounds(307, 155, 120, 41);
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
		
		if(StringUtil.isEmpty(labelPrintLog.getNetWeight())){
			netweigth_label = new JLabel("");
		}else{
			netweigth_label = new JLabel("净重"+labelPrintLog.getNetWeight()+"KG");
			netweigth_label.setFont(font);
			netweigth_label.setBounds(152, 186, 98, 14);
			this.add(netweigth_label);
		}

		if(StringUtil.isEmpty(labelPrintLog.getGrossWeight())){
			grossweith_label = new JLabel("");
		}else{
			grossweith_label = new JLabel("毛重"+labelPrintLog.getGrossWeight()+"KG");
			grossweith_label.setFont(font);
			grossweith_label.setBounds(252, 186, 98, 14);
			this.add(grossweith_label);
		}
		

		imagePannel = new ImagePannel();
		imagePannel.setBounds(307, 49, 115, 115);
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
					+ labelPrintLog.getPN() + '|' + labelPrintLog.getLot_No() +'|' + labelPrintLog.getRemarks() + '|'
					+ labelPrintLog.getAISHINo() + '|' + labelPrintLog.getProductionDate() + '|'
					+ labelPrintLog.getQty1() + '|' + '|'
					+ labelPrintLog.getNetWeight()+ '|' + labelPrintLog.getGrossWeight() + '|'+labelPrintLog.getMaterialName() +'|'+StringUtil.getMaterialProperty(labelPrintLog);
		}else{
			inside_code = labelPrintLog.getPO() + '|' + labelPrintLog.getVendorCode() + '|'
					+ labelPrintLog.getPN() + '|' + labelPrintLog.getLot_No() + '|' + labelPrintLog.getRemarks() + '|'
					+ labelPrintLog.getAISHINo() + '|' + labelPrintLog.getProductionDate() + '|'
					+ labelPrintLog.getQty1() + '|' + labelPrintLog.getQty2() + '|'
					+ labelPrintLog.getNetWeight()+ '|' + labelPrintLog.getGrossWeight() + '|'+labelPrintLog.getMaterialName() +'|'+StringUtil.getMaterialProperty(labelPrintLog);
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
