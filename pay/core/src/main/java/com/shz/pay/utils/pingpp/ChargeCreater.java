package com.shz.pay.utils.pingpp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.shz.foundation.utils.MapUtils;
import com.shz.pay.model.Channel;

/**
 * 采用更加面向对象的方式，封装Charge对象的创建过程
 * 参数说明：
 * orderNo:		required, 商户订单号，适配每个渠道对此参数的要求，必须在商户系统内唯一。
 * (alipay: 1-64 位， wx: 1-32 位，bfb: 1-20 位，upacp: 8-40 位，yeepay_wap:1-50 位，jdpay_wap:1-30 位，推荐使用 8-20 位，
 * 要求数字或字母，不允许特殊字符)。
 * appId:		required, 支付使用的 app 对象的 id。
 * channel:		required, 支付使用的第三方支付渠道
 * amount:		required, 订单总金额, 单位为对应币种的最小货币单位，例如：人民币为分（如订单总金额为 1 元，此处请填 100）。
 * clientIP:	required, 发起支付请求终端的 IP 地址，格式为 IPV4，如: 127.0.0.1。
 * currency:	required, 三位 ISO 货币代码，目前仅支持人民币 cny。
 * subject：		required, 商品的标题，该参数最长为 32 个 Unicode 字符，银联全渠道（upacp/upacp_wap）限制在 32 个字节。
 * body:		required, 商品的描述信息，该参数最长为 128 个 Unicode 字符，yeepay_wap 对于该参数长度限制为 100 个 Unicode 字符。
 * expireTime:	optional, 订单失效时间，用 Unix 时间戳表示。时间范围在订单创建后的 1 分钟到 15 天，默认为 1 天，创建时间以 Ping++ 服务器时间为准。 
 * 该参数不适用于微信旧渠道支付，微信新渠道支付可支持该参数设置；银联对该参数的有效值限制为 1 小时内。
 * description:	optional, 订单附加说明，最多 255 个 Unicode 字符。
 * metadata:	optional, 一些 Ping++ 对象支持加入用户指定的 metadata 参数。你可以使用键值对的形式来构建自己的 metadata，例如 metadata[color] = red，
 * 你可以在每一个 charge 对象中加入订单的一些详情，如颜色、型号等属性，在查询时获得更多信息。每一个对象的 metadata 最多可以拥有 10 个键值对，
 * 数据总长度在 1000 个 Unicode 字符以内。
 * extra:		optional, 特定渠道发起交易时需要的额外参数以及部分渠道支付成功返回的额外参数。
 * @author pc
 *
 */
public class ChargeCreater {

	/**默认支付货币类型为cny*/
	public static final String CURRENCY="cny";
	
	protected static Map<String, Object> createChargeParematers(String orderNo, String appId, Channel channel, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> extra, Map<String, String> metadata ) {
		Map<String, Object> chargeParameters=new HashMap<>();
		MapUtils.putNotNullKeyValues(chargeParameters, "order_no", orderNo);
		Map<String, String> app = new HashMap<String, String>();
		MapUtils.putNotNullKeyValues(app, "id", appId);
		chargeParameters.put("app", app);
		MapUtils.putNotNullKeyValues(chargeParameters, "channel", channel);
		MapUtils.putNotNullKeyValues(chargeParameters, "amount", amount);
		MapUtils.putNotNullKeyValues(chargeParameters, "client_ip", clientIP);
		if(currency==null)currency=CURRENCY;
		MapUtils.putNotNullKeyValues(chargeParameters, "currency", currency);
		MapUtils.putNotNullKeyValues(chargeParameters, "subject", subject);
		MapUtils.putNotNullKeyValues(chargeParameters, "body", body);
		if(expireTime!=null) {
			chargeParameters.put("time_expire", expireTime.getTime()/1000);
		}
		MapUtils.put(chargeParameters, "description", description);
		MapUtils.put(chargeParameters, "extra", extra);
		MapUtils.put(chargeParameters, "metadata", metadata);
		return chargeParameters;
	}
	
	protected static Charge generalPay(String orderNo, String appId, Channel channel, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata)
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, Object> params=createChargeParematers(orderNo, appId, channel, amount, clientIP, 
				currency, subject, body, expireTime, description, null, metadata);
		return Charge.create(params);
	}
	
	/**
	 * 支付宝手机支付
	 */
	public static Charge alipay(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata)
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		return generalPay(orderNo, appId, Channel.alipay, amount, clientIP, currency, subject, body, expireTime, 
				description, metadata);
	}
	
	/**
	 * 支付扫码支付
	 */
	public static Charge alipay_qr(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata) 
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		return generalPay(orderNo, appId, Channel.alipay_qr, amount, clientIP, currency, subject, body, expireTime, 
				description, metadata);
	}
	
	/**
	 * 支付宝手机网页支付
	 * @param successUrl required，支付成功回调地址
	 * @param cancelUrl optional, 支付取消回调地址
	 */
	public static Charge alipay_wap(String orderNo, String appId,  
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata, String successUrl, String cancelUrl) 
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, String> extra=new HashMap<>();
		MapUtils.putNotNullKeyValues(extra, "success_url", successUrl);
		MapUtils.put(extra, "cancel_url", cancelUrl);
		Map<String, Object> params=createChargeParematers(orderNo, appId, Channel.alipay_wap, amount, clientIP, currency, 
				subject, body, expireTime, description, extra, metadata);
		return Charge.create(params);
	}
	
	/**
	 * 百度钱包移动快捷支付
	 */
	public static Charge bfb(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata) 
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		return generalPay(orderNo, appId, Channel.bfb, amount, clientIP, currency, subject, body, expireTime, 
				description, metadata);
	}
	
	/**
	 * 百度钱包手机网页支付
	 * @param required，resultUrl，支付成功回调地址
	 * @param required，bfbLogin，是否需要登录百度钱包
	 */
	public static Charge bfb_wap(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata, String resultUrl, boolean bfbLogin)
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, String> extra=new HashMap<>();
		MapUtils.putNotNullKeyValues(extra, "result_url", resultUrl);
		MapUtils.putNotNullKeyValues(extra, "bfb_login", Boolean.toString(bfbLogin));
		Map<String, Object> params=createChargeParematers(orderNo, appId, Channel.bfb_wap, amount, clientIP, currency, 
				subject, body, expireTime, description, extra, metadata);
		return Charge.create(params);
	}
	
	/**银联全渠道支付*/
	public static Charge upacp(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata) 
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		return generalPay(orderNo, appId, Channel.upacp, amount, clientIP, currency, subject, body, expireTime, 
				description, metadata);
	}
	
	/**
	 * 银联全渠道手机网页支付
	 * resultUrl required，支付完成回调地址
	 */
	public static Charge upacp_wap(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata, String resultUrl)
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, String> extra=new HashMap<>();
		MapUtils.putNotNullKeyValues(extra, "result_url", resultUrl);
		Map<String, Object> params=createChargeParematers(orderNo, appId, Channel.upacp_wap, amount, clientIP, currency, 
				subject, body, expireTime, description, extra, metadata);
		return Charge.create(params);
	}
	
	/**银联手机支付（限个人工作室和 2014 年之前的银联老客户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）*/
	public static  Charge upmp(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata) 
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		return generalPay(orderNo, appId, Channel.upmp, amount, clientIP, currency, subject, body, expireTime, 
				description, metadata);
	}
	
	/**
	 * 银联手机网页支付（限个人工作室和 2014 年之前的银联老客户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）
	 * resultUrl required，支付完成回调地址
	 */
	public static Charge upmp_wap(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata, String resultUrl)
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, String> extra=new HashMap<>();
		MapUtils.putNotNullKeyValues(extra, "result_url", resultUrl);
		Map<String, Object> params=createChargeParematers(orderNo, appId, Channel.upmp_wap, amount, clientIP, currency, 
				subject, body, expireTime, description, extra, metadata);
		return Charge.create(params);
	}
	
	/**微信支付*/
	public static Charge wx(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata) 
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		return generalPay(orderNo, appId, Channel.wx, amount, clientIP, currency, subject, body, expireTime, 
				description, metadata);
	}
	
	/**
	 * 微信支付
	 * @param openId required，为用户在商户 appid 下的唯一标识
	 */
	public static Charge wx_pub(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata, String openId)
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, String> extra=new HashMap<>();
		MapUtils.putNotNullKeyValues(extra, "open_id", openId);
		Map<String, Object> params=createChargeParematers(orderNo, appId, Channel.wx_pub, amount, clientIP, currency, 
				subject, body, expireTime, description, extra, metadata);
		return Charge.create(params);
	}
	
	/**
	 * 微信公众账号扫码支付
	 * @param productId required，商品 ID，1-32 位字符串。此 id 为二维码中包含的商品 ID，商户自行维护
	 */
	public static Charge wx_pub_qr(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata, String productId)
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, String> extra=new HashMap<>();
		MapUtils.putNotNullKeyValues(extra, "product_id", productId);
		Map<String, Object> params=createChargeParematers(orderNo, appId, Channel.wx_pub_qr, amount, clientIP, currency, 
				subject, body, expireTime, description, extra, metadata);
		return Charge.create(params);
	}
	
	/**
	 * 易宝手机网页支付
	 * @param productCategory required，为商品类别码，详见商品类型码表 <a>https://pingxx.com/document/api#api-event</a>
	 * @param identityId required,  为用户标识,商户生成的用户账号唯一标识，最长 50 位字符串
	 * @param identityType required, 为用户标识类型，详见用户标识类型码表 <a>https://pingxx.com/document/api#api-appendix</a>
	 * @param terminalType required, 为终端类型，对应取值 0:IMEI, 1:MAC, 2:UUID, 3:other
	 * @param terminalId required, 终端id
	 * @param userUa required，为用户使用的移动终端的 UserAgent 信息
	 * @param resultUrl required, 为前台通知地址
	 */
	public static Charge yeepay_wap(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata, String productCategory, String identityId, 
			String identityType, String terminalType, String terminalId, String userUa, String resultUrl)
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, String> extra=new HashMap<>();
		MapUtils.putNotNullKeyValues(extra, "product_category", productCategory);
		MapUtils.putNotNullKeyValues(extra, "identity_id", identityId);
		MapUtils.putNotNullKeyValues(extra, "identity_type", identityType);
		MapUtils.putNotNullKeyValues(extra, "terminal_type", terminalType);
		MapUtils.putNotNullKeyValues(extra, "terminal_id", terminalId);
		MapUtils.putNotNullKeyValues(extra, "user_ua", userUa);
		MapUtils.putNotNullKeyValues(extra, "result_url", resultUrl);
		Map<String, Object> params=createChargeParematers(orderNo, appId, Channel.yeepay_wap, amount, clientIP, currency, 
				subject, body, expireTime, description, extra, metadata);
		return Charge.create(params);
	}
	
	/**
	 * 京东手机网页支付
	 * @param success_url required，支付成功回调地址
	 * @param fail_url required，支付失败页面跳转地址
	 * @param token optional，为用户交易令牌，用于识别用户信息，支付成功后会调用 success_url 返回给商户。
	 * 商户可以记录这个 token 值，当用户再次支付的时候传入该 token，用户无需再次输入银行卡信息，直接输入短信验证码进行支付。32 位字符串
	 */
	public static Charge jdpay_wap(String orderNo, String appId, 
			Long amount, String clientIP, String currency, String subject, String body, Date expireTime, 
			String description, Map<String, String> metadata, String successUrl, String failUrl, String token)
		throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, String> extra=new HashMap<>();
		MapUtils.putNotNullKeyValues(extra, "success_url", successUrl);
		MapUtils.putNotNullKeyValues(extra, "fail_url", failUrl);
		MapUtils.put(extra, "token", token);
		Map<String, Object> params=createChargeParematers(orderNo, appId, Channel.jdpay_wap, amount, clientIP, currency, 
				subject, body, expireTime, description, extra, metadata);
		return Charge.create(params);
	}
}
