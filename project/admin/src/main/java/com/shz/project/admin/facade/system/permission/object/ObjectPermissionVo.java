package com.shz.project.admin.facade.system.permission.object;

import com.shz.project.admin.facade.system.permission.PermissionVo;
import com.shz.project.admin.facade.system.permission.operation.OperatePermissionVo;

public class ObjectPermissionVo extends PermissionVo {

	/**对do的具体操作*/
	private OperatePermissionVo operation;

	public OperatePermissionVo getOperation() {
		return operation;
	}
	public void setOperation(OperatePermissionVo operation) {
		this.operation = operation;
	}
}
