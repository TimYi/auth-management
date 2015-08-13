package com.shz.project.admin.facade.system.department.role;

import java.util.List;

import com.shz.foundation.mapping.model.DtoBaseModel;

public class DepartmentRoleInputArgs extends DtoBaseModel {

	private String departmentId;
	private String roleId;	
	private List<String> permissionIds;
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public List<String> getPermissionIds() {
		return permissionIds;
	}
	public void setPermissionIds(List<String> permissionIds) {
		this.permissionIds = permissionIds;
	}
}
