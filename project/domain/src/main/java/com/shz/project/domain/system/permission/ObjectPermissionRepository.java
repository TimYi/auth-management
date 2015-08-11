package com.shz.project.domain.system.permission;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 需要应用层确保，同一个operation，对应的object权限code唯一
 * @author pc
 *
 */
public interface ObjectPermissionRepository extends PagingAndSortingRepository<ObjectPermission, String> {

	ObjectPermission getByCodeAndOperationId(String code, String operationId);
	
	ObjectPermission getByCodeAndOperation(String code, OperatePermission operate);
	
	List<ObjectPermission> findByOperationId(String operationId);
}
