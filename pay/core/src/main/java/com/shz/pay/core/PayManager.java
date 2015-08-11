package com.shz.pay.core;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Refund;
import com.shz.pay.model.Channel;
import com.shz.pay.utils.pingpp.ChargeCreater;
import com.shz.pay.utils.pingpp.RefundCreater;

@Service
@Transactional
public class PayManager {
	
	@Autowired
	private PayRecordRepository repository;
	@Autowired
	private RefundRecordRepository refundRecordRepository;
	
	public static final Map<Channel, ChargeCreaterByArgs> CREATE_CHARGE_MAP;
	
	static {
		CREATE_CHARGE_MAP=new HashMap<Channel, ChargeCreaterByArgs>();
		CREATE_CHARGE_MAP.put(Channel.alipay, 
				(t)->{Double amount=t.getAmount()*1000L;
					return ChargeCreater.alipay(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(), 
				t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(), t.getMetedata());});
		CREATE_CHARGE_MAP.put(Channel.alipay_qr, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.alipay_qr(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(), 
					t.getMetedata());
		});
		CREATE_CHARGE_MAP.put(Channel.alipay_wap, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.alipay_wap(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata(), t.getSuccessUrl(), t.getCancelUrl());
		});
		CREATE_CHARGE_MAP.put(Channel.bfb, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.bfb(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata());
		});
		CREATE_CHARGE_MAP.put(Channel.bfb_wap, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.bfb_wap(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata(),t.getResultUrl(),t.getBfbLogin());
		});
		CREATE_CHARGE_MAP.put(Channel.jdpay_wap, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.jdpay_wap(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata(),t.getSuccessUrl(),t.getFailUrl(),t.getToken());
		});
		CREATE_CHARGE_MAP.put(Channel.upacp, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.upacp(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata());
		});
		CREATE_CHARGE_MAP.put(Channel.upacp_wap, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.upacp_wap(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata(),t.getResultUrl());
		});
		CREATE_CHARGE_MAP.put(Channel.upmp, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.upmp(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata());
		});
		CREATE_CHARGE_MAP.put(Channel.upmp_wap, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.upmp_wap(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata(), t.getResultUrl());
		});
		CREATE_CHARGE_MAP.put(Channel.wx, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.wx(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata());
		});
		CREATE_CHARGE_MAP.put(Channel.wx_pub, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.wx_pub(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata(), t.getOpenId());
		});
		CREATE_CHARGE_MAP.put(Channel.wx_pub_qr, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.wx_pub_qr(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata(), t.getProductId());
		});
		CREATE_CHARGE_MAP.put(Channel.yeepay_wap, (t)->{Double amount=t.getAmount()*1000L;
			return ChargeCreater.yeepay_wap(t.getOrderNo(), t.getAppId(), amount.longValue(), t.getClientIP(),
					t.getCurrency(), t.getSubject(), t.getBody(), t.getExpireTime(), t.getDescription(),
					t.getMetedata(), t.getProductCategory(),t.getIdentityId(),t.getIdentityType(),t.getTerminalType()
					,t.getTerminalId(),t.getUserUa(),t.getResultUrl());
		});
	}

	/**
	 * 创建新支付记录，并在返回此记录的同时，在charge属性中返回charge实体
	 * 返回的支付记录，存在于session中，可以在外层事务中使用。
	 * @param args
	 * @return
	 * @throws AuthenticationException
	 * @throws InvalidRequestException
	 * @throws APIConnectionException
	 * @throws APIException
	 */
	public PayRecord create(ChargeInputArgs args) 
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		PayRecord payRecord=new PayRecord();
		return refreshPayRecord(payRecord, args);
	}
	
	private PayRecord refreshPayRecord(PayRecord payRecord, ChargeInputArgs args)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		payRecord.setOrderNo(args.getOrderNo());
		payRecord.setSubject(args.getBody());
		payRecord.setAmount(args.getAmount());
		payRecord.setBody(args.getBody());
		payRecord.setChannel(args.getChannel());
		payRecord.setClientIP(args.getClientIP());
		ChargeCreaterByArgs creater=CREATE_CHARGE_MAP.get(args.getChannel());
		if(creater==null) {
			throw new IllegalArgumentException("找不到相应的支付方式，请检查支付方式字段");
		}
		Charge charge=creater.create(args);
		payRecord.setChargeId(charge.getId());		
		payRecord=repository.save(payRecord);
		payRecord.setCharge(charge);
		return payRecord;
	}
	
	/**
	 * 查询支付记录，如果支付未更新，而charge为已更新状态，则更新支付记录。
	 * 传入的支付记录本身应该存在于session中
	 * 返回的同时，在charge字段中返回charge
	 * @param payRecord
	 * @return
	 * @throws AuthenticationException
	 * @throws InvalidRequestException
	 * @throws APIConnectionException
	 * @throws APIException
	 */
	public PayRecord check(PayRecord payRecord) 
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		if(payRecord==null)return null;
		Charge charge=Charge.retrieve(payRecord.getChargeId());
		if(!payRecord.isPaid() && charge.getPaid()) {
			payRecord.setPaid(true);
		}
		payRecord.setCharge(charge);
		return payRecord;
	}
	
	/**
	 * 更新支付信息，会检查支付是否已经完成，如果支付已经完成，不能更换支付信息。
	 * 注意：请确保每次更换支付信息，将对应orderNo也更换一个新的。
	 * @param payRecord
	 * @param args
	 * @return
	 * @throws AuthenticationException
	 * @throws InvalidRequestException
	 * @throws APIConnectionException
	 * @throws APIException
	 */
	public PayRecord refresh(PayRecord payRecord, ChargeInputArgs args) 
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		if(payRecord.isPaid()) throw new IllegalArgumentException("不允许对已经支付完成的支付进行修改");
		payRecord=check(payRecord);
		if(payRecord.isPaid()) throw new IllegalArgumentException("不允许对已经支付完成的支付进行修改");
		return refreshPayRecord(payRecord, args);
	}
	
	public PayRecord complete(PayRecord payRecord) {
		payRecord.setPaid(true);
		return payRecord;
	}
	
	public PayRecord complete(String chargeId) throws Exception {
		PayRecord payRecord=repository.findByChargeId(chargeId);
		if(payRecord==null) throw new Exception("没有找到charge对应的支付记录");
		return complete(payRecord);		
	}
	
	public RefundRecord refund(PayRecord payRecord, String description, Map<String, Object> metadata) throws Exception {
		return refund(payRecord, null, description, metadata);
	}
	
	/**
	 * 申请退款
	 * @param payRecord
	 * @param amount 如果为null，表示申请全额退款
	 * @throws Exception
	 */
	public RefundRecord refund(PayRecord payRecord, Double amount, String description, Map<String, Object> metadata)
			throws Exception {		
		payRecord=check(payRecord);
		if(!payRecord.isPaid()) throw new Exception("订单尚未支付");	
		
		//计算剩余款项，已经成功退款的，和正在等待webhooks确认退款成功的，都算已退款项
		Double refundMoney=0.0;
		if(payRecord.getRefunds()!=null) {
			for (RefundRecord record : payRecord.getRefunds()) {
				refundMoney=refundMoney+record.getAmount();
			}
		}
		
		Double remainAmount=payRecord.getAmount()-refundMoney;
		if(remainAmount==0.0) throw new Exception("已经全额退款，或者申请退款总金额已达到付款金额，无法继续退款。请等待全部退款完成。");
		
		if(amount==null) amount=remainAmount;
		if(remainAmount<amount) throw new Exception("退款金额必须小于支付金额");
		
		Long refundAmount=amount.longValue();
		Refund refund=RefundCreater.create(payRecord.getCharge(), refundAmount, description, metadata);
		RefundRecord refundRecord=new RefundRecord();
		refundRecord.setAmount(amount);
		refundRecord.setPay(payRecord);
		refundRecord.setRefundId(refund.getId());
		refundRecord=refundRecordRepository.save(refundRecord);
		refundRecord.setRefund(refund);
		return refundRecord;
	}
	
	public RefundRecord checkRefund(RefundRecord refundRecord) 
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		PayRecord payRecord=refundRecord.getPay();
		payRecord=check(payRecord);
		Charge charge=payRecord.getCharge();
		Refund refund=charge.getRefunds().retrieve(refundRecord.getRefundId());
		if(!refundRecord.isSucceed() && refund.getSucceed()) {
			refundRecord.setSucceed(true);
			refundRecord.setSucceedTime(new Date());
			Double refundAmount=payRecord.getRefundAmount();
			refundAmount=refundAmount+refundRecord.getAmount();
			payRecord.setRefundAmount(refundAmount);
		}
		refundRecord.setRefund(refund);
		return refundRecord;
	}
	
	public PayRecord refundSuccess(String refundId) throws Exception {
		RefundRecord refundRecord=refundRecordRepository.getByRefundId(refundId);
		if(refundRecord==null) throw new Exception("没有找到refund对应的退款记录");
		PayRecord payRecord=refundRecord.getPay();
		refundRecord.setSucceed(true);
		refundRecord.setSucceedTime(new Date());
		Double refundAmount=payRecord.getRefundAmount();
		refundAmount=refundAmount+refundRecord.getAmount();
		payRecord.setRefundAmount(refundAmount);
		return payRecord;
	}
}
