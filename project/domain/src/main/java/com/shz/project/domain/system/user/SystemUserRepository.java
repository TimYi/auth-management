package com.shz.project.domain.system.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SystemUserRepository extends PagingAndSortingRepository<SystemUser, String> {

	SystemUser getByUsername(String username);
}
