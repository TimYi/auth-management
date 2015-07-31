package com.shz.foundation.mapping.adapter;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

/**
 * 为dto和do之间的转换提供互相转换方法。
 * 继承 @Converter 接口，方便使用 @Page 的map方法 
 * @author ytm
 *
 * @param <D> domain object
 * @param <T> dto object
 * @param <I> input args
 */
public interface DtoAdapter<D, T, I> extends Converter<D, T> {
	
	/**
	 * 将Dto转为Do
	 * @param t never null，应返回空值
	 * @return
	 */
	D convertToDo(I t);
	
	/**
	 * 将Do转换为Dto
	 * 此方法用户单个转换，会转换较多细节
	 * 而convert方法用户列表转换，理论上会转换较少的细节
	 * @param d
	 * @return
	 */
	T convertToDetailedDto(D d);
	
	/**
	 * 更新Do
	 * @param t never null
	 * @param d never null
	 * @return
	 */
	D update(I t, D d);
	
	List<T> convertDoList(Iterable<? extends D> ds);
}
