package com.shz.project.admin.facade.system.permission.object;

import org.apache.commons.lang3.StringUtils;

import com.shz.project.domain.system.permission.ObjectPermission;
import com.shz.project.domain.system.permission.ObjectPermissionRepository;
import com.shz.project.domain.system.permission.OperatePermission;
import com.shz.foundation.mapping.service.DtoPagingService;

public class ObjectPermissionService extends 
	DtoPagingService<ObjectPermission, ObjectPermissionVo, ObjectPermissionInputArgs, String> {

	@Override
	public ObjectPermissionVo add(ObjectPermissionInputArgs entity) {
		if(entity==null) return null;
		String operationId=entity.getOperationId();
		if(StringUtils.isBlank(operationId)) {
			throw new IllegalArgumentException("操作id不能为空");
		}
		
		ObjectPermission d=adapter.convertToDo(entity);
		
		OperatePermission operation=d.getOperation();
		String code=d.getCode();
		ObjectPermission permission=getRepository().getByCodeAndOperation(code, operation);
		if(permission!=null) {
			throw new IllegalArgumentException("统一操作权限下，资源code重复");
		}
		
		d=getRepository().save(d);
		ObjectPermissionVo result=adapter.convertToDetailedDto(d);
		return result;
	}
	@Override
	public ObjectPermissionVo update(ObjectPermissionInputArgs entity) {
		if(entity==null) return null;
		String id=entity.getId();
		ObjectPermission d=getRepository().findOne(id);
		if(d==null) return null;
		
		//TODO
		
		d=adapter.update(entity, d);
		d=getRepository().save(d);
		ObjectPermissionVo result=adapter.convertToDetailedDto(d);
		return result;
	}
	
	@Override
	public ObjectPermissionRepository getRepository() {
		return (ObjectPermissionRepository)super.getRepository();
	}
}
