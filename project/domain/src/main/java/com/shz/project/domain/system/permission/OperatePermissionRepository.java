package com.shz.project.domain.system.permission;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 保证field code唯一，并要保证同一个field下，operate的code也是唯一的，也由应用层保证
 * @author pc
 *
 */
public interface OperatePermissionRepository extends PagingAndSortingRepository<OperatePermission, String> {

	OperatePermission getByCodeAndFieldId(String code, String fieldId);
	
	OperatePermission getByCodeAndField(String code, FieldPermission field);
	
	List<OperatePermission> findByFieldId(String fieldId);
}
