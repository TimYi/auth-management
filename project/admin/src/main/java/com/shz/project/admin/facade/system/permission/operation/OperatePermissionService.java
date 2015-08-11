package com.shz.project.admin.facade.system.permission.operation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.project.domain.system.permission.FieldPermission;
import com.shz.project.domain.system.permission.FieldPermissionRepository;
import com.shz.project.domain.system.permission.OperatePermission;
import com.shz.project.domain.system.permission.OperatePermissionRepository;
import com.shz.foundation.mapping.service.DtoPagingService;

@Service
@Transactional
public class OperatePermissionService extends 
	DtoPagingService<OperatePermission, OperatePermissionVo, OperatePermissionInputArgs, String> {
	
	@Autowired
	private FieldPermissionRepository fieldPermissionRepository;

	/**
	 * 确保同一个field的操作code不重复，并且确保fieldId不为空
	 */
	@Override
	public OperatePermissionVo add(OperatePermissionInputArgs entity) {
		if(entity==null) return null;		
		
		if(StringUtils.isBlank(entity.getId())) {
			throw new IllegalArgumentException("权限域id不能为空");
		}
		
		OperatePermission d=adapter.convertToDo(entity);
		//确保统一操作域权限码不重复
		String code=d.getCode();
		FieldPermission field=d.getField();		
		OperatePermission permission=getRepository().getByCodeAndField(code, field);
		if(permission!=null) {
			throw new IllegalArgumentException("统一权限域下的操作码重复");
		}
		
		d=getRepository().save(d);
		OperatePermissionVo result=adapter.convertToDetailedDto(d);
		return result;
	}
	
	/**
	 * 确保更新时不会造成统一权限域的操作码重复
	 * @param entity
	 * @return
	 */
	@Override
	public OperatePermissionVo update(OperatePermissionInputArgs entity) {
		if(entity==null) return null;
		String id=entity.getId();
		OperatePermission d=getRepository().findOne(id);
		if(d==null) return null;
		
		//确保更新时不会造成统一权限域的操作码重复
		String fieldId=entity.getFieldId();
		String code=entity.getCode();
		FieldPermission field;
		if(StringUtils.isNotBlank(fieldId)) {
			field=d.getField();
		} else {
			field=fieldPermissionRepository.findOne(fieldId);
		}
		OperatePermission operate=getRepository().getByCodeAndField(code, field);
		if(operate!=null && !operate.getId().equals(d.getId())) {
			throw new IllegalArgumentException("统一权限域下的操作码重复");
		}
		
		d=adapter.update(entity, d);
		d=getRepository().save(d);
		OperatePermissionVo result=adapter.convertToDetailedDto(d);
		return result;
	}
	
	@Override
	public OperatePermissionRepository getRepository() {
		return (OperatePermissionRepository)super.getRepository();
	}
}
