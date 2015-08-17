package com.shz.project.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shz.foundation.rest.RequestResult;
import com.shz.foundation.test.TestUtils;
import com.shz.foundation.utils.LogUtils;
import com.shz.project.admin.facade.system.department.DepartmentDto;
import com.shz.project.admin.facade.system.department.DepartmentInputArgs;
import com.shz.project.admin.facade.system.department.DepartmentService;
import com.shz.project.admin.facade.system.user.SystemUserService;

@Controller
public class HomeController {

	@Autowired
	private SystemUserService userService;
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String loginError() {
		return "login";
	}
	
	@RequestMapping(value="regist",method=RequestMethod.GET)
	public ModelAndView regist() {
		ModelAndView view=new ModelAndView("regist");
		List<DepartmentDto> ds=departmentService.findAll();
		view.addObject("ds", ds);
		return view;
	}
	
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public @ResponseBody String regist(String username, String password, String realname, String email, String telephone,
			String departmentId) {
		try {
			userService.regist(username, password, realname, email, telephone, departmentId);
			return RequestResult.success("申请成功，请等待审核").toJson();
		} catch (Exception e) {
			LogUtils.debug(e.toString());
			return RequestResult.internalError(e);
		}		
	}
	
	@RequestMapping(value="test",method=RequestMethod.GET)
	public String test() {
		return "test";
	}
	
	@RequestMapping(value="test",method=RequestMethod.POST)
	public @ResponseBody String test(DepartmentInputArgs args) {
		TestUtils.printJson(args);
		return "success";
	}
}
