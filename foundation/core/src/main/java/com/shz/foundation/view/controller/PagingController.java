package com.shz.foundation.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.rest.RequestResult;
import com.shz.foundation.service.PagingService;
import com.shz.foundation.utils.LogUtils;

@Controller
public abstract class PagingController<T,I> {

	protected abstract PagingService<T,I, String> getService();
	/**jsp页面基础路径，结尾不带/*/
	protected abstract String getBasePath();
	
	@RequestMapping(value="page/{page}",method=RequestMethod.GET)
	public ModelAndView listPage(@PathVariable Integer page,@RequestParam(defaultValue="8") Integer size,
			HttpServletRequest request) {
		String path=getBasePath()+"/page";
		ModelAndView view=new ModelAndView(path);
		PagedList<? extends T> pagedList=getService().findPage(page, size);
		view.addObject("page", pagedList);
		return view;
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ModelAndView infoPage(@PathVariable String id) {
		String path=getBasePath()+"/info";
		ModelAndView view=new ModelAndView(path);
		T t=getService().findOne(id);
		view.addObject("t", t);
		return view;
	}
	
	@RequestMapping(value="{id}/edit",method=RequestMethod.GET)
	public ModelAndView eidtPage(@PathVariable String id) {
		String path=getBasePath()+"/edit";
		ModelAndView view=new ModelAndView(path);
		T t=getService().findOne(id);
		view.addObject("t", t);
		return view;
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public ModelAndView addPage(HttpServletRequest request) {
		String path=getBasePath()+"/add";
		ModelAndView view=new ModelAndView(path);
		return view;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody String add(I args) {
		try {
			getService().add(args);
			return RequestResult.success("添加成功").toJson();
		} catch (Exception e) {
			LogUtils.error(e);
			return internalError(e);
		}				
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.POST)
	public @ResponseBody String edit(I args) {
		try {
			getService().update(args);
			return RequestResult.success("修改成功").toJson();
		} catch (Exception e) {
			LogUtils.error(e);
			return internalError(e);
		}				
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public @ResponseBody String delete(@PathVariable String id) {
		try {
			getService().delete(id);
			return RequestResult.success("删除成功").toJson();
		} catch (Exception e) {
			LogUtils.error(e);
			return internalError(e);
		}
	}
	
	protected String internalError(Exception e) {
		e.printStackTrace();
		return RequestResult.error(500, "服务器内部错误", e.getMessage()).toJson();
	}
}
