package com.shz.project.domain.system.permission;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("FIELD")
public class FieldPermission extends Permission {
	
	private Set<OperatePermission> operations;

	@OneToMany(mappedBy="field")
	public Set<OperatePermission> getOperations() {
		return operations;
	}
	public void setOperations(Set<OperatePermission> operations) {
		this.operations = operations;
	}

	@Override
	@Transient
	public String getPermission() {
		return getCode();
	}
}
