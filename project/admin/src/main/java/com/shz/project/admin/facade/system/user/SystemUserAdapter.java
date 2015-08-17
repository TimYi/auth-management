package com.shz.project.admin.facade.system.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.department.DepartmentRepository;
import com.shz.project.domain.system.role.Role;
import com.shz.project.domain.system.role.RoleRepository;
import com.shz.project.domain.system.user.SystemUser;

@Component
public class SystemUserAdapter extends AbstractDtoAdapter<SystemUser, SystemUserDto, SystemUserInputArgs> {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public SystemUser postConvertToDo(SystemUserInputArgs i, SystemUser d) {
		return postUpdate(i, d);
	}

	@Override
	public SystemUser postUpdate(SystemUserInputArgs i, SystemUser d) {
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
}
