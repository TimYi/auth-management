package com.shz.project.admin.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
import com.shz.foundation.service.PagingService;
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
	protected PagingService<SystemUserDto, SystemUserInputArgs, String> getService() {
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
}
