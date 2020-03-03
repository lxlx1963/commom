package com.du.common.model.business;

import java.sql.Timestamp;

/**
 * SensetimeSdkStatus
 * @author dxy
 * @date 2019/8/12 15:00
 */
public class SensetimeSdkStatus {
	/**
	 * 主键
	 */
	private Long sensetimeSdkStatusId;
	/**
	 * 机器码
	 */
	private String deviceNumber;
	/**
	 * 商汤sdk状态
	 */
	private String sensetimeSdkStatus;
	/**
	 * 添加时间
	 */
	private Timestamp addTime;
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;

	public Long getSensetimeSdkStatusId() {
		return sensetimeSdkStatusId;
	}

	public void setSensetimeSdkStatusId(Long sensetimeSdkStatusId) {
		this.sensetimeSdkStatusId = sensetimeSdkStatusId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getSensetimeSdkStatus() {
		return sensetimeSdkStatus;
	}

	public void setSensetimeSdkStatus(String sensetimeSdkStatus) {
		this.sensetimeSdkStatus = sensetimeSdkStatus;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
