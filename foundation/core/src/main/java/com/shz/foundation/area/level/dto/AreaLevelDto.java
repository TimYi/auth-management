package com.shz.foundation.area.level.dto;

import com.shz.foundation.persistence.UUIDBaseModel;

public class AreaLevelDto extends UUIDBaseModel {

	private String name;
	private AreaLevelVo upperLevel;
	private AreaLevelDto lowerLevel;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AreaLevelVo getUpperLevel() {
		return upperLevel;
	}
	public void setUpperLevel(AreaLevelVo upperLevel) {
		this.upperLevel = upperLevel;
	}
	public AreaLevelDto getLowerLevel() {
		return lowerLevel;
	}
	public void setLowerLevel(AreaLevelDto lowerLevel) {
		this.lowerLevel = lowerLevel;
	}
}
