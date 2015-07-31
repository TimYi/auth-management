package com.shz.foundation.mapping.adapter.polymorphic;

import com.shz.foundation.mapping.BeanMapper;

public class SimplePolymorphicDtoAdapter<S extends D,G extends T,D,T>
	extends SimplePolymorphicDtoConverter<S, G, D, T>
	implements PolymorphicDtoAdapter<D, T> {

	@Override
	public boolean supportTarget(T t) {
		if(t==null)return false;
		if(t.getClass().equals(getGClass()))return true;
		return false;
	}

	@Override
	public D reConvert(T t) {
		if(!supportTarget(t))throw new IllegalArgumentException("不支持的参数类型");
		D d=BeanMapper.map(t, getSClass());
		return postReConvert(t, d);
	}
	
	/**
	 * 子类可以重写此方法，处理特殊字段
	 * @param t
	 * @param d
	 * @return
	 */
	public D postReConvert(T t, D d) {
		return d;
	}

	@Override
	public D update(T t, D d) {
		if(!(supportTarget(t) && support(d)))throw new IllegalArgumentException("不支持的参数类型");
		BeanMapper.copy(t, d);
		return postUpdate(t, d);
	}
	
	public D postUpdate(T t, D d) {
		return d;
	}
}
