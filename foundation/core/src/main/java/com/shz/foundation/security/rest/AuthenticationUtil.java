package com.shz.foundation.security.rest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationUtil {		
	public static <T extends UserDetails> T getUserDetail() throws Exception {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof UsernamePasswordAuthenticationToken))throw new Exception();
		@SuppressWarnings("unchecked")
		T userDetail=(T)authentication.getDetails();
		return userDetail;
	}
}
