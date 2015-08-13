package com.shz.project.admin.facade.system.permission.object;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.project.admin.facade.system.permission.PermissionInputArgs;
import com.shz.project.domain.system.permission.ObjectPermission;
import com.shz.project.domain.system.permission.ObjectPermissionRepository;
import com.shz.project.domain.system.permission.OperatePermission;
import com.shz.foundation.mapping.service.DtoPagingService;

@Service
@Transactional
public class ObjectPermissionService extends 
	DtoPagingService<ObjectPermission, ObjectPermissionVo, PermissionInputArgs, String> {

	@Override
	public ObjectPermissionVo add(PermissionInputArgs entity) {
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
	public ObjectPermissionVo update(PermissionInputArgs entity) {
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
