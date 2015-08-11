package com.shz.foundation.view.menu.facade;

import java.util.List;

import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.foundation.view.menu.Menu.MenuType;

public class MenuVo extends UUIDBaseModel {

	/**菜单编码，有编码的菜单可以根据编码方便获取菜单*/
	private String code;
	private MenuType type;
	private String name;
	private String url;
	private List<MenuVo> subMenus;
	private int ordernum;
	/**权限代码，为空代表不做任何访问限制*/
	private String permission;
	private ParentMenu parent;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
	public List<MenuVo> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<MenuVo> subMenus) {
		this.subMenus = subMenus;
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
	public ParentMenu getParent() {
		return parent;
	}
	public void setParent(ParentMenu parent) {
		this.parent = parent;
	}
}
