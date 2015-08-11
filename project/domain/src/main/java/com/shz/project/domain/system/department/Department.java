package com.shz.project.domain.system.department;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.domain.system.department.role.DepartmentRole;
import com.shz.project.domain.system.user.SystemUser;

@Entity
public class Department extends UUIDBaseModel {

	/**部门名称*/
	private String name;
	/**部门用户*/
	private Set<SystemUser> users;
	/**部门角色*/
	private Set<DepartmentRole> roles;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy="department")
	public Set<SystemUser> getUsers() {
		return users;
	}
	public void setUsers(Set<SystemUser> users) {
		this.users = users;
	}
	@OneToMany
	public Set<DepartmentRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<DepartmentRole> roles) {
		this.roles = roles;
	}
}
