package com.shz.project.domain.system.permission;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 应用层要保证fieldPermission的code是唯一的，在新增和修改时，都要确保不会造成code重复
 * @author pc
 *
 */
public interface FieldPermissionRepository extends PagingAndSortingRepository<FieldPermission, String> {
	
	FieldPermission getByCode(String code);
}
