package com.shz.foundation.area.level.dto;

import com.shz.foundation.persistence.UUIDBaseModel;

public class AreaLevelVo extends UUIDBaseModel {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
