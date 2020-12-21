package com.aihua.label.model;

/**
 * 用户
 * @author Administrator
 *
 */
public class User {
	/**
	 * 登陆用户ID
	 */
	private int userId;
	/**
	 * 登陆用户密码
	 */
	private String userPassword;
	/**
	 * 登陆供应商ID
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
