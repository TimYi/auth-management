package com.shz.foundation.view.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.service.dynamic.SpecifactionService;
import com.shz.foundation.utils.Servlets;

public abstract class SpecificationController<T,I> extends PagingController<T, I> {

	public static final String SEARCH_PREFIX="search_";
	public static final String SORT_PREFIX="sort_";
	
	@Override
	protected abstract SpecifactionService<T, I, String> getService();

	@Override
	public ModelAndView listPage(@PathVariable Integer page,@RequestParam(defaultValue="8") Integer size,
			HttpServletRequest request) {
		String path=getBasePath()+"/page";
		ModelAndView view=new ModelAndView(path);
		Map<String, Object> searchs=Servlets.getParametersStartingWith(request, SEARCH_PREFIX);
		Map<String, Object> sorts=Servlets.getParametersStartingWith(request, SORT_PREFIX);
		PagedList<T> result=getService().findAll(page, size, searchs, sorts);
		view.addObject("page", result);
		return view;
	}
}
