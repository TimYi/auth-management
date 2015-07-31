package com.shz.foundation.persistence;

import java.io.Serializable;

/**
 * 拥有唯一主键的实体
 * 此接口可以表示实现它的model是一个entity
 * @author pc
 *
 * @param <PK>
 */
public interface Identified<PK extends Serializable> {
	PK getId();
	void setId(PK id);
}
