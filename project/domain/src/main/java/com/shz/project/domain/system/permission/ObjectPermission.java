package com.shz.project.domain.system.permission;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * 具体到domain object的操作权限
 * @author pc
 *
 */
@Entity
@DiscriminatorValue("OBJECT")
public class ObjectPermission extends Permission {

	/**对do的具体操作*/
	private OperatePermission operation;

	@ManyToOne
	public OperatePermission getOperation() {
		return operation;
	}
	public void setOperation(OperatePermission operation) {
		this.operation = operation;
	}
	
	@Override
	@Transient
	public String getPermission() {		
		return operation.getPermission()+":"+getCode();
	}
}
