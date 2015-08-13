package com.shz.project.admin.facade.system.department;

import java.util.List;

import com.shz.foundation.mapping.model.DtoBaseModel;
import com.shz.project.admin.facade.system.department.role.DepartmentRoleInputArgs;

public class DepartmentInputArgs extends DtoBaseModel {

	/**部门名称*/
	private String name;
	/**部门描述*/
	private String desrciption;
	/**部门角色*/
	private List<DepartmentRoleInputArgs> departmentRoles;
	
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
	public List<DepartmentRoleInputArgs> getDepartmentRoles() {
		return departmentRoles;
	}
	public void setDepartmentRoles(List<DepartmentRoleInputArgs> departmentRoles) {
		this.departmentRoles = departmentRoles;
	}
}
