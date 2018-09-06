package com.weeds.aoi.utils.officeutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;


public class WordTest {
	
	public static void main(String[] args) {
		
		String templatePath = "D:/opt/pdf/template.doc";  
//		writeNewDoc(templatePath);
		createBlankDoc(templatePath, 2);
		addDocData(templatePath);
//		readDoc(templatePath);
	}
	
	/**
	 * 创建空白文档
	 * @param path
	 * @param pages
	 */
	private static void createBlankDoc(String path, int pages){
		try {
			//Blank Document
			XWPFDocument document= new XWPFDocument();
			
			//Write the Document in file system
			FileOutputStream out = new FileOutputStream(new File(path));
			
			for (int i = 0; i < pages; i++) {
				XWPFParagraph firstParagraph = document.createParagraph();
				XWPFRun run = firstParagraph.createRun();
				run.addBreak(BreakType.PAGE);
			}
			CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
			XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
			
			//添加页眉
			CTP ctpHeader = CTP.Factory.newInstance();
			CTR ctrHeader = ctpHeader.addNewR();
			CTText ctHeader = ctrHeader.addNewT();
			String headerText = "W E E D S";
			ctHeader.setStringValue(headerText);
			XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
			//设置为右对齐
			headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
			XWPFParagraph[] parsHeader = new XWPFParagraph[1];
			parsHeader[0] = headerParagraph;
			policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
			
			
			//添加页脚
			CTP ctpFooter = CTP.Factory.newInstance();
			CTR ctrFooter = ctpFooter.addNewR();
			CTText ctFooter = ctrFooter.addNewT();
			String footerText = "http://www.xxyweeds.top";
			ctFooter.setStringValue(footerText);
			XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
			headerParagraph.setAlignment(ParagraphAlignment.CENTER);
			XWPFParagraph[] parsFooter = new XWPFParagraph[1];
			parsFooter[0] = footerParagraph;
			policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
			document.write(out);
			out.close();
			System.out.println("新文档生成成功,共"+pages+"页");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 文档追加数据 或页数
	 * @param path
	 * @param pages
	 */
	private static void addDocData(String path){
		try {
			XWPFDocument document = new XWPFDocument(new FileInputStream(new File(path)));
	 
	        //Write the Document in file system
	        FileOutputStream out = new FileOutputStream(new File(path));
	        
	        XWPFParagraph firstParagraph = document.createParagraph();
	        XWPFRun run = firstParagraph.createRun();
	        run.setText("new add");
	        //run.addBreak(BreakType.PAGE);
	        
	        document.write(out);
	        out.close();
	        System.out.println("文档追加成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 读取word 内容
	 * @param word
	 */
	public static void readDoc(String word){
		try {
			/*XWPFDocument doc = new XWPFDocument(new FileInputStream(new File(word)));
			XWPFWordExtractor extractor = new XWPFWordExtractor(doc) ;
			int pages = doc.getProperties().getExtendedProperties().getPages();
			System.out.println("总页数："+pages + ",内容体："+extractor.getText());*/
			
			XWPFDocument doc = new XWPFDocument(new FileInputStream(new File(word)));
			doc.addPictureData(FileUtils.readFileToByteArray(new File("D:/opt/pdf/1.jpg")), XWPFDocument.PICTURE_TYPE_JPEG);
			
			
			FileOutputStream out = new FileOutputStream(new File(word));
			doc.write(out);
	        out.close();
			
			
	        /*Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
			XWPFParagraph para;
			int count=1;
			while (iterator.hasNext()) {
				para = iterator.next();

				if(count==2){//第二页插入数据
					replaceInPara(para, null);
				}
				++count;
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*//区分doc 和 docx类型
		try {
		    InputStream is = new FileInputStream(new File("2003.doc"));
		    WordExtractor ex = new WordExtractor(is);
		    String text2003 = ex.getText();
		    System.out.println(text2003);
		
		    OPCPackage opcPackage = POIXMLDocument.openPackage("2007.docx");
		    POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
		    String text2007 = extractor.getText();
		    System.out.println(text2007);
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}*/
	}
	
	/**
	 * 替换段落里面的变量或追加内容
	 * 
	 * @param para
	 *            要替换的段落
	 * @param params
	 *            参数
	 */
	private static void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
		List<XWPFRun> runs;
		runs = para.getRuns();
		for (int i = 0; i < runs.size(); i++) {
			para.insertNewRun(i).setText("insert 轩");
		}
	}
	
	/**
	 * 新建并写入word
	 * @param templatePath
	 */
	public static void writeNewDoc(String templatePath){
			try {
				
				 //Blank Document
		        XWPFDocument document= new XWPFDocument();
		 
		        //Write the Document in file system
		        FileOutputStream out = new FileOutputStream(new File(templatePath));
		 
		 
		        //添加标题
		        XWPFParagraph titleParagraph = document.createParagraph();
		        //设置段落居中
		        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
		 
		        XWPFRun titleParagraphRun = titleParagraph.createRun();
		        titleParagraphRun.setText("Java PoI");
		        titleParagraphRun.setColor("000000");
		        titleParagraphRun.setFontSize(20);
		 
		 
		        //段落
		        XWPFParagraph firstParagraph = document.createParagraph();
		        XWPFRun run = firstParagraph.createRun();
		        run.setText("Java POI 生成word文件。");
		        run.setColor("696969");
		        run.setFontSize(16);
		 
		        //设置段落背景颜色
		        CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
		        cTShd.setVal(STShd.CLEAR);
		        cTShd.setFill("97FFFF");
		 
		 
		        //换行
		        XWPFParagraph paragraph1 = document.createParagraph();
		        XWPFRun paragraphRun1 = paragraph1.createRun();
		        paragraphRun1.setText("\r");
		 
		 
		        //基本信息表格
		        XWPFTable infoTable = document.createTable();
		        //去表格边框
		        infoTable.getCTTbl().getTblPr().unsetTblBorders();
		 
		 
		        //列宽自动分割
		        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
		        infoTableWidth.setType(STTblWidth.DXA);
		        infoTableWidth.setW(BigInteger.valueOf(9072));
		 
		 
		        //表格第一行
		        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
		        infoTableRowOne.getCell(0).setText("职位");
		        infoTableRowOne.addNewTableCell().setText(": Java 开发工程师");
		 
		        //表格第二行
		        XWPFTableRow infoTableRowTwo = infoTable.createRow();
		        infoTableRowTwo.getCell(0).setText("姓名");
		        infoTableRowTwo.getCell(1).setText(": seawater");
		 
		        //表格第三行
		        XWPFTableRow infoTableRowThree = infoTable.createRow();
		        infoTableRowThree.getCell(0).setText("生日");
		        infoTableRowThree.getCell(1).setText(": xxx-xx-xx");
		 
		        //表格第四行
		        XWPFTableRow infoTableRowFour = infoTable.createRow();
		        infoTableRowFour.getCell(0).setText("性别");
		        infoTableRowFour.getCell(1).setText(": 男");
		 
		        //表格第五行
		        XWPFTableRow infoTableRowFive = infoTable.createRow();
		        infoTableRowFive.getCell(0).setText("现居地");
		        infoTableRowFive.getCell(1).setText(": xx");
		 
		 
		        //两个表格之间加个换行
		        XWPFParagraph paragraph = document.createParagraph();
		        XWPFRun paragraphRun = paragraph.createRun();
		        paragraphRun.setText("\r");
		        
		        
		        
		        XWPFParagraph paragraphImg = document.createParagraph();
		        XWPFRun paragraphRunImg = paragraphImg.createRun();
		        paragraphRunImg.setText("asdfasdfasdf 轩阿嘎的发生的飞洒发");
		        paragraphRunImg.addPicture(new FileInputStream(new File("d:/opt/pdf/1.jpg")), 
		        						XWPFDocument.PICTURE_TYPE_JPEG, "1.jpg", 
		        						Units.toEMU(200),Units.toEMU(200));
		        
//		        paragraphRunImg.addBreak(BreakType.PAGE);
		        
		        XWPFParagraph paragraphI = document.createParagraph();
		        XWPFRun paragraphRunI = paragraphI.createRun();
		        paragraphRunI.setText("\r");
		 
		 
		        //工作经历表格
		        XWPFTable ComTable = document.createTable();
		 
		 
		        //列宽自动分割
		        CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();
		        comTableWidth.setType(STTblWidth.DXA);
		        comTableWidth.setW(BigInteger.valueOf(9072));
		 
		        //表格第一行
		        XWPFTableRow comTableRowOne = ComTable.getRow(0);
		        comTableRowOne.getCell(0).setText("开始时间");
		        comTableRowOne.addNewTableCell().setText("结束时间");
		        comTableRowOne.addNewTableCell().setText("公司名称");
		        comTableRowOne.addNewTableCell().setText("title");
		 
		        //表格第二行
		        XWPFTableRow comTableRowTwo = ComTable.createRow();
		        comTableRowTwo.getCell(0).setText("2016-09-06");
		        comTableRowTwo.getCell(1).setText("至今");
		        comTableRowTwo.getCell(2).setText("seawater");
		        comTableRowTwo.getCell(3).setText("Java开发工程师");
		 
		 
		        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
		 
		        //添加页眉
		        CTP ctpHeader = CTP.Factory.newInstance();
		        CTR ctrHeader = ctpHeader.addNewR();
		        CTText ctHeader = ctrHeader.addNewT();
		        String headerText = "W E E D S";
		        ctHeader.setStringValue(headerText);
		        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
		        //设置为右对齐
		        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
		        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
		        parsHeader[0] = headerParagraph;
		        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
		 
		 
		        //添加页脚
		        CTP ctpFooter = CTP.Factory.newInstance();
		        CTR ctrFooter = ctpFooter.addNewR();
		        CTText ctFooter = ctrFooter.addNewT();
		        String footerText = "http://www.xxyweeds.top";
		        ctFooter.setStringValue(footerText);
		        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
		        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
		        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
		        parsFooter[0] = footerParagraph;
		        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
		 
		 
		        document.write(out);
		        out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
	  }  
	    

}
