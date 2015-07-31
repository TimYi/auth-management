package com.shz.foundation.security.rest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * 获取验证凭据，并根据凭据将授权信息加载到验证流中。
 * @author pc
 *
 */
@Component("restAuthFilter")
public class RestAuthFilter extends GenericFilterBean {
	
	/**验证信息所在请求头*/
	private String authHeader="Auth_Token";
	@Autowired
	private RestAuthManager restAuthManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication oldAuthentication=context.getAuthentication();
		//如果用户已经通过session方式登录，不会再次验证
		if(oldAuthentication!=null && oldAuthentication.isAuthenticated()) {
			chain.doFilter(request, response);
			return;
		}
		
		String token=getToken((HttpServletRequest)request);		
		if(token==null) {
			chain.doFilter(request, response);
			return;
		}
		//标识是否需要清除Authentication，防止重复请求接口产生大量Authentication
		boolean shouldClean=false;
		try {
			UserDetails user=restAuthManager.authenticate(token);
			if(user!=null) {
				UsernamePasswordAuthenticationToken authentication=
						new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());	
				authentication.setDetails(user);
				context.setAuthentication(authentication);
				SecurityContextHolder.setContext(context);
				shouldClean=true;			
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		chain.doFilter(request, response);
		if(shouldClean) {
			SecurityContextHolder.clearContext(); //单单clearContext无法清除缓存，所以需要手动清除
			context.setAuthentication(null);
			SecurityContextHolder.setContext(context);			
		}
	}
	
	/**
	 * 负责从ServletRequest中解析token，如果没有符合规则的token，返回<code>null</code>
	 * @param request
	 * @return
	 */
	protected String getToken(HttpServletRequest request) {
		String token;
		token=request.getHeader(authHeader);
		return token;
	}

	public void setAuthHeader(String authHeader) {
		this.authHeader = authHeader;
	}
	
	public void setAuthenticationManager(RestAuthManager authenticationManager) {
		this.restAuthManager = authenticationManager;
	}
}
