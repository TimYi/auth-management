package com.shz.foundation.view.menu.facade;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import com.shz.foundation.view.menu.Menu;
import com.shz.foundation.view.menu.MenuRepository;

@Component
public class MenuAdapter extends AbstractDtoAdapter<Menu, MenuVo, MenuInputArgs> {
	
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Menu postConvertToDo(MenuInputArgs i, Menu d) {
		return postUpdate(i, d);
	}

	@Override
	public Menu postUpdate(MenuInputArgs i, Menu d) {
		String parentId=i.getParentId();
		if(StringUtils.isNotBlank(parentId)) {
			Menu parent=menuRepository.findOne(parentId);
			d.setParent(parent);
		}
		return d;
	}
}
