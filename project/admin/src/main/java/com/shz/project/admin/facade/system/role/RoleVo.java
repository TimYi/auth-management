package com.shz.project.admin.facade.system.role;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.domain.system.role.Role.RoleType;

public class RoleVo extends UUIDBaseModel {

	/**角色名称*/
	private String name;
	/**角色类型*/
	private RoleType type;
	/**角色描述*/
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RoleType getType() {
		return type;
	}
	public void setType(RoleType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
