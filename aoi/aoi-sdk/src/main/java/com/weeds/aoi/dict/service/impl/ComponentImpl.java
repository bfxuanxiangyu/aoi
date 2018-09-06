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
package com.weeds.aoi.dict.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.weeds.aoi.dict.domain.Dict;
import com.weeds.aoi.dict.mapper.DictMapper;
import com.weeds.aoi.dict.service.DictComponent;

/**
 * @author sunxiaopeng
 * @date 2017年10月23日 下午2:06:37	
 */
@Component("dictComponent")
@CacheConfig(cacheNames = "system")
public class ComponentImpl implements DictComponent {
	
	@Resource
	private DictMapper dictMapper;

	@Override
	@Cacheable(key="'dict-key' + #typeId + '-' + #dictId")
	public String getDictName(Integer typeId, Integer dictId, String defaultStr) {
		Dict dict = this.getDict(typeId, dictId);
		if(dict == null){
			return defaultStr;
		}
		return dict.getDictName();
	}

	@Override
	@Cacheable(key="'dictList-key' + #typeId")
	public List<Dict> getDictByTypeId(Integer typeId) {
		return dictMapper.selectByTypeId(typeId);
	}
	
	@Override
	@Cacheable(key="'dict-key' + #typeId + '-' + #dictId")
	public Dict getDict(Integer typeId, Integer dictId) {
		
		if(typeId == null || dictId == null){
			return null;
		}
		
		Dict di = new Dict();
		di.setTypeId(typeId);
		di.setDictId(dictId + "");
		List<Dict> list = dictMapper.selectByTypeIdDictId(di);
		if(list == null || list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public int addDict(Dict dict) {
		return dictMapper.insert(dict);
	}

	@Override
	public int getMaxDictId(Integer typeId) {
		return dictMapper.selectMaxDictId(typeId);
	}

}
