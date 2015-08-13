package com.shz.project.admin.facade.system.permission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.shz.project.admin.facade.system.permission.field.FieldPermissionAdapter;
import com.shz.project.admin.facade.system.permission.object.ObjectPermissionAdapter;
import com.shz.project.admin.facade.system.permission.operation.OperatePermissionAdapter;
import com.shz.project.domain.system.permission.FieldPermission;
import com.shz.project.domain.system.permission.ObjectPermission;
import com.shz.project.domain.system.permission.OperatePermission;
import com.shz.project.domain.system.permission.Permission;

@Component
public class PermissionConverter implements Converter<Permission, PermissionVo> {

	@Autowired
	private FieldPermissionAdapter fieldAdapter;
	@Autowired
	private OperatePermissionAdapter operateAdapter;
	@Autowired
	private ObjectPermissionAdapter objectAdapter;
	
	
	@Override
	public PermissionVo convert(Permission source) {
		switch (source.getType()) {
		case "FIELD":
			return fieldAdapter.convertToDetailedDto((FieldPermission)source);
		case "OPERATE":
			return operateAdapter.convertToDetailedDto((OperatePermission)source);
		case "OBJECT":
			return objectAdapter.convertToDetailedDto((ObjectPermission)source);
		default:
			return null;
		}
	}
	
	public List<PermissionVo> convertList(Iterable<Permission> permissions) {
		if(permissions==null)return null;
		List<PermissionVo> result=new ArrayList<PermissionVo>();
		for (Permission permission : permissions) {
			result.add(convert(permission));
		}
		return result;
	}
}
