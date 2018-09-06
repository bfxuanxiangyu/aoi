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

import java.io.File;
import java.net.URI;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import org.springframework.boot.loader.JarLauncher;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.ExplodedArchive;
import org.springframework.boot.loader.archive.JarFileArchive;

/**
 * @author Jetory
 * @date 2017年11月6日 上午11:42:47	
 */
public class SpringBootJarLauncher extends JarLauncher {

	public SpringBootJarLauncher() throws Exception{
		super(archive());
	}
	
	public static Archive archive() throws Exception{
		ProtectionDomain protectionDomain = JarLauncher.class.getProtectionDomain();
		CodeSource codeSource = protectionDomain.getCodeSource();
		URI location = (codeSource == null ? null : codeSource.getLocation().toURI());
		String path = (location == null ? null : location.getSchemeSpecificPart());
		if (path == null) {
			throw new IllegalStateException("Unable to determine code source archive");
		}
		File root = new File(path);
		if (!root.exists()) {
			throw new IllegalStateException(
					"Unable to determine code source archive from " + root);
		}
		Archive archive = (root.isDirectory() ? new ExplodedArchive(root)
				: new JarFileArchive(root));
		
		return archive;
	}
}
