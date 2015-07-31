package com.shz.foundation.dics.dto;

import org.apache.commons.lang3.StringUtils;
import com.shz.foundation.dics.Category;
import com.shz.foundation.dics.CategoryRepository;
import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoAdapter extends AbstractDtoAdapter<Category, CategoryDto, CategoryInputArgs> {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category postConvertToDo(CategoryInputArgs t, Category d) {
		return postUpdate(t, d);
	}

	@Override
	public Category postUpdate(CategoryInputArgs t, Category d) {
		String parentid=t.getParentId();
		Category parent=null;
		if(StringUtils.isNotEmpty(parentid)) {
			parent=categoryRepository.findOne(parentid);
		} else {
			String parentType=t.getParentType();
			if(StringUtils.isNotEmpty(parentType)) {
				parent=categoryRepository.getByType(parentType);
			}
		}
		d.setParent(parent);
		return d;
	}
}
