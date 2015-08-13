package com.shz.foundation.service;

import java.io.Serializable;

import com.shz.foundation.persistence.springdata.PagedList;


public interface PagingService<T, I, ID extends Serializable> extends CrudService<T, I, ID> {
	
	/**
	 * Returns a {@link PagedList} of entities meeting the paging restriction.
	 * @param page page number from 1
	 * @param size page size
	 * @return
	 */
	PagedList<? extends T> findPage(int page, int size);
}
