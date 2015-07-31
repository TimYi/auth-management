package com.shz.foundation.area.dto;

import org.apache.commons.lang3.StringUtils;
import com.shz.foundation.area.Area;
import com.shz.foundation.area.AreaRepository;
import com.shz.foundation.area.level.AreaLevel;
import com.shz.foundation.area.level.AreaLevelRepository;
import com.shz.foundation.mapping.adapter.AbstractDtoAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaAdapter extends AbstractDtoAdapter<Area, AreaDto, AreaInputArgs> {
	
	@Autowired
	private AreaLevelRepository areaLevelRepository;
	@Autowired
	private AreaRepository areaRepository;

	@Override
	public Area postConvertToDo(AreaInputArgs i, Area d) {
		return postUpdate(i, d);
	}

	@Override
	public Area postUpdate(AreaInputArgs i, Area d) {
		String levelId=i.getLevelId();
		if(StringUtils.isNotBlank(levelId)) {
			AreaLevel level=areaLevelRepository.findOne(levelId);
			d.setLevel(level);
		}
		String upperAreaId=i.getUpperAreaId();
		if(StringUtils.isNotBlank(upperAreaId)) {
			Area upperArea=areaRepository.findOne(upperAreaId);
			d.setUpperArea(upperArea);
		}
		validate(d);
		return d;
	}

	/**
	 * 验证area的level和上级区域level是否匹配
	 * @param area
	 * @return
	 */
	private void validate(Area area) {
		Area upperArea=area.getUpperArea();
		AreaLevel level=area.getLevel();
		if(upperArea==null || level==null) return;
		if(!upperArea.getLevel().isUpperLevelTo(level)) {
			throw new RuntimeException("区域等级和上级区域无法匹配");
		}
	}
}
