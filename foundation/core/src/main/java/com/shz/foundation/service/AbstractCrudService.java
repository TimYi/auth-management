package com.shz.foundation.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public abstract class AbstractCrudService<T,ID extends Serializable> implements CrudService<T, T, ID> {
	
	private CrudRepository<T, ID> repository;
	

	public CrudRepository<T, ID> getRepository() {
		return repository;
	}

	@Autowired
	public void setRepository(CrudRepository<T, ID> repository) {
		this.repository = repository;
	}

	@Override
	public T add(T entity) {
		if(entity==null)return null;
		return getRepository().save(entity);
	}

	@Override
	public T findOne(ID id) {
		if(id==null)return null;
		return getRepository().findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return getRepository().exists(id);
	}

	@Override
	public List<T> findAll() {
		Iterable<T> result=getRepository().findAll();
		if(result==null) return null;
	    List<T> list=new ArrayList<>();
	    for (T t : result) {
			list.add(t);
		}
	    return list;
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		if(ids==null)return null;
		Iterable<T> result=getRepository().findAll(ids);
		List<T> list=new ArrayList<>();
	    for (T t : result) {
			list.add(t);
		}
	    return list;
	}

	@Override
	public long count() {
		return getRepository().count();
	}

	@Override
	public void delete(ID id) {
		if(id==null)return;
		getRepository().delete(id);
	}

	@Override
	public void delete(Iterable<ID> ids) {
		for (ID id : ids) {
			getRepository().delete(id);
		}
	}
	
	@Override
	public void deleteAll() {
		getRepository().deleteAll();
	}
}
