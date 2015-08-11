package com.shz.foundation.view.menu;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.security.utils.AuthDecesider;

@Service
@Transactional
public class MenuManager {

	@Autowired
	private MenuRepository menuRepository;
	
	public List<Menu> accessedTopMenus() {
		List<Menu> menus=menuRepository.findTopMenus();
		menus=filtMenus(menus);
		return menus;
	}
	
	private boolean filtAuth(Menu menu) {
		if(!AuthDecesider.couldAccess(menu.getPermission())) {
			return false;
		} else {
			Set<Menu> subMenus=menu.getSubMenus();
			if(subMenus!=null && !subMenus.isEmpty() ) {
				subMenus=subMenus.stream().filter(m->filtAuth(m)).collect(Collectors.toSet());
				if(subMenus.isEmpty()) {
					return false;
				}
				menu.setSubMenus(subMenus);
			}
			return true;
		}
	}
	//对目录按照权限进行过滤
	private List<Menu> filtMenus(Collection<Menu> menus) {
		List<Menu> result=menus.stream().filter(m->filtAuth(m)).collect(Collectors.toList());
		return result;
	}
}
