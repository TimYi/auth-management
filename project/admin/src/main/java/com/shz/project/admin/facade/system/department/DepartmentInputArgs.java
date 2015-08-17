package com.shz.project.admin.facade.system.department;

import java.util.List;
import java.util.Map;

import com.shz.foundation.mapping.model.DtoBaseModel;

public class DepartmentInputArgs extends DtoBaseModel {

	/**部门名称*/
	private String name;
	/**部门描述*/
	private String description;
	/**部门角色*/
	private Map<String, List<String>> rolePermissions;
	
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
	public Map<String, List<String>> getRolePermissions() {
		return rolePermissions;
	}
	public void setRolePermissions(Map<String, List<String>> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
}
