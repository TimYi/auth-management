package com.shz.pay.utils.pingpp;

import java.util.EventListener;

import com.pingplusplus.model.Event;

public interface PingppEventListener extends EventListener {

	boolean support(Event event);
	
	void handle(Event event);
}
