package com.shz.project.domain.system.user;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.role.Role;

@Entity
public class SystemUser extends UUIDBaseModel {

	private String username;
	private String password;
	/**用户是否可用*/
	private boolean verified;
	/**用户所拥有的角色*/
	private Set<Role> roles;
	/**用户所属部门*/
	private Department department;
	
	@Column(nullable=false, unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	@ManyToMany
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@ManyToOne
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}
