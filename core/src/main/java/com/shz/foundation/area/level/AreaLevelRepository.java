package com.shz.foundation.area.level;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AreaLevelRepository extends PagingAndSortingRepository<AreaLevel, String> {

	@Query("select a from AreaLevel a where a.upperLevel is null")
	AreaLevel topLevel();
}
