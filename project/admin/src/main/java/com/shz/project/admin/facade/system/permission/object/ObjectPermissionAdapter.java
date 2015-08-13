package com.shz.project.admin.facade.system.permission.object;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shz.project.admin.facade.system.permission.PermissionInputArgs;
import com.shz.project.domain.system.permission.ObjectPermission;
import com.shz.project.domain.system.permission.OperatePermission;
import com.shz.project.domain.system.permission.OperatePermissionRepository;
import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;

@Component
public class ObjectPermissionAdapter extends 
	AbstractDtoAdapter<ObjectPermission, ObjectPermissionVo, PermissionInputArgs> {
	
	@Autowired
	private OperatePermissionRepository operatePermissionRepository;
	
	@Override
	public ObjectPermission postConvertToDo(PermissionInputArgs i,
			ObjectPermission d) {
		return postUpdate(i, d);
	}

	@Override
	public ObjectPermission postUpdate(PermissionInputArgs i,
			ObjectPermission d) {
		String operateId=i.getOperationId();
		if(StringUtils.isNotBlank(operateId)) {
			OperatePermission operation=operatePermissionRepository.findOne(operateId);
			if(operation==null) {
				throw new IllegalArgumentException("获取不到指定关联操作");
			}
			d.setOperation(operation);
		}
		return d;
	}
}
