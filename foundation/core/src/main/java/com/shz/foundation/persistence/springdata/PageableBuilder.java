package com.shz.foundation.persistence.springdata;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableBuilder {

	public static Pageable build(int page, int size) {
		PageRequest pageRequest=new PageRequest(page-1, size);
		return pageRequest;
	}
	
	public static Pageable build(Sort sort, int page, int size) {
		PageRequest pageRequest=new PageRequest(page-1, size, sort);
		return pageRequest;
	}
}
