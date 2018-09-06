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
package com.weeds.aoi.boot;

/**
 * @author Jetory
 * @date 2017年11月6日 上午10:24:21	
 */
public class SpringBootStartLauncher extends SpringBootJarLauncher {

	public SpringBootStartLauncher() throws Exception {
		super();
	}

	@Override
	protected String getMainClass() throws Exception {
		return "net.nicoll.boot.daemon.StartSpringBootService";
	}
	
	public static void main(String[] args) throws Exception {
		SpringBootStartLauncher launcher = new SpringBootStartLauncher();
		launcher.launch(args);
	}
	
}
