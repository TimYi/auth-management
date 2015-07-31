package com.shz.foundation.dics;

import java.util.List;

import com.shz.foundation.dics.dto.CategoryItemDto;
import com.shz.foundation.mapping.service.DtoPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CategoryItemService extends DtoPagingService<CategoryItem, CategoryItemDto, CategoryItemDto, String> {

	@Autowired
	public void setCategoryItemRepository(CategoryItemRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public CategoryItemRepository getRepository() {
		return (CategoryItemRepository)super.getRepository();
	}
	
	public List<CategoryItemDto> getCategoryItemsByType(String type) {
		List<CategoryItem> items=getRepository().findByCategoryType(type);
		return adapter.convertDoList(items);
	}
}
