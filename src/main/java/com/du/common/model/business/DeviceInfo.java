package com.du.common.model.business;

/**
 * 终端信息
 *
 * @author dxy
 * @date 2018/10/9 18:19
 */
public class DeviceInfo {
	/**
	 * 终端信息ID
	 */
	private Long deviceInfoId;
	/**
	 * 机器码
	 */
	private String deviceNumber;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 行政区域
	 */
	private String administrativeRegions;
	/**
	 * 详细地址
	 */
	private String detailAddress;

	public Long getDeviceInfoId() {
		return deviceInfoId;
	}

	public void setDeviceInfoId(Long deviceInfoId) {
		this.deviceInfoId = deviceInfoId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdministrativeRegions() {
		return administrativeRegions;
	}

	public void setAdministrativeRegions(String administrativeRegions) {
		this.administrativeRegions = administrativeRegions;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
}
