package com.shz.foundation.mapping.service;

import java.io.Serializable;

import com.shz.foundation.mapping.adapter.DtoAdapter;
import com.shz.foundation.persistence.Identified;
import com.shz.foundation.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class DtoCrudService<D extends Identified<ID>, T, I extends Identified<ID>, ID extends Serializable>
	implements CrudService<T, I, ID> {
	
	private CrudRepository<D, ID> repository;
	
	public CrudRepository<D, ID> getRepository() {
		return repository;
	}

	@Autowired
	public void setRepository(CrudRepository<D, ID> repository) {
		this.repository = repository;
	}

	@Autowired
	protected DtoAdapter<D, T, I> adapter;

	@Override
	public T add(I entity) {
		if(entity==null) return null;
		D d=adapter.convertToDo(entity);
		d=getRepository().save(d);
		T result=adapter.convertToDetailedDto(d);
		return result;
	}

	@Override
	public T update(I entity) {
		if(entity==null) return null;
		ID id=entity.getId();
		D d=getRepository().findOne(id);
		if(d==null) return null;
		d=adapter.update(entity, d);
		d=getRepository().save(d);
		T result=adapter.convertToDetailedDto(d);
		return result;
	}

	@Override
	public T findOne(ID id) {
		if(id==null)return null;
		D d=getRepository().findOne(id);
		if(d==null) return null;
		return adapter.convertToDetailedDto(d);
	}

	@Override
	public boolean exists(ID id) {
		return getRepository().exists(id);
	}

	@Override
	public Iterable<T> findAll() {
		Iterable<D> all=getRepository().findAll();
		if(all==null)return null;
		return adapter.convertDoList(all);
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		Iterable<D> all=getRepository().findAll(ids);
		if(all==null)return null;
		return adapter.convertDoList(all);
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
