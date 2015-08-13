package com.shz.project.domain.system.role;

import java.util.HashSet;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.shz.project.domain.system.permission.Permission;

@Service
@Transactional
public class RoleManager {

	public void addPermission(Role role, Permission permission) {
		if(role==null || permission==null) return;
		Set<Permission> permissions=role.getPermissions();
		if(permissions==null)permissions=new HashSet<>();
		permissions.add(permission);
		role.setPermissions(permissions);
	}
	
	public void removePermission(Role role, Permission permission) {
		if(role==null || permission==null) return;
		Set<Permission> permissions=role.getPermissions();
		if(permissions==null) return;
		permissions.remove(permission);
		role.setPermissions(permissions);
	}
}
