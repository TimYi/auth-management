package com.shz.pay.utils.pingpp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingplusplus.model.Event;
import com.pingplusplus.model.Webhooks;

@Service
@Transactional
public class WebhooksListener {

	private List<PingppEventListener> listeners;

	public List<PingppEventListener> getListeners() {
		return listeners;
	}

	@Autowired
	public void setListeners(List<PingppEventListener> listeners) {
		this.listeners = listeners;
	}
	
	/**
	 * 处理Webhooks回调，
	 * @param requestBody
	 * @return
	 */
	public int revoke(String requestBody) {
		try {
			Event event = Webhooks.eventParse(requestBody);
			for (PingppEventListener listener : listeners) {
				if(listener.support(event)) {
					listener.handle(event);
				}
			}
			return 200;
		} catch (Exception e) {
			return 500;
		}		
	}
	
	/**
	 * 处理webhooks回调，并验证签名
	 * @param requestBody 请求参数
	 * @param signHeader 签名所在请求头信息
	 * 处理时，先对sighHeader做base64解密
	 * 把 Webhooks 通知、Ping++ 管理平台提供的 RSA 公钥、 和 base64 解码后的签名三者一同放入 RSA 的签名函数中进行非对称的签名运算，来判断签名是否验证通过。
	 * @return
	 */
	public int revoke(String requestBody, String signHeader) {
		//TODO 对签名进行验证
		return revoke(requestBody);
	}
}
