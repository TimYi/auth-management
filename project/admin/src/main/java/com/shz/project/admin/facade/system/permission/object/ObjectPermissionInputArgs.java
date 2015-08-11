package com.shz.project.admin.facade.system.permission.object;

import com.shz.project.admin.facade.system.permission.PermissionInputArgs;

public class ObjectPermissionInputArgs extends PermissionInputArgs {

	private String operationId;

	public String getOperationId() {
		return operationId;
	}
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
}
