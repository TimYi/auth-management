package com.shz.foundation.dics;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.shz.foundation.persistence.UUIDBaseModel;

@Entity
@Table(name = "fhzj_category",
	indexes=@Index(name="type_index",columnList="type")
)
public class Category extends UUIDBaseModel {
	private String name;
	private String remark;
	private Set<CategoryItem> items;
	private String type;
	private Category parent;
	private Set<Category> subCategories;

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

	@OneToMany(mappedBy="category",fetch=FetchType.EAGER)
	@OrderBy("priority")
	public Set<CategoryItem> getItems() {
		return items;
	}

	public void setItems(Set<CategoryItem> items) {
		this.items = items;
	}

	/**
	 * @return the type
	 * 用于根据类别查询
	 */
	@Column(nullable=false,unique=true)
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the parent
	 */
	@ManyToOne
	@JoinColumn(name="parent_id")
	public Category getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Category parent) {
		this.parent = parent;
	}

	/**
	 * @return the subCategories
	 */
	@OneToMany(mappedBy="parent",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	public Set<Category> getSubCategories() {
		return subCategories;
	}

	/**
	 * @param subCategories the subCategories to set
	 */
	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
	}	
}
