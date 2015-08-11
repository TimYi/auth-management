package com.shz.project.admin.facade.system.permission.operation;

import com.shz.project.admin.facade.system.permission.PermissionInputArgs;

public class OperatePermissionInputArgs extends PermissionInputArgs {

	private String fieldId;

	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
}
