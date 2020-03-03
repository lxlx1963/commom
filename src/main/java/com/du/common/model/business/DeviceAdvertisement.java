package com.du.common.model.business;

/**
 * 终端广告
 *
 * @author dxy
 * @date 2018/11/6 17:26
 */
public class DeviceAdvertisement {
	/**
	 * 终端广告ID
	 */
	private Long deviceAdvertisementId;
	/**
	 * 机器码
	 */
	private String deviceNumber;
	/**
	 * 广告总数
	 */
	private Integer advertisementTotal;
	/**
	 * 已下载广告数量
	 */
	private Integer downloadedAdvertisementNumber;
	/**
	 * 更新时间
	 */
	private Long updateTime;

	public Long getDeviceAdvertisementId() {
		return deviceAdvertisementId;
	}

	public void setDeviceAdvertisementId(Long deviceAdvertisementId) {
		this.deviceAdvertisementId = deviceAdvertisementId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public Integer getAdvertisementTotal() {
		return advertisementTotal;
	}

	public void setAdvertisementTotal(Integer advertisementTotal) {
		this.advertisementTotal = advertisementTotal;
	}

	public Integer getDownloadedAdvertisementNumber() {
		return downloadedAdvertisementNumber;
	}

	public void setDownloadedAdvertisementNumber(Integer downloadedAdvertisementNumber) {
		this.downloadedAdvertisementNumber = downloadedAdvertisementNumber;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
}
