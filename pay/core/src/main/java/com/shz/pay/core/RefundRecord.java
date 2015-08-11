package com.shz.pay.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.pingplusplus.model.Refund;
import com.shz.foundation.persistence.UUIDBaseModel;

@Entity
public class RefundRecord extends UUIDBaseModel {

	/**支付记录*/
	private PayRecord pay;
	/**是否退款成功*/
	private boolean succeed;
	/**退款成功时间*/
	private Date succeedTime;
	/**退款金额*/
	private Double amount;
	/**对应ping++退款id*/
	private String refundId;
	/**用于携带ping++图款记录*/
	private Refund refund;
	
	@ManyToOne(optional=false)
	public PayRecord getPay() {
		return pay;
	}
	public void setPay(PayRecord pay) {
		this.pay = pay;
	}
	public boolean isSucceed() {
		return succeed;
	}
	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	public Date getSucceedTime() {
		return succeedTime;
	}
	public void setSucceedTime(Date succeedTime) {
		this.succeedTime = succeedTime;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Column(unique=true)
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	@Transient
	public Refund getRefund() {
		return refund;
	}
	public void setRefund(Refund refund) {
		this.refund = refund;
	}
}
