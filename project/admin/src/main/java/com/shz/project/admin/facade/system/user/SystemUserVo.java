package com.shz.project.admin.facade.system.user;

import com.shz.foundation.persistence.UUIDBaseModel;

public class SystemUserVo extends UUIDBaseModel {

	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
