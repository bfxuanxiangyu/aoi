package com.weeds.aoi.auth.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalysisDatabaseStructure {
	
    static Connection conn = null;
    
	public static void main(String[] args) {
		
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://172.17.10.110:33036/srdb", "ffa", "sinnrenadmin");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		// 1、获取数据库所有表
    	String allTableSql = "show table status ";
    	try {
    		int tableCounter = 0;
    		ResultSet resultSet = conn.createStatement().executeQuery(allTableSql);
    		String ifTable;
    		while (resultSet.next()) {
    			ifTable = resultSet.getString("ENGINE");
    			if(ifTable==null || !ifTable.equals("InnoDB")){
    				continue;
    			}
    			tableCounter++;
            	StringBuffer sbTables = new StringBuffer();
                sbTables.append("表名：" + resultSet.getString("NAME") + "---");
                sbTables.append("表编码：" + resultSet.getString("Collation") + "---");
                sbTables.append("表注释：" + resultSet.getString("Comment") + "");
                System.out.println(sbTables);
                
                // 2、遍历数据库表，获取各表的字段等信息
                StringBuffer sbCloumns = new StringBuffer();
            	String sql = "show full fields from " + resultSet.getString("NAME");
            	try {
            		ResultSet cloumnsResultSet = conn.createStatement().executeQuery(sql);
            		while (cloumnsResultSet.next()) {
            			sbCloumns.append("字段名："+cloumnsResultSet.getString("Field")+"\t");
            			sbCloumns.append("备注："+cloumnsResultSet.getString("Comment")+"\t");
            			sbCloumns.append("类型："+cloumnsResultSet.getString("Type")+"\r\n");
            		}
            		System.out.println(sbCloumns.toString());
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
                
    		}
    		System.out.println("数据库中共有"+tableCounter+"张数据表");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
}
