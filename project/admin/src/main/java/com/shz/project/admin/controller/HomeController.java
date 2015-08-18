package com.shz.project.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shz.foundation.captcha.SimpleCaptchaUtils;
import com.shz.foundation.rest.RequestResult;
import com.shz.foundation.utils.LogUtils;
import com.shz.project.admin.facade.system.department.DepartmentDto;
import com.shz.project.admin.facade.system.department.DepartmentService;
import com.shz.project.admin.facade.system.user.SystemUserService;
import com.shz.project.domain.system.user.SystemUserManager;

@Controller
public class HomeController {

	@Autowired
	private SystemUserService userService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private SystemUserManager userManager;
	
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
	
	@RequestMapping(value="changePassword",method=RequestMethod.GET)
	public ModelAndView changePassword() {
		return new ModelAndView("password");
	}
	
	@RequestMapping(value="changePassword",method=RequestMethod.POST)
	public @ResponseBody String changePassword(String username, String oldPassword, 
			String newPassword, String confirmPassword, HttpServletRequest request) {
		try {
			if(!SimpleCaptchaUtils.rightCaptcha(request)) {
				throw new RuntimeException("验证码错误！");
			}
			if(!newPassword.equals(confirmPassword)) throw new RuntimeException("两次输入密码不一致！");
			userService.changePassword(username, oldPassword, newPassword);
			return RequestResult.success("修改密码成功").toJson();
		} catch (Exception e) {
			return RequestResult.internalError(e);
		}		
	}
	
	@RequestMapping(value="forgetPassword/sendEmail",method=RequestMethod.GET)
	public ModelAndView sendValidateEmail() {
		return new ModelAndView("sendValidateEmail");
	}
	
	@RequestMapping(value="forgetPassword/sendEmail",method=RequestMethod.POST)
	public @ResponseBody String sendValidateEmail(String email,HttpServletRequest request) {
		if(!SimpleCaptchaUtils.rightCaptcha(request)) {
			return RequestResult.illegalArgument("验证码错误！");
		}
		try {
			userManager.emailValidate(email);
			return RequestResult.success("邮件发送成功").toJson();
		} catch (Exception e) {
			return RequestResult.internalError(e);
		}
	}
	
	@RequestMapping(value="changePassword/byEmail",method=RequestMethod.GET)
	public ModelAndView emailValidate(String validater) {
		userManager.checkEmailValidater(validater);
		ModelAndView view=new ModelAndView("emailValidate");
		view.addObject("validater", validater);
		return view;
	}
	
	@RequestMapping(value="changePassword/byEmail",method=RequestMethod.POST)
	public @ResponseBody String emailValidate(String validater, String newPassword, String confirmPassword) {
		System.out.println(validater);
		if(!newPassword.equals(confirmPassword)) {
			return RequestResult.illegalArgument("两次输入密码不一致");
		}
		try {			
			userManager.changePasswordByEmail(validater, newPassword);
			return RequestResult.success("修改成功").toJson();
		} catch (Exception e) {
			return RequestResult.internalError(e);
		}
	}
	
	
	/*
	@RequestMapping(value="test",method=RequestMethod.GET)
	public String test() {
		return "test";
	}
	
	@RequestMapping(value="test",method=RequestMethod.POST)
	public @ResponseBody String test(DepartmentInputArgs args) {
		TestUtils.printJson(args);
		return "success";
	}*/
}
