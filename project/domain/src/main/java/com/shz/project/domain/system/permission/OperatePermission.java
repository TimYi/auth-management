package com.shz.project.domain.system.permission;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("OPERATE")
public class OperatePermission extends Permission {

	/**操作所属领域*/
	private FieldPermission field;

	@ManyToOne(optional=false)
	public FieldPermission getField() {
		return field;
	}
	public void setField(FieldPermission field) {
		this.field = field;
	}
	
	@Override
	@Transient
	public String getPermission() {
		return getField().getCode()+":"+getCode();
	}
}
