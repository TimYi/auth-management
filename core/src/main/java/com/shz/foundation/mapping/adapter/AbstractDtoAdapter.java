package com.shz.foundation.mapping.adapter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.shz.foundation.mapping.BeanMapper;

public abstract class AbstractDtoAdapter<D, T, I> extends SimpleDtoAdapter<D, T, I> {
	
	@Override
	public T convert(D source) {
		if(source==null)return null;
		T t=BeanMapper.map(source, getTClass());
		return t;
	}
	
	@Override
	public D convertToDo(I i) {
		if(i==null)return null;
		D d=BeanMapper.map(i, getDClass());
		return postConvertToDo(i, d);
	}
	
	/**
	 * 在dozer转换完一般属性之后，将dto的特殊属性转换给do
	 * @param t
	 * @param d
	 * @return
	 */
	public abstract D postConvertToDo(I i, D d);
	
	@Override
	public D update(I i, D d) {
		if(i==null || d==null)return null;
		BeanMapper.copy(i, d);
		return postUpdate(i, d);
	}
	
	/**
	 * 在dozer转换完一般属性之后，将dto的特殊属性更新给do
	 * @param t
	 * @param d
	 * @return
	 */
	public abstract D postUpdate(I i, D d);
	
	/**
	 * 获取do运行时类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<D> getDClass() {
		Type t = getClass().getGenericSuperclass();
		Type[] p=((ParameterizedType)t).getActualTypeArguments();
		Class<D> dClass=(Class<D>)p[0];
        return dClass;
	}
	
	/**
	 * 获取dto运行时类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getTClass() {
		Type t = getClass().getGenericSuperclass();
		Type[] p=((ParameterizedType)t).getActualTypeArguments();
		Class<T> tClass=(Class<T>)p[1];
        return tClass;
	}
	
	/**
	 * 获取input args运行时类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getIClass() {
		Type t = getClass().getGenericSuperclass();
		Type[] p=((ParameterizedType)t).getActualTypeArguments();
		Class<T> tClass=(Class<T>)p[2];
        return tClass;
	}
}
