package com.aihua.label.model;

/**
 * ��Ӧ����Ϣ
 * @author Administrator
 *
 */
public class Company {
	/**
	 * ��Ӧ��ID
	 */
	private int companyId;
	/**
	 * ��Ӧ����������
	 */
	private String companyName;
	/**
	 * ��Ӧ�̷�������:0����,1����,2���Ÿ���ͨ��
	 */
	private int consignmentId;
	
	private String creator;
	
	private String updator;
	
	/**
	 * ״̬:0ͣ��,1����
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
