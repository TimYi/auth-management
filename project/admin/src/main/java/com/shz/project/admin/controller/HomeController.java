package com.shz.project.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shz.foundation.test.TestUtils;
import com.shz.project.admin.facade.system.department.DepartmentInputArgs;

@Controller
public class HomeController {

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
