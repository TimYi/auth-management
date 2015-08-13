package com.shz.project.admin.facade.system.department;

import java.util.List;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.admin.facade.system.department.role.DepartmentRoleDto;
import com.shz.project.admin.facade.system.user.SystemUserVo;

public class DepartmentDto extends UUIDBaseModel {

	/**部门名称*/
	private String name;
	/**部门描述*/
	private String desrciption;
	/**部门用户*/
	private List<SystemUserVo> users;
	/**部门角色*/
	private List<DepartmentRoleDto> roles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesrciption() {
		return desrciption;
	}
	public void setDesrciption(String desrciption) {
		this.desrciption = desrciption;
	}
	public List<SystemUserVo> getUsers() {
		return users;
	}
	public void setUsers(List<SystemUserVo> users) {
		this.users = users;
	}
	public List<DepartmentRoleDto> getRoles() {
		return roles;
	}
	public void setRoles(List<DepartmentRoleDto> roles) {
		this.roles = roles;
	}
}
