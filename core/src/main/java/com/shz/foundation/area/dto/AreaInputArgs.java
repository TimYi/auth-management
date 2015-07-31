package com.shz.foundation.area.dto;

import com.shz.foundation.mapping.model.DtoBaseModel;

public class AreaInputArgs extends DtoBaseModel {

	/**区域名称*/
	private String name;
	/**区号，只有市一级有*/
	private String code;
	/**区域等级*/
	private String levelId;
	/**所属上级区域*/
	private String upperAreaId;
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
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public String getUpperAreaId() {
		return upperAreaId;
	}
	public void setUpperAreaId(String upperAreaId) {
		this.upperAreaId = upperAreaId;
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
