package com.shz.project.admin.facade.system.department;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.department.role.DepartmentRolePermissions;
import com.shz.project.domain.system.department.role.DepartmentRolePermissionsRepository;
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
	private DepartmentRolePermissionsRepository departmentRoleRepository;
	
	@Override
	public Department postConvertToDo(DepartmentInputArgs i, Department d) {
		return postUpdate(i, d);
	}

	@Override
	public Department postUpdate(DepartmentInputArgs i, Department d) {
		//提前清理原来的权限配置
		Set<DepartmentRolePermissions> oldRoles=d.getRoles();
		if(oldRoles!=null) {
			departmentRoleRepository.delete(oldRoles);
		}
		d.setRoles(null);
		Map<String, List<String>> rolePermissions=i.getRolePermissions();
		if(rolePermissions!=null) {			
			Set<DepartmentRolePermissions> roles=new HashSet<>();
			for (Entry<String, List<String>> entry : rolePermissions.entrySet()) {
				String roleId=entry.getKey();
				Role role=roleRepository.findOne(roleId);
				if(role==null) continue;
				List<String> permissionIds=entry.getValue();
				if(permissionIds==null) continue;
				DepartmentRolePermissions droleps=new DepartmentRolePermissions();
				for (String permissionId : permissionIds) {
					Permission permission=permissionRepository.findOne(permissionId);
					droleps.add(permission);
				}
				droleps.setDepartment(d);
				droleps.setRole(role);
				droleps=departmentRoleRepository.save(droleps);
				roles.add(droleps);
			}
			d.setRoles(roles);
		}
		return d;
	}
}
