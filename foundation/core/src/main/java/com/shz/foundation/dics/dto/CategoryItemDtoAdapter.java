package com.shz.foundation.dics.dto;

import org.apache.commons.lang3.StringUtils;
import com.shz.foundation.dics.Category;
import com.shz.foundation.dics.CategoryItem;
import com.shz.foundation.dics.CategoryRepository;
import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryItemDtoAdapter extends AbstractDtoAdapter<CategoryItem, CategoryItemDto, CategoryItemInputArgs> {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryItem postConvertToDo(CategoryItemInputArgs t, CategoryItem d) {
		return postUpdate(t, d);
	}

	@Override
	public CategoryItem postUpdate(CategoryItemInputArgs t, CategoryItem d) {
		String type=t.getType();
		String categoryid=t.getCategoryId();
		Category category=null;
		if(StringUtils.isNotEmpty(type)) {
			category=categoryRepository.getByType(type);
		} else {
			if(StringUtils.isNotEmpty(categoryid)) {
				category=categoryRepository.findOne(categoryid);
			}
		}
		d.setCategory(category);
		return d;
	}
	
}
