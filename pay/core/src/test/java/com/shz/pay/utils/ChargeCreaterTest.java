package com.shz.pay.utils;

import org.junit.Test;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.shz.foundation.test.TestUtils;
import com.shz.pay.utils.pingpp.ChargeCreater;

public class ChargeCreaterTest {
	
	static {
		Pingpp.apiKey="sk_test_nr5evLWzfT00Gurrz14aXrLO";
	}

	@Test
	public void alipay() {
		String orderNo=OrderNumberGenerater.generateOrderNumber("TE");
		try {
			Charge charge=ChargeCreater.alipay(orderNo, "app_HGyTaTzrvrnTOevb", 1000L, "172.22.185.1", null,
					"测试商品", "商品购买测试", null, null, null);
			TestUtils.printJson(charge);
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException e) {
			e.printStackTrace();
		}		
	}
}
