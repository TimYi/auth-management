package com.shz.project.admin.facade.system.department;

import java.util.Set;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.admin.facade.system.permission.PermissionVo;
import com.shz.project.admin.facade.system.role.RoleVo;

public class DepartmentRolePermissionsVo extends UUIDBaseModel {

	/**关联角色*/
	private RoleVo role;
	/**部门角色特殊权限*/
	private Set<PermissionVo> permissions;
	public RoleVo getRole() {
		return role;
	}
	public void setRole(RoleVo role) {
		this.role = role;
	}
	public Set<PermissionVo> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<PermissionVo> permissions) {
		this.permissions = permissions;
	}
}
