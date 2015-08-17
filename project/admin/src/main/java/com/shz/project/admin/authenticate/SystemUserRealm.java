package com.shz.project.admin.authenticate;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.shz.project.domain.system.EncryptService;
import com.shz.project.domain.system.PermissionManager;
import com.shz.project.domain.system.user.SystemUser;
import com.shz.project.domain.system.user.SystemUserRepository;

public class SystemUserRealm extends AuthorizingRealm {
	
	@Autowired
	private SystemUserRepository userRepository;
	@Autowired
	private PermissionManager permissionManager;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser user=(ShiroUser)principals.getPrimaryPrincipal();
		String userId=user.getId();
		Set<String> permissions=permissionManager.extractPermissions(userId);
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.setStringPermissions(permissions);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String username=userToken.getUsername();
		SystemUser user=userRepository.getByUsername(username);
		if(user==null || !user.isVerified()) {
			throw new AuthenticationException("用户不存在或未通过认证");
		}
		ShiroUser shiroUser=new ShiroUser(user.getId(), user.getUsername(), user.getRealname());
		return new SimpleAuthenticationInfo(shiroUser,
				user.getPassword(),ByteSource.Util.bytes(user.getSalt()),getName());
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		setCredentialsMatcher(EncryptService.CREDENTIALS_MATCHER);
	}
	
	public static class ShiroUser {
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
}
