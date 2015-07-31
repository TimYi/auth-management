package com.shz.foundation.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Java8TimeUtils {

	public static Date fromLocalDate(LocalDate date) {
		Instant instant=date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date res=Date.from(instant);
		return res;
	}
	
	public static Date fromLocalDateTime(LocalDateTime time) {
		Instant instant=time.atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}
	
	public static LocalDate asLocalDate(Date date) {
		Instant instant=Instant.ofEpochMilli(date.getTime());
		return instant.atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static LocalDateTime asLocalDateTime(Date date) {
		Instant instant=Instant.ofEpochMilli(date.getTime());
		return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}
