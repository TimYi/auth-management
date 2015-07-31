package com.shz.foundation.shortmessage.validate;

import com.shz.foundation.shortmessage.MessageSender;
import com.shz.foundation.shortmessage.SendMessageException;
import com.shz.foundation.utils.validate.PhoneNumberValidater;
import com.shz.foundation.validate.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageValidateManager {
	
	@Autowired
	private MessageValidaterBuilder builder;
	@Autowired
	private MessageValidaterRepository repository;
	@Autowired
	private MessageSender sender;

	/**
	 * 发送短信并保存验证凭据
	 * @param telephone
	 * @throws SendMessageException
	 */
	public void sendMessage(String telephone) throws SendMessageException {
		if(!PhoneNumberValidater.isMobile(telephone)) {
			throw new SendMessageException("手机号码不正确，请输入11位手机号码");
		}
		MessageValidater validater=repository.findOne(telephone);
		if(validater==null) {
			validater=builder.build(telephone);
		} else {
			validater=builder.build(validater);
		}
		repository.save(validater);
		sender.sendValidateMessage(telephone, validater.getValidater(), validater.getExpireMinutes());
	}
	
	/**
	 * 验证短信正确性，不正确抛出错误原因
	 * @param telephone
	 * @param validater
	 * @throws ValidateException
	 */
	public void validate(String telephone, String validater) throws ValidateException {
		MessageValidater messageValidater=repository.findOne(telephone);
		if(messageValidater==null) {
			throw new ValidateException("验证记录不存在，请确认验证短信已发送");
		}
		try {
			messageValidater.validate(validater);
		}  finally {
			repository.delete(messageValidater);
		}		
	}
}
