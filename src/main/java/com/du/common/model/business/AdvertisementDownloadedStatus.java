package com.du.common.model.business;

import java.sql.Timestamp;

/**
 * AdvertisementDownloadedStatus
 *
 * @author dxy
 * @date 2019/8/12 15:31
 */
public class AdvertisementDownloadedStatus {
	/**
	 * 主键
	 */
	private Long advertisementSownloadedStatusId;
	/**
	 * 机器码
	 */
	private String deviceNumber;
	/**
	 * md5值
	 */
	private String md5;
	/**
	 * 媒体类型
	 */
	private String mediaType;
	/**
	 * 下载状态
	 */
	private String downloadedStatus;
	/**
	 * 添加时间
	 */
	private Timestamp addTime;
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;

	public Long getAdvertisementSownloadedStatusId() {
		return advertisementSownloadedStatusId;
	}

	public void setAdvertisementSownloadedStatusId(Long advertisementSownloadedStatusId) {
		this.advertisementSownloadedStatusId = advertisementSownloadedStatusId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getDownloadedStatus() {
		return downloadedStatus;
	}

	public void setDownloadedStatus(String downloadedStatus) {
		this.downloadedStatus = downloadedStatus;
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
