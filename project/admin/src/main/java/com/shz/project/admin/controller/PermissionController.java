package com.shz.project.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.view.controller.PagingController;
import com.shz.project.admin.facade.system.permission.PermissionInputArgs;
import com.shz.project.admin.facade.system.permission.PermissionService;
import com.shz.project.admin.facade.system.permission.PermissionVo;
import com.shz.project.admin.facade.system.permission.PermissionInputArgs.PermissionType;
import com.shz.project.admin.facade.system.permission.field.FieldPermissionService;
import com.shz.project.admin.facade.system.permission.field.FieldPermissionVo;

@Controller
@RequestMapping("/permission")
public class PermissionController extends PagingController<PermissionVo, PermissionInputArgs> {

	@Autowired
	private PermissionService service;
	@Autowired
	private FieldPermissionService fieldService;
	
	@Override
	public PermissionService getService() {
		return service;
	}

	@Override
	protected String getBasePath() {
		return "permission";
	}

	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("redirect:permission/page/1");
	}
	
	@Override
	public ModelAndView listPage(@PathVariable Integer page,@RequestParam(defaultValue="8") Integer size,
			HttpServletRequest request) {
		String path=getBasePath()+"/page";
		ModelAndView view=new ModelAndView(path);
		PagedList<FieldPermissionVo> pagedList=fieldService.findPage(page, size);
		view.addObject("page", pagedList);
		return view;
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView addPage(HttpServletRequest request) {
		String path=getBasePath()+"/add";
		ModelAndView view=new ModelAndView(path);
		
		String type=request.getParameter("type");		
		PermissionType permissionType;
		if(type!=null) {
			permissionType=PermissionType.valueOf(type);
		} else {
			permissionType=PermissionType.FIELD;
		}
		view.addObject("type", permissionType);		
		
		String parentId=request.getParameter("parentId");
		if(parentId!=null) {
			PermissionVo permission=getService().findOne(parentId);
			view.addObject("parent", permission);
		}
		return view;
	}
}
