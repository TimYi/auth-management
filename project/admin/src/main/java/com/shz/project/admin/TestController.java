package com.shz.project.admin;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {
	

	@RequiresRoles("admin")
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
}
