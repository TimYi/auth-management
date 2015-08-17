package com.shz.project.admin.facade.system.department;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.shz.foundation.test.TestUtils;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.department.role.DepartmentRolePermissions;
import com.shz.project.domain.system.permission.FieldPermission;
import com.shz.project.domain.system.role.Role;
import com.shz.project.domain.system.user.SystemUser;


public class MappingTest {

	@Test
	public void map() {
		Department department=new Department();
		department.setId("test");
		department.setName("test");
		department.setDescription("test");
		Set<SystemUser> users=new HashSet<>();
		SystemUser user=new SystemUser();
		user.setId("test");
		users.add(user);
		department.setUsers(users);
		
		
		Set<DepartmentRolePermissions> roles=new HashSet<>();
		Role role=new Role();
		role.setId("test");
		role.setName("test");	
		
		DepartmentRolePermissions drole=new DepartmentRolePermissions();
		FieldPermission fieldPermission=new FieldPermission();
		fieldPermission.setCode("test");
		fieldPermission.setId("test");
		drole.add(fieldPermission);
		drole.setRole(role);
		roles.add(drole);
		department.setRoles(roles);
		DepartmentAdapter adapter=new DepartmentAdapter();
		DepartmentDto dto=adapter.convert(department);
		TestUtils.printJson(dto);
	}
}
