package com.shz.project.admin.facade.system.permission.operation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import com.shz.project.admin.facade.system.permission.PermissionInputArgs;
import com.shz.project.domain.system.permission.FieldPermission;
import com.shz.project.domain.system.permission.FieldPermissionRepository;
import com.shz.project.domain.system.permission.OperatePermission;

@Component
public class OperatePermissionAdapter 
	extends AbstractDtoAdapter<OperatePermission, OperatePermissionVo, PermissionInputArgs> {

	@Autowired
	private FieldPermissionRepository fieldPermissionRepository;
	
	@Override
	public OperatePermission postConvertToDo(PermissionInputArgs i,
			OperatePermission d) {
		return postUpdate(i, d);
	}

	@Override
	public OperatePermission postUpdate(PermissionInputArgs i,
			OperatePermission d) {
		String fieldId=i.getFieldId();
		if(StringUtils.isNotBlank(fieldId)) {
			FieldPermission field=fieldPermissionRepository.findOne(fieldId);
			if(field==null) {
				throw new IllegalArgumentException("没有关联到正确的权限域");
			}
			d.setField(field);
		}
		return d;
	}
}
