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
package com.weeds.aoi.auth.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jetory
 * @date 2017年10月16日 下午1:24:55	
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperateLog {

	/**
	 * 操作描述
	 * @return
	 */
	String desc() default "";
	
	/**
	 * 1:新增，2：删除，3：修改，4：查询，98:登录，99：其他
	 * @return
	 */
	OperateType type() default OperateType.QUERY;
	
	/**
	 * 模块名称
	 * @return
	 */
	String moudle() default "";
	
	/**
	 * 菜单名称
	 * @return
	 */
	String menu() default "";
	
	/**
	 * 是否保存请求参数
	 * @return
	 */
	boolean param() default true;
	
}
