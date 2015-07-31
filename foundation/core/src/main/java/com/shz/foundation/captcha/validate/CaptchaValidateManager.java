package com.shz.foundation.captcha.validate;

import java.awt.image.BufferedImage;

import nl.captcha.Captcha;

import com.shz.foundation.captcha.CaptchaGenerater;
import com.shz.foundation.validate.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CaptchaValidateManager {

	@Autowired
	private CaptchaValidaterRepository repository;
	@Autowired
	private CaptchaValidaterBuilder builder;
	
	public BufferedImage validate(String id) {
		Captcha captcha=CaptchaGenerater.generateCaptcha();
		String validater=captcha.getAnswer();
		CaptchaValidater captchaValidater=repository.findOne(id);
		if(captchaValidater==null) {
			captchaValidater=builder.build(id, validater);
		} else {
			captchaValidater=builder.build(captchaValidater, validater);
		}
		repository.save(captchaValidater);
		return captcha.getImage();
	}
	
	public void validate(String id, String validater) throws ValidateException {
		CaptchaValidater captchaValidater=repository.findOne(id);
		if(captchaValidater==null) {
			throw new ValidateException("验证码id不存在");
		}
		captchaValidater.validate(validater);
	}
}
