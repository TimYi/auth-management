package com.shz.foundation.security.access;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.service.AbstractPagingService;

@Service
@Transactional
public class UrlAccessDefinitionService extends AbstractPagingService<UrlAccessDefinition, String> {
	
	@Autowired
	private UrlAccessDefinitionManager manager;
	
	@Override
	public UrlAccessDefinition update(UrlAccessDefinition args) {
		String id=args.getId();
		UrlAccessDefinition definition=getRepository().findOne(id);
		definition.setUrl(args.getUrl());
		definition.setOrdernum(args.getOrdernum());
		definition.setPermission(args.getPermission());
		return definition;
	}
	
	@Override
	public PagedList<UrlAccessDefinition> findPage(int page, int size) {
		page=page-1; //PageRequest从0开始，需要减一以匹配
		PageRequest pageable=new PageRequest(page, size, UrlAccessDefinitionManager.SORT);
		Page<UrlAccessDefinition> result=getRepository().findAll(pageable);
		return new PagedList<>(result);
	}
	
	@Override
	public List<UrlAccessDefinition> findAll() {
		Iterable<UrlAccessDefinition> result=getRepository().findAll(UrlAccessDefinitionManager.SORT);
		if(result==null) return null;
	    List<UrlAccessDefinition> list=new ArrayList<>();
	    for (UrlAccessDefinition t : result) {
			list.add(t);
		}
	    return list;
	}
	
	public void refresh() {
		manager.refresh();
	}
}
