package com.weeds.aoi.utils;

import static com.weeds.aoi.utils.AoiStringUtils.failJson;
import static com.weeds.aoi.utils.AoiStringUtils.printJson;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class SmsBankPatternUtils {
	
	private static Logger logger = LoggerFactory.getLogger(SmsBankPatternUtils.class);
	
	public static final String rule_fuzzy [] = {"交易","代收"};//模糊词意   兼容“-”
    public static final String rule_out [] = {"失败","股票","验证码","资金需求","短信帐单","诚邀","动态密码"};//排除关键字   type_id=51
    public static String expend_front [] = {"取出","应还","转出","还款","代收","消费","取款","交易","转至","提款"
    												,"网上支付","消费支出","收费","支出","付款","实扣","支取"
    												,"保费","转存","转账","集团","扣款","快捷金额","偿还","交费"};// 支出关键字   type_id=28
    public static String income_front [] = {"收入","转入","收到","存入","存款","发起","入账","到账"};//收入关键字   type_id=29
    public static String expend_behind [] = {"(还款)","(跨行消费)"};//支出前推算法关键字      type_id=65
    public static String income_behind [] = {"(他行转入)","(转入资产)"};//收入前推算法关键字   type_id=64
    public static String card [] = {"尾号","尾号为","账号","户","卡"};//卡号提取关键   type_id=30
    public static String balance [] = {"余额","余额为"};//余额提取关键字    type_id=62
    public static String key_middle_replace [] = {"，",",","人民币","RMB","\\*","￥"," ","金额为","理财宝账户"};//替换关键字    type_id=63
    
    public static final Pattern pattern_parentheses = Pattern.compile("(?<=\\()[^\\)]+");//带括号的内容  
    public static final Pattern pattern_brackets = Pattern.compile("(?<=\\[)[^\\]]+");//带[]的内容  
    public static final Pattern pattern_money = Pattern.compile("(\\d+(\\.\\d+)?)");;//带括号的内容  
    public static final Pattern pattern_url = Pattern.compile("^*(([a-zA-Z]))+\\.([a-zA-Z])+");
    public static final String income_type = "income", expend_type = "expend", balance_type = "balance", card_type = "card";
	
	
	
    public static String singleExtract(String content){
    	logger.info("原文："+content);
    	String value = "";
    	content = format(content);
    	
    	if(content == null){
    		return failJson("无法识别",null);
    	}
    	
    	Matcher matcher = pattern_money.matcher(content);
    	
    	Map<String, Object> valueMap = getContent(matcher, content);
    	if(valueMap == null || valueMap.isEmpty()){
    		return failJson("无法识别",null);
    	}
    	/*for (String s : valueMap.keySet()) {
    		System.out.print(s + "--" + valueMap.get(s));
    	}*/
    	value = printJson("提取成功", valueMap);
    	
    	logger.info("提取结果："+value);
    	
    	return value;
    }
	public static String singleExtract(String contents[]){
		String content,value;
		List<Map<String, Object>> list = Lists.newArrayList(); 
		for (int i = 0; i < contents.length; i++) {
			content = contents[i];
			try {
				content =new String(Base64Utils.decode(content.getBytes()),"UTF-8");
				logger.info("原文："+content);
				content = format(content);
				
				if(content == null){
					continue;
				}
				
				Matcher matcher = pattern_money.matcher(content);
				
				Map<String, Object> valueMap = getContent(matcher, content);
				if(valueMap == null || valueMap.isEmpty()){
					continue;
				}
				valueMap.put("content", new String(Base64Utils.decode(content.getBytes()),"UTF-8"));
				list.add(valueMap);
				
				logger.info("提取结果："+ printJson("success", valueMap));
			} catch (Exception e) {
				continue;
			}	
		}
		value = printJson("提取成功", list);
    	
    	return value;
    }
	
	
	/**
	 * 格式化字符串
	 * @param content
	 * @return
	 */
	public static String format(String content){
		if(contaionsUrl(content)){
    		return null;
		}
		boolean filterFlag = false;
		for (int i = 0; i < rule_out.length; i++) {
			if(content.contains(rule_out[i])){
				filterFlag = true; 
				break;
			}
		}
		if(filterFlag){
			return null;
		}
    	content = replaceKey(content);//剔除一部分备注内容
    	for (int i = 0; i < key_middle_replace.length; i++) {
    		content = content.replaceAll(key_middle_replace[i], "");
		}
    	return content;
	}
    
    public static Map<String, Object> getContent(Matcher matcher,String content){
    	String mactchStr = null,incomeStr,expendStr,cardStr,balanceStr;
    	StringBuffer sb = new StringBuffer();
    	boolean incomeFlag = false,expendFlag = false, cardFlag = false, balanceFlag = false;
    	Map<String, Object> valueMap = Maps.newHashMap();
    	while(matcher.find()){
    		mactchStr = matcher.group();
    		if(!filterMoneyTime(content,mactchStr)){

    			if(!incomeFlag){
    				for (int i = 0; i < income_front.length; i++) {//先进行收入判断
    					incomeStr = income_front[i];
    					if(content.indexOf(incomeStr+mactchStr) != -1 ){
    						if(!filterMoneyNotNow(content, incomeStr+mactchStr,1)){
    							sb.append("#收入"+mactchStr);
    							valueMap.put(income_type, mactchStr);
    							incomeFlag = true;
    							break;
    						}
    					}
    				}
    				//循环完成仍未发现金额   进行前推算法
    				if(!incomeFlag){
    					for (int i = 0; i < income_behind.length; i++) {//先进行收入判断
        					incomeStr = income_behind[i];
        					if(content.indexOf(mactchStr+incomeStr) != -1 || content.indexOf(mactchStr+"元"+incomeStr) != -1 ){
        						if(!filterMoneyNotNow(content, incomeStr+mactchStr,-1) 
        								|| !filterMoneyNotNow(content, mactchStr+"元"+incomeStr,-1)){
        							sb.append("#收入"+mactchStr);
        							valueMap.put(income_type, mactchStr);
        							incomeFlag = true;
        							break;
        						}
        					}
        				}
    				}
    			}
    			if(!expendFlag){
    				for (int i = 0; i < expend_front.length; i++) {//先进行收入判断
    					expendStr = expend_front[i];
    					if(content.indexOf(expendStr+mactchStr) != -1 
    							|| content.indexOf(expendStr+"-"+mactchStr) != -1 ){//如果击中交易   并且金额为负数    则定性为支出   否则转为收入计算
    						if(!ArrayUtils.contains(rule_fuzzy, expendStr)){//如果击中后 不是模糊定义下的进行"-"号剔除
    							content = content.replaceAll("-", "");
    						}
    						if(!filterMoneyNotNow(content, expendStr+mactchStr,1) 
    								|| !filterMoneyNotNow(content, expendStr+"-"+mactchStr,1)){
    							if((expendStr.equals("交易")||expendStr.equals("代收")) && content.indexOf(expendStr+mactchStr) != -1 ){
    								sb.append("#收入"+mactchStr);
    								valueMap.put(income_type, mactchStr);
        							incomeFlag = true;
    							}else{
    								sb.append("#支出"+mactchStr);
    								valueMap.put(expend_type, mactchStr);
    								expendFlag = true;
    							}
    							break;
    						}
    					}
    				}
    				//循环完成仍未发现金额   进行前推算法
    				if(!expendFlag){
    					for (int i = 0; i < expend_behind.length; i++) {//先进行收入判断
        					incomeStr = expend_behind[i];
        					if(content.indexOf(mactchStr+incomeStr) != -1 || content.indexOf(mactchStr+"元"+incomeStr) != -1 ){
        						if(!filterMoneyNotNow(content, incomeStr+mactchStr,-1) 
        								|| !filterMoneyNotNow(content, mactchStr+"元"+incomeStr,-1)){
        							sb.append("#支出"+mactchStr);
        							valueMap.put(expend_type, mactchStr);
        							expendFlag = true;
        							break;
        						}
        					}
        				}
    				}
    			}
    			if(!balanceFlag){
    				for (int i = 0; i < balance.length; i++) {//先进行收入判断
    					balanceStr = balance[i];
    					//如果余额属于最后一位   直接提取
    					if(content.endsWith(balanceStr+mactchStr)){
    						sb.append("#余额"+mactchStr);
    						valueMap.put(balance_type, mactchStr);
    						balanceFlag = true;
    						break;
    					}
    					if(content.indexOf(balanceStr+mactchStr) != -1){
    						if(filterMoneyNotNow(content, balanceStr+mactchStr,1)){
    							continue;
    						}
    						sb.append("#余额"+mactchStr);
    						valueMap.put(balance_type, mactchStr);
    						balanceFlag = true;
    						break;
    					}
    				}
    			}	
    			if(!cardFlag){
    				for (int i = 0; i < card.length; i++) {//先进行收入判断
    					cardStr = card[i];
    					if(content.indexOf(cardStr+mactchStr) != -1){
    						if(!filterMoneyNotNow(content, cardStr+mactchStr,1)){
	    						sb.append("#尾号"+mactchStr);
	    						valueMap.put(card_type, mactchStr);
	    						cardFlag = true;
	    						break;
    						}	
    					}
    				}
    			}
    		}
    	}	
    	if(valueMap == null || valueMap.isEmpty()){
    		sb.append("无效");
    	}
    	return valueMap;
    }
    
    /**
     * 过滤抽取的时间
     * @param content
     * @param indexStr
     * @return
     */
    private static boolean filterMoneyTime(String content,String indexStr){
    	boolean flag = false;
    	try {
    		if(indexStr.indexOf(".") != -1){//数据带小数点或者前边带 “人民币”，“人民币-”的不处理
				return flag;
			}
    		if(indexStr.length()> 1 && indexStr.startsWith("0") && !filterCard(content,indexStr)){
    			return true;
    		}
    		String timeArray [] = {"月","日","时","分",":","-"};//时间过滤
    		String timeStr;
    		for (int i = 0; i < timeArray.length; i++) {
    			timeStr = timeArray[i];
    			if( (content.indexOf(indexStr+timeStr) != -1 || content.indexOf(timeStr+indexStr) != -1)
    					&& content.indexOf(timeStr+indexStr+"元") == -1 ){//以元结尾的不处理
    				if(!filterMoney(content, indexStr)){
    					flag = true;
    					break;
    				}
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return flag;
    }
    /**
     * 确定是尾号的保留
     * @param content
     * @param indexStr
     * @return
     */
    private static boolean filterCard(String content, String indexStr){
    	boolean flag = false;
    	try {
    		for (int i = 0; i < card.length; i++) {
    			if(content.contains(card[i]+indexStr)){
    				flag = true;
    				break;
    			}
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return flag;
    }
    /**
     * 确定是金额的保留
     * @param content
     * @param indexStr
     * @return
     */
    private static boolean filterMoney(String content, String indexStr){
    	boolean flag = false;
    	try {
    		for (int i = 0; i < income_front.length; i++) {
				if(content.contains(income_front[i]+indexStr+"元")){
					flag = true;
					break;
				}
			}
    		if(!flag){
    			for (int i = 0; i < expend_front.length; i++) {
    				if(content.contains(expend_front[i]+indexStr+"元")){
    					flag = true;
    					break;
    				}
    			}
    		}
    		if(!flag){
    			for (int i = 0; i < balance.length; i++) {
    				if(content.contains(balance[i]+indexStr+"元")){
    					flag = true;
    					break;
    				}
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return flag;
    }
    /**
     * 判断提取的数字后一位还是数字，如果是数字则击中金额和其他数字前几位相同问题
     * @param content
     * @param matcherStr
     * @return
     */
    private static boolean filterMoneyNotNow(String content,String matcherStr,int type){
    	boolean flag = false;
    	try {
    		int index = content.indexOf(matcherStr);
    		if(index==-1){
    			return true;
    		}
    		int start = index+matcherStr.length();
    		if(type == -1){
    			start = index - 1;
    		}
    		String str = content.substring(start, start+1);
    		//判断当前是不是结束语句
    		String endStr = content.substring(start+1);
    		if((str.equals(".") || str.matches("\\d")) && endStr.length()>6){
    			flag = true;
    		}
		} catch (Exception e) {
			flag = false;
		}
    	return flag;
    }
    
    public static String replaceKey(String content){
    	content = content.replaceAll("（", "(").replaceAll("）", ")");
    	Matcher matcher = pattern_parentheses.matcher(content);
    	while(matcher.find()){
    		if( (ArrayUtils.contains(income_behind,"("+matcher.group()+")") 
    				|| ArrayUtils.contains(expend_behind,"("+matcher.group()+")"))
    				&& !content.contains("("+matcher.group()+")人民币")){// 括弧识别，发现在人民币之前的不过滤
    			continue;
    		}
    		//如果包含账号的尾号信息   不进行过滤
    		boolean cardFlag = false;
    		for (int i = 0; i < card.length; i++) {
    			if(matcher.group().contains(card[i])){
    				cardFlag = true;
    				break;
    			}
			}
    		if(cardFlag){
    			continue;
    		}
    		try {
    			content = content.replaceAll("\\("+matcher.group()+"\\)","");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	//识别“[]”过滤
    	matcher = pattern_brackets.matcher(content);
    	while(matcher.find()){
    		try {
    			content = content.replaceAll("\\["+matcher.group()+"\\]","");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	return content;
    }
    
    
    /**
     * 是否包含url
     * @param content
     * @return
     */
    public static boolean contaionsUrl(String content){
    	Matcher matcher = pattern_url.matcher(content);
    	return matcher.find();
    }
    
    public static void main(String[] args) {
    	System.out.println(singleExtract("您尾号4608的储蓄卡账户1月8日7时4分37秒短信服务费支出人民币3.00元,活期余额82.00元。[建设银行]"));
	}

}
