package com.weeds.aoi.utils.des;

import java.nio.charset.Charset;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.weeds.aoi.utils.AoiStringUtils;

public class Des3Utils {
	private static Logger logger = LoggerFactory.getLogger(Des3Utils.class);

	public static String Encodeing = "UTF-16LE";//.net中Encoding.Unicode.GetBytes(key)  相当于getBytes("UTF-16LE")
	public static String key = "sinnren_weeds";
	
	public static Cipher de_cipher = null;//解
	public static Cipher e_cipher = null;//加
	
	/**
	 * 解密单例
	 * @return
	 */
	private static Cipher getDecodeCipherInstance(){
		if(de_cipher == null){
			DESedeKeySpec dks;
			try {
				dks = new DESedeKeySpec(truncateHash(key, 24));
				IvParameterSpec iv = new IvParameterSpec(truncateHash("", 8));
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
				SecretKey securekey = keyFactory.generateSecret(dks);
				//解密
				de_cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
				de_cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
			} catch (Exception e) {
				logger.error("des解密初始化异常"+e.getMessage(),e);
			}
		}
		return de_cipher;
	}
	/**
	 * 加密单例
	 * @return
	 */
	private static Cipher getEcodeCipherInstance(){
		if(e_cipher == null){
			DESedeKeySpec dks;
			try {
				dks = new DESedeKeySpec(truncateHash(key, 24));
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
				SecretKey securekey = keyFactory.generateSecret(dks);
				//解密
				e_cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
				IvParameterSpec iv = new IvParameterSpec(truncateHash("", 8));
				e_cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
			} catch (Exception e) {
				logger.error("des加密初始化异常"+e.getMessage(),e);
			}
		}
		return e_cipher;
	}
    /**
     * @param key
     * @param length
     * @return
     */
    public static byte[] truncateHash(String key, int length){
//    	MessageDigest sha = MessageDigest.getInstance("SHA-1");
//    	sha.update(key.getBytes("UTF-16LE"));
//    	byte[] array = sha.digest();
    	try {
    		byte[] array = DigestUtils.sha1(key.getBytes(Encodeing));
    		byte[] array2 = new byte[length];
    		if (length < array.length) {
    			for (int i = 0; i < length; i++) {
    				array2[i] = array[i];
    			}
    		} else {
    			System.arraycopy(array, 0, array2, 0, array.length);
    		}
    		return array2;
		} catch (Exception e) {
			logger.error("des解密key sha加密异常"+e.getMessage(),e);
		}
    	return null;
    }

    public static String decryptData(String encryptedText){
    	String pwd = null;
    	try {
    		byte[] array = shift(Base64.decodeBase64(encryptedText.getBytes()));
    		byte[] result = getDecodeCipherInstance().doFinal(array);
    		pwd = new String(result,Charset.forName(Encodeing));
    	} catch (Exception e) {
    		logger.error("des解密解密异常"+e.getMessage(),e);
    	}
    	return pwd;
    }
    
    private static byte[] shift(byte[] ba) {
    	int k = 0;
		if (ba[1] == 1 && ba[0] >= 54) {
			k = 2;
		} else if (ba[2] == 1) {
			k = 3;
		}
		for (; k < ba.length; ++k) {
			byte b = ba[k];
			ba[k] = (byte) (((b & 0xf) << 4) | ((b & 0xf0) >> 4));
		}
		return ba;
    }
    
    public static String ecryptData(String encryptedText){
    	String pwd = null;
    	try {
            byte[] encryptData = shift(getEcodeCipherInstance().doFinal(encryptedText.getBytes(Encodeing))); 
            return Base64.encodeBase64String(encryptData);  
		} catch (Exception e) {
			logger.error("des加密异常"+e.getMessage(),e);
		}
        return pwd;
    }
    
    public static void main(String[] args) throws Exception {
    	Map<String, Object> map = Maps.newHashMap();
    	Map<String, Object> cmap_baseInfo = Maps.newHashMap();
    	Map<String, Object> cmap_function = Maps.newHashMap();
    	String menu = "/collection/caseentry,/collection/datamain";
    	map.put("menu", menu);
    	cmap_baseInfo.put("key_code", "123");//狗码
    	cmap_baseInfo.put("version", "1.0.17");//狗码
    	map.put("base_info", cmap_baseInfo);
    	String functions = "multiple_collect";
    	cmap_function.put("functions", functions);
    	cmap_function.put("person_limit", "10");
    	map.put("function", cmap_function);
        String mS = AoiStringUtils.getJsonObj(map);
        String mSs=ecryptData(mS);
        System.out.println(mSs);
        System.out.println(decryptData(mSs));
        
    }
}
