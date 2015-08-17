package com.shz.project.domain.system.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shz.project.domain.system.role.Role.RoleType;

public interface RoleRepository extends PagingAndSortingRepository<Role, String> {

	Page<Role> findByType(RoleType type, Pageable pageable);
}
