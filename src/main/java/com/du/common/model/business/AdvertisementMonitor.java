package com.du.common.model.business;

/**
 * 广告监播
 *
 * @author dxy
 * @date 2018/9/20 15:53
 */
public class AdvertisementMonitor {
	/**
	 * 广告监播ID
	 */
	private Long advertisementMonitorId;
	/**
	 * 广告主ID
	 */
	private Long advertiserId;
	/**
	 * 机器码
	 */
	private String deviceNumber;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 广告编码
	 */
	private String advertisementCode;
	/**
	 * 广告名称
	 */
	private String advertisementName;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 曝光次数
	 */
	private Double exposuresNumber;
	/**
	 * 触达人次
	 */
	private Integer touchNumber;
	/**
	 * 观看人次
	 */
	private Integer watchNumber;
	/**
	 * 观看时长
	 */
	private Integer playDuration;
	/**
	 * 播放时长
	 */
	private Double duration;
	/**
	 * 播放时间
	 */
	private Long playTime;
	/**
	 *  屏幕类型
	 */
	private Integer screenType;
	/**
	 * 终端类型（2:2代机；3:3代机）
	 */
	private Integer deviceType;
	/**
	 * 添加时间
	 */
	private Long addTime;

	public Long getAdvertisementMonitorId() {
		return advertisementMonitorId;
	}

	public void setAdvertisementMonitorId(Long advertisementMonitorId) {
		this.advertisementMonitorId = advertisementMonitorId;
	}

	public Long getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(Long advertiserId) {
		this.advertiserId = advertiserId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAdvertisementCode() {
		return advertisementCode;
	}

	public void setAdvertisementCode(String advertisementCode) {
		this.advertisementCode = advertisementCode;
	}

	public String getAdvertisementName() {
		return advertisementName;
	}

	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Double getExposuresNumber() {
		return exposuresNumber;
	}

	public void setExposuresNumber(Double exposuresNumber) {
		this.exposuresNumber = exposuresNumber;
	}

	public Integer getTouchNumber() {
		return touchNumber;
	}

	public void setTouchNumber(Integer touchNumber) {
		this.touchNumber = touchNumber;
	}

	public Integer getWatchNumber() {
		return watchNumber;
	}

	public void setWatchNumber(Integer watchNumber) {
		this.watchNumber = watchNumber;
	}

	public Integer getPlayDuration() {
		return playDuration;
	}

	public void setPlayDuration(Integer playDuration) {
		this.playDuration = playDuration;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public Long getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Long playTime) {
		this.playTime = playTime;
	}

	public Integer getScreenType() {
		return screenType;
	}

	public void setScreenType(Integer screenType) {
		this.screenType = screenType;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
}
