package com.shz.project.admin.facade.system.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.mapping.service.DtoPagingService;
import com.shz.foundation.persistence.springdata.PageableBuilder;
import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.department.DepartmentRepository;
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
	
	public void regist(String username, String password, String realname, String email, String telephone,
			String departmentId) {
		Department department=null;
		if(StringUtils.isNotBlank(departmentId)) {
			department=departmentRepository.findOne(departmentId);
		}
		manager.regist(username, password, realname, email, telephone, department);
	}
	
	public void changePassword(String username, String oldPassword, String newPassword) {
		SystemUser user=userRepository.getByUsername(username);
		manager.changePassword(user, oldPassword, newPassword);
	}
	
	public PagedList<SystemUserDto> unauthenticatedUsers(int page, int size) {
		Pageable pageable=PageableBuilder.build(page, size);
		Page<SystemUser> users=userRepository.findByVerified(false, pageable);
		Page<SystemUserDto> result=users.map(adapter);
		return new PagedList<>(result);
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
