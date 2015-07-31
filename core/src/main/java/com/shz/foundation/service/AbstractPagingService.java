package com.shz.foundation.service;

import java.io.Serializable;

import com.shz.foundation.persistence.springdata.PagedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class AbstractPagingService<T, ID extends Serializable>
	extends AbstractCrudService<T, ID> implements PagingService<T, T, ID> {
	
	@Autowired
	public void setPagingAndSortingRepository(PagingAndSortingRepository<T, ID> repository) {
		super.setRepository(repository);
	}
	
	@Override
	public PagingAndSortingRepository<T, ID> getRepository() {
		return (PagingAndSortingRepository<T, ID>)super.getRepository();
	}
	
	@Override
	public PagedList<T> findPage(int page, int size) {
		page=page-1; //PageRequest从0开始，需要减一以匹配
		PageRequest pageable=new PageRequest(page, size);
		Page<T> result=getRepository().findAll(pageable);
		return new PagedList<>(result);
	}
}
