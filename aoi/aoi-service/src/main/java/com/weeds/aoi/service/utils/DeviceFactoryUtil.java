package com.weeds.aoi.service.utils;

import com.weeds.aoi.service.device.domain.DeviceEntity;



public class DeviceFactoryUtil {
	
	private static final ThreadLocal<DeviceEntity> deviceLocal = new ThreadLocal<DeviceEntity>(); 
	
	public static DeviceEntity getDevice() { 
		try {
			return deviceLocal.get();  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}  
	
	public static void setDevice(final DeviceEntity device) {
		deviceLocal.set(device);
	}

}
