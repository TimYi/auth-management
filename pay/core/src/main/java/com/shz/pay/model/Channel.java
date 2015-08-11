package com.shz.pay.model;

/**
 * ping++支持的支付方式
 * @author pc
 *
 */
public enum Channel {

	/**支付宝手机支付*/
	alipay,
	/**支付宝手机网页支付*/
	alipay_wap,
	/**支付宝扫码支付*/
	alipay_qr,
	/**Apple Pay*/
	apple_pay,
	/**百度钱包移动快捷支付*/
	bfb,
	/**百度钱包手机网页支付*/
	bfb_wap,
	/**银联全渠道支付（2015 年 1 月 1 日后的银联新商户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）*/
	upacp,
	/**银联全渠道手机网页支付（2015 年 1 月 1 日后的银联新商户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）*/
	upacp_wap,
	/**银联手机支付（限个人工作室和 2014 年之前的银联老客户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）*/
	upmp,
	/**银联手机网页支付（限个人工作室和 2014 年之前的银联老客户使用。若有疑问，请与 Ping++ 或者相关的收单行联系）*/
	upmp_wap,
	/**微信支付*/
	wx,
	/**微信公众账号支付*/
	wx_pub,
	/**微信公众账号扫码支付*/
	wx_pub_qr,
	/**易宝手机网页支付*/
	yeepay_wap,
	/**京东手机网页支付*/
	jdpay_wap;
}
