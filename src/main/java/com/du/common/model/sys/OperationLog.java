package com.du.common.model.sys;

public class OperationLog {
	/**
	 * 操作日子ID
	 */
	private Long operationLogId;
	/**
	 * 操作用户
	 */
	private String userName;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 操作模块
	 */
	private String operationModule;
	/**
	 * 操作内容
	 */
	private String operationContent;
	/**
	 * 操作状态
	 */
	private Integer operationStatus;
	/**
	 * 操作ID地址
	 */
	private String operationIp;
	/**
	 * 操作时间
	 */
	private Long operationTime;

	public Long getOperationLogId() {
		return operationLogId;
	}

	public void setOperationLogId(Long operationLogId) {
		this.operationLogId = operationLogId;
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

	public String getOperationModule() {
		return operationModule;
	}

	public void setOperationModule(String operationModule) {
		this.operationModule = operationModule;
	}

	public String getOperationContent() {
		return operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	public Integer getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(Integer operationStatus) {
		this.operationStatus = operationStatus;
	}

	public String getOperationIp() {
		return operationIp;
	}

	public void setOperationIp(String operationIp) {
		this.operationIp = operationIp;
	}

	public Long getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Long operationTime) {
		this.operationTime = operationTime;
	}

}