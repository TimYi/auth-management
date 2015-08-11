package com.shz.project.admin.facade.system.permission.field;

import java.util.Set;

import com.shz.project.admin.facade.system.permission.PermissionVo;

public class FieldPermissionVo extends PermissionVo {

	private Set<PermissionVo> operations;

	public Set<PermissionVo> getOperations() {
		return operations;
	}
	public void setOperations(Set<PermissionVo> operations) {
		this.operations = operations;
	}
}
