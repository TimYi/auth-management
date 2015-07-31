package com.shz.foundation.dics.dto;

public class CategoryVo {
	private String name;
	private String remark;
	private String type;
	private CategoryVo parent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public CategoryVo getParent() {
		return parent;
	}
	public void setParent(CategoryVo parent) {
		this.parent = parent;
	}
}
