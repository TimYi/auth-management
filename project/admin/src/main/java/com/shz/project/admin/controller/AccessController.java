package com.shz.project.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shz.foundation.rest.RequestResult;
import com.shz.foundation.security.access.UrlAccessDefinition;
import com.shz.foundation.security.access.UrlAccessDefinitionService;
import com.shz.foundation.view.controller.PagingController;

@Controller
@RequestMapping("access")
public class AccessController extends PagingController<UrlAccessDefinition, UrlAccessDefinition> {

	@Autowired
	private UrlAccessDefinitionService service;
	
	@Override
	protected UrlAccessDefinitionService getService() {
		return service;
	}

	@Override
	protected String getBasePath() {
		return "access";
	}
	
	@RequestMapping(value="refresh",method=RequestMethod.POST)
	public @ResponseBody String refresh() {
		getService().refresh();
		return RequestResult.success("刷新成功").toJson();
	}
}
