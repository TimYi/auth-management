package com.shz.foundation.captcha.validate;

import com.shz.foundation.validate.AbstractValidaterBuilder;

public class CaptchaValidaterBuilder extends AbstractValidaterBuilder {

	private Integer expireMinutes=1;
	
	public CaptchaValidater build(String id, String validater) {
		CaptchaValidater captchaValidater=new CaptchaValidater();
		captchaValidater.setId(id);
		return build(captchaValidater, validater, expireMinutes);
	}
	
	public CaptchaValidater build(CaptchaValidater captchaValidater, String validater) {
		return build(captchaValidater, validater, expireMinutes);
	}
	
	public Integer getExpireMinutes() {
		return expireMinutes;
	}
	public void setExpireMinutes(Integer expireMinutes) {
		this.expireMinutes = expireMinutes;
	}
}
