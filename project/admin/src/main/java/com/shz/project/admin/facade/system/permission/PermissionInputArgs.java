package com.shz.project.admin.facade.system.permission;

import com.shz.foundation.mapping.model.DtoBaseModel;

public class PermissionInputArgs extends DtoBaseModel {

	private PermissionType permissionType;	
	/**权限名称*/
	private String name;
	/**权限代码*/
	private String code;
	/**权限描述*/
	private String description;
	private String fieldId;
	private String operationId;
	
	public PermissionType getPermissionType() {
		return permissionType;
	}
	public void setPermissionType(PermissionType permissionType) {
		this.permissionType = permissionType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getOperationId() {
		return operationId;
	}
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}	
	
	public static enum PermissionType {
		FIELD,OPERATE,OBJECT;
	}
}
