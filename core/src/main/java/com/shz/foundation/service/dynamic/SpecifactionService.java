package com.shz.foundation.service.dynamic;

import java.io.Serializable;
import java.util.Map;

import com.shz.foundation.persistence.springdata.PagedList;
import com.shz.foundation.service.PagingService;

public interface SpecifactionService<T, I, ID extends Serializable>
	extends PagingService<T, I, ID> {

	PagedList<T> findAll(int page, int size,
			Map<String, Object> filters, Map<String, Object> orders);
}
