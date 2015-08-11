package com.shz.pay.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.shz.foundation.test.TestUtils;
import com.shz.pay.model.Channel;
import com.shz.pay.utils.OrderNumberGenerater;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PayManagerTest {

	static {
		Pingpp.apiKey="sk_test_nr5evLWzfT00Gurrz14aXrLO";
	}
	
	@Autowired
	private PayManager manager;
	
	@Test
	public void createPayRecord() {
		ChargeInputArgs args=new ChargeInputArgs();
		String orderNo=OrderNumberGenerater.generateOrderNumber("TE");
		args.setOrderNo(orderNo);
		args.setAppId("app_HGyTaTzrvrnTOevb");
		args.setClientIP("172.22.185.1");
		args.setChannel(Channel.alipay);
		args.setAmount(1000.0);
		args.setSubject("测试支付");
		args.setBody("测试支付");
		try {
			PayRecord payRecord=manager.create(args);
			TestUtils.printJson(payRecord);
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException e) {
			e.printStackTrace();
		}
	}
}
