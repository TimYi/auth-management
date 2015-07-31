package com.shz.foundation.persistence.springdata;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 可以使用JPA动态查询的Repository
 * @author pc
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface SpecificationRepository<T,ID extends Serializable>
	extends PagingAndSortingRepository<T, ID>,JpaSpecificationExecutor<T> {
}
