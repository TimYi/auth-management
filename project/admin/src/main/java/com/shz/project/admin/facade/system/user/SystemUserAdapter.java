package com.shz.project.admin.facade.system.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shz.foundation.mapping.BeanMapper;
import com.shz.foundation.mapping.adapter.SimpleDtoAdapter;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.department.DepartmentRepository;
import com.shz.project.domain.system.role.Role;
import com.shz.project.domain.system.role.RoleRepository;
import com.shz.project.domain.system.user.SystemUser;
import com.shz.project.domain.system.user.SystemUserRepository;

@Component
public class SystemUserAdapter extends SimpleDtoAdapter<SystemUser, SystemUserDto, SystemUserInputArgs> {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private SystemUserRepository userRepository;

	@Override
	public SystemUser convertToDo(SystemUserInputArgs i) {
		SystemUser d=BeanMapper.map(i, SystemUser.class);
		String departmentId=i.getDepartmentId();
		if(StringUtils.isNotBlank(departmentId)) {
			Department department=departmentRepository.findOne(departmentId);
			d.setDepartment(department);
		}
		List<String> roleIds=i.getRoleIds();
		if(roleIds!=null) {
			Set<Role> roles=new HashSet<>();
			for (String roleId : roleIds) {
				Role role=roleRepository.findOne(roleId);
				roles.add(role);
			}
			d.setRoles(roles);
		}
		return d;
	}

	/**
	 * 确保用户名不重复，并且不更新密码
	 */
	@Override
	public SystemUser update(SystemUserInputArgs i, SystemUser d) {
		if(!i.getUsername().equals(d.getUsername())) {
			SystemUser userByName=userRepository.getByUsername(i.getUsername());
			if(userByName!=null) {
				throw new RuntimeException("用户名已存在，请更换");
			}
		}
		d.setUsername(i.getUsername());
		String departmentId=i.getDepartmentId();
		if(StringUtils.isNotBlank(departmentId)) {
			Department department=departmentRepository.findOne(departmentId);
			d.setDepartment(department);
		}
		List<String> roleIds=i.getRoleIds();
		if(roleIds!=null) {
			Set<Role> roles=new HashSet<>();
			for (String roleId : roleIds) {
				Role role=roleRepository.findOne(roleId);
				roles.add(role);
			}
			d.setRoles(roles);
		}
		return d;
	}

	@Override
	public SystemUserDto convert(SystemUser source) {
		return BeanMapper.map(source, SystemUserDto.class);
	}
}
