package com.shz.project.admin.facade.system.role;

import java.util.List;
import java.util.Set;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.admin.facade.system.permission.PermissionVo;
import com.shz.project.admin.facade.system.user.SystemUserVo;

public class RoleDto extends UUIDBaseModel {

	/**角色名称*/
	private String name;
	/**角色描述*/
	private String description;
	/**用户角色*/
	private List<SystemUserVo> users;
	/**角色通用权限*/
	private Set<PermissionVo> permissions;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<SystemUserVo> getUsers() {
		return users;
	}
	public void setUsers(List<SystemUserVo> users) {
		this.users = users;
	}
	public Set<PermissionVo> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<PermissionVo> permissions) {
		this.permissions = permissions;
	}
}
