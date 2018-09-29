package com.weeds.aoi.web.main;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weeds.aoi.service.system.service.ShortStringService;

@Controller
@RequestMapping("/api")
public class WebmainController {

	//private Logger logger = LoggerFactory.getLogger(WebmainController.class);
	
	@Resource 
	private ShortStringService shortStringService; 
	
	/*@RequestMapping("")
	public ModelAndView webmain(){
		ModelAndView view = new ModelAndView();
		view.setViewName("main");
		return view;
	}*/
	@RequestMapping("/moneymsg")
	public ModelAndView moneymsg(){
		ModelAndView view = new ModelAndView();
		view.setViewName("extract");
		return view;
	}
	@RequestMapping("/baseinfo")
	public ModelAndView baseinfo(){
		ModelAndView view = new ModelAndView();
		view.setViewName("remark");
		return view;
	}
	@RequestMapping("/updateinfo")
	public ModelAndView updateinfo(){
		ModelAndView view = new ModelAndView();
		view.setViewName("update");
		return view;
	}
	@RequestMapping("/lnglat")
	public ModelAndView lnglat(){
		ModelAndView view = new ModelAndView();
		view.setViewName("lnglat");
		return view;
	}
	@RequestMapping("/ipinfo")
	public ModelAndView ipinfo(){
		ModelAndView view = new ModelAndView();
		view.setViewName("ipinfo");
		return view;
	}
	@RequestMapping("/askfor")
	public ModelAndView askfor(){
		ModelAndView view = new ModelAndView();
		view.setViewName("askfor");
		return view;
	}
	@RequestMapping("/short")
	public ModelAndView shortMethod(){
		ModelAndView view = new ModelAndView();
		view.setViewName("short");
		return view;
	}
	
}
