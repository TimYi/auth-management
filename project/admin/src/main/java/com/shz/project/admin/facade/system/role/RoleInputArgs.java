package com.shz.project.admin.facade.system.role;

import java.util.List;

import com.shz.foundation.mapping.model.DtoBaseModel;

public class RoleInputArgs extends DtoBaseModel {

	/**角色名称*/
	private String name;
	/**角色描述*/
	private String description;
	/**角色通用权限*/
	private List<String> permissionIds;
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
	public List<String> getPermissionIds() {
		return permissionIds;
	}
	public void setPermissionIds(List<String> permissionIds) {
		this.permissionIds = permissionIds;
	}
}
