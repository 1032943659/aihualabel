package com.label.model;

import java.util.Date;

/**
 * 集团打印信息model
 * @author ZY
 */
public class LabelPrintLog {
	private int n_Id;
	private String n_n_Id;
	private String PO;
	private String VendorCode;
	private String Vendor;
	private String Item;
	private String Lot_No;
	private String PN;
	private String AISHINo;
	private String Qty1;
	private String Qty2;
	private String Unit1;
	private String Unit2;
	private String production_Date;
	private String net_Weight;
	private String gross_Weight;
	private int Status;
	private int BoxType;
	private String SerialNumber;//流水号->箱号
	private String Creator;
	private Date create_Time;
	private String Updator;
	private String remarks;
	private String update_Time;
	private String MaterialName;
	private String material_Property;
	
	
	public String getMaterialName() {
		return MaterialName;
	}
	public void setMaterialName(String materialName) {
		MaterialName = materialName;
	}
	public String getMaterial_Property() {
		return material_Property;
	}
	public void setMaterial_Property(String material_Property) {
		this.material_Property = material_Property;
	}
	public String getN_n_Id() {
		return n_n_Id;
	}
	public void setN_n_Id(String n_n_Id) {
		this.n_n_Id = n_n_Id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getN_Id() {
		return n_Id;
	}
	public void setN_Id(int n_Id) {
		this.n_Id = n_Id;
	}
	public String getPO() {
		return PO;
	}
	public void setPO(String pO) {
		PO = pO;
	}
	public String getVendorCode() {
		return VendorCode;
	}
	public void setVendorCode(String vendorCode) {
		VendorCode = vendorCode;
	}
	public String getVendor() {
		return Vendor;
	}
	public void setVendor(String vendor) {
		Vendor = vendor;
	}
	public String getItem() {
		return Item;
	}
	public void setItem(String item) {
		Item = item;
	}
	public String getLot_No() {
		return Lot_No;
	}
	public void setLot_No(String lot_No) {
		Lot_No = lot_No;
	}
	public String getPN() {
		return PN;
	}
	public void setPN(String pN) {
		PN = pN;
	}
	public String getAISHINo() {
		return AISHINo;
	}
	public void setAISHINo(String aISHINo) {
		AISHINo = aISHINo;
	}
	
	public String getQty1() {
		return Qty1;
	}
	public void setQty1(String qty1) {
		Qty1 = qty1;
	}
	public String getQty2() {
		return Qty2;
	}
	public void setQty2(String qty2) {
		Qty2 = qty2;
	}
	public String getUnit1() {
		return Unit1;
	}
	public void setUnit1(String unit1) {
		Unit1 = unit1;
	}
	public String getUnit2() {
		return Unit2;
	}
	public void setUnit2(String unit2) {
		Unit2 = unit2;
	}
	public String getProduction_Date() {
		return production_Date;
	}
	public void setProduction_Date(String string) {
		this.production_Date = string;
	}
	public String getNet_Weight() {
		return net_Weight;
	}
	public void setNet_Weight(String net_Weight) {
		this.net_Weight = net_Weight;
	}
	public String getGross_Weight() {
		return gross_Weight;
	}
	public void setGross_Weight(String gross_Weight) {
		this.gross_Weight = gross_Weight;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getBoxType() {
		return BoxType;
	}
	public void setBoxType(int boxType) {
		BoxType = boxType;
	}
	public String getSerialNumber() {
		return SerialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	public String getCreator() {
		return Creator;
	}
	public void setCreator(String creator) {
		Creator = creator;
	}
	public Date getCreate_Time() {
		return create_Time;
	}
	public void setCreate_Time(Date create_Time) {
		this.create_Time = create_Time;
	}
	public String getUpdator() {
		return Updator;
	}
	public void setUpdator(String updator) {
		Updator = updator;
	}
	public String getUpdate_Time() {
		return update_Time;
	}
	public void setUpdate_Time(String update_Time) {
		this.update_Time = update_Time;
	}
	
}
