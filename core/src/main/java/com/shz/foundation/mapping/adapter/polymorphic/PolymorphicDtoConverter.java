package com.shz.foundation.mapping.adapter.polymorphic;

/**
 * 多态转换接口
 * @author pc
 *
 * @param <S>
 * @param <T>
 */
public interface PolymorphicDtoConverter<D,T> {

	/**
	 * 是否支持实际参数类型
	 * @param source
	 * @return
	 */
	boolean support(D d);
	
	/**
	 * 进行参数转换
	 * @param source
	 * @return T的子类
	 */
	T convert(D d);
}
