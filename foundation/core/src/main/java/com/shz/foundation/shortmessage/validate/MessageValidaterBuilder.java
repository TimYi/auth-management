package com.shz.foundation.shortmessage.validate;

import com.shz.foundation.utils.CodeGenerater;
import com.shz.foundation.validate.AbstractValidaterBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageValidaterBuilder extends AbstractValidaterBuilder {

	private Integer charCount=4;
	private Integer expireMinutes=5;
	
	public MessageValidater build(String telephone) {
		MessageValidater messageValidater=new MessageValidater();
		messageValidater.setId(telephone);
		return build(messageValidater);
	}
	
	public MessageValidater build(MessageValidater messageValidater) {
		String validater=CodeGenerater.getRandomNumber(charCount);
		return build(messageValidater, validater, expireMinutes);
	}
	
	public Integer getCharCount() {
		return charCount;
	}
	public void setCharCount(Integer charCount) {
		this.charCount = charCount;
	}
	public Integer getExpireMinutes() {
		return expireMinutes;
	}
	public void setExpireMinutes(Integer expireMinutes) {
		this.expireMinutes = expireMinutes;
	}
}
