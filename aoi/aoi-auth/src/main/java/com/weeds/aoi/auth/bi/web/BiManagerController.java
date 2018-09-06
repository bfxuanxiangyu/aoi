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
package com.weeds.aoi.auth.bi.web;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.weeds.aoi.auth.bi.domain.TableVo;
import com.weeds.aoi.auth.bi.service.DbService;
import com.weeds.aoi.utils.AoiStringUtils;

/**
 * @author sunxiaopeng
 * @date 2017年10月16日 下午4:32:08	
 */
@Controller
@RequestMapping("/bi")
public class BiManagerController {
	
	private static Logger logger = LoggerFactory.getLogger(BiManagerController.class);
	
	public List<String[]> resultSetGlobal;
	
	public String[] tableNames;
	
	@Resource
	private DbService dbService;
	
	@RequestMapping("/bi-manager")
	public ModelAndView userList(){
		ModelAndView view = new ModelAndView();
		view.setViewName("bi/bi-page");
		return view;
	}
	
	@RequestMapping("/bi-data")
	public ModelAndView getTableData(){
		ModelAndView view = new ModelAndView();
		view.setViewName("bi/bi-db-list");
		List<TableVo> tableList =  dbService.getTables("show table status");
		view.addObject("tableList",tableList);
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/bi-gensql")
	public String gensql(String tableArray[],String columArray[],String orderName,String functionName,
			String functionPara,String orderPara,String groupbyPara,String functionOrder){
		
		tableNames = tableArray;
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		
		//函数拼接 1
		if(AoiStringUtils.isNotBlank(functionName)){
			sb.append(functionName + "(" + functionPara +") as count_result ,");
			List<String> ls = Lists.newArrayList();
			for (int i = 0; i < columArray.length; i++) {
				if(!columArray[i].equals(functionPara)){
					ls.add(columArray[i]);
				}
			}
			columArray = ls.toArray(new String[columArray.length-1]);
		}
		
		//表字段查询
		for(int i=0; i<columArray.length ; i++){
			sb.append(columArray[i]);
			if(i < columArray.length-1){
				sb.append(", ");
			}
		}
		sb.append(" FROM ");
		for (int i = 0; i < tableArray.length; i++) {
			sb.append(tableArray[i] +" "+tableArray[i]);
			if(i < tableArray.length-1){
				sb.append(", ");
			}
		}
		
		
		//函数拼接 2
		if(StringUtils.isNotBlank(groupbyPara)){
			sb.append(" group by " + groupbyPara +"");
		}
		
		//排序拼接
		if(StringUtils.isNotEmpty(functionName) && tableArray.length==1 && StringUtils.isNotBlank(functionOrder)){
			sb.append(" order by count_result "+ functionOrder);
		}else if(StringUtils.isNotEmpty(orderName)){
			sb.append(" order by " + orderPara + " " + orderName);
		}
		
		
		return sb.toString();
	}
	
	@RequestMapping("/bi-resultSet")
	public ModelAndView gensql(String sql){
		
		ModelAndView view = new ModelAndView();
		view.setViewName("bi/bi-results");
		Pattern pt = Pattern.compile("INSERT|DELETE|UPDATE|TRUNCATE|DROP");
		Matcher match = pt.matcher(sql.toUpperCase());
		if(match.find()){
			return view;
		}
		logger.info("执行sql="+sql);
		List<String[]> resultSet = dbService.resultSet(sql);
		view.addObject("resultSet",resultSet);
		
		resultSetGlobal = resultSet;
		
		return view;
	}
	
	@RequestMapping("/bi-clearresult")
	public ModelAndView clearresult(){
		
		ModelAndView view = new ModelAndView();
		view.setViewName("bi/bi-db-clear");
		List<TableVo> tableList =  dbService.getTables("show table status");
		TableVo tv;
		String tableName;
		List<TableVo> clearList = Lists.newArrayList();
		for (int i = 0; i < tableList.size(); i++) {
			tv = tableList.get(i);
			tableName = tv.getTableName();
			if(tableName.toUpperCase().contains("AOITEMP")){
				clearList.add(tv);
			}
		}
		
		view.addObject("tableList",clearList);
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/bi-saveResultSet")
	public String saveResultSet(String sql,String tableName){
		
		logger.info("执行sql="+sql);
		
		if(StringUtils.isBlank(sql)){
			return "fail";
		}
		
		
		StringBuffer tableTempName = new StringBuffer(); 
		tableTempName.append(AoiStringUtils.TABLE_PREFIX);
		for (int i = 0; i < tableNames.length; i++) {
			tableTempName.append(tableNames[i]+"_");
		}
		tableTempName.append(AoiStringUtils.getRandomNum(6));
		
		if(StringUtils.isBlank(tableName)){
			tableName = tableTempName.toString();
		}else{
			tableName = AoiStringUtils.TABLE_PREFIX + tableName;
		}
		
		String [] tables = resultSetGlobal.get(0);
		StringBuffer createTableSql = new StringBuffer();
		createTableSql.append("create table "+tableName+"(");
		for (int i = 0; i < tables.length; i++) {
			createTableSql.append(tables[i] + " varchar(255)");
			if(i < tables.length-1){
				createTableSql.append(", ");
			}
		}
		createTableSql.append(")");
		
		StringBuffer insertSql = new StringBuffer();
		insertSql.append("insert into "+tableName+" values");
		try {
			dbService.execute(createTableSql.toString());
			logger.info("执行 crate sql = "+createTableSql);
			for (int i = 1; i < resultSetGlobal.size(); i++) {
				String [] tmpArray = resultSetGlobal.get(i);
				insertSql.append("(");
				for (int j = 0; j < tmpArray.length; j++) {
					insertSql.append("'"+tmpArray[j]+"'");
					if(j < tmpArray.length-1){
						insertSql.append(",");
					}
				}
				insertSql.append(")");
				if(i < resultSetGlobal.size()-1){
					insertSql.append(",");
				}
			}
//			logger.info(insertSql.toString());
			dbService.execute(insertSql.toString());
		} catch (Exception e) {
			logger.error("持久化异常"+e.getMessage(),e);
			return "fail";
		}
		
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping("/bi-cleartable")
	public String cleartable(String[] tables){
		try {
			String table;
			for (int i = 0; i < tables.length; i++) {
				table = tables[i];
				if(table.toUpperCase().contains("AOITEMP")){
					dbService.execute("truncate "+table);
					dbService.execute("drop table "+table);
				}
			}
		} catch (Exception e) {
			logger.error("清理异常"+e.getMessage(),e);
			return "fail";
		}
		return "success";
	}
	
}
