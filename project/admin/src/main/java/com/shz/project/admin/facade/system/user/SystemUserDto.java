package com.shz.project.admin.facade.system.user;

import java.util.Set;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.admin.facade.system.department.DepartmentVo;
import com.shz.project.admin.facade.system.role.RoleVo;

public class SystemUserDto extends UUIDBaseModel {

	private String username;
	/**用户真实姓名*/
	private String realname;
	/**用户是否可用*/
	private boolean verified;
	/**电子邮箱*/
	private String email;
	/**电话号码*/
	private String telephone;
	/**用户所拥有的角色*/
	private Set<RoleVo> roles;
	/**用户所属部门*/
	private DepartmentVo department;
	
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
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Set<RoleVo> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleVo> roles) {
		this.roles = roles;
	}
	public DepartmentVo getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentVo department) {
		this.department = department;
	}
}
