package com.shz.foundation.view.menu;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shz.foundation.security.access.UrlAccessDefinitionManager;
import com.shz.foundation.utils.LogUtils;
import com.shz.foundation.view.menu.Menu.MenuType;

@Service
public class MenuManager {

	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private UrlAccessDefinitionManager accessDefinitionManager;
	
	public List<Menu> accessedTopMenus() {
		List<Menu> menus=menuRepository.findTopMenus();
		menus=filtMenus(menus);
		return menus;
	}
	
	private boolean filtAuth(Menu menu) {
		try {
			if(menu.getType()==MenuType.URL) {
				if(menu.getUrl()==null) {
					return false;
				}
				accessDefinitionManager.couldAccess(menu.getUrl());	
				return true;
			} else {
				Set<Menu> subMenus=menu.getSubMenus();
				if(subMenus==null || subMenus.isEmpty()) return true;
				subMenus=subMenus.stream().filter(m->filtAuth(m)).collect(Collectors.toSet());
				if(subMenus.isEmpty()) {
					return false;
				}
				menu.setSubMenus(subMenus);
				return true;	
			}						
		} catch (Exception e) {
			LogUtils.debug(e.toString());
			return false;
		}
	}
	//对目录按照权限进行过滤
	private List<Menu> filtMenus(Collection<Menu> menus) {
		List<Menu> result=menus.stream().filter(m->filtAuth(m)).collect(Collectors.toList());
		return result;
	}
}
