package com.weeds.aoi.service.utils;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.weeds.aoi.utils.AoiStringUtils;


/**
 * 从数据库表反射出实体类，自动生成实体类
 *
 * @author xuanxy
 */
public class GenEntityMysql {

    private String packageOutPath = "com.weeds.aoi.service.system.domain";//指定实体生成所在包的路径
    private String authorName = "xuanxy";//作者名字
    private String tablename = "short_string";//表名
    private String className = "ShortString";//类名
    private String tableComment = null;//表注释
    private String javaFilePath = "./";
    private String[] colnames; // 列名数组
    private String[] filedNames;//属性名，遵守驼峰规则
    private Map<String, String> columnCommentMap = new HashMap<String, String>();
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组
    private boolean f_util = true; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*
    private boolean f_jpa = true; // 是否需要生成基于注解的JPA实体对象

    //数据库连接
    private static final String URL = "jdbc:mysql://61.129.57.240:33036/aoi";
    private static final String NAME = "aoi";
    private static final String PASS = "sinnrenadmin";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    
    //开始生成dao层mapper对象   
    private String mapperFilePath = javaFilePath;//同一个项目里边
    private String mapperPackageOutPath = "com.wounds.dao";//dao层包路径 
    private String mapperClassName = className+"Mapper";

    private String [] manyMapperFilePath = {"./","./"};//同一个项目里边
    private String [] manyMapperPackageOutPath = {"com.wounds.dao","com.wounds.dao"};//dao层包路径 
    private String [] myBatisPackageOutPath = {"com.wounds.dao","com.wounds.dao"};//dao层包路径 
    //开始生成dao层mapperImpl到不同项目中   
    private String mapperImplClassName = mapperClassName;
    private String jpaImplClassName = className;
    
    //生成xml
    private String xmlName = mapperClassName;
    
    /*
     * 构造函数
     */
    public GenEntityMysql(){
    	
    }
    
    /**
     * 得到实体类对象
     * @throws Exception
     */
    private void genEntity() throws Exception {
    	List<String> list = null;
        if (StringUtils.isEmpty(tablename)) {
            list = getTableName();
        } else {
            list = new ArrayList<String>() {{
                add(tablename);
            }};
        }

        for (int p = 0; p < list.size(); p++) {
            tablename = list.get(p);
            //创建连接
            Connection con;
            //查要生成实体类的表
            String sql = "select * from " + tablename;
            PreparedStatement pStemt = null;
            try {
                try {
                    Class.forName(DRIVER);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                con = DriverManager.getConnection(URL, NAME, PASS);
                getTableComment(con);
                getTableColumnComment(con);
                pStemt = con.prepareStatement(sql);
                ResultSetMetaData rsmd = pStemt.getMetaData();
                int size = rsmd.getColumnCount();    //统计列
                colnames = new String[size];
                filedNames = new String[size];
                colTypes = new String[size];
                colSizes = new int[size];
                for (int i = 0; i < size; i++) {
                    colnames[i] = rsmd.getColumnName(i + 1).toLowerCase();//小写
                    filedNames[i] = AoiStringUtils.underlineToCamelZz(colnames[i]);//转换成属性
                    colTypes[i] = rsmd.getColumnTypeName(i + 1);

                    if (colTypes[i].equalsIgnoreCase("datetime")) {
                        f_util = true;
                    }
                    if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                        f_sql = true;
                    }
                    colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
                }

                String content = parse(colnames, colTypes, colSizes);

                try {
                    String outputPath = javaFilePath + "/src/main/java/" + this.packageOutPath.replace(".", "/") + "/" + initcap(className) + ".java";
                    FileWriter fw = new FileWriter(outputPath);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(content);
                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

            }
        }
        System.out.println("实体类生成完毕！");
    }
    
    
    /**
     * 生成公共mapper对象
     * @throws Exception
     */
    public void genMapper() throws Exception{
    	
    	try {
    		String outputPath = mapperFilePath + "/src/main/java/" + this.mapperPackageOutPath.replace(".", "/") + "/" + initcap(mapperClassName) + ".java";
    		String content = parseMapper();
    		FileWriter fw = new FileWriter(outputPath);
    		PrintWriter pw = new PrintWriter(fw);
    		pw.println(content);
    		pw.flush();
    		pw.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	System.out.println("公共mapper接口生成完毕！");
    }
    
    /**
     * 生成公共mapperImp对象
     * type   1只生成manage项目下的继承    2只生成api下的    3两个项目都生成
     * @throws Exception
     */
    public void genMapperImpl(int type) throws Exception{
    	
    	try {
    		if(type==1){
    			//生成manage项目路径
    			String outputPath = manyMapperFilePath[0] + "/src/main/java/" + this.manyMapperPackageOutPath[0].replace(".", "/") + "/" + initcap(mapperImplClassName) + ".java";
    			String content = parseMapperImpl( this.manyMapperPackageOutPath[0],this.myBatisPackageOutPath[0]);
    			FileWriter fw = new FileWriter(outputPath);
    			PrintWriter pw = new PrintWriter(fw);
    			pw.println(content);
    			pw.flush();
    			pw.close();
    		}
    		if(type==2){
    			//生成api项目路径
    			String outputPath = manyMapperFilePath[1] + "/src/main/java/" + this.manyMapperPackageOutPath[1].replace(".", "/") + "/" + initcap(mapperImplClassName) + ".java";
    			String content = parseMapperImpl( this.manyMapperPackageOutPath[1],this.myBatisPackageOutPath[1]);
    			FileWriter fw = new FileWriter(outputPath);
    			PrintWriter pw = new PrintWriter(fw);
    			pw.println(content);
    			pw.flush();
    			pw.close();
    		}
    		if(type==3){
    			//生成manage项目路径
    			String outputPath = manyMapperFilePath[0] + "/src/main/java/" + this.manyMapperPackageOutPath[0].replace(".", "/") + "/" + initcap(mapperImplClassName) + ".java";
    			String content = parseMapperImpl( this.manyMapperPackageOutPath[0],this.myBatisPackageOutPath[0]);
    			FileWriter fw = new FileWriter(outputPath);
    			PrintWriter pw = new PrintWriter(fw);
    			pw.println(content);
    			pw.flush();
    			pw.close();
    			//生成api项目路径
    			String outputPath1 = manyMapperFilePath[1] + "/src/main/java/" + this.manyMapperPackageOutPath[1].replace(".", "/") + "/" + initcap(mapperImplClassName) + ".java";
    			String content1 = parseMapperImpl( this.manyMapperPackageOutPath[1],this.myBatisPackageOutPath[1]);
    			FileWriter fw1 = new FileWriter(outputPath1);
    			PrintWriter pw1 = new PrintWriter(fw1);
    			pw1.println(content1);
    			pw1.flush();
    			pw1.close();
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	System.out.println("mapperImpl接口生成完毕！");
    }
    /**
     * 生成公共Jap对象
     * type   1只生成manage项目下的继承    2只生成api下的    3两个项目都生成
     * @throws Exception
     */
    public void genJpaImpl(int type) throws Exception{
    	
    	try {
    		if(type==1){
    			//生成manage项目路径
    			String outputPath = manyMapperFilePath[0] + "/src/main/java/" + this.manyMapperPackageOutPath[0].replace(".", "/") + "/" + initcap(jpaImplClassName) + ".java";
    			String content = parseJpaImpl( this.manyMapperPackageOutPath[0]);
    			FileWriter fw = new FileWriter(outputPath);
    			PrintWriter pw = new PrintWriter(fw);
    			pw.println(content);
    			pw.flush();
    			pw.close();
    		}
    		if(type==2){
    			//生成api项目路径
    			String outputPath = manyMapperFilePath[1] + "/src/main/java/" + this.manyMapperPackageOutPath[1].replace(".", "/") + "/" + initcap(jpaImplClassName) + ".java";
    			String content = parseJpaImpl( this.manyMapperPackageOutPath[1]);
    			FileWriter fw = new FileWriter(outputPath);
    			PrintWriter pw = new PrintWriter(fw);
    			pw.println(content);
    			pw.flush();
    			pw.close();
    		}
    		if(type==3){
    			//生成manage项目路径
    			String outputPath = manyMapperFilePath[0] + "/src/main/java/" + this.manyMapperPackageOutPath[0].replace(".", "/") + "/" + initcap(jpaImplClassName) + ".java";
    			String content = parseJpaImpl( this.manyMapperPackageOutPath[0]);
    			FileWriter fw = new FileWriter(outputPath);
    			PrintWriter pw = new PrintWriter(fw);
    			pw.println(content);
    			pw.flush();
    			pw.close();
    			//生成api项目路径
    			String outputPath1 = manyMapperFilePath[1] + "/src/main/java/" + this.manyMapperPackageOutPath[1].replace(".", "/") + "/" + initcap(jpaImplClassName) + ".java";
    			String content1 = parseJpaImpl( this.manyMapperPackageOutPath[1]);
    			FileWriter fw1 = new FileWriter(outputPath1);
    			PrintWriter pw1 = new PrintWriter(fw1);
    			pw1.println(content1);
    			pw1.flush();
    			pw1.close();
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	System.out.println("jpa接口生成完毕！");
    }
    
    /**
     * 生成公共mapperImp xml对象
     * type   1只生成manage项目下的继承    2只生成api下的    3两个项目都生成
     * @throws Exception
     */
    public void genMapperXml(int type) throws Exception{
    	
    	try {
    		if(type==1){
    			//生成manage项目路径
        		String outputPath = manyMapperFilePath[0] + "/src/main/resources/mybatis/"+xmlName+".xml";
        		String content = parseXml(this.manyMapperPackageOutPath[0]+"."+mapperImplClassName);
        		FileWriter fw = new FileWriter(outputPath);
        		PrintWriter pw = new PrintWriter(fw);
        		pw.println(content);
        		pw.flush();
        		pw.close();
    		}
    		if(type==2){
    			//生成api项目路径
    			String outputPath = manyMapperFilePath[1] + "/src/main/resources/mybatis/"+xmlName+".xml";
        		String content = parseXml( this.manyMapperPackageOutPath[1]+"."+mapperImplClassName);
        		FileWriter fw = new FileWriter(outputPath);
        		PrintWriter pw = new PrintWriter(fw);
        		pw.println(content);
        		pw.flush();
        		pw.close();
    		}
    		if(type==3){
    			//生成manage项目路径
    			String outputPath = manyMapperFilePath[0] + "/src/main/resources/mybatis/"+xmlName+".xml";
        		String content = parseXml(this.manyMapperPackageOutPath[0]+"."+mapperImplClassName);
        		FileWriter fw = new FileWriter(outputPath);
        		PrintWriter pw = new PrintWriter(fw);
        		pw.println(content);
        		pw.flush();
        		pw.close();
    			//生成api项目路径
    			String outputPath1 = manyMapperFilePath[1] + "/src/main/resources/mybatis/"+xmlName+".xml";
        		String content1 = parseXml( this.manyMapperPackageOutPath[1]+"."+mapperImplClassName);
        		FileWriter fw1 = new FileWriter(outputPath1);
        		PrintWriter pw1 = new PrintWriter(fw1);
        		pw1.println(content1);
        		pw1.flush();
        		pw1.close();
    		}
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	 System.out.println("xml接口生成完毕！");
    }

    /**
     * Java方法 得到当前数据库下所有的表名
     */
    private List<String> getTableName() {
        List<String> list = new ArrayList<String>();
        try {
            DatabaseMetaData meta = DriverManager.getConnection(URL, NAME, PASS).getMetaData();
            ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                list.add(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取表的注释
     *
     * @param con
     * @throws Exception
     */
    private void getTableComment(Connection con) throws Exception {
        String sql = "show table status where name = '" + tablename + "'";
        ResultSet resultSet = con.createStatement().executeQuery(sql);
        if (resultSet.next()) {
            tableComment = resultSet.getString("Comment");
        }
    }

    /**
     * 获取表的注释
     *
     * @param con
     * @throws Exception
     */
    private void getTableColumnComment(Connection con) throws Exception {
        String sql = "show full fields from " + tablename;
        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            columnCommentMap.put(resultSet.getString("Field").toLowerCase(), resultSet.getString("Comment"));
        }
    }
    
    /**
     * 功能：生成实体类主体代码
     *
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
    	StringBuffer sb = new StringBuffer();
    	sb.append("package " + this.packageOutPath + ";\r\n");
    	sb.append("\r\n");
    	
    	//判断是否导入工具包
    	if (f_util) {
    		sb.append("import java.util.Date;\r\n");
    	}
    	if (f_sql) {
    		sb.append("import java.sql.*;\r\n");
    	}
    	
    	//jpa
    	if (f_jpa) {
    		sb.append("import javax.persistence.*;\r\n");
    		sb.append("\r\n");
    		sb.append("import org.hibernate.annotations.GenericGenerator;\r\n");
    		sb.append("\r\n");
    		sb.append("import com.fasterxml.jackson.annotation.JsonFormat; \r\n");
    	}
    	
    	//注释部分
    	sb.append("/**\r\n");
    	sb.append(" * " + tablename + " 实体类\r\n");
    	if (StringUtils.isNotEmpty(tableComment)) {
    		sb.append(" * " + tableComment + "\r\n");
    	}
    	//GenEntityMysql
    	sb.append(" * 由GenEntityMysql类自动生成\r\n");
    	sb.append(" * " + new Date() + "\r\n");
    	sb.append(" * @" + this.authorName + "\r\n");
    	sb.append(" */ \r\n");
    	
    	if (f_jpa) {
    		sb.append("@Entity\r\n");
    		sb.append("@Table(name=\"" + tablename + "\")\r\n");
    	}
    	//实体部分
    	//类名稍微改造 ,去掉第一个单词
    	sb.append("public class " + initcap(className) + " {\r\n\r\n");
    	processAllAttrs(sb);//属性
    	processAllMethod(sb);//get set方法
    	sb.append("}\r\n");
    	
    	//System.out.println(sb.toString());
    	return sb.toString();
    }
    
    /**
     * Mapper功能：生成实体类主体代码
     *
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parseMapper() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("package " + this.mapperPackageOutPath + ";\r\n");
    	sb.append("\r\n");
    	sb.append("import java.util.List;\r\n");
    	sb.append("import java.util.Map;\r\n");
    	sb.append("import "+this.myBatisPackageOutPath[0]+".MyBatisRepository; \r\n");
    	sb.append("\r\n");
    	sb.append("import "+this.packageOutPath+"."+className+"; \r\n");
    	
    	//注释部分
    	sb.append("/**\r\n");
    	sb.append(" * mapper接口公共接口\r\n");
    	//GenEntityMysql
    	sb.append(" * 由GenEntityMysql类自动生成\r\n");
    	sb.append(" * " + new Date() + "\r\n");
    	sb.append(" * @" + this.authorName + "\r\n");
    	sb.append(" */ \r\n");
    	
    	//接口部分
    	//类名稍微改造 ,去掉第一个单词
    	sb.append("@MyBatisRepository\r\n");
    	sb.append("public interface " + initcap(mapperClassName) + " {\r\n\r\n");
    	
    	sb.append("	/**\r\n");
    	sb.append("	* 根据条件获取所有"+className+"集合\r\n");
    	sb.append("	*/ \r\n");
    	sb.append("\tList<"+className+">  get"+className+"List(Map<String, Object> parameters);\r\n");
    	sb.append("\r\n");
    	sb.append("\r\n");
    	sb.append("	/**\r\n");
    	sb.append("	* 根据条件获取所有"+className+"总数\r\n");
    	sb.append("	*/ \r\n");
    	sb.append("\tint  get"+className+"Count(Map<String, Object> parameters);\r\n");
    	
    	sb.append("}\r\n");
    	
    	//System.out.println(sb.toString());
    	return sb.toString();
    }
    
    /**
     * Mapper功能：生成实体类主体代码
     *
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parseMapperImpl(String packageOutPath,String myBatisPath) {
    	StringBuffer sb = new StringBuffer();
    	sb.append("package " + packageOutPath + ";\r\n");
    	sb.append("\r\n");
    	sb.append("\r\n");
    	sb.append("import "+myBatisPath+".MyBatisRepository; \r\n");
    	sb.append("import "+mapperPackageOutPath+"."+mapperClassName+"; \r\n");
    	
    	//注释部分
    	sb.append("/**\r\n");
    	sb.append(" * mapperImpl扫描接口\r\n");
    	//GenEntityMysql
    	sb.append(" * 由GenEntityMysql类自动生成\r\n");
    	sb.append(" * " + new Date() + "\r\n");
    	sb.append(" * @" + this.authorName + "\r\n");
    	sb.append(" */ \r\n");
    	
    	//接口部分
    	//类名稍微改造 ,去掉第一个单词
    	sb.append("@MyBatisRepository\r\n");
    	sb.append("public interface " + initcap(mapperImplClassName) + " extends "+mapperClassName+" {\r\n\r\n");
    	sb.append("}\r\n");
    	
    	System.out.println("生成"+packageOutPath+"完毕!");
    	return sb.toString();
    }

    /**
     * Jpa功能：生成实体类主体代码
     *
     * @return
     */
    private String parseJpaImpl(String packageOutPath) {
    	StringBuffer sb = new StringBuffer();
    	sb.append("package " + packageOutPath + ";\r\n");
    	sb.append("\r\n");
    	sb.append("\r\n");
    	sb.append("import org.springframework.data.repository.PagingAndSortingRepository; \r\n");
    	sb.append("import org.springframework.data.jpa.repository.JpaSpecificationExecutor; \r\n");
    	sb.append("\r\n");
    	sb.append("\r\n");
    	sb.append("import "+this.packageOutPath+"."+className+"; \r\n");
    	
    	//注释部分
    	sb.append("/**\r\n");
    	sb.append(" * jpa扫描接口\r\n");
    	//GenEntityMysql
    	sb.append(" * 由GenEntityMysql类自动生成\r\n");
    	sb.append(" * " + new Date() + "\r\n");
    	sb.append(" * @" + this.authorName + "\r\n");
    	sb.append(" */ \r\n");
    	
    	//接口部分
    	//类名稍微改造 ,去掉第一个单词
    	sb.append("public interface " + initcap(jpaImplClassName) + " extends PagingAndSortingRepository<"+className+",String>,JpaSpecificationExecutor<"+className+">  {\r\n\r\n");
    	sb.append("}\r\n");
    	
    	System.out.println("生成"+packageOutPath+"完毕!");
    	return sb.toString();
    }
    
    /**
     * xml功能：生成实体类主体代码
     *
     * @return
     */
    private String parseXml(String namespace) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
        sb.append("\r\n");
    	sb.append("<!--由GenEntityMysql类自动生成" + new Date() + "@" + this.authorName + " -->");
    	sb.append("\r\n");
    	sb.append("\r\n");
    	sb.append("<mapper namespace=\""+namespace+"\">");
    	sb.append("\r\n");
    	sb.append("\r\n");
    	sb.append("   <!-- 查询集合 -->\r\n");
    	sb.append("   <select id=\"get"+className+"List\" parameterType=\"map\" resultType=\""+className+"\">\r\n");
    	sb.append("       select * from "+tablename +" where 1=1 order by create_time desc \r\n");
    	sb.append("       <if test=\"begin != null\">\r\n");
    	sb.append("           limit #{begin},#{end}\r\n\r\n");
    	sb.append("       </if>\r\n");
    	sb.append("   </select>\r\n");
    	sb.append("\r\n");
    	sb.append("\r\n");
    	sb.append("   <!-- 查询集合总数 -->\r\n");
    	sb.append("   <select id=\"get"+className+"Count\" parameterType=\"map\" resultType=\"int\">\r\n");
    	sb.append("       select count(1) from "+tablename +" where 1=1 \r\n");
    	sb.append("   </select>\r\n");
    	sb.append("\r\n");
    	sb.append("\r\n");
        sb.append("</mapper>\r\n");

        System.out.println("生成"+namespace+"完毕!");
        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            String comment = columnCommentMap.get(colnames[i]);
            if (StringUtils.isNotEmpty(comment)) {
                sb.append("	/**\r\n");
                sb.append("	* " + comment + "\r\n");
                sb.append("	*/ \r\n");
            }
            if (filedNames[i].equals("id")) {
                sb.append("	@Id\r\n" +
                		"	@GeneratedValue(generator = \"uuid\")\r\n" +
                        "	@GenericGenerator(name = \"uuid\", strategy = \"uuid\")\r\n");
            } else {
                sb.append("	@Column(name=\"" + colnames[i] + "\")\r\n");
            }
            if (colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("timestamp")) {
                sb.append("	@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"GMT+08:00\")\r\n");
            }
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + filedNames[i] + ";\r\n\r\n");
        }
        sb.append("\r\n");
    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(filedNames[i]) + "(){\r\n");
            sb.append("\t\treturn " + filedNames[i] + ";\r\n");
            sb.append("\t}\r\n\r\n");
            sb.append("\tpublic void set" + initcap(filedNames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " + filedNames[i] + "){\r\n");
            sb.append("\t\tthis." + filedNames[i] + "=" + filedNames[i] + ";\r\n");
            sb.append("\t}\r\n\r\n");
        }

    }

    /**
     * 将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {
        //第2个字母就是大写字母,第1个字母是小写字母，则直接返回原字符串
        if (str.length() > 1 && Character.isUpperCase(str.charAt(1)) && Character.isLowerCase(str.charAt(0))) {
            return str;
        }
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INT UNSIGNED")) {
            //INT UNSIGNED无符号整形
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney") || sqlType.equalsIgnoreCase("double")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("timestamp")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        } else if (sqlType.equalsIgnoreCase("binary") || sqlType.equalsIgnoreCase("varbinary")) {
            return "byte[]";
        }
        return null;
    }

    /**
     * 出口
     * TODO
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
    	GenEntityMysql gem =  new GenEntityMysql();
    	gem.genEntity();
    	/*gem.genMapper();
    	gem.genJpaImpl(1);
    	gem.genMapperXml(1);*/
    }

	public String getPackageOutPath() {
		return packageOutPath;
	}

	public void setPackageOutPath(String packageOutPath) {
		this.packageOutPath = packageOutPath;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getJavaFilePath() {
		return javaFilePath;
	}

	public void setJavaFilePath(String javaFilePath) {
		this.javaFilePath = javaFilePath;
	}

	public String[] getColnames() {
		return colnames;
	}

	public void setColnames(String[] colnames) {
		this.colnames = colnames;
	}

	public String[] getFiledNames() {
		return filedNames;
	}

	public void setFiledNames(String[] filedNames) {
		this.filedNames = filedNames;
	}

	public Map<String, String> getColumnCommentMap() {
		return columnCommentMap;
	}

	public void setColumnCommentMap(Map<String, String> columnCommentMap) {
		this.columnCommentMap = columnCommentMap;
	}

	public String[] getColTypes() {
		return colTypes;
	}

	public void setColTypes(String[] colTypes) {
		this.colTypes = colTypes;
	}

	public int[] getColSizes() {
		return colSizes;
	}

	public void setColSizes(int[] colSizes) {
		this.colSizes = colSizes;
	}

	public boolean isF_util() {
		return f_util;
	}

	public void setF_util(boolean f_util) {
		this.f_util = f_util;
	}

	public boolean isF_sql() {
		return f_sql;
	}

	public void setF_sql(boolean f_sql) {
		this.f_sql = f_sql;
	}

	public boolean isF_jpa() {
		return f_jpa;
	}

	public void setF_jpa(boolean f_jpa) {
		this.f_jpa = f_jpa;
	}

	public String getMapperFilePath() {
		return mapperFilePath;
	}

	public void setMapperFilePath(String mapperFilePath) {
		this.mapperFilePath = mapperFilePath;
	}

	public String getMapperPackageOutPath() {
		return mapperPackageOutPath;
	}

	public void setMapperPackageOutPath(String mapperPackageOutPath) {
		this.mapperPackageOutPath = mapperPackageOutPath;
	}

	public String getMapperClassName() {
		return mapperClassName;
	}

	public void setMapperClassName(String mapperClassName) {
		this.mapperClassName = mapperClassName;
	}

	public String[] getManyMapperFilePath() {
		return manyMapperFilePath;
	}

	public void setManyMapperFilePath(String[] manyMapperFilePath) {
		this.manyMapperFilePath = manyMapperFilePath;
	}

	public String[] getManyMapperPackageOutPath() {
		return manyMapperPackageOutPath;
	}

	public void setManyMapperPackageOutPath(String[] manyMapperPackageOutPath) {
		this.manyMapperPackageOutPath = manyMapperPackageOutPath;
	}

	public String[] getMyBatisPackageOutPath() {
		return myBatisPackageOutPath;
	}

	public void setMyBatisPackageOutPath(String[] myBatisPackageOutPath) {
		this.myBatisPackageOutPath = myBatisPackageOutPath;
	}

	public String getMapperImplClassName() {
		return mapperImplClassName;
	}

	public void setMapperImplClassName(String mapperImplClassName) {
		this.mapperImplClassName = mapperImplClassName;
	}

	public String getJpaImplClassName() {
		return jpaImplClassName;
	}

	public void setJpaImplClassName(String jpaImplClassName) {
		this.jpaImplClassName = jpaImplClassName;
	}

	public String getXmlName() {
		return xmlName;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}

	public static String getUrl() {
		return URL;
	}

	public static String getName() {
		return NAME;
	}

	public static String getPass() {
		return PASS;
	}

	public static String getDriver() {
		return DRIVER;
	}
}