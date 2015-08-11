package com.shz.pay.listener.external;

import java.util.EventListener;

import com.shz.pay.core.PayRecord;

public interface PaySuccessEventListener extends EventListener {

	void handle(PayRecord payRecord);
	
	void handleException(Exception e);
}
