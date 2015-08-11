package com.shz.pay.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pingplusplus.model.Charge;
import com.shz.foundation.utils.LogUtils;
import com.shz.pay.core.PayManager;
import com.shz.pay.core.PayRecord;
import com.shz.pay.listener.external.PaySuccessEventListener;

@Component
public class ChargeSuccessEventListener extends AbstractPingppEventListener<Charge> {

	@Autowired
	private PayManager payManager;
	@Autowired(required=false)
	private PaySuccessEventListener paySuccessEventListener;
	
	@Override
	protected String getEventType() {
		return "charge.succeeded";
	}

	@Override
	protected void internalHandle(Charge t) {
		try {
			PayRecord payRecord=payManager.complete(t.getId());
			if(paySuccessEventListener!=null) {
				paySuccessEventListener.handle(payRecord);
			}
		} catch (Exception e) {
			if(paySuccessEventListener!=null) {
				paySuccessEventListener.handleException(e);
			} else {
				LogUtils.error(e);
			}
		}
	}
}
