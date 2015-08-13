package com.shz.project.admin.facade.system.permission.field;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.project.admin.facade.system.permission.PermissionInputArgs;
import com.shz.project.domain.system.permission.FieldPermission;
import com.shz.project.domain.system.permission.FieldPermissionRepository;
import com.shz.foundation.mapping.service.DtoPagingService;

@Service
@Transactional
public class FieldPermissionService extends 
	DtoPagingService<FieldPermission, FieldPermissionVo, PermissionInputArgs, String> {

	/**需要保证添加时，fieldPermission的code没有重复值*/
	@Override
	public FieldPermissionVo add(PermissionInputArgs entity) {
		if(entity==null) return null;
		
		//保证权限代码不重复
		String code=entity.getCode();
		FieldPermission permission=getRepository().getByCode(code);
		if(permission!=null) {
			throw new IllegalArgumentException("权限代码和已有权限冲突！");
		}
		
		FieldPermission d=adapter.convertToDo(entity);
		d=getRepository().save(d);
		FieldPermissionVo result=adapter.convertToDetailedDto(d);
		return result;
	}
	
	@Override
	public FieldPermissionVo update(PermissionInputArgs entity) {
		if(entity==null) return null;
		//保证权限代码不重复
		String code=entity.getCode();
		FieldPermission permission=getRepository().getByCode(code);
		if(permission!=null && !permission.getId().equals(entity.getId())) {
			throw new IllegalArgumentException("权限代码和已有权限冲突！");
		}
		
		String id=entity.getId();
		FieldPermission d=getRepository().findOne(id);
		if(d==null) return null;
		d=adapter.update(entity, d);
		d=getRepository().save(d);
		FieldPermissionVo result=adapter.convertToDetailedDto(d);
		return result;
	}
	
	@Override
	public FieldPermissionRepository getRepository() {
		return (FieldPermissionRepository)super.getRepository();
	}
}
