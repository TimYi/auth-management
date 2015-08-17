package com.shz.project.admin.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shz.project.admin.authenticate.SystemUserRealm.ShiroUser;
import com.shz.project.admin.facade.system.department.DepartmentDto;
import com.shz.project.admin.facade.system.department.DepartmentService;
import com.shz.project.admin.facade.system.role.RoleDto;
import com.shz.project.admin.facade.system.role.RoleService;
import com.shz.project.admin.facade.system.user.SystemUserDto;
import com.shz.project.admin.facade.system.user.SystemUserInputArgs;
import com.shz.project.admin.facade.system.user.SystemUserService;
import com.shz.project.domain.system.role.Role;
import com.shz.project.domain.system.user.SystemUser;
import com.shz.project.domain.system.user.SystemUserRepository;
import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.view.controller.PagingController;

@Controller
@RequestMapping("user")
public class UserController extends PagingController<SystemUserDto, SystemUserInputArgs> {

	@Autowired
	private SystemUserService service;
	@Autowired
	private SystemUserRepository repository;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private RoleService roleService;
	
	
	
	@Override
	protected SystemUserService getService() {
		return service;
	}
	@Override
	protected String getBasePath() {
		return "user";
	}
	
	@Override
	public ModelAndView addPage(HttpServletRequest request) {
		ModelAndView view=super.addPage(request);
		List<DepartmentDto> departments=departmentService.findAll();
		view.addObject("ds", departments);
		List<RoleDto> roles=roleService.findAll();
		view.addObject("rs", roles);
		return view;
	}
	
	@Override
	@Transactional
	public ModelAndView editPage(@PathVariable String id) {
		ModelAndView view=super.editPage(id);
		List<DepartmentDto> departments=departmentService.findAll();
		view.addObject("ds", departments);
		List<RoleDto> roles=roleService.findAll();
		SystemUser user=repository.findOne(id);
		Set<Role> userRoles=user==null?new HashSet<>():user.getRoles();
		List<String> roleIds=userRoles.stream().map(Role::getId).collect(Collectors.toList());
		view.addObject("rids", roleIds);
		view.addObject("rs", roles);
		return view;
	}
	
	@RequestMapping(value="authentication/page/{page}",method=RequestMethod.GET)
	public ModelAndView authentication(@PathVariable Integer page,@RequestParam(defaultValue="8") Integer size) {
		ModelAndView view=new ModelAndView(getBasePath()+"/authentication");
		PagedList<SystemUserDto> users=getService().unauthenticatedUsers(page, size);
		view.addObject("page", users);
		return view;
	}
	
	@RequestMapping(value="profile/{userId}",method=RequestMethod.GET)
	public ModelAndView profile(@PathVariable String userId) {
		Subject subject=SecurityUtils.getSubject();
		if(!subject.isAuthenticated()) {
			throw new AuthenticationException("请登录！");
		}
		ShiroUser user=(ShiroUser)subject.getPrincipal();
		String id=user.getId();
		if(!id.equals(userId)) {
			throw new RuntimeException("您只能访问您自己的个人信息");
		}
		SystemUserDto info=getService().findOne(userId);
		ModelAndView view=new ModelAndView(getBasePath()+"/profile");
		view.addObject("t", info);
		return view;
	}
}
