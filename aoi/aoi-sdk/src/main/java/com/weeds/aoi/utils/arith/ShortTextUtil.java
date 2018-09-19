package com.weeds.aoi.utils.arith;

import java.security.MessageDigest;
import java.util.UUID;

public class ShortTextUtil {
	
	private static final String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };
	private static final char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * @param url
	 * @return
	 */
	public static String[] shortUrl(String url,boolean confusion) {
		String sMD5EncryptResult;// 对传入字符进行 MD5 加密
		if(confusion){
			sMD5EncryptResult = encoderByMd5(confusion() + url);
		}else{
			sMD5EncryptResult = encoderByMd5(url);
		}
		String hex = sMD5EncryptResult;

		String[] resUrl = new String[4];

		for (int i = 0; i < 4; i++) {
			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);
			// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用long ，则会越界
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";

			for (int j = 0; j < 6; j++) {
				long index = 0x0000003D & lHexLong;// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				outChars += chars[(int) index];// 把取得的字符相加
				lHexLong = lHexLong >> 5;// 每次循环按位右移 5 位
			}
			resUrl[i] = outChars;// 把字符串存入对应索引的输出数组
		}
		return resUrl;
	}
	
	public static String encoderByMd5(String buf) {
	    try {
	        MessageDigest digist = MessageDigest.getInstance("MD5");
	        byte[] rs = digist.digest(buf.getBytes("UTF-8"));
	        StringBuffer digestHexStr = new StringBuffer();
	        for (int i = 0; i < 16; i++) {
	            digestHexStr.append(byteHEX(rs[i]));
	        }
	        return digestHexStr.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	 
	}
	
	/**
	 * 混淆值
	 * @return
	 */
	private static String confusion(){
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		String key = UUID.randomUUID().toString();
		// 混淆key,加上当前时间,并且取一个随机字符串
		key = System.currentTimeMillis() + key;
		return key;
	}
	
	public static String byteHEX(byte ib) {
	    char[] ob = new char[2];
	    ob[0] = Digit[(ib >>> 4) & 0X0F];
	    ob[1] = Digit[ib & 0X0F];
	    String s = new String(ob);
	    return s;
	}

	/**
	 * 获取我想要的字符串,将生成的两个相加,得到我想要的12位字符
	 * 
	 * @param url
	 * @return
	 */
	public static String getShortUrl(String url,boolean confusion,Integer length) {
		String[] aResult = shortUrl(url,confusion);
		String res = aResult[0]+aResult[1]+aResult[2]+aResult[3];
		if(length==null || length<6){
			length=6;
		}else if(length>24){
			length=24;
		}
		return res.substring(0,length);
//		return aResult[0] + aResult[1];
	}

	// 测试方法
	public static void main(String[] args) {
		String buf = "123456";
		String md5Str = getShortUrl(buf,true,6);
		
		System.out.println(md5Str);
	}

}
