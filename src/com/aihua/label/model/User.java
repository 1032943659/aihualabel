package com.aihua.label.model;

/**
 * �û�
 * @author Administrator
 *
 */
public class User {
	/**
	 * ��½�û�ID
	 */
	private int userId;
	/**
	 * ��½�û�����
	 */
	private String userPassword;
	/**
	 * ��½��Ӧ��ID
	 */
	private int userCompanyId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getUserCompanyId() {
		return userCompanyId;
	}
	public void setUserCompanyId(int userCompanyId) {
		this.userCompanyId = userCompanyId;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPassword=" + userPassword + ", userCompanyId=" + userCompanyId + "]";
	}
	
}
