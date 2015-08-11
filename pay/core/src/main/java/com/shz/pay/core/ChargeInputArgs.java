package com.shz.pay.core;

import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.shz.pay.model.Channel;

public class ChargeInputArgs {

	private Channel channel;
	/**商户订单号，适配每个渠道对此参数的要求，必须在商户系统内唯一。*/
	private String orderNo;
	/**支付使用的 app 对象的 id。*/
	private String appId;
	/**订单总金额, 对应单位为元*/
	private Double amount;
	/**发起支付请求终端的 IP 地址，格式为 IPV4，如: 127.0.0.1。*/
	private String clientIP;
	/**三位 ISO 货币代码，目前仅支持人民币 cny。*/
	private String currency="cny";
	/**商品的标题，该参数最长为 32 个 Unicode 字符，银联全渠道（upacp/upacp_wap）限制在 32 个字节。*/
	private String subject;
	/**商品的描述信息，该参数最长为 128 个 Unicode 字符，yeepay_wap 对于该参数长度限制为 100 个 Unicode 字符。*/
	private String body;
	/**订单失效时间，用 Unix 时间戳表示。时间范围在订单创建后的 1 分钟到 15 天，默认为 1 天，创建时间以 Ping++ 服务器时间为准。 
	 * 该参数不适用于微信旧渠道支付，微信新渠道支付可支持该参数设置；银联对该参数的有效值限制为 1 小时内。*/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date expireTime;
	/**订单附加说明，最多 255 个 Unicode 字符。*/
	private String description;
	/**一些 Ping++ 对象支持加入用户指定的 metadata 参数。你可以使用键值对的形式来构建自己的 metadata*/
	private Map<String, String> metedata;
	/**支付宝和京东手机网页支付的成功回调地址*/
	private String successUrl;
	/**支付宝手机网页支付的取消回调地址*/
	private String cancelUrl;
	/**京东手机网页支付支付失败跳转页面*/
	private String failUrl;
	/**微信公众号支付公众号下用户唯一id*/
	private String openId;
	/**银联全渠道手机网页支付、银联手机网页支付、百度钱包手机网页支付、易宝手机网页支付的成功回调地址*/
	private String resultUrl;
	/**百度钱包 手机网页支付，是否需要登录手机百度钱包支付*/
	private Boolean bfbLogin=false;
	/**微信扫码支付商品id，客户自行维护*/
	private String productId;
	/**京东手机网页支付用户交易令牌，凭此token用户可以不用重复填写交易信息。*/
	private String token;
	/**易宝手机网页支付山品类型*/
	private String productCategory;
	/**用户唯一标识*/
	private String identityId;
	/**用户类别*/
	private String identityType;
	/**终端类型*/
	private String terminalType;
	/**终端id*/
	private String terminalId;
	/**移动终端UserAgent信息*/
	private String userUa;
	
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Map<String, String> getMetedata() {
		return metedata;
	}
	public void setMetedata(Map<String, String> metedata) {
		this.metedata = metedata;
	}
	public String getSuccessUrl() {
		return successUrl;
	}
	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
	public String getCancelUrl() {
		return cancelUrl;
	}
	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}
	public String getFailUrl() {
		return failUrl;
	}
	public void setFailUrl(String failUrl) {
		this.failUrl = failUrl;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getResultUrl() {
		return resultUrl;
	}
	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}
	public Boolean getBfbLogin() {
		return bfbLogin;
	}
	public void setBfbLogin(Boolean bfbLogin) {
		this.bfbLogin = bfbLogin;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getIdentityId() {
		return identityId;
	}
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getUserUa() {
		return userUa;
	}
	public void setUserUa(String userUa) {
		this.userUa = userUa;
	}
}
