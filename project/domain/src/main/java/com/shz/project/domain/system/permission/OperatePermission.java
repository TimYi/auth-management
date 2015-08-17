package com.shz.project.domain.system.permission;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("OPERATE")
public class OperatePermission extends Permission {

	/**操作所属领域*/
	private FieldPermission field;
	/**下辖domain级别权限*/
	private Set<ObjectPermission> objects;

	@ManyToOne
	public FieldPermission getField() {
		return field;
	}
	public void setField(FieldPermission field) {
		this.field = field;
	}
	@OneToMany(mappedBy="operation",cascade=CascadeType.ALL)
	public Set<ObjectPermission> getObjects() {
		return objects;
	}
	public void setObjects(Set<ObjectPermission> objects) {
		this.objects = objects;
	}
	
	@Override
	@Transient
	public String getPermission() {
		return getField().getCode()+":"+getCode();
	}
}
