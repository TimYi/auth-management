package com.shz.foundation.security.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shz.foundation.security.shiro.AuthDecesider;

@Service(value="accessDefinitionManager")
@Transactional
public class UrlAccessDefinitionManager {
	
	public static final Sort SORT=new Sort(new Sort.Order("ordernum"));

	@Autowired
	private UrlAccessDefinitionRepository repository;
	@Autowired
	private DefaultFilterChainManager filterChainManager; 
	private Map<String, NamedFilterList> defaultFilterChains; 	
	protected PatternMatcher pathMatcher=new AntPathMatcher();
	
	
	public List<ShiroAccessDefinition> getDefinitions() {
		Iterable<UrlAccessDefinition> definitions=repository.findAll(SORT);
		List<ShiroAccessDefinition> shiroAccessDefinitions=new ArrayList<>();
		for (UrlAccessDefinition d : definitions) {
			shiroAccessDefinitions.add(getShiroDefinition(d));
		}
		return shiroAccessDefinitions;
	}
	
	/**刷新当前应用访问权限控制*/
	public void refresh() {
		List<ShiroAccessDefinition> definitions=getDefinitions();
		//1、首先删除以前老的filter chain并注册默认的  
        filterChainManager.getFilterChains().clear();  
        if(defaultFilterChains != null) {  
            filterChainManager.getFilterChains().putAll(defaultFilterChains);  
        }  
        //2、循环URL Filter 注册filter chain  
        for (ShiroAccessDefinition definition : definitions) {  
        	filterChainManager.addToChain(definition.getUrl(), definition.getType(),definition.getPermission());
        }  
	}
	
	/**判断用户是否有权接触某个url*/
	public void couldAccess(String url) throws AuthorizationException {
		Iterable<UrlAccessDefinition> definitions=repository.findAll(SORT);
		Subject subject=SecurityUtils.getSubject();
		for (UrlAccessDefinition d : definitions) {
			if(pathMatcher.matches(d.getUrl(), url)) {
				AuthDecesider.permissionDeside(d.getPermission(), p->{if(!subject.isAuthenticated()) {
					throw new AuthorizationException("用户未登录");
					}
				}
						,p->{}, p->subject.checkPermission(p));
			}
		}
	}
	
	public ShiroAccessDefinition getShiroDefinition(UrlAccessDefinition definition) {
		ShiroAccessDefinition d=new ShiroAccessDefinition();
		d.setUrl(definition.getUrl());
		String permission=definition.getPermission();
		AuthDecesider.permissionDeside(permission, p->d.setType("user"),
				p->d.setType("anon"), p->{d.setType("perms");d.setPermission(p);});
		return d;
	}
	
	public void init() {
		defaultFilterChains =   
		          new HashMap<String, NamedFilterList>(filterChainManager.getFilterChains());  
	}
}
