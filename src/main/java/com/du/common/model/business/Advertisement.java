package com.du.common.model.business;

/**
 * 广告
 * @author dxy
 * @date 2018/9/18 17:02
 */
public class Advertisement {
	/**
	 * 广告ID
	 */
	private Long advertisementId;
	/**
	 * 广告主ID
	 */
	private Long advertiserId;
	/**
	 * 广告状态(0：待审核，1：上线，2，下线)
	 */
	private Integer advertisementStatus;
	/**
	 * 广告名称
	 */
	private String advertisementName;
	/**
	 * 广告品牌
	 */
	private String advertisementBrand;
	/**
	 * 广告编码
	 */
	private String advertisementCode;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 上屏视频地址
	 */
	private String topVideoURL;
	/**
	 * 视频md5
	 */
	private String videoMd5;
	/**
	 * 视频名称
	 */
	private String videoName;
	/**
	 * mp4视频地址
	 */
	private String mp4VideoURL;
	/**
	 * mp4视频md5
	 */
	private String mp4VideoMd5;
	/**
	 * 下屏图片地址
	 */
	private String bottomImageURL;
	/**
	 * 图片md5
	 */
	private String imageMd5;
	/**
	 * 图片名称
	 */
	private String imageName;
	/**
	 * 时长
	 */
	private Long length;
	/**
	 * 是否是品宣广告（0：是；1：否）
	 */
	private Integer declareGoodsAdvertisement;
	/**
	 * 添加时间
	 */
	private Long addTime;
	/**
	 * 更新时间
	 */
	private Long updateTime;

	/**
	 * 状态（0：删除；1：正常）
	 */
	private Integer status;


	public Long getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}

	public Long getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(Long advertiserId) {
		this.advertiserId = advertiserId;
	}

	public Integer getAdvertisementStatus() {
		return advertisementStatus;
	}

	public void setAdvertisementStatus(Integer advertisementStatus) {
		this.advertisementStatus = advertisementStatus;
	}

	public String getAdvertisementName() {
		return advertisementName;
	}

	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}

	public String getAdvertisementBrand() {
		return advertisementBrand;
	}

	public void setAdvertisementBrand(String advertisementBrand) {
		this.advertisementBrand = advertisementBrand;
	}

	public String getAdvertisementCode() {
		return advertisementCode;
	}

	public void setAdvertisementCode(String advertisementCode) {
		this.advertisementCode = advertisementCode;
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

	public String getTopVideoURL() {
		return topVideoURL;
	}

	public void setTopVideoURL(String topVideoURL) {
		this.topVideoURL = topVideoURL;
	}

	public String getVideoMd5() {
		return videoMd5;
	}

	public void setVideoMd5(String videoMd5) {
		this.videoMd5 = videoMd5;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getMp4VideoURL() {
		return mp4VideoURL;
	}

	public void setMp4VideoURL(String mp4VideoURL) {
		this.mp4VideoURL = mp4VideoURL;
	}

	public String getMp4VideoMd5() {
		return mp4VideoMd5;
	}

	public void setMp4VideoMd5(String mp4VideoMd5) {
		this.mp4VideoMd5 = mp4VideoMd5;
	}

	public String getBottomImageURL() {
		return bottomImageURL;
	}

	public void setBottomImageURL(String bottomImageURL) {
		this.bottomImageURL = bottomImageURL;
	}

	public String getImageMd5() {
		return imageMd5;
	}

	public void setImageMd5(String imageMd5) {
		this.imageMd5 = imageMd5;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public Integer getDeclareGoodsAdvertisement() {
		return declareGoodsAdvertisement;
	}

	public void setDeclareGoodsAdvertisement(Integer declareGoodsAdvertisement) {
		this.declareGoodsAdvertisement = declareGoodsAdvertisement;
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
}
