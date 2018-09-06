package com.weeds.aoi.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class AoiValidationUtils extends StringUtils{
	
	/**
     * 验证邮箱输入是否合法
     *
     * @param strEmail
     * @return
     */
    public static boolean isEmail(String strEmail) {
        // String strPattern =
        // "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        String strPattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 验证是否是手机号码
     *
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符全角化
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 判断是否是中文
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


    /**
     * 判断字符串是否超过指定字符数
     *
     * @param content
     * @param stringNum 指定字符数  如：140
     * @return
     */
    public static boolean countStringLength(String content, int stringNum) {
        int result = 0;
        if (content != null && !"".equals(content)) {
            char[] contentArr = content.toCharArray();
            if (contentArr != null) {
                for (int i = 0; i < contentArr.length; i++) {
                    char c = contentArr[i];
                    if (isChinese(c)) {
                        result += 3;
                    } else {
                        result += 1;
                    }
                }
            }
        }
        if (result > stringNum * 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch < '0' || ch > '9') {
                return false;

            }
        }
        return true;
    }

    /**
     * 随机字符串(a-zA-Z0-9)
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);//[0,62)  

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 随机字存数字符串
     *
     * @param length
     * @return
     */
    public static String getRandomNum(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(10);//[0,62)  

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    /**
	 * 随机生成多少位
	 * @param longVal
	 * @param status  1 获取纯数字(整数)     2获取字符串类型
	 * @param type  intnum(整数)     doublenum获取字符串类型
	 * @return
	 */
	public static String getRandomVal(int status,int longVal,String type){
		String returnVal = "";
		if(status==1){
			Random rd=new Random();//随机数生成器
			if(isNotEmpty(type) && type.equals("doublenum")){
				double j= rd.nextDouble()*10  + longVal;
				returnVal = j+"";
			}else if(isNotEmpty(type) && type.equals("intnum")){
				String longValue = "10";
				for (int i = 1; i < longVal; i++) {
					longValue += "0";
				}
				int j = (int) (rd.nextDouble()*(Integer.valueOf(longValue)));
				returnVal = j+"";
			}else{
				returnVal = rd.nextInt()*longVal+"";
			}
		}else if(status==2){
			String str = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
	        String str2[] = str.split(",");// 将字符串以,分割
	        Random rand = new Random();// 创建Random类的对象rand
	        int index = 0;
	        String randStr = "";// 创建内容为空字符串对象randStr
	        randStr = "";// 清空字符串对象randStr中的值
	        for (int i = 0; i < longVal; ++i) {
	            index = rand.nextInt(str2.length - 1);// 在0到str2.length-1生成一个伪随机数赋值给index
	            randStr += str2[index];// 将对应索引的数组与randStr的变量值相连接
	        }
	        returnVal = (randStr+"").toUpperCase();
		}
		return returnVal;
	}

    public static void main(String[] args) {
        System.out.println(getRandomString(12));
        System.out.println(getRandomNum(1));
        System.out.println(getRandomVal(1, 3, "intnum"));
    }

}
