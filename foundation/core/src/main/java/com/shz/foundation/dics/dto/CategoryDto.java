package com.shz.foundation.dics.dto;

import java.util.Set;

import com.shz.foundation.persistence.UUIDBaseModel;

public class CategoryDto extends UUIDBaseModel {
	private String name;
	private String remark;
	private Set<CategoryItemDto> items;
	private String type;
	private CategoryVo parent;
	private Set<CategoryDto> subCategories;
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
	public Set<CategoryItemDto> getItems() {
		return items;
	}
	public void setItems(Set<CategoryItemDto> items) {
		this.items = items;
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
	public Set<CategoryDto> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(Set<CategoryDto> subCategories) {
		this.subCategories = subCategories;
	}
}
