package com.aihua.label.model;

/**
 * 标签模板基本信息
 * @author Administrator
 *
 */
public class LabelTemplate {
	/**
	 * 标签模板ID
	 */
	private int labelId;
	/**
	 * 标签模板编码
	 */
	private String labelCode;
	/**
	 * 标签模板中文描述
	 */
	private String labelName;
	/**
	 * 标签模板供应商ID
	 */
	private int companyId;
	/**
	 * 包装类型:0内箱,1外箱
	 */
	private int packageType;
	/**
	 * 标签模板存放地址
	 */
	private String labelTemplateURL;
	/**
	 * 标签模板字段信息
	 */
	private String labelTemplateCol;
	/**
	 * 标签模板高度
	 */
	private int labelHeight;
	/**
	 * 标签模板宽度
	 */
	private int labelWidth;
	/**
	 * 标签模板状态:1可用0停用
	 */
	private int labelState;
	/**
	 * 子标签模板ID:NULL为当前标签模板为内箱标签模板
	 */
	private int mappingChildLabelId;
	/**
	 * 流水号格式生成模板
	 */
	private String serialCode;
	
	public String getLabelCode() {
		return labelCode;
	}
	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getPackageType() {
		return packageType;
	}
	public void setPackageType(int packageType) {
		this.packageType = packageType;
	}
	public String getLabelTemplateURL() {
		return labelTemplateURL;
	}
	public void setLabelTemplateURL(String labelTemplateURL) {
		this.labelTemplateURL = labelTemplateURL;
	}
	public String getLabelTemplateCol() {
		return labelTemplateCol;
	}
	public void setLabelTemplateCol(String labelTemplateCol) {
		this.labelTemplateCol = labelTemplateCol;
	}
	public int getLabelHeight() {
		return labelHeight;
	}
	public void setLabelHeight(int labelHeight) {
		this.labelHeight = labelHeight;
	}
	public int getLabelWidth() {
		return labelWidth;
	}
	public void setLabelWidth(int labelWidth) {
		this.labelWidth = labelWidth;
	}
	public int getLabelState() {
		return labelState;
	}
	public void setLabelState(int labelState) {
		this.labelState = labelState;
	}
	public int getMappingChildLabelId() {
		return mappingChildLabelId;
	}
	public void setMappingChildLabelId(int mappingChildLabelId) {
		this.mappingChildLabelId = mappingChildLabelId;
	}
	public String getSerialCode() {
		return serialCode;
	}
	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}
	public int getLabelId() {
		return labelId;
	}
	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	
	
}
