package com.shz.project.admin.facade.system.permission.operation;

import com.shz.project.admin.facade.system.permission.PermissionVo;

public class OperatePermissionVo extends PermissionVo {

	/**操作所属领域*/
	private PermissionVo field;

	public PermissionVo getField() {
		return field;
	}
	public void setField(PermissionVo field) {
		this.field = field;
	}
}
