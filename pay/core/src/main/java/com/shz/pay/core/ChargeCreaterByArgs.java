package com.shz.pay.core;

import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;

public interface ChargeCreaterByArgs {

	Charge create(ChargeInputArgs args)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException;
}
