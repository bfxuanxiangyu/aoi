package com.weeds.aoi.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

public class AoiResponseUtil {

	private static Logger logger = LoggerFactory.getLogger(AoiResponseUtil.class);
	
	// 请求标识
	public static final int RESULT_ERROR = 0;
	public static final int RESULT_SUCCESS = 1;
	public static final int NORMAL = 6666;//正常请求成功码
	// 请求错误编码
	/** 服务器内部异常 */
	public static final int SERVERUPLOAD = 10000;
	/*** 详情不存在 ***/
	public static final int INEXISTENCE = 10001;
	/*** 没有权限 ***/
	public static final int AUTHORITY = 10002;
	// 用户编码 10 结果码集合
	/*** 缺少参数 ***/
	public static final int PARAMETERS = 400;
	/*** 密码错误 ***/
	public static final int PASSWORDFAIL = 401;
	/*** 手机号不合法 ***/
	public static final int PHONEFAIL = 402;
	/*** RSA解密失败 ***/
	public static final int RSAFAIL = 403;
	/*** 手机号已注册 ***/
	public static final int PHONEALEDY = 404;
	/*** 昵称已存在 ***/
	public static final int NICKALEDY = 405;
	/*** 手机未注册 ***/
	public static final int PHONENO = 406;
	/*** 邮箱不合法 ***/
	public static final int EMAILFIAL = 407;
	/*** 邮箱为空 ***/
	public static final int EMAILNO = 408;
	/*** 缺少对象，该字段是根据id、或者其他条件获取对象 ***/
	public static final int NOOBJECT = 409;
	/*** 改商品已收藏 ***/
	public static final int ALREADYADD = 410;
	/*** 服务器内部错误 ***/
	public static final int SERVERFAIL = 500;
	// 系统模块 起始编码 15
	/*** 根据最大版本号查询，没有版本控制信息 ***/
	public static final int VERSIONNOBIG = 1501;
	/*** 获取版本信息，没有版本控制信息 ***/
	public static final int NOVERSION = 1502;
	/*** 该版本无需升级 ***/
	public static final int NOUP = 1503;
	/*** 没有版本控制信息 ***/
	public static final int NOSERVICE = 1504;
	/** 验证码错误 */
	public static final int validateError = 1505;
	/** 失败 */
	public static final int failed = 1506;
	/** 图片上传失败 */
	public static final int PICUPLOADFAIL = 1507;
	/** token失败 */
	public static final int token_error = 1508;
	
	/**
	 * @param result
	 * @param code
	 * @param data
	 * @return
	 */
	public static String printJson(int result,int code,Object data){
		Map<String, Object> printMap = Maps.newHashMap();
		printMap.put("result", result);
		printMap.put("code", code);
		printMap.put("data", data);
		ObjectMapper mapper = new ObjectMapper();
		String returnValue = "";
		try {
			returnValue = mapper.writeValueAsString(printMap);
		} catch (JsonProcessingException e) {
			logger.error("json转换失败"+e.getMessage(),e);
		}
		return returnValue;
	}
}
