package com.shz.foundation.area.level;

import com.shz.foundation.area.level.dto.AreaLevelDto;
import com.shz.foundation.area.level.dto.AreaLevelInputArgs;
import com.shz.foundation.mapping.service.DtoPagingService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AreaLevelService extends DtoPagingService<AreaLevel, AreaLevelDto, AreaLevelInputArgs, String> {

	
	public AreaLevelDto topLevel() {
		AreaLevel level=getRepository().topLevel();
		return adapter.convert(level);
	}
	
	@Override
	public AreaLevelRepository getRepository() {
		return (AreaLevelRepository)super.getRepository();
	}
}
