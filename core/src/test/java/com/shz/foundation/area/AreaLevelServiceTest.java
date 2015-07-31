package com.shz.foundation.area;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.shz.foundation.area.level.AreaLevelService;
import com.shz.foundation.area.level.dto.AreaLevelDto;
import com.shz.foundation.area.level.dto.AreaLevelInputArgs;
import com.shz.foundation.rest.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AreaLevelServiceTest {

	@Autowired
	private AreaLevelService service;
	
	@Test
	public void add() {
		AreaLevelInputArgs args=new AreaLevelInputArgs();
		args.setName("国家");
		AreaLevelDto level1=service.add(args);
		AreaLevelInputArgs args2=new AreaLevelInputArgs();
		args2.setName("省");
		args2.setUpperLevelId(level1.getId());
		AreaLevelDto level2=service.add(args2);
		System.out.println(RequestResult.success(level2).toJson());
	}
}
