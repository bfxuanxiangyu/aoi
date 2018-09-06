package com.weeds.aoi.api.utils;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.collect.Maps;
import com.weeds.aoi.common.Constants;

import sun.misc.BASE64Encoder;

/*
 * @author Wayne
 */
public class HMac {

	/*
	 * 加密参数名
	 */
	public static  String APP_SECRET_NAME = "appSecret";

	/**
	 * 加密KEY
	 */
	public static String APP_SECRET_VALUE = "goowXvgtDKeDrTNoojTCNfIxMdFNqFlA";

	/**
	 * 获取Hmac算法结果 参与加密参数的顺序： 按参数名称的首字符升序排序，首字符相同按第二字母，以此类推
	 * 
	 * @param parameter
	 *            参数全部加密转化成String格式.
	 * @return
	 */
	public static String encrypt(final Map<String, String[]> parametersMap) {
		final StringBuffer parameterBuffer = new StringBuffer();

		final TreeMap<String, String> treeMap = Maps.newTreeMap();
		if (parametersMap != null) {
			final Iterator<String> iter = parametersMap.keySet().iterator();
			while (iter.hasNext()) {
				final String key = iter.next();
				treeMap.put(key, String.valueOf(parametersMap.get(key)[0]));
			}
		}
		treeMap.put(HMac.APP_SECRET_NAME, HMac.APP_SECRET_VALUE);

		final Iterator<Entry<String, String>> entrys = treeMap.entrySet().iterator();
		while (entrys.hasNext()) {
			final Entry<String, String> entry = entrys.next();
//			parameterBuffer.append(entry.getKey().toString());
//			parameterBuffer.append("=");
			parameterBuffer.append(entry.getValue().toString());
//			parameterBuffer.append("&");
		}
//		if (parameterBuffer.length() > 0 && parameterBuffer.lastIndexOf("&") == parameterBuffer.length() - 1) {
//			parameterBuffer.deleteCharAt(parameterBuffer.length() - 1);
//		}
//		System.out.println(parameterBuffer.toString());
		return base32Md5(parameterBuffer.toString());
	}
	public static void main(String[] args) {
		Map<String, Object> parms= new HashMap<String, Object>();
		//parms.put("userId", "614773788126613504");
		//parms.put("token", "b789c27c4c46c3c518b516e5285af9");
		System.out.println(getEncryptString(parms));

	}
	/**
	 * 传入需要的参数进行加密
	 * @param parms
	 * @return
	 */
	public static String getEncryptString(Map<String, Object> parms){
		String str="appSecret=";
		Map<String, String[]> parametersMap = new HashMap<String, String[]>();
		String strArray [] = new String [100];
		Iterator<String> iter = parms.keySet().iterator();
		while (iter.hasNext()) {
			final String key = iter.next();
			strArray[0] = String.valueOf(parms.get(key));
			parametersMap.put(key, strArray);
			strArray = new String [100];
		}
		String encryptStr = encrypt(parametersMap);
		return str+encryptStr;
		
	}

	/**
	 * MD5加密 BASE64位
	 * 
	 * @param secretKey
	 * @return
	 */
	public static String base64Md5(final String secretKey) {
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(secretKey.getBytes(Constants.ENCODING));
			final byte[] byteArray = messageDigest.digest();
			final BASE64Encoder b64Encoder = new BASE64Encoder();
			return b64Encoder.encode(byteArray);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * MD5加密
	 * 
	 * @param secretKey
	 * @return
	 */
	public static String base32Md5(final String secretKey) {
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(secretKey.getBytes(Constants.ENCODING));
			final byte[] byteArray = messageDigest.digest();

			int i;
			final StringBuffer buffer = new StringBuffer("");
			for (int offset = 0; offset < byteArray.length; offset++) {
				i = byteArray[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buffer.append("0");
				buffer.append(Integer.toHexString(i));
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}