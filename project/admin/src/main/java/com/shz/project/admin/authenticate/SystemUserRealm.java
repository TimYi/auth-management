package com.shz.project.admin.authenticate;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

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
		String username=principals.getPrimaryPrincipal().toString();
		Set<String> permissions=permissionManager.extractPermissions(username);
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
		return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
	}

}
