package com.shz.project.domain.system;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.department.role.DepartmentRole;
import com.shz.project.domain.system.permission.Permission;
import com.shz.project.domain.system.role.Role;
import com.shz.project.domain.system.user.SystemUser;
import com.shz.project.domain.system.user.SystemUserRepository;

@Component("permissionManager")
@Transactional
public class PermissionManager {
	
	@Autowired
	private SystemUserRepository userRepository;
	
	public Set<String> extractPermissions(String username) {
		SystemUser user=userRepository.getByUsername(username);
		if(user==null)return null;
		return extractPermissions(user);
	}

	public Set<String> extractPermissions(SystemUser user) {
		Set<String> permissions=new HashSet<>();
		
		//提取角色通用权限
		Set<Role> roles=user.getRoles();
		if(roles==null) return null;
		for (Role role : roles) {
			Set<Permission> rolePermissions=role.getPermissions();
			if(rolePermissions!=null) {
				Set<String> rps=rolePermissions.stream().map(Permission::getPermission).collect(Collectors.toSet());
				permissions.addAll(rps);
			}
		}
		
		//提取部门角色特殊权限
		Set<DepartmentRole> departmentRoles=null;
		Department department=user.getDepartment();
		if(department!=null) {
			departmentRoles=department.getRoles();
		}
		if(departmentRoles!=null) {
			Set<DepartmentRole> allocatedRoles=departmentRoles.stream().filter(d->roles.contains(d.getRole()))
					.collect(Collectors.toSet());
			if(allocatedRoles!=null) {
				for (DepartmentRole departmentRole : allocatedRoles) {
					Set<Permission> departmentPermissions=departmentRole.getPermissions();
					if(departmentPermissions!=null) {
						permissions.addAll(departmentPermissions.stream().map(Permission::getPermission).collect(Collectors.toSet()));
					}
				}
			}			
		}
		return permissions;
	}
}
