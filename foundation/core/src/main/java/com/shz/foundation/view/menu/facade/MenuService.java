package com.shz.foundation.view.menu.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.mapping.service.DtoPagingService;
import com.shz.foundation.view.menu.Menu;
import com.shz.foundation.view.menu.Menu.MenuType;
import com.shz.foundation.view.menu.MenuManager;
import com.shz.foundation.view.menu.MenuRepository;

@Service
@Transactional
public class MenuService extends DtoPagingService<Menu, MenuVo, MenuInputArgs, String> {
	
	@Autowired
	private MenuManager menuManager;

	public MenuVo getByCode(String code) {
		Menu menu=getRepository().getByCode(code);
		return adapter.convertToDetailedDto(menu);
	}
	
	/**
	 * 获取所有一级目录，同时对目录进行权限过滤
	 * @return
	 */
	public List<MenuVo> findTopMenus() {
		List<Menu> menus=getRepository().findTopMenus();
		return adapter.convertDoList(menus);
	}
	
	//对菜单进行过滤时，必须处于非事务状态，否则过滤的结果会自动进行提交
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<MenuVo> accessedTopMenus() {
		List<Menu> menus=menuManager.accessedTopMenus();
		return adapter.convertDoList(menus);
	}
	
	public List<MenuVo> findByType(MenuType type) {
		List<Menu> menus=getRepository().findByType(type);
		return adapter.convertDoList(menus);
	}
	
	@Override
	public MenuRepository getRepository() {
		return (MenuRepository)super.getRepository();
	}
}
