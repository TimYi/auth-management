package com.shz.foundation.shortmessage;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 统一读取配置信息
 * @author pc
 *
 */
public class Config {
	/**短信发送请求地址*/
	public static final String SMS_SITE;
	/**端口号*/
	public static final String SMS_PORT;
	/**账户*/
	public static final String SMS_ACCOUNT;
	/**账户token*/
	public static final String SMS_PASSWORD;
	/**应用Id*/
	public static final String SMS_APPID;
	/**验证短信template*/
	public static final String VALIDATE_TEMPLATE;
	
	private static Configuration configuration;
	
	static {
		try {
			configuration=new PropertiesConfiguration("cms.properties");			
		} catch (ConfigurationException e) {
		}
		SMS_SITE=configuration.getString("sms.site");
		SMS_PORT=configuration.getString("sms.port");
		SMS_ACCOUNT=configuration.getString("sms.account");
		SMS_PASSWORD=configuration.getString("sms.password");
		SMS_APPID=configuration.getString("sms.appid");
		VALIDATE_TEMPLATE=configuration.getString("sms.template");
	}
}
