package com.shz.project.admin.facade.system.role;

import com.shz.foundation.persistence.UUIDBaseModel;

public class RoleVo extends UUIDBaseModel {

	/**角色名称*/
	private String name;
	/**角色描述*/
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
