package com.shz.foundation.dics;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryItemRepository extends PagingAndSortingRepository<CategoryItem, String> {
	List<CategoryItem> findByCategoryType(String type);
}
