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
	/**用户真实姓名*/
	private String realname;
	private String password;
	/**电子邮箱*/
	private String email;
	/**电话号码*/
	private String telephone;
	/**加密盐*/
	private String salt;
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(unique=true,nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(unique=true,nullable=false)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
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
