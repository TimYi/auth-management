package com.shz.project.admin.facade.system.permission.field;

import org.springframework.stereotype.Component;

import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import com.shz.project.domain.system.permission.FieldPermission;

@Component
public class FieldPermissionAdapter extends 
	AbstractDtoAdapter<FieldPermission, FieldPermissionVo, FieldPermissionInputArgs> {
	
	@Override
	public FieldPermission postConvertToDo(FieldPermissionInputArgs i,
			FieldPermission d) {
		return d;
	}

	@Override
	public FieldPermission postUpdate(FieldPermissionInputArgs i,
			FieldPermission d) {
		return d;
	}
}
