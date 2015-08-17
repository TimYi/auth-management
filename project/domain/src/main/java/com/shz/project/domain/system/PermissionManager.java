package com.shz.project.domain.system;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.department.role.DepartmentRolePermissions;
import com.shz.project.domain.system.permission.Permission;
import com.shz.project.domain.system.role.Role;
import com.shz.project.domain.system.user.SystemUser;
import com.shz.project.domain.system.user.SystemUserRepository;

@Component("permissionManager")
@Transactional
public class PermissionManager {
	
	@Autowired
	private SystemUserRepository userRepository;
	
	public Set<String> extractPermissions(String userId) {
		SystemUser user=userRepository.findOne(userId);
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
		Set<DepartmentRolePermissions> departmentRoles=null;
		Department department=user.getDepartment();
		if(department!=null) {
			departmentRoles=department.getRoles();
		}
		if(departmentRoles!=null) {
			for (DepartmentRolePermissions drole : departmentRoles) {
				if(roles.contains(drole.getRole())) {
					if(!departmentRoles.isEmpty()) {
						permissions.addAll(drole.stream().map(Permission::getPermission).collect(Collectors.toSet()));
					}
				}
			}
		}
		return permissions;
	}
}
