package com.weeds.aoi.auth.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnalysisDatabaseStructureDemo {
	
	private static final long serialVersionUID = 1L;
    static Connection conn = null;
    Statement st = null;
    
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
        StringBuffer sbTables = new StringBuffer();
        List<String> tables = new ArrayList<String>();
        sbTables.append("-------------- 数据库中有下列的表 ----------<br/>");
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getTables(null, null, null,new String[] { "TABLE" });
            while (rs.next()) {// ///TABLE_TYPE/REMARKS
                sbTables.append("表名：" + rs.getString("TABLE_NAME") + "\r\n");
                sbTables.append("表类型：" + rs.getString("TABLE_TYPE") + "\r\n");
                sbTables.append("表所属数据库：" + rs.getString("TABLE_CAT") + "\r\n");
                sbTables.append("表所属用户名：" + rs.getString("TABLE_SCHEM")+ "\r\n");
                sbTables.append("表备注：" + rs.getString("REMARKS") + "\r\n");
                sbTables.append("------------------------------\r\n");
                tables.add(rs.getString("TABLE_NAME"));
                System.out.println(tables);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 2、遍历数据库表，获取各表的字段等信息
        StringBuffer sbCloumns = new StringBuffer();
        for (String tableName : tables) {
            String sql = "select * from " + tableName;
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData meta = rs.getMetaData();
                int columeCount = meta.getColumnCount();
                sbCloumns.append("表 "+ tableName + "共有 "+columeCount+" 个字段。字段信息如下：<br/>");
                for (int i = 1; i < columeCount + 1; i++) {
                    sbCloumns.append("字段名："+meta.getColumnName(i)+"<br/>");
                    sbCloumns.append("类型："+meta.getColumnType(i)+"<br/>");
                    sbCloumns.append("------------------------------<br/>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sbCloumns.append("------------------------------<br/>");
        }

        System.out.println("" + sbTables.toString());
        System.out.println("" + sbCloumns.toString());
	}

}
