package com.weeds.aoi.api.system.rest;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weeds.aoi.service.system.domain.SystemVersionInfo;
import com.weeds.aoi.service.system.service.SystemVersionInfoService;
import com.weeds.aoi.utils.AoiResponseUtil;
import com.weeds.aoi.utils.AoiStringUtils;

@Controller
@RequestMapping("/api/system")
public class SystemController {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	
	@Resource
	private SystemVersionInfoService systemVersionInfoService;
	
	@ResponseBody
	@RequestMapping("/version")
	public String index() {
		
		SystemVersionInfo systemVersionInfo = systemVersionInfoService.getSystemVersionInfoObj("402889ea6055620f0160556252f40000");
		
		return AoiResponseUtil.printJson(AoiResponseUtil.RESULT_SUCCESS,AoiResponseUtil.NORMAL, systemVersionInfo);
	}
	
	@ResponseBody
	@RequestMapping("/save-version")
    public String saveVersion() {
		
		SystemVersionInfo systemVersionInfo = new SystemVersionInfo();
		systemVersionInfo.setCityCode("10010");
		systemVersionInfo.setCompel("1");
		systemVersionInfo.setCreateTime(new Date());
		systemVersionInfo.setDevicetype(1);
		systemVersionInfo.setPlatCode("1001");
		systemVersionInfo.setSystemVersion("1.1.1");
		systemVersionInfo.setVersionCode(12);
		systemVersionInfo.setVersionDesc("正式上线");
		
		systemVersionInfoService.saveSystemVersionInfo(systemVersionInfo);
		
		return AoiStringUtils.getJsonObj("success");
    }

}
