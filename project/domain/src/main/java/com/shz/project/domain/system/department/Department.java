package com.shz.project.domain.system.department;

import com.shz.foundation.persistence.UUIDBaseModel;

public class Department extends UUIDBaseModel {

	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
