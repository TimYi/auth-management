package com.shz.foundation.dics.dto;

import com.shz.foundation.mapping.model.DtoBaseModel;

public class CategoryItemInputArgs extends DtoBaseModel {

	private String name;
	private String attr;            // 属性，对于颜色，此处显示颜色代码，例如#FFFFFF
	private String attr2;           // 其他属性信息
	private String remark; 	        // 描述
	private String categoryId;      // 所属分类
	private Integer priority;
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
