package com.shz.project.domain.system.user;

import com.shz.foundation.persistence.UUIDBaseModel;

public class SystemUser extends UUIDBaseModel {

	private String username;
	private String password;
	/**用户是否可用*/
	private boolean verified;
	
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
}
