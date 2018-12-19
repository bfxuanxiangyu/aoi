package com.weeds.aoi.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ZipUtils.class);

	private static final int BUFFER_SIZE = 2 * 1024;

	
	public static boolean toZip(String srcDir, String zipFileName, boolean KeepDirStructure){
		boolean flag = false;
		long start = System.currentTimeMillis();
		ZipOutputStream zos = null;
		try {
			OutputStream out = new FileOutputStream(new File(zipFileName));
			zos = new ZipOutputStream(out);
			File sourceFile = new File(srcDir);
			compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
			long end = System.currentTimeMillis();
			flag = true;
			logger.info("压缩完成，耗时：" + (end - start) + " ms");
		} catch (Exception e) {
			logger.error("zip error from ZipUtils"+e.getMessage(), e);
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public static void toZip(List<File> srcFiles, OutputStream out){
		long start = System.currentTimeMillis();
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(out);
			for (File srcFile : srcFiles) {
				byte[] buf = new byte[BUFFER_SIZE];
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				int len;
				FileInputStream in = new FileInputStream(srcFile);
				while ((len = in.read(buf)) != -1) {
					zos.write(buf, 0, len);
				}
				zos.closeEntry();
				in.close();
			}
			long end = System.currentTimeMillis();
			logger.info("压缩完成，耗时：" + (end - start) + " ms");
		} catch (Exception e) {
			logger.error("zip error from ZipUtils"+e.getMessage(), e);
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private static void compress(File sourceFile, ZipOutputStream zos, String name,boolean KeepDirStructure){
		
		try {
			byte[] buf = new byte[BUFFER_SIZE];
			if (sourceFile.isFile()) {
				// 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
				zos.putNextEntry(new ZipEntry(name));
				// copy文件到zip输出流中
				int len;
				FileInputStream in = new FileInputStream(sourceFile);
				while ((len = in.read(buf)) != -1) {
					zos.write(buf, 0, len);
				}
				// Complete the entry
				zos.closeEntry();
				in.close();
			} else {
				File[] listFiles = sourceFile.listFiles();
				if (listFiles == null || listFiles.length == 0) {
					// 需要保留原来的文件结构时,需要对空文件夹进行处理
					if (KeepDirStructure) {
						// 空文件夹的处理
						zos.putNextEntry(new ZipEntry(name + "/"));
						// 没有文件，不需要文件的copy
						zos.closeEntry();
					}
				} else {
					for (File file : listFiles) {
						// 判断是否需要保留原来的文件结构
						if (KeepDirStructure) {
							// 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
							// 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
							compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
						} else {
							compress(file, zos, file.getName(), KeepDirStructure);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("递归zip异常"+e.getMessage(),e);
		}
	}

	public static void main(String[] args) throws Exception {
		/** 测试压缩方法1 */
		ZipUtils.toZip("d:\\opt\\keyauth", "D:\\opt\\keyauth.zip", true);
		/** 测试压缩方法2 */
		/*List<File> fileList = new ArrayList<>();
		fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/jar.exe"));
		fileList.add(new File("D:/Java/jdk1.7.0_45_64bit/bin/java.exe"));
		FileOutputStream fos2 = new FileOutputStream(new File("c:/mytest02.zip"));
		ZipUtils.toZip(fileList, fos2);*/
	}

}