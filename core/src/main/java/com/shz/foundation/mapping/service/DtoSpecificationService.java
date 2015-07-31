package com.shz.foundation.mapping.service;

import java.io.Serializable;
import java.util.Map;

import com.shz.foundation.persistence.Identified;
import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.persistence.springdata.SpecificationRepository;
import com.shz.foundation.service.dynamic.DynamicSpecifications;
import com.shz.foundation.service.dynamic.SearchFilter;
import com.shz.foundation.service.dynamic.SortFilter;
import com.shz.foundation.service.dynamic.SpecifactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public class DtoSpecificationService
	<D extends Identified<ID>, T,
	I extends Identified<ID>, ID extends Serializable>
	extends DtoPagingService<D, T, I, ID>
	implements SpecifactionService<T, I, ID> {
	
	@Override
	public PagedList<T> findAll(int page, int size,
			Map<String, Object> filters, Map<String, Object> orders) {
		Pageable pageable=SortFilter.toPageable(page, size, orders);
		Map<String, SearchFilter> filterMap=SearchFilter.parse(filters);
		Specification<D> specification=DynamicSpecifications.bySearchFilter(filterMap.values(), null);
		Page<D> dlist=getRepository().findAll(specification, pageable);
		Page<T> list=dlist.map(adapter);
		return new PagedList<T>(list);
	}
	
	@Override
	public SpecificationRepository<D, ID> getRepository() {
		return (SpecificationRepository<D, ID>)super.getRepository();
	}
	
	@Autowired
	public void setSpecificationRepository(
			SpecificationRepository<D, ID> repository) {
		super.setPagingAndSortingRepository(repository);
	}
}
