package com.shz.project.domain.system.department.role;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.shz.foundation.persistence.SetEntity;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.permission.Permission;
import com.shz.project.domain.system.role.Role;

/**
 * 部门角色，用来记录某个部门某个角色所拥有的权限
 * @author pc
 *
 */
@Entity
public class DepartmentRolePermissions extends SetEntity<Permission> {
	
	/**关联角色*/
	private Role role;
	/**关联部门*/
	private Department department;	
	/**部门角色特殊权限*/
	private Set<Permission> permissions;

	@ManyToOne
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@ManyToOne
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@ManyToMany
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	@Override
	@Transient
	protected Set<Permission> getInternalSet() {
		if(permissions==null) permissions=new HashSet<>();
		return permissions;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if(getId()==null) return super.equals(obj);
		if(obj instanceof DepartmentRolePermissions) return false;
		DepartmentRolePermissions drole=(DepartmentRolePermissions)obj;
		return (getId().equals(drole.getId()) ||
				(getDepartment().getId().equals(drole.getDepartment().getId()) 
						&& getRole().getId().equals(drole.getRole().getId())));
	}
}
