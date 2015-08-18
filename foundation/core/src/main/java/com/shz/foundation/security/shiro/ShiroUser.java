package com.shz.foundation.security.shiro;

public class ShiroUser {
	private String id;
	private String username;
	private String realname;
	
	public ShiroUser(){}
	public ShiroUser(String id, String username, String realname) {
		this.id=id;
		this.username=username;
		this.realname=realname;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
}
