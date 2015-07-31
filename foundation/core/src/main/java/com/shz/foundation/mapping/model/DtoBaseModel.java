package com.shz.foundation.mapping.model;

import com.shz.foundation.persistence.Identified;


public class DtoBaseModel implements Identified<String> {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
