package com.du.common.model.sys;

/**
 * 模块（菜单）
 */
public class Module {
	/**
	 * 模块主键
	 */
	private Long moduleId;
	/**
	 * 上级模块
	 */
	private Long parentId;
	/**
	 * 模块名
	 */
	private String moduleName;
	/**
	 * 模块描述
	 */
	private String moduleDesc;
	/**
	 * 链接
	 */
	private String url;
	/**
	 * icon
	 */
	private String icon;
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

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

}