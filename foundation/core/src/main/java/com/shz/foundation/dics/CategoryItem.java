package com.shz.foundation.dics;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.shz.foundation.persistence.UUIDBaseModel;

@Entity
@Table(name = "fhzj_categoryitem",
uniqueConstraints={@UniqueConstraint(columnNames={"name","category_id"})})
public class CategoryItem extends UUIDBaseModel {
	private String name;
	private String attr;            // 属性，对于颜色，此处显示颜色代码，例如#FFFFFF
	private String attr2;           // 其他属性信息
	private String remark; 	        // 描述
	private Category category;      // 所属分类
	private Integer priority;

	@ManyToOne(optional=false)
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

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
	
	@Transient
	public String getType() {
		return category.getType();
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
