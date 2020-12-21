package com.aihua.label.model;

/**
 * 供应商信息
 * @author Administrator
 *
 */
public class Company {
	/**
	 * 供应商ID
	 */
	private int companyId;
	/**
	 * 供应商中文描述
	 */
	private String companyName;
	/**
	 * 供应商发货对象:0集团,1富贤,2集团富贤通用
	 */
	private int consignmentId;
	
	private String creator;
	
	private String updator;
	
	/**
	 * 状态:0停用,1正常
	 */
	private int state;
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getConsignmentId() {
		return consignmentId;
	}
	public void setConsignmentId(int consignmentId) {
		this.consignmentId = consignmentId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", consignmentId=" + consignmentId
				+ ", creator=" + creator + ", updator=" + updator + ", state=" + state + "]";
	}
	
}
