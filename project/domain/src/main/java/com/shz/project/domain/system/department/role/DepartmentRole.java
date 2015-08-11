package com.shz.project.domain.system.department.role;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.permission.Permission;
import com.shz.project.domain.system.role.Role;

/**
 * 部门角色，用来记录某个部门某个角色所拥有的权限
 * @author pc
 *
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(name="unique_role_department",columnNames={"department","role"})})
public class DepartmentRole extends UUIDBaseModel {

	/**关联部门*/
	private Department department;
	/**关联角色*/
	private Role role;
	/**部门角色特殊权限*/
	private Set<Permission> permissions;
	@ManyToOne(optional=false)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@ManyToOne(optional=false)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@ManyToMany
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
}
