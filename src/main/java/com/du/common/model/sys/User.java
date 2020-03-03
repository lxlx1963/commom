package com.du.common.model.sys;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 用户主键
	 */
	private Long userId;

	/**
	 * 部门ID
	 */
	private Long departmentId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 姓名
	 */
	private String realname;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 手机号
	 */
	private String mobilePhone;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 添加时间
	 */
	private Long addTime;
	/**
	 * 更新时间
	 */
	private Long updateTime;
	/**
	 * 状态（0：启用；1：禁用）
	 */
	private Integer status;
	/**
	 * 是否是超级管理员用户(0：是; 1：不是)
	 */
	private Integer superAdminUser;
	/**
	 * 城市编码（该字段不保存到数据库）
	 */
	private String cityCode;
	/**
	 * 城市名称（该字段不保存到数据库）
	 */
	private String cityName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSuperAdminUser() {
		return superAdminUser;
	}

	public void setSuperAdminUser(Integer superAdminUser) {
		this.superAdminUser = superAdminUser;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}