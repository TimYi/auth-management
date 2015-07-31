package com.shz.foundation.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

/**
 * Hibernate存储基本模型
 * @author pc
 *
 */
@MappedSuperclass
public abstract class BaseModel {
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	/**是否删除*/
	private boolean deleted;
	/**删除事件*/
	private Date deleteTime;
	
	public BaseModel() {
		createTime=new Date();
		updateTime=new Date();
	}
	/**
	 * @return the createTime
	 */
	@Column(updatable=false)
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 用作乐观锁，同时保存更新时间，一举两得
	 * @return the modifyTime
	 */
	@Version
	@Type(type="timestamp")
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param modifyTime the modifyTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	

	/**
	 * 逻辑删除标识
	 */
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 * 删除时间
	 * @return
	 */
	public Date getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
}
