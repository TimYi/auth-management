package com.shz.project.domain.system.user;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.project.domain.system.EncryptService;
import com.shz.project.domain.system.department.Department;
import com.shz.project.domain.system.role.Role;

@Service
@Transactional
public class SystemUserManager {
	
	@Autowired
	private SystemUserRepository repository;

	/**
	 * 新增用户时，检查用户名是否重复，为密码进行加密
	 * @param entity
	 * @return
	 */
	public SystemUser add(SystemUser entity) {
		if(entity==null)return null;
		String username=entity.getUsername();
		SystemUser user=repository.getByUsername(username);
		if(user!=null) {
			throw new RuntimeException("用户名已存在，请更换");
		}
		String password=entity.getPassword();
		String salt=EncryptService.generateSalt();
		entity.setSalt(salt);
		password=EncryptService.encryptPassword(password, salt);
		entity.setPassword(password);
		entity=repository.save(entity);
		return entity;
	}
	
	public SystemUser regist(String username, String password, Department department) {
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			throw new RuntimeException("用户名和密码都不能位空，请重新输入");
		}
		SystemUser user=new SystemUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setDepartment(department);
		return add(user);
	}
	
	/**对新注册的用户进行审核，审核通过账户才能使用。审核的同时，可以为用户分配角色*/
	public SystemUser authenticate(SystemUser user, Set<Role> roles) {
		user.setVerified(true);
		user.setRoles(roles);
		return user;
	}
	
	public SystemUser addRole(SystemUser user, Role role) {
		Set<Role> roles=user.getRoles();
		if(roles==null) roles=new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		return user;
	}
	
	public SystemUser removeRole(SystemUser user, Role role) {
		Set<Role> roles=user.getRoles();
		if(roles==null) return user;
		roles.remove(role);
		user.setRoles(roles);
		return user;
	}
}
