package com.shz.foundation.area.level;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.shz.foundation.persistence.UUIDBaseModel;

@Entity
public class AreaLevel extends UUIDBaseModel {

	private String name;
	private AreaLevel upperLevel;
	private AreaLevel lowerLevel;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne
	@JoinColumn(unique=true)
	public AreaLevel getUpperLevel() {
		return upperLevel;
	}
	public void setUpperLevel(AreaLevel upperLevel) {
		this.upperLevel = upperLevel;
	}
	@OneToOne(mappedBy="upperLevel")
	public AreaLevel getLowerLevel() {
		return lowerLevel;
	}
	public void setLowerLevel(AreaLevel lowerLevel) {
		this.lowerLevel = lowerLevel;
	}
	
	@Transient
	public boolean isUpperLevelTo(AreaLevel lowerLevel) {
		return (this.lowerLevel!=null && lowerLevel!=null && this.lowerLevel.getId().equals(lowerLevel.getId()));
	}
}
