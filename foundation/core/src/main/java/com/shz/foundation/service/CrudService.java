package com.shz.foundation.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 增删改查Service
 * @author pc
 *
 * @param <T> dto类型
 * @param <I> 数据录入类型
 * @param <ID> 主键类型
 */
public interface CrudService<T, I, ID extends Serializable> {
	
	Logger logger=LoggerFactory.getLogger(CrudService.class);
	
	/**
	 * Add a given entity. Use the returned instance for further operations as the save operation might have changed the
	 * entity instance completely.
	 * 
	 * @param args
	 * @return the saved entity
	 */
	T add(I args);
	
	/**
	 * Update a given entity. Use the returned instance for further operations as the save operation might have changed
	 * the entity instance completely.
	 * 
	 * @param args
	 * @return the updated entity
	 */
	T update(I args);

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}
	 */
	T findOne(ID id);

	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id must not be {@literal null}.
	 * @return true if an entity with the given id exists, {@literal false} otherwise
	 * @throws IllegalArgumentException if {@code id} is {@literal null}
	 */
	boolean exists(ID id);

	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 */
	List<? extends T> findAll();

	/**
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return
	 */
	List<? extends T> findAll(Iterable<ID> ids);

	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	long count();

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
	 */
	void delete(ID id);

	/**
	 * Deletes the given ids.
	 * 
	 * @param entities
	 * @throws IllegalArgumentException in case the given {@link Iterable} is (@literal null}.
	 */
	void delete(Iterable<ID> ids);
	
	/**
	 * delete all the entities managed by the service
	 */
	void deleteAll();
}
