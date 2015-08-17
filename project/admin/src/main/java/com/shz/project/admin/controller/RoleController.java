package com.shz.project.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.rest.RequestResult;
import com.shz.foundation.view.controller.PagingController;
import com.shz.project.admin.facade.system.role.RoleDto;
import com.shz.project.admin.facade.system.role.RoleInputArgs;
import com.shz.project.admin.facade.system.role.RoleService;
import com.shz.project.domain.system.role.Role.RoleType;

@Controller
@RequestMapping("role")
public class RoleController extends PagingController<RoleDto, RoleInputArgs> {

	@Autowired
	private RoleService service;
	
	@Override
	public RoleService getService() {
		return service;
	}

	@Override
	protected String getBasePath() {
		return "role";
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("redirect:role/page/1");
	}
	
	@RequestMapping(value="rest/department/page/{page}",method=RequestMethod.GET)
	public @ResponseBody String restPage(@PathVariable Integer page,@RequestParam(defaultValue="8") Integer size,
			HttpServletRequest request) {
		try {
			PagedList<RoleDto> pagedList=getService().findByType(RoleType.DEPARTMENT, page, size);
			return RequestResult.success(pagedList).toJson();
		} catch (Exception e) {
			return internalError(e);
		}		
	}
}
