package com.shz.project.admin.facade.system.permission;

import com.shz.foundation.mapping.model.DtoBaseModel;

public abstract class PermissionInputArgs extends DtoBaseModel {

	/**权限名称*/
	private String name;
	/**权限代码*/
	private String code;
	/**权限描述*/
	private String description;
	
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
}
