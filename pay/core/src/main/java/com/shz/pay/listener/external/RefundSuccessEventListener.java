package com.shz.pay.listener.external;

import com.pingplusplus.model.Refund;
import com.shz.pay.core.PayRecord;

public interface RefundSuccessEventListener {

	void handle(PayRecord record, Refund refund);
	
	void handleException(Exception e);
}
