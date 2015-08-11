package com.shz.pay.utils;

import org.junit.Test;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Refund;
import com.shz.foundation.rest.RequestResult;
import com.shz.pay.utils.pingpp.RefundCreater;

public class RefundCreaterTest {

	static {
		Pingpp.apiKey="sk_test_nr5evLWzfT00Gurrz14aXrLO";
	}
	
	@Test
	public void createRefund() {
		try {
			Refund refund=RefundCreater.create("ch_j5qfj1XjnzrT4CS4yPO8ifj1", 1000L, "测试退款", null);
			System.out.println(RequestResult.success(refund).toJson());
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException e) {
			e.printStackTrace();
		}		
	}
}
