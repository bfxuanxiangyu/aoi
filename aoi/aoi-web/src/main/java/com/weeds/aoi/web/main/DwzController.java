package com.weeds.aoi.web.main;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.weeds.aoi.service.system.domain.ShortUrl;
import com.weeds.aoi.service.system.service.ShortStringService;
import com.weeds.aoi.utils.AoiStringUtils;
import com.weeds.aoi.utils.GlobalParameters;

@Controller
@RequestMapping("/dwz")
public class DwzController {

	//private Logger logger = LoggerFactory.getLogger(WebmainController.class);
	
	@Resource 
	private ShortStringService shortStringService; 
	
	@RequestMapping("/{shortUrl}")
	public ModelAndView shortUrlGo(@PathVariable("shortUrl") String shortUrl){
		ModelAndView view = new ModelAndView();
		view.setViewName("dwz");
		if(AoiStringUtils.isBlank(shortUrl)){
			view.addObject("error", "链接不存在");
			return view;
		}
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("shortUrl", GlobalParameters.netUrl+shortUrl);
		ShortUrl su = shortStringService.getObject(parameters);
		if(su==null){
			view.addObject("error", "链接不存在");
			return view;
		}
		view.addObject("url", su.getUrl());
		
		return view;
	}
	
	
	
}
