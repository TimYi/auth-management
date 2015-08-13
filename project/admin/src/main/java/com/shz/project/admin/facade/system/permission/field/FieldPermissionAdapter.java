package com.shz.project.admin.facade.system.permission.field;

import org.springframework.stereotype.Component;

import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import com.shz.project.admin.facade.system.permission.PermissionInputArgs;
import com.shz.project.domain.system.permission.FieldPermission;

@Component
public class FieldPermissionAdapter extends 
	AbstractDtoAdapter<FieldPermission, FieldPermissionVo, PermissionInputArgs> {
	
	@Override
	public FieldPermission postConvertToDo(PermissionInputArgs i,
			FieldPermission d) {
		return d;
	}

	@Override
	public FieldPermission postUpdate(PermissionInputArgs i,
			FieldPermission d) {
		return d;
	}
}
