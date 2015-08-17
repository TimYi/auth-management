package com.shz.project.admin.facade.system.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.mapping.service.DtoPagingService;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.department.DepartmentRepository;
import com.shz.project.domain.system.role.Role;
import com.shz.project.domain.system.role.RoleRepository;
import com.shz.project.domain.system.user.SystemUser;
import com.shz.project.domain.system.user.SystemUserManager;
import com.shz.project.domain.system.user.SystemUserRepository;

@Service
@Transactional
public class SystemUserService extends DtoPagingService<SystemUser, SystemUserDto, SystemUserInputArgs, String> {

	@Autowired
	private SystemUserManager manager;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private SystemUserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public SystemUserDto add(SystemUserInputArgs args) {
		if(args==null) return null;
		SystemUser user=adapter.convertToDo(args);
		user=manager.add(user);
		return adapter.convertToDetailedDto(user);
	}
	
	@Override
	public SystemUserDto update(SystemUserInputArgs entity) {
		SystemUser u=adapter.convertToDo(entity);
		u=manager.update(u.getId(), u.getUsername(), u.getRealname(), u.isVerified(), u.getEmail(), u.getTelephone(), 
				u.getDepartment(), u.getRoles());
		return adapter.convertToDetailedDto(u);
	}
	
	public void regist(String username, String password, String departmentId) {
		Department department=new Department();
		manager.regist(username, password, department);
	}
	
	public void authenticate(String userId, List<String> roleIds) {
		SystemUser user=userRepository.findOne(userId);
		if(user==null) return;
		Set<Role> roles=null;
		if(roleIds!=null) {
			roles=new HashSet<>();
			for (String roleId : roleIds) {
				Role role=roleRepository.findOne(roleId);
				if(role!=null) roles.add(role);
			}
		}
		manager.authenticate(user, roles);
	}
	
	public void addRole(String userId, String roleId) {
		SystemUser user=userRepository.findOne(userId);
		Role role=roleRepository.findOne(roleId);
		if(user==null || role==null) return;
		manager.addRole(user, role);
	}
	
	public void removeRole(String userId, String roleId) {
		SystemUser user=userRepository.findOne(userId);
		Role role=roleRepository.findOne(roleId);
		if(user==null || role==null) return;
		manager.removeRole(user, role);
	}
	
	public SystemUserDto getByUsername(String username) {
		SystemUser user=getRepository().getByUsername(username);
		return adapter.convertToDetailedDto(user);
	}
	
	@Override
	public SystemUserRepository getRepository() {
		return (SystemUserRepository)super.getRepository();
	}
}
