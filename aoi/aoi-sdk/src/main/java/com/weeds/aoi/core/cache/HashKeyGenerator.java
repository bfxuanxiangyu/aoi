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
package com.weeds.aoi.core.cache;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.cache.interceptor.SimpleKeyGenerator;

/**
 * @author Jetory
 * @date 2017年11月16日 上午10:36:34	
 */
public class HashKeyGenerator extends SimpleKeyGenerator {

	private static final Logger logger = LoggerFactory.getLogger(HashKeyGenerator.class);
	
	@Override
	public Object generate(Object target, Method method, Object... params) {
		String key = target.getClass().getSimpleName() +  method.getName() + "" + generateKey(params).hashCode();
		if(logger.isTraceEnabled()){
			logger.trace(target.getClass().getSimpleName() + method.getName() + " key: " + key);
		}
		return key;
	}

	public static Object generateKey(Object... params) {
		if (params.length == 0) {
			return SimpleKey.EMPTY;
		}
		if (params.length == 1) {
			Object param = params[0];
			if (param != null && !param.getClass().isArray()) {
				return param;
			}
		}
		return new SimpleKey(params);
	}
	
}
