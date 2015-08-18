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

import com.shz.foundation.security.shiro.ShiroUser;
import com.shz.foundation.utils.validate.EmailValidater;
import com.shz.foundation.utils.validate.PhoneNumberValidater;
import com.shz.project.domain.system.EncryptService;
import com.shz.project.domain.system.PermissionManager;
import com.shz.project.domain.system.user.SystemUser;
import com.shz.project.domain.system.user.SystemUserRepository;

public class SystemUserRealm extends AuthorizingRealm {
	
	private SystemUserRepository userRepository;
	private PermissionManager permissionManager;
	public void setUserRepository(SystemUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public void setPermissionManager(PermissionManager permissionManager) {
		this.permissionManager = permissionManager;
	}

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
		SystemUser user;
		if(EmailValidater.isEmail(username)) {
			user=userRepository.getByEmail(username);
		} else if(PhoneNumberValidater.isMobile(username)) {
			user=userRepository.getByTelephone(username);
		} else {
			user=userRepository.getByUsername(username);
		}		
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
}
