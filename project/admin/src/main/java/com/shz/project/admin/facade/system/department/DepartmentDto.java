package com.shz.project.admin.facade.system.department;

import java.util.List;
import java.util.Set;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.admin.facade.system.user.SystemUserVo;

public class DepartmentDto extends UUIDBaseModel {

	/**部门名称*/
	private String name;
	/**部门描述*/
	private String description;
	/**部门用户*/
	private List<SystemUserVo> users;
	/**部门角色*/
	private Set<DepartmentRolePermissionsVo> roles;
	
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
	public List<SystemUserVo> getUsers() {
		return users;
	}
	public void setUsers(List<SystemUserVo> users) {
		this.users = users;
	}
	public Set<DepartmentRolePermissionsVo> getRoles() {
		return roles;
	}
	public void setRoles(Set<DepartmentRolePermissionsVo> roles) {
		this.roles = roles;
	}
}
