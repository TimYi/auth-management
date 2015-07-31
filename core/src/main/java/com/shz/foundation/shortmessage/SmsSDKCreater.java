package com.shz.foundation.shortmessage;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

public class SmsSDKCreater {
	private static CCPRestSmsSDK smsSDK;
	
	public static CCPRestSmsSDK getInstance(){
		if(smsSDK==null){
			smsSDK=new CCPRestSmsSDK();
			smsSDK.init(Config.SMS_SITE, Config.SMS_PORT);
			smsSDK.setAccount(Config.SMS_ACCOUNT, Config.SMS_PASSWORD);
			smsSDK.setAppId(Config.SMS_APPID);
		}
		return smsSDK;
	}
}
