package com.shz.foundation.validate;

import java.util.Calendar;
import java.util.Date;

public abstract class AbstractValidaterBuilder {

	public <T extends Validater> T build(T t, String validater, Integer expireMinutes) {
		t.setValidater(validater);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MINUTE, expireMinutes);
		Date expireTime=calendar.getTime();
		t.setExpireTime(expireTime);
		t.setExpireMinutes(expireMinutes);
		return t;
	}
}
