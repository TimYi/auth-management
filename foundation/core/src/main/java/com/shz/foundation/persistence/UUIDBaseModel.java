package com.shz.foundation.persistence;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class UUIDBaseModel extends BaseModel implements Identified<String> {
	private String id;

	/**
	 * @return the id
	 */
	@Override
	@Id
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 由于是uuid，所以id相同认为是相等
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if(id==null) return super.equals(obj);
		if(!(obj instanceof UUIDBaseModel))return false;
		return ((UUIDBaseModel)obj).getId().equals(id);
	}
	
	/**
	 * 相应的修改hashCode方法
	 */
	@Override
	public int hashCode() {
		if(id==null) return super.hashCode();
		return id.hashCode();
	};
}
