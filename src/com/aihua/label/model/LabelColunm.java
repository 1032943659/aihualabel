package com.aihua.label.model;

/**
 * ��ǩ�ֶ���Ϣ
 * @author Administrator
 *
 */
public class LabelColunm {
	/**
	 * ��ǩ�ֶ�ID
	 */
	private int colId;
	/**
	 * ��ǩ�ֶ���������
	 */
	private String colName;
	/**
	 * ��ǩ�ֶ����ú���
	 */
	private String colFunc;
	/**
	 * ��ǩ�ֶ���������:�ݶ�
	 */
	private int colInputType;
	/**
	 * ��ǩ�ֶ�ƴװ����
	 */
	private String comboSource;
	/**
	 * ��ǩ�ֶ�״̬:1����0ͣ��
	 */
	private int colState;
	/**
	 * �Ƿ�Ϊ�����ֶ�:1��,������
	 */
	private int colPrimaryKey;
	public int getColId() {
		return colId;
	}
	public void setColId(int colId) {
		this.colId = colId;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getColFunc() {
		return colFunc;
	}
	public void setColFunc(String colFunc) {
		this.colFunc = colFunc;
	}
	public int getColInputType() {
		return colInputType;
	}
	public void setColInputType(int colInputType) {
		this.colInputType = colInputType;
	}
	public String getComboSource() {
		return comboSource;
	}
	public void setComboSource(String comboSource) {
		this.comboSource = comboSource;
	}
	public int getColState() {
		return colState;
	}
	public void setColState(int colState) {
		this.colState = colState;
	}
	public int getColPrimaryKey() {
		return colPrimaryKey;
	}
	public void setColPrimaryKey(int colPrimaryKey) {
		this.colPrimaryKey = colPrimaryKey;
	}
}
