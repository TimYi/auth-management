package com.shz.project.domain.system.role;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.project.domain.system.permission.Permission;
import com.shz.project.domain.system.user.SystemUser;

/**
 * 角色，可以为角色分配权限，用户通过和角色关联获取权限
 * @author pc
 *
 */
@Entity
public class Role extends UUIDBaseModel {

	/**角色名称*/
	private String name;
	/**角色类型*/
	private RoleType type;
	/**角色描述*/
	private String description;
	/**用户角色*/
	private Set<SystemUser> users;
	/**角色通用权限*/
	private Set<Permission> permissions;
	
	@Column(nullable=false,unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable=false)
	public RoleType getType() {
		return type;
	}
	public void setType(RoleType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToMany(mappedBy="roles")
	public Set<SystemUser> getUsers() {
		return users;
	}
	public void setUsers(Set<SystemUser> users) {
		this.users = users;
	}
	@ManyToMany
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	/**
	 * 角色类型，用于区分是通用角色，还是部门角色。
	 * 部门角色可以分配通用权限
	 * @author pc
	 *
	 */
	public static enum RoleType {
		GENERIC,DEPARTMENT;
	}
}
