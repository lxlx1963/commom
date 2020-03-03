package com.du.common.model.sys;

public class Department {
	/**
	 * 部门ID
	 */
	private Long departmentId;
	/**
	 * 上级ID
	 */
	private Long parentId;
	/**
	 * 部门名称
	 */
	private String departmentName;
	/**
	 * 部门编码
	 */
	private String departmentCode;
	/**
	 * 备注
	 */
	private String departmentDesc;
	/**
	 * 状态（0：启用；1：禁用）
	 */
	private Integer status;
	/**
	 * 添加时间
	 */
	private Long addTime;
	/**
	 * 更新时间
	 */
	private Long updateTime;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName == null ? null : departmentName.trim();
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode == null ? null : departmentCode.trim();
	}

	public String getDepartmentDesc() {
		return departmentDesc;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc == null ? null : departmentDesc.trim();
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

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
}