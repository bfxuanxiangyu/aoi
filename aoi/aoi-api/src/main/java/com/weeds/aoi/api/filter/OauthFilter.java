package com.weeds.aoi.api.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weeds.aoi.api.token.domain.AccessToken;
import com.weeds.aoi.api.token.mapper.AccessTokenMapper;
import com.weeds.aoi.api.utils.HMac;
import com.weeds.aoi.service.device.domain.DeviceEntity;
import com.weeds.aoi.service.utils.DeviceFactoryUtil;
import com.weeds.aoi.utils.AoiResponseUtil;

@WebFilter(filterName="oauthFilter",urlPatterns="/*")
public class OauthFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(OauthFilter.class);
	
	@Resource
	private AccessTokenMapper accessTokenMapper;
	
	
	private List<String> oauthUriList = Lists.newArrayList();

	@Override
	public void destroy() {
		logger.info("过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hsRequest = (HttpServletRequest)request;
		HttpServletResponse hsResponse = (HttpServletResponse)response;
		String uri = hsRequest.getRequestURI(); 
		//过滤器中获取设备信息
		DeviceEntity device = new DeviceEntity();
		String devicetype = hsRequest.getParameter("devicetype");//androd、ios.pad
		String version = hsRequest.getParameter("version");
		device.setDevicetype(devicetype);
		device.setVersion(version);
		DeviceFactoryUtil.setDevice(device);
		chain.doFilter(request, response);
		
		/*if (uri.contains("/api/upload/addSrc") || uri.contains("getToken.json") || uri.contains("getCitysList.json")||uri.contains("createRescueCompany.json")
				||uri.contains("/api/baoxian/")||uri.contains("nearRescue.json")||uri.contains("userReviewInfo.json")
				||uri.contains("userReview.json")||uri.contains("/api/system/toPayByScanQr.json")) {
			chain.doFilter(request, response);
			return;
		}
		Map<String, String[]> parametersMap = hsRequest.getParameterMap();
		final String actualSecret = request.getParameter(HMac.APP_SECRET_NAME);
		String deviceId = "";
		if(parametersMap!=null && parametersMap.get("deviceId")!=null){
			deviceId = String.valueOf(parametersMap.get("deviceId")[0]);
		}
		if (StringUtils.isEmpty(actualSecret) || StringUtils.isEmpty(deviceId)) {
			response.getWriter().println("Illegal url.");
			return;
		}
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("deviceId", deviceId);
		AccessToken at = accessTokenMapper.selectAccessToken(parameters);
		if(at==null || StringUtils.isEmpty(at.getAccessToken())){
			AoiResponseUtil.printJson(AoiResponseUtil.RESULT_ERROR, AoiResponseUtil.token_error, "token失效");
			return;
		}
		HMac.APP_SECRET_VALUE= at.getAccessToken();//给动态密令赋值
		final String expectSecret = HMac.encrypt(request.getParameterMap());

		if (logger.isDebugEnabled()) {
			logger.debug("actualSecret:" + actualSecret);
			logger.debug("expectSecret:" + expectSecret);
		}

		if (!expectSecret.equals(actualSecret)) {
			response.getWriter().println("Invalid request.");
			return;
		}
		chain.doFilter(request, response);*/
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("过滤器初始化");
	}
	
	
	/**
	 * 需要验证
	 * @param uri
	 * @return
	 */
	public boolean isOauthUri(String uri){
		boolean isOauth = false;
		for (String oauthUri: oauthUriList) {
			if(uri.contains(oauthUri)){
				return true;
			}
		}
		return isOauth;
	}

}
