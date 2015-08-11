package com.shz.pay.utils.pingpp;

import java.util.HashMap;
import java.util.Map;

import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Refund;
import com.shz.foundation.utils.MapUtils;

public class RefundCreater {

	/**
	 * 申请退款
	 * @param chargeId 订单id
	 * @param amount 数量
	 * @param description 描述
	 * @param metadata 附加元数据
	 * @return
	 * @throws AuthenticationException
	 * @throws InvalidRequestException
	 * @throws APIConnectionException
	 * @throws APIException
	 */
	public static Refund create(String chargeId, Long amount, String description, Map<String, Object> metadata)
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {		
		Charge ch=Charge.retrieve(chargeId);
		return create(ch, amount, description, metadata);
	}
	
	public static Refund create(Charge charge, Long amount, String description, Map<String, Object> metadata)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, Object> params = new HashMap<String, Object>();
		MapUtils.putNotNullKeyValues(params, "description", description);
		MapUtils.put(params, "amount", amount);
		MapUtils.put(params, "metadata", metadata);	
		return charge.getRefunds().create(params);
	}
}
