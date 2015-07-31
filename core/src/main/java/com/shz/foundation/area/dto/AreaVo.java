package com.shz.foundation.area.dto;

import com.shz.foundation.area.level.dto.AreaLevelVo;
import com.shz.foundation.persistence.UUIDBaseModel;

public class AreaVo extends UUIDBaseModel {

	/**区域名称*/
	private String name;
	/**区号，只有市一级有*/
	private String code;
	/**区域等级*/
	private AreaLevelVo level;
	/**所属上级区域*/
	private AreaVo upperArea;
	/**经度*/
	private String longtitude;
	/**纬度*/
	private String latitude;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public AreaLevelVo getLevel() {
		return level;
	}
	public void setLevel(AreaLevelVo level) {
		this.level = level;
	}
	public AreaVo getUpperArea() {
		return upperArea;
	}
	public void setUpperArea(AreaVo upperArea) {
		this.upperArea = upperArea;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
