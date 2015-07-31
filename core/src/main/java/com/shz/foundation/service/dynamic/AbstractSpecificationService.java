package com.shz.foundation.service.dynamic;

import java.io.Serializable;
import java.util.Map;

import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.persistence.springdata.SpecificationRepository;
import com.shz.foundation.service.AbstractPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractSpecificationService<T, ID extends Serializable>
	extends AbstractPagingService<T, ID>
	implements SpecifactionService<T, T, ID> {

	@Override
	public PagedList<T> findAll(int page, int size,
			Map<String, Object> filters, Map<String, Object> orders) {
		Pageable pageable=SortFilter.toPageable(page, size, orders);
		Map<String, SearchFilter> filterMap=SearchFilter.parse(filters);
		Specification<T> specification=DynamicSpecifications.bySearchFilter(filterMap.values(), null);
		Page<T> list=getRepository().findAll(specification, pageable);
		return new PagedList<T>(list);
	}
	
	@Override
	public SpecificationRepository<T, ID> getRepository() {
		return (SpecificationRepository<T, ID>)super.getRepository();
	}
	
	@Autowired
	public void setSpecificationRepository(
			SpecificationRepository<T, ID> repository) {
		super.setPagingAndSortingRepository(repository);
	}
}
