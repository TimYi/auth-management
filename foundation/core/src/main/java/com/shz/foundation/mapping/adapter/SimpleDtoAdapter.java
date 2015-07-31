package com.shz.foundation.mapping.adapter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SimpleDtoAdapter<D,T, I> implements DtoAdapter<D, T, I> {

	protected static Logger logger=LoggerFactory.getLogger(SimpleDtoAdapter.class);
	
	@Override
	public List<T> convertDoList(Iterable<? extends D> ds) {
		if(ds==null)return null;
		List<T> ts=new ArrayList<T>();
		for (D d : ds) {
			T t=convert(d);
			ts.add(t);
		}
		return ts;
	}
	
	/**
	 * 此方法用于列表数据的单个转换
	 * 如果无特殊处理，列表转换和单个转换结果相同
	 */
	@Override
	public T convertToDetailedDto(D d) {
		return convert(d);
	}
}
