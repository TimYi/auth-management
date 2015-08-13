package com.shz.project.admin.facade.system.department;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import com.shz.project.admin.facade.system.department.role.DepartmentRoleInputArgs;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.department.role.DepartmentRole;
import com.shz.project.domain.system.department.role.DepartmentRoleRepository;
import com.shz.project.domain.system.permission.Permission;
import com.shz.project.domain.system.permission.PermissionRepository;
import com.shz.project.domain.system.role.Role;
import com.shz.project.domain.system.role.RoleRepository;

@Component
public class DepartmentAdapter extends AbstractDtoAdapter<Department, DepartmentDto, DepartmentInputArgs> {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private DepartmentRoleRepository departmentRoleRepository;
	
	@Override
	public Department postConvertToDo(DepartmentInputArgs i, Department d) {
		List<DepartmentRoleInputArgs> departmentRoles=i.getDepartmentRoles();
		if(departmentRoles!=null) {
			Set<DepartmentRole> roles=new HashSet<>();
			for (DepartmentRoleInputArgs departmentRole : departmentRoles) {
				DepartmentRole drole=new DepartmentRole();
				drole.setDepartment(d);
				String roleId=departmentRole.getRoleId();
				Role role=roleRepository.findOne(roleId);
				drole.setRole(role);
				List<String> permissionIds=departmentRole.getPermissionIds();
				if(permissionIds!=null) {
					Set<Permission> permissions=new HashSet<>();
					for (String permissionId : permissionIds) {
						Permission permission=permissionRepository.findOne(permissionId);
						permissions.add(permission);
					}
					drole.setPermissions(permissions);
				}
				roles.add(drole);
			}
			d.setRoles(roles);
		}
		return d;
	}

	@Override
	public Department postUpdate(DepartmentInputArgs i, Department d) {
		List<DepartmentRoleInputArgs> departmentRoles=i.getDepartmentRoles();
		if(departmentRoles!=null) {
			Set<DepartmentRole> roles=new HashSet<>();
			for (DepartmentRoleInputArgs departmentRole : departmentRoles) {
				//根据id是否为空，判断是更新或者是提交
				String id=departmentRole.getId();
				DepartmentRole drole;
				if(StringUtils.isNoneBlank(id)) {
					drole=departmentRoleRepository.findOne(id);
				}else {
					drole=new DepartmentRole();
				}
				drole.setDepartment(d);
				String roleId=departmentRole.getRoleId();
				Role role=roleRepository.findOne(roleId);
				drole.setRole(role);
				List<String> permissionIds=departmentRole.getPermissionIds();
				if(permissionIds!=null) {
					Set<Permission> permissions=new HashSet<>();
					for (String permissionId : permissionIds) {
						Permission permission=permissionRepository.findOne(permissionId);
						permissions.add(permission);
					}
					drole.setPermissions(permissions);
				}
				roles.add(drole);
			}
			//没有传过来的role，自动被删掉
			d.setRoles(roles);
		} else {
			d.setRoles(null);
		}
		return d;
	}
}
