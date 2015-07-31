package com.shz.foundation.mapping.adapter.polymorphic;


//TODO 完善多态对象转换支持
/**
 * 多态互相转换
 * @author pc
 *
 * @param <S>
 * @param <T>
 */
public interface PolymorphicDtoAdapter<D,T> extends PolymorphicDtoConverter<D, T> {

	boolean supportTarget(T t);
	
	D reConvert(T t);
	
	/**
	 * 
	 * @param t dto对象
	 * @param d 业务对象
	 * @return
	 */
	D update(T t, D d);
}
