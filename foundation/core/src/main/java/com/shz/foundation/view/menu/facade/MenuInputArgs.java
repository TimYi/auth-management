package com.shz.foundation.view.menu.facade;

import com.shz.foundation.mapping.model.DtoBaseModel;
import com.shz.foundation.view.menu.Menu.MenuType;

public class MenuInputArgs extends DtoBaseModel {

	/**菜单编码，有编码的菜单可以根据编码方便获取菜单*/
	private String code;
	private MenuType type;
	private String name;
	private String url;
	private String parentId;
	private Integer ordernum=0;
	/**权限代码，为空代表不做任何访问限制*/
	private String permission;
	
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		if(ordernum==null)return;
		this.ordernum = ordernum;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
}
