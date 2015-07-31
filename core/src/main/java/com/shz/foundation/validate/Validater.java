package com.shz.foundation.validate;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.shz.foundation.persistence.BaseModel;

@MappedSuperclass
public class Validater extends BaseModel {

	private String id;
	private String validater;
	private Date expireTime;
	/**过期分钟数*/
	private Integer expireMinutes;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String telephone) {
		this.id = telephone;
	}
	public String getValidater() {
		return validater;
	}
	public void setValidater(String validater) {
		this.validater = validater;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Integer getExpireMinutes() {
		return expireMinutes;
	}
	public void setExpireMinutes(Integer expireMinutes) {
		this.expireMinutes = expireMinutes;
	}
	
	public void validate(String validater) throws ValidateException {
		if(!this.validater.equals(validater)) {
			throw new ValidateException("验证码错误");
		}
		if(new Date().after(expireTime)) {
			throw new ValidateException("验证码已过期");
		}
	}
}
