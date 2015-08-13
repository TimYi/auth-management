package com.shz.project.admin.facade.system.department.role;

import java.util.List;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.admin.facade.system.permission.PermissionVo;
import com.shz.project.admin.facade.system.role.RoleVo;

public class DepartmentRoleDto extends UUIDBaseModel {

	/**关联角色*/
	private RoleVo role;
	/**部门角色特殊权限*/
	private List<PermissionVo> permissions;
	
	public RoleVo getRole() {
		return role;
	}
	public void setRole(RoleVo role) {
		this.role = role;
	}
	public List<PermissionVo> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionVo> permissions) {
		this.permissions = permissions;
	}
	
	public String getName() {
		return role.getName();
	}
}
