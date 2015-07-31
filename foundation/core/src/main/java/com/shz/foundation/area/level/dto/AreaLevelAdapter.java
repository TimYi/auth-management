package com.shz.foundation.area.level.dto;

import org.apache.commons.lang3.StringUtils;
import com.shz.foundation.area.level.AreaLevel;
import com.shz.foundation.area.level.AreaLevelRepository;
import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaLevelAdapter extends AbstractDtoAdapter<AreaLevel, AreaLevelDto, AreaLevelInputArgs> {

	@Autowired
	private AreaLevelRepository areaLevelRepository;
	
	@Override
	public AreaLevel postConvertToDo(AreaLevelInputArgs i, AreaLevel d) {
		return postUpdate(i, d);
	}

	@Override
	public AreaLevel postUpdate(AreaLevelInputArgs i, AreaLevel d) {
		String upperLevelId=i.getUpperLevelId();
		if(StringUtils.isNotBlank(upperLevelId)) {
			AreaLevel upperLevel=areaLevelRepository.findOne(upperLevelId);
			d.setUpperLevel(upperLevel);
		}
		return d;
	}
}
