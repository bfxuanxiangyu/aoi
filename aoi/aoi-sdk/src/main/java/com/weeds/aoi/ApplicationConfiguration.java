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
package com.weeds.aoi;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.weeds.aoi.core.cache.HashKeyGenerator;

/**
 * @author Jetory
 * @date 2017年11月8日 上午10:40:45	
 */
@Configuration
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages={"com.weeds.aoi"})
public class ApplicationConfiguration {

	@Bean
	public HashKeyGenerator hashKeyGenerator() {
		return new HashKeyGenerator();
	}
	
}
