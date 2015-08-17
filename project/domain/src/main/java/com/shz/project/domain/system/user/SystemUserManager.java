package com.shz.project.domain.system.user;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.utils.validate.EmailValidater;
import com.shz.foundation.utils.validate.PhoneNumberValidater;
import com.shz.foundation.utils.validate.UsernameValidater;
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
		
		//用户名检查
		String username=entity.getUsername();
		checkUsername(username);
		//邮箱检查
		String email=entity.getEmail();
		checkEmail(email);
		
		//手机号码检查
		String telephone=entity.getTelephone();
		checkTelephone(telephone);
		
		//生成加密密码
		String password=entity.getPassword();
		String salt=EncryptService.generateSalt();
		entity.setSalt(salt);
		password=EncryptService.encryptPassword(password, salt);
		entity.setPassword(password);
		entity=repository.save(entity);
		return entity;
	}
	
	public SystemUser update(String id, String username, String realname, Boolean verified, String email, String telephone,
			Department department, Set<Role> roles) {
		SystemUser user=repository.findOne(id);
		if(user==null) return null;
		if(!user.getUsername().equals(username)) {
			checkUsername(username);
		}
		if(!user.getEmail().equals(email)) {
			checkEmail(email);
		}
		if(!user.getTelephone().equals(telephone)) {
			checkTelephone(telephone);
		}
		user.setUsername(username);
		user.setRealname(realname);
		user.setVerified(verified);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setDepartment(department);
		user.setRoles(roles);
		return user;
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
	
	/**检查用户名，需要满足2~10位数字+字母这个规则，不能和已有用户名重复，不能有特殊符号*/
	private void checkUsername(String username) {
		if(StringUtils.isBlank(username)) throw new RuntimeException("用户名不能为空");
		if(!UsernameValidater.isUsername(username)) {
			throw new RuntimeException("只能使用2~10位字母或数字组成的用户名");
		}
		//用户名检查
		SystemUser user=repository.getByUsername(username);
		if(user!=null) {
			throw new RuntimeException("用户名已存在，请更换");
		}
		
	}
	
	/**检查邮箱，需要满足邮箱地址格式，不能重复*/
	private void checkEmail(String email) {
		//邮箱检查
		if(StringUtils.isBlank(email)) {
			throw new RuntimeException("需要填写邮箱地址");
		}
		if(!EmailValidater.isEmail(email)) {
			throw new RuntimeException("请填写正确的邮箱地址");
		}
		SystemUser eUser=repository.getByEmail(email);
		if(eUser!=null) {
			throw new RuntimeException("邮箱地址已存在，请更换");
		}
	}
	
	/**检查手机号，需要满足11位手机格式，不能有重复*/
	private void checkTelephone(String telephone) {
		//手机号码检查
		if(StringUtils.isBlank(telephone)) {
			throw new RuntimeException("需要填写手机号码");
		}
		if(!PhoneNumberValidater.isMobile(telephone)) {
			throw new RuntimeException("请填写正确的11位手机号码");
		}
		SystemUser tUser=repository.getByEmail(telephone);
		if(tUser!=null) {
			throw new RuntimeException("手机号码已存在，请更换");
		}
	}
}
