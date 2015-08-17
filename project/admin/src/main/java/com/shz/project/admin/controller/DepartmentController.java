package com.shz.project.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shz.foundation.view.controller.PagingController;
import com.shz.project.admin.facade.system.department.DepartmentDto;
import com.shz.project.admin.facade.system.department.DepartmentInputArgs;
import com.shz.project.admin.facade.system.department.DepartmentService;

@Controller
@RequestMapping("department")
public class DepartmentController extends PagingController<DepartmentDto, DepartmentInputArgs> {

	@Autowired
	private DepartmentService service;
	
	@Override
	protected DepartmentService getService() {
		return service;
	}

	@Override
	protected String getBasePath() {
		return "department";
	}
}
