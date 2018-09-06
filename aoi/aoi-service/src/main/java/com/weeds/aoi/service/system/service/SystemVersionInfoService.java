package com.weeds.aoi.service.system.service;

import com.weeds.aoi.service.system.domain.AskFor;
import com.weeds.aoi.service.system.domain.SystemVersionInfo;

public interface SystemVersionInfoService {
	
	void saveSystemVersionInfo(SystemVersionInfo systemVersionInfo);
	
	void deleteSystemVersionInfo(String id);
	
	SystemVersionInfo getSystemVersionInfoObj(String id);
	
	void saveAskForInfo(AskFor askFor);

}
