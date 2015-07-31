package com.shz.foundation.area.level.dto;

import com.shz.foundation.mapping.model.DtoBaseModel;

public class AreaLevelInputArgs extends DtoBaseModel {

	private String name;
	private String upperLevelId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpperLevelId() {
		return upperLevelId;
	}
	public void setUpperLevelId(String upperLevelId) {
		this.upperLevelId = upperLevelId;
	}
}
