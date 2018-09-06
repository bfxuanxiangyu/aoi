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
package com.weeds.aoi.dict.service;

import java.util.List;

import com.weeds.aoi.dict.domain.Dict;

/**
 * 
 * @date 2017年10月23日 下午2:06:12	
 */
public interface DictComponent {
	
	Dict getDict(Integer typeId, Integer dictId);
	
	String getDictName(Integer typeId, Integer dictId, String defaultStr);
	
	List<Dict> getDictByTypeId(Integer typeId);
	
	int addDict(Dict dict);
	
	int getMaxDictId(Integer typeId);
}
