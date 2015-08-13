package com.shz.project.admin.facade.system.role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shz.foundation.mapping.BeanMapper;
import com.shz.foundation.mapping.adapter.SimpleDtoAdapter;
import com.shz.project.domain.system.permission.Permission;
import com.shz.project.domain.system.permission.PermissionRepository;
import com.shz.project.domain.system.role.Role;

@Component
public class RoleAdapter extends SimpleDtoAdapter<Role, RoleDto, RoleInputArgs> {
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Role convertToDo(RoleInputArgs i) {
		Role role=new Role();
		return update(i, role);
	}

	@Override
	public Role update(RoleInputArgs i, Role d) {
		d.setName(i.getName());
		d.setDescription(i.getDescription());
		List<String> permissionIds=i.getPermissionIds();
		if(permissionIds!=null) {
			Set<Permission> permissions=new HashSet<>();
			for (String permissionId : permissionIds) {
				Permission permission=permissionRepository.findOne(permissionId);
				permissions.add(permission);
			}
			d.setPermissions(permissions);
		}
		return d;
	}

	@Override
	public RoleDto convert(Role source) {
		return BeanMapper.map(source, RoleDto.class);
	}

}
