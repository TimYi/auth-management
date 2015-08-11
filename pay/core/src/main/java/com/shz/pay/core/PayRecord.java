package com.shz.pay.core;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.pingplusplus.model.Charge;
import com.shz.foundation.persistence.UUIDBaseModel;
import com.shz.pay.model.Channel;

@Entity
public class PayRecord extends UUIDBaseModel {

	/**ping++支付记录唯一标识*/
	private String chargeId;
	/**是否已经付款*/
	private boolean paid;
	/**支付渠道*/
	private Channel channel;
	/**商户订单号，适配每个渠道对此参数的要求，必须在商户系统内唯一。最好是8~10位*/
	private String orderNo;
	/**客户端ip*/
	private String clientIP;
	/**支付金额，单位为元*/
	private Double amount;
	/**支付名称（商品标题）*/
	private String subject;
	/**支付信息记录*/
	private String body;
	/**全部退款记录*/
	private Set<RefundRecord> refunds;
	/**总退款金额*/
	private Double refundAmount=0.0;
	/**charge实体*/
	private Charge charge;
	
	@Column(unique=true)
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
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
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
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
	@OneToMany(cascade=CascadeType.ALL,mappedBy="pay")
	public Set<RefundRecord> getRefunds() {
		return refunds;
	}
	public void setRefunds(Set<RefundRecord> refunds) {
		this.refunds = refunds;
	}
	public Double getRefundAmount() {
		if(refundAmount==null) {
			refundAmount=0.0;
		}
		return refundAmount;
	}
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}
	@Transient
	public Charge getCharge() {
		return charge;
	}
	public void setCharge(Charge charge) {
		this.charge = charge;
	}
}
