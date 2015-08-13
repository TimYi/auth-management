package com.shz.project.domain.system.department;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.domain.system.department.role.DepartmentRole;
import com.shz.project.domain.system.user.SystemUser;

@Entity
public class Department extends UUIDBaseModel {

	/**部门名称*/
	private String name;
	/**部门描述*/
	private String desrciption;
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
	public String getDesrciption() {
		return desrciption;
	}
	public void setDesrciption(String desrciption) {
		this.desrciption = desrciption;
	}
	@OneToMany(mappedBy="department")
	@Where(clause="verified=1")
	public Set<SystemUser> getUsers() {
		return users;
	}
	public void setUsers(Set<SystemUser> users) {
		this.users = users;
	}
	@OneToMany(cascade=CascadeType.ALL)
	public Set<DepartmentRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<DepartmentRole> roles) {
		this.roles = roles;
	}
}
