package com.shz.foundation.mapping.service;

import java.io.Serializable;

import com.shz.foundation.persistence.Identified;
import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.service.PagingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class DtoPagingService<D extends Identified<ID>, T,
	I extends Identified<ID>, ID extends Serializable> 
	extends DtoCrudService<D, T, I, ID>
	implements PagingService<T, I, ID>{
	
	public void setPagingAndSortingRepository(PagingAndSortingRepository<D, ID> repository) {
		super.setRepository(repository);
	}

	@Override
	public PagingAndSortingRepository<D, ID> getRepository() {
		return (PagingAndSortingRepository<D, ID>)super.getRepository();
	}
	
	@Override
	public PagedList<T> findPage(int page, int size) {
		PageRequest request=new PageRequest(page-1, size);
		Page<D> list=getRepository().findAll(request);
		Page<T> result=list.map(adapter);
		return new PagedList<>(result);
	}
}
