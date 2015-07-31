package com.shz.foundation.area;

import java.util.List;

import com.shz.foundation.area.dto.AreaDto;
import com.shz.foundation.area.dto.AreaInputArgs;
import com.shz.foundation.mapping.service.DtoPagingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AreaService extends DtoPagingService<Area, AreaDto, AreaInputArgs, String> {

	public List<AreaDto> findByLevel(String levelId) {
		List<Area> areas=getRepository().findByLevelId(levelId);
		return adapter.convertDoList(areas);
	}
	
	public List<AreaDto> findByUpperArea(String upperAreaId) {
		List<Area> areas=getRepository().findByUpperAreaId(upperAreaId);
		return adapter.convertDoList(areas);
	}
	
	@Override
	public AreaRepository getRepository() {
		return (AreaRepository)super.getRepository();
	}
}
