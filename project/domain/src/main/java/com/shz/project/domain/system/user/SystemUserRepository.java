package com.shz.project.domain.system.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SystemUserRepository extends PagingAndSortingRepository<SystemUser, String> {

	SystemUser getByUsername(String username);
	
	SystemUser getByEmail(String email);
	
	SystemUser getByTelephone(String telephone);
	
	SystemUser getByEmailValidater(String emailValidater);
	
	Page<SystemUser> findByVerified(boolean verified,Pageable pageable);
}
