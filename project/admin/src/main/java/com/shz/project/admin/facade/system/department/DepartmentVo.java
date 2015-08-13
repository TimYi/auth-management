package com.shz.project.admin.facade.system.department;

import com.shz.foundation.persistence.UUIDBaseModel;

public class DepartmentVo extends UUIDBaseModel {

	/**部门名称*/
	private String name;
	/**部门描述*/
	private String desrciption;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesrciption() {
		return desrciption;
	}
	public void setDesrciption(String desrciption) {
		this.desrciption = desrciption;
	}
}
