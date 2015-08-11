package com.shz.project.domain.system.permission;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import com.shz.foundation.persistence.UUIDBaseModel;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public abstract class Permission extends UUIDBaseModel {

	/**权限名称*/
	private String name;
	/**权限代码*/
	private String code;
	/**权限描述*/
	private String description;	
	/**权限类型*/
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable=false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(insertable=false,updatable=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	/**转化为权限代码*/
	@Transient
	public abstract String getPermission();
}
