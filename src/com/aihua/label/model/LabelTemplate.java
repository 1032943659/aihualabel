package com.aihua.label.model;

/**
 * ��ǩģ�������Ϣ
 * @author Administrator
 *
 */
public class LabelTemplate {
	/**
	 * ��ǩģ��ID
	 */
	private int labelId;
	/**
	 * ��ǩģ�����
	 */
	private String labelCode;
	/**
	 * ��ǩģ����������
	 */
	private String labelName;
	/**
	 * ��ǩģ�幩Ӧ��ID
	 */
	private int companyId;
	/**
	 * ��װ����:0����,1����
	 */
	private int packageType;
	/**
	 * ��ǩģ���ŵ�ַ
	 */
	private String labelTemplateURL;
	/**
	 * ��ǩģ���ֶ���Ϣ
	 */
	private String labelTemplateCol;
	/**
	 * ��ǩģ��߶�
	 */
	private int labelHeight;
	/**
	 * ��ǩģ����
	 */
	private int labelWidth;
	/**
	 * ��ǩģ��״̬:1����0ͣ��
	 */
	private int labelState;
	/**
	 * �ӱ�ǩģ��ID:NULLΪ��ǰ��ǩģ��Ϊ�����ǩģ��
	 */
	private int mappingChildLabelId;
	/**
	 * ��ˮ�Ÿ�ʽ����ģ��
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
