package com.shz.foundation.area;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.shz.foundation.area.level.AreaLevel;
import com.shz.foundation.persistence.UUIDBaseModel;

@Entity
public class Area extends UUIDBaseModel {

	/**区域名称*/
	private String name;
	/**区号，只有市一级有*/
	private String code;
	/**区域等级*/
	private AreaLevel level;
	/**所属上级区域*/
	private Area upperArea;
	/**所属下级区域*/
	private Set<Area> subAreas;
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
	@ManyToOne(optional=false)
	public AreaLevel getLevel() {
		return level;
	}
	public void setLevel(AreaLevel level) {
		this.level = level;
	}
	@ManyToOne
	public Area getUpperArea() {
		return upperArea;
	}
	public void setUpperArea(Area upperArea) {
		this.upperArea = upperArea;
	}
	@OneToMany(mappedBy="upperArea")
	public Set<Area> getSubAreas() {
		return subAreas;
	}
	public void setSubAreas(Set<Area> subAreas) {
		this.subAreas = subAreas;
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
