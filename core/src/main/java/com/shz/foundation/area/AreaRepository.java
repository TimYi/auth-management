package com.shz.foundation.area;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AreaRepository extends PagingAndSortingRepository<Area, String> {

	List<Area> findByLevelId(String levelId);
	
	List<Area> findByUpperAreaId(String upperAreaId);
}
