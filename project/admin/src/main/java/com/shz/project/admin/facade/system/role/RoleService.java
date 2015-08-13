package com.shz.project.admin.facade.system.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.mapping.service.DtoPagingService;
import com.shz.project.domain.system.permission.Permission;
import com.shz.project.domain.system.permission.PermissionRepository;
import com.shz.project.domain.system.role.Role;
import com.shz.project.domain.system.role.RoleManager;
import com.shz.project.domain.system.role.RoleRepository;

@Service
@Transactional
public class RoleService extends DtoPagingService<Role, RoleDto, RoleInputArgs, String> {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleManager manager;
	@Autowired
	private PermissionRepository permissionRepository;
	
	public void addPermission(String roleId, String permissionId) {
		Role role=roleRepository.findOne(roleId);
		Permission permission=permissionRepository.findOne(permissionId);
		manager.addPermission(role, permission);
	}
	
	public void removePermission(String roleId, String permissionId) {
		Role role=roleRepository.findOne(roleId);
		Permission permission=permissionRepository.findOne(permissionId);
		manager.removePermission(role, permission);
	}
}
