package com.shz.pay.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pingplusplus.model.Refund;
import com.shz.foundation.utils.LogUtils;
import com.shz.pay.core.PayManager;
import com.shz.pay.core.PayRecord;

@Component
public class RefundSuccessEventListener extends AbstractPingppEventListener<Refund> {

	@Autowired
	private PayManager payManager;
	@Autowired(required=false)
	private com.shz.pay.listener.external.RefundSuccessEventListener listener;
	
	@Override
	protected String getEventType() {
		return "refund.succeeded";
	}

	@Override
	protected void internalHandle(Refund t) {
		try {
			PayRecord record=payManager.refundSuccess(t.getId());
			if(listener!=null) {
				listener.handle(record, t);
			}
		} catch (Exception e) {
			if(listener!=null) {
				listener.handleException(e);
			} else {
				LogUtils.error(e);
			}
		}
	}
}
