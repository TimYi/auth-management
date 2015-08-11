package com.shz.pay.listener;

import com.pingplusplus.model.Event;
import com.shz.pay.utils.pingpp.PingppEventListener;

public abstract class AbstractPingppEventListener<T> implements PingppEventListener {

	@Override
	public boolean support(Event event) {
		return getEventType().equals(event.getType());
	}
	
	protected abstract String getEventType();

	@Override
	public void handle(Event event) {
		T t=getData(event);
		internalHandle(t);
	}

	protected T getData(Event event) {
		@SuppressWarnings("unchecked")
		T t=(T)event.getData().get("object");
		return t;
	}
	
	protected abstract void internalHandle(T t);
}
