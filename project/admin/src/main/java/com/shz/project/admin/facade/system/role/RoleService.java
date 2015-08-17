package com.shz.project.admin.facade.system.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.mapping.service.DtoPagingService;
import com.shz.foundation.persistence.springdata.PageableBuilder;
import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.project.domain.system.permission.Permission;
import com.shz.project.domain.system.permission.PermissionRepository;
import com.shz.project.domain.system.role.Role;
import com.shz.project.domain.system.role.RoleManager;
import com.shz.project.domain.system.role.RoleRepository;
import com.shz.project.domain.system.role.Role.RoleType;

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
	
	public PagedList<RoleDto> findByType(RoleType type, int page, int size) {
		Pageable pageable=PageableBuilder.build(page, size);
		Page<Role> roles=getRepository().findByType(type, pageable);
		Page<RoleDto> result=roles.map(adapter);
		return new PagedList<>(result);
	}
	
	@Override
	public RoleRepository getRepository() {
		return (RoleRepository)super.getRepository();
	}
}
