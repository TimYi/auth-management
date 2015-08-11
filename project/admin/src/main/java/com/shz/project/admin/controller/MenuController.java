package com.shz.project.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shz.foundation.rest.RequestResult;
import com.shz.foundation.utils.LogUtils;
import com.shz.foundation.view.controller.PagingController;
import com.shz.foundation.view.menu.Menu.MenuType;
import com.shz.foundation.view.menu.facade.MenuInputArgs;
import com.shz.foundation.view.menu.facade.MenuService;
import com.shz.foundation.view.menu.facade.MenuVo;

@Controller
@RequestMapping("/menu")
public class MenuController extends PagingController<MenuVo, MenuInputArgs> {
	
	@Autowired
	private MenuService service;

	@Override
	protected MenuService getService() {
		return service;
	}

	@Override
	protected String getBasePath() {
		return "menu";
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view=new ModelAndView("menu/index");
		List<MenuVo> menus=getService().findTopMenus();
		view.addObject("menus", menus);
		return view;
	}	
	
	@Override
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView addPage(HttpServletRequest request) {
		String path=getBasePath()+"/add";
		ModelAndView view=new ModelAndView(path);
		List<MenuVo> menus=getService().findByType(MenuType.CONTAINER);
		String parentId=request.getParameter("parentId");
		view.addObject("parentId",parentId);
		view.addObject("menus", menus);
		return view;
	}
	
	@Override
	@RequestMapping(value="{id}/edit",method=RequestMethod.GET)
	public ModelAndView eidtPage(@PathVariable String id) {
		String path=getBasePath()+"/edit";
		ModelAndView view=new ModelAndView(path);
		MenuVo t=getService().findOne(id);
		List<MenuVo> menus=getService().findByType(MenuType.CONTAINER);
		view.addObject("t", t);
		view.addObject("menus", menus);
		return view;
	}
	
	@RequestMapping(value="accessedTops",method=RequestMethod.GET)
	public @ResponseBody String accessedTopMenus() {
		try {
			List<MenuVo> menus=getService().accessedTopMenus();
			return RequestResult.success(menus).toJson();
		} catch (Exception e) {
			LogUtils.error(e);
			return internalError(e);
		}
	}
	
	@RequestMapping(value="tops",method=RequestMethod.GET)
	public @ResponseBody String topMenus() {
		try {
			List<MenuVo> menus=getService().findTopMenus();
			return RequestResult.success(menus).toJson();
		} catch (Exception e) {
			LogUtils.error(e);
			return internalError(e);
		}
	}
}
