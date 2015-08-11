package com.shz.project.admin.facade.system.permission;

import com.shz.foundation.persistence.UUIDBaseModel;

public abstract class PermissionVo extends UUIDBaseModel {

	/**权限名称*/
	private String name;
	/**权限代码*/
	private String code;
	/**权限描述*/
	private String description;	
	/**权限类型*/
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
