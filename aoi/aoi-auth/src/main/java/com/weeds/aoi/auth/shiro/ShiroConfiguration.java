/*
 * Copyright (C) 2017 Shanghai sinnren soft Co., Ltd
 *
 * All copyrights reserved by Shanghai sinnren.
 * Any copying, transferring or any other usage is prohibited.
 * Or else, Shanghai sinnren possesses the right to require legal 
 * responsibilities from the violator.
 * All third-party contributions are distributed under license by
 * Shanghai sinnren soft Co., Ltd.
 */
package com.weeds.aoi.auth.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.format.Shiro1CryptFormat;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import net.sf.ehcache.CacheManager;

/**
 * @author Jetory
 * @date 2017年9月19日 下午1:32:42
 */
@Configuration
@ControllerAdvice
public class ShiroConfiguration {

	private static Logger log = LoggerFactory.getLogger(ShiroConfiguration.class);
	
	@Value("#{ @environment['shiro.loginUrl'] ?: '/login' }")
	protected String loginUrl;

	@Value("#{ @environment['shiro.successUrl'] ?: '/main' }")
	protected String successUrl;

	@Value("#{ @environment['shiro.unauthorizedUrl'] ?: '/login' }")
	protected String unauthorizedUrl;
	
	@Bean
	protected EhCacheManager shiroCacheManager(CacheManager ehCacheCacheManager) {
		EhCacheManager shiroCacheManager = new EhCacheManager();
		shiroCacheManager.setCacheManager(ehCacheCacheManager);
		//shiroCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		return shiroCacheManager;
	}
	
	@Bean
	protected Realm realm(EhCacheManager shiroCacheManager) {
		JdbcAuthorizingRealm realm = new JdbcAuthorizingRealm();
		realm.setCredentialsMatcher(passwordMatcher());
		realm.setCacheManager(shiroCacheManager);
		realm.setAuthenticationCacheName("authenticationCache");
		realm.setAuthenticationCachingEnabled(true);
		realm.setAuthorizationCachingEnabled(true);
		realm.setCachingEnabled(true);
		return realm;
	}
	
	@Bean
	protected PasswordMatcher passwordMatcher() {
		PasswordMatcher passwordMatcher = new PasswordMatcher();
		DefaultPasswordService passwordService = new DefaultPasswordService();
		
		Shiro1CryptFormat format = new Shiro1CryptFormat();
		
		passwordService.setHashFormat(format);
		
		passwordMatcher.setPasswordService(passwordService);
		
		return passwordMatcher;
	}
	
	@Bean
    public ShiroDialect shiroDialect(){
		return new ShiroDialect();
	}
	
	@Bean
	protected ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
		
		filterFactoryBean.setLoginUrl(loginUrl);
		filterFactoryBean.setSuccessUrl(successUrl);
		filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
		filterFactoryBean.setSecurityManager(securityManager);
		filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition()
				.getFilterChainMap());
		filterFactoryBean.getFilters().put("authc", userFormAuthenticationFilter());
		LogoutFilter logout = new LogoutFilter();
		logout.setRedirectUrl("/login");
		filterFactoryBean.getFilters().put("logout", logout);
		return filterFactoryBean;
	}
	
	@Bean
	protected UserFormAuthenticationFilter userFormAuthenticationFilter() {
		UserFormAuthenticationFilter userForm = new UserFormAuthenticationFilter();
		userForm.setLoginUrl(loginUrl);
		userForm.setSuccessUrl(successUrl);
		return userForm;
	}
	
	@Bean
	protected AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	
	@Bean
	protected MethodInvokingFactoryBean setsecurityUtils(SecurityManager securityManager) {
		MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
		bean.setArguments(new Object[]{securityManager});
		bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		return bean;
	}

	@Bean
	protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
	
		chainDefinition.addPathDefinition("/css/**", "anon");
		chainDefinition.addPathDefinition("/js/**", "anon");
		chainDefinition.addPathDefinition("/vendor/**", "anon");
		chainDefinition.addPathDefinition("/images/**", "anon");
		chainDefinition.addPathDefinition("/download/**", "anon");
		
		chainDefinition.addPathDefinition("/assets/**", "anon");
		chainDefinition.addPathDefinition("/favicon.ico", "anon");
		
		chainDefinition.addPathDefinition("/api/**", "anon");
		
//		chainDefinition.addPathDefinition("/admin/login", "authc");
//		chainDefinition.addPathDefinition("/admin/logout", "logout");
//		chainDefinition.addPathDefinition("/admin/**", "user,roles[admin]");
		
		chainDefinition.addPathDefinition("/", "anon");
		chainDefinition.addPathDefinition("/**", "user");
		
		return chainDefinition;
	}
	
	@Bean
	protected MenusModel menusModel() {
		return new MenusModel();
	}
	
	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	protected String handleException(AuthorizationException e, Model model) {
		log.debug("AuthorizationException was thrown", e);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", HttpStatus.FORBIDDEN.value());
		map.put("message", "No message available");
		model.addAttribute("errors", map);
		return "error";
	}

	@ModelAttribute(name = "subject")
	protected Subject subject(HttpServletRequest request) {
		return SecurityUtils.getSubject();
	}
	
	@ModelAttribute(name = "menus")
	protected MenusModel menus(HttpServletRequest request) {
		return menusModel();
	}
	
}
