package com.aihua.label.model;

/**
 * 标签字段信息
 * @author Administrator
 *
 */
public class LabelColunm {
	/**
	 * 标签字段ID
	 */
	private int colId;
	/**
	 * 标签字段中文描述
	 */
	private String colName;
	/**
	 * 标签字段引用函数
	 */
	private String colFunc;
	/**
	 * 标签字段输入类型:暂定
	 */
	private int colInputType;
	/**
	 * 标签字段拼装规则
	 */
	private String comboSource;
	/**
	 * 标签字段状态:1正常0停用
	 */
	private int colState;
	/**
	 * 是否为主键字段:1是,其他否
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
