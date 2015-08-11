package com.shz.pay.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.shz.foundation.rest.RequestResult;
import com.shz.foundation.utils.Servlets;
import com.shz.pay.core.ChargeInputArgs;
import com.shz.pay.core.PayManager;
import com.shz.pay.core.PayRecord;
import com.shz.pay.model.Channel;
import com.shz.pay.utils.OrderNumberGenerater;

@Controller
public class TestController {
	
	static {
		Pingpp.apiKey="sk_test_nr5evLWzfT00Gurrz14aXrLO";
	}
	
	@Autowired
	private PayManager payManager;

	@RequestMapping("")
	public ModelAndView index() {
		Channel[] channels=Channel.values();
		ModelAndView view=new ModelAndView("index");
		view.addObject("channels", channels);
		return view;
	}
	
	@RequestMapping("charge")
	public @ResponseBody String getCharge(ChargeInputArgs args,HttpServletRequest request) {
		String clientIP=Servlets.getClientIP(request);
		clientIP="174.127.165.200";
		args.setClientIP(clientIP);
		args=processArgs(args);		
		try {
			PayRecord record=payManager.create(args);
			return RequestResult.success(record.getCharge()).toJson();
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | APIException e) {
			return RequestResult.error(0, "支付接口调用错误", e.getMessage()).toJson();
		}		
	}
	
	private ChargeInputArgs processArgs(ChargeInputArgs args) {
		String orderNo=OrderNumberGenerater.generateOrderNumber("TE");
		args.setOrderNo(orderNo);
		args.setAmount(1000.0);
		args.setAppId("app_HGyTaTzrvrnTOevb");
		args.setSubject("测试");
		args.setBody("测试");
		switch (args.getChannel()) {
		case alipay_wap:
			args.setSuccessUrl("http://www.doublev2v.com/success");
			args.setCancelUrl("http://www.doublev2v.com/cancel");
			break;
		case bfb_wap:
			args.setResultUrl("http://www.doublev2v.com/success");
			args.setBfbLogin(false);
			break;
		case upacp_wap:
			args.setResultUrl("http://www.doublev2v.com/success");
			break;
		case upmp_wap:
			args.setResultUrl("http://www.doublev2v.com/success");
			break;
		case jdpay_wap:
			args.setSuccessUrl("http://www.doublev2v.com/success");
			args.setFailUrl("http://www.doublev2v.com/cancel");
			break;
		default:
			break;
		}
		return args;
	}
}
