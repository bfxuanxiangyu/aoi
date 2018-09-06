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
package com.weeds.aoi.auth.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author Jetory
 * @date 2017年10月13日 下午2:59:30
 */
public class PinyinUtils {

	HanyuPinyinOutputFormat format = null;

	public PinyinUtils() {
		format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}

	public String toPinYin(String str) throws BadHanyuPinyinOutputFormatCombination {
		return toPinYin(str, "");
	}

	/**
	 * 将str转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换 如： 明天 转换成 MINGTIAN
	 * 
	 * @param str：要转化的汉字
	 * @param spera：转化结果的分割符
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public String toPinYin(String str, String spera) throws BadHanyuPinyinOutputFormatCombination {
		if (str == null || str.trim().length() == 0)
			return "";

		String py = "";
		String temp = "";
		String[] t;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((int) c <= 128)
				py += c;
			else {
				t = PinyinHelper.toHanyuPinyinStringArray(c, format);
				if (t == null)
					py += c;
				else {
					temp = t[0];
					py += temp + (i == str.length() - 1 ? "" : spera);
				}
			}
		}
		return py.trim();
	}
	
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		PinyinUtils py = new PinyinUtils();
		System.out.println(py.toPinYin("中国songjian"));
	}
}
