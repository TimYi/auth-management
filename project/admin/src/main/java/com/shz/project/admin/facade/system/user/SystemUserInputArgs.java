package com.shz.project.admin.facade.system.user;

import java.util.List;

import com.shz.foundation.mapping.model.DtoBaseModel;

public class SystemUserInputArgs extends DtoBaseModel {

	private String username;
	/**更新时要确保密码不变*/
	private String password;
	/**用户是否可用*/
	private boolean verified;
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
