package com.shz.foundation.shortmessage;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

/**
 * 封装后的短信发送器
 * @author pc
 *
 */
@Component()
public class MessageSender {
		
	private CCPRestSmsSDK smsSDK;
	
	public MessageSender(){
		smsSDK=SmsSDKCreater.getInstance();
	}
	
	public void sendMessage(String telephone, String template, String[] datas) throws SendMessageException {
		HashMap<String, Object> result=smsSDK.sendTemplateSMS(telephone, template, datas);
		if(!"000000".equals(result.get("statusCode"))){
			String message="错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg");
			throw new SendMessageException(message);
		}	
	}
	
	public void sendValidateMessage(String telephone, String validater, Integer expireMinutes) throws SendMessageException {
		String[] datas={validater,expireMinutes.toString()};
		sendMessage(telephone, Config.VALIDATE_TEMPLATE, datas);
	}
}
