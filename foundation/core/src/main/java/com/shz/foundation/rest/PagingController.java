package com.shz.foundation.rest;

import javax.servlet.http.HttpServletRequest;

import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.service.PagingService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class PagingController<T,I> {

	protected abstract PagingService<T,I, String> getService();
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String page(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="8") Integer size,HttpServletRequest request) {
		PagedList<? extends T> result=getService().findPage(page, size);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="all",method=RequestMethod.GET)
	public String page() {
		Iterable<? extends T> result=getService().findAll();
		return RequestResult.success(new PagedList<>(result)).toJson();
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String info(@PathVariable String id) {
		T result=getService().findOne(id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String add(I t) {
		getService().add(t);
		return RequestResult.success("保存成功").toJson();
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.POST)
	public String edit(I t) {
		getService().update(t);
		return RequestResult.success("修改成功").toJson();
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable String id) {
		getService().delete(id);
		return RequestResult.success("删除成功").toJson();
	}
}
