package com.weeds.aoi.auth.bi.service.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.weeds.aoi.auth.bi.domain.CloumnVo;
import com.weeds.aoi.auth.bi.domain.TableVo;
import com.weeds.aoi.auth.bi.mapper.DbMapper;
import com.weeds.aoi.auth.bi.service.DbService;
import com.weeds.aoi.utils.AoiStringUtils;

@Service
public class DbServiceImpl implements DbService{

	private static Logger logger = LoggerFactory.getLogger(DbServiceImpl.class);
	
	@Resource
	public DbMapper dbMapper;

	@Override
	public List<TableVo> getTables(String sql) {
		List<LinkedHashMap<String, Object>>  list = dbMapper.resultSet(sql);
		
		Map<String, Object> tableMap,cloumnMap;
		String tableName,tableCollation,tableComment,cloumnSql,Field,Comment,Type;
		
		List<TableVo> tableList = Lists.newArrayList();
		TableVo tableVo = null;
		List<CloumnVo> cloumnList = Lists.newArrayList();
		CloumnVo cloumnVo = null;
		for (int i = 0; i < list.size(); i++) {
			tableMap = list.get(i);
			tableName = (String) tableMap.get("Name");//表名
			if(StringUtils.isEmpty(tableName)){
				continue;
			}
			tableCollation = (String) tableMap.get("Collation");//表编码
			tableComment = (String) tableMap.get("Comment");//表注释
			logger.info("表名称："+tableName + "--表编码："+tableCollation+"--表注释："+tableComment);
			tableVo = new TableVo();
			cloumnList = Lists.newArrayList();
			tableVo.setTableName(tableName);
			tableVo.setCollation(tableCollation);
			tableVo.setComment(tableComment);
			
			
			cloumnSql = "show full fields from " + tableName;
			List<LinkedHashMap<String, Object>>  cloumnMapList = dbMapper.resultSet(cloumnSql);
			
			for (int j = 0; j < cloumnMapList.size(); j++) {
				cloumnMap = cloumnMapList.get(j);
				Field = (String) cloumnMap.get("Field");//字段名
				if(StringUtils.isEmpty(Field)){
					continue;
				}
				Comment = (String) cloumnMap.get("Comment");//注释
				Type = (String) cloumnMap.get("Type");//类型
				logger.info("字段名："+Field + "--注释："+Comment+"--类型："+Type);
				cloumnVo = new CloumnVo();
				cloumnVo.setComment(Comment);
				cloumnVo.setField(Field);
				cloumnVo.setType(Type);
				cloumnList.add(cloumnVo);
			}
			tableVo.setCloumnList(cloumnList);
			tableVo.setJsonValue(AoiStringUtils.getJsonObj(tableVo));
			tableList.add(tableVo);
		}
		
		return tableList;
	}

	@Override
	public List<String[]> resultSet(String sql) {
		List<String[]> resultSet = Lists.newArrayList();
		List<String> resultList;
		List<String> titleSet = Lists.newArrayList();
		List<LinkedHashMap<String, Object>>  list = dbMapper.resultSet(sql);
		LinkedHashMap<String, Object> resultMap;
		String elementStr;
		StringBuffer sb;
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			resultMap = list.get(i);
			if(i == 0){
				resultList = Lists.newArrayList();
				Set<Entry<String, Object>> set = resultMap.entrySet();
				Iterator<Entry<String, Object>> iter = set.iterator();
				Entry<String, Object> entry ;
				while(iter.hasNext()){
					counter++;
					entry = iter.next();
					titleSet.add(entry.getKey());
					resultList.add(entry.getKey());
				}
				resultSet.add(resultList.toArray(new String[counter]));
				logger.info(titleSet.toString());
			}
			sb = new StringBuffer();
			resultList = Lists.newArrayList();
			sb.append("[");
			for (int j = 0; j < titleSet.size(); j++) {
				elementStr = titleSet.get(j);
				sb.append(resultMap.get(elementStr) + ",");
				resultList.add(resultMap.get(elementStr)+"");
			}
			sb.append("]");
			resultSet.add(resultList.toArray(new String[counter]));
			logger.info(sb.toString());
		}
		return resultSet;
	}

	@Override
	public void execute(String sql) {
//		logger.info("执行sql = "+sql);
		dbMapper.execute(sql);
	}

}
