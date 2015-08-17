package com.shz.project.admin.facade.system.user;

import java.util.List;

import com.shz.foundation.mapping.model.DtoBaseModel;

public class SystemUserInputArgs extends DtoBaseModel {

	private String username;
	/**用户真实姓名*/
	private String realname;
	/**更新时要确保密码不变*/
	private String password;
	/**用户是否可用*/
	private boolean verified;
	/**电子邮箱*/
	private String email;
	/**电话号码*/
	private String telephone;
	/**用户所拥有的角色*/
	private List<String> roleIds;
	/**用户所属部门*/
	private String departmentId;
	
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
	public List<String> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}
