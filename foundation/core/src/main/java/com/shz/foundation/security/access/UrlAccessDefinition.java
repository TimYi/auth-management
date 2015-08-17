package com.shz.foundation.security.access;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.shz.foundation.persistence.UUIDBaseModel;

@Entity
public class UrlAccessDefinition extends UUIDBaseModel {

	/**url地址，采用ant方式过滤*/
	private String url;
	/**过滤顺序*/
	private int ordernum;	
	/**权限代码*/
	private String permission;
	
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	@Column(nullable=false, unique=true)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(nullable=false)
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
}
