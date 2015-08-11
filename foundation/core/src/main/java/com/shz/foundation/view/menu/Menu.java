package com.shz.foundation.view.menu;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.shz.foundation.persistence.UUIDBaseModel;

/**
 * 用于管理菜单导航
 * @author pc
 *
 */
@Entity
public class Menu extends UUIDBaseModel {
	
	/**菜单编码，有编码的菜单可以根据编码方便获取菜单*/
	private String code;
	private MenuType type;
	private String name;
	private String url;
	private Set<Menu> subMenus;
	private Menu parent;
	private int ordernum;
	/**权限代码，为空代表不做任何访问限制*/
	private String permission;
	
	@Column(unique=true)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(nullable=false)
	public MenuType getType() {
		return type;
	}
	public void setType(MenuType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@OneToMany(mappedBy="parent")
	@OrderBy("ordernum")
	public Set<Menu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(Set<Menu> subMenus) {
		this.subMenus = subMenus;
	}
	@ManyToOne
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}

	public static enum MenuType {
		URL,CONTAINER;
	}
}
