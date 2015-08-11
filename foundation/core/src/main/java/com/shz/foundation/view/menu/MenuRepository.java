package com.shz.foundation.view.menu;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shz.foundation.view.menu.Menu.MenuType;

public interface MenuRepository extends PagingAndSortingRepository<Menu, String> {

	Menu getByCode(String code);
	
	@Query("select m from Menu m where m.parent is null order by m.ordernum")
	List<Menu> findTopMenus();
	
	List<Menu> findByType(MenuType type);
}
