package com.weeds.aoi.utils.officeutils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hslf.model.textproperties.WrapFlagsTextProp;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;

public class PdfOfficeUtils {
	
	public static void main(String[] args) {
		
		String pdfFileName = "D:\\opt\\pdf\\腾讯.pdf";
		String docFileName = "D:\\opt\\pdf\\腾讯.doc";
		String destPdf = "D:\\opt\\pdf\\images\\";
//		pdfTodoc(pdfFileName, docFileName);
//		readImage(pdfFileName, destPdf);
		readImageAndText(pdfFileName, docFileName);
	}
	
	private static void parsePdf(PDDocument doc,PDFTextStripper stripper,int startPage,int endPage,Writer writer,FileOutputStream fos){
		try {
			stripper.setStartPage(startPage);//设置转换的开始页
			stripper.setEndPage(endPage);//设置转换的结束页
			stripper.writeText(doc,writer);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
     * 从pdf文档中读取所有的图片信息
     * 
     * @return
     * @throws Exception 
     */
    public static List<PDImageXObject> getImageListFromPDF(PDDocument document,Integer startPage) throws Exception {
        List<PDImageXObject> imageList = new ArrayList<PDImageXObject>();
        if(null != document){
            PDPageTree pages = document.getPages();
            startPage = startPage == null ? 0 : startPage;
            int len = pages.getCount();
            if(startPage < len){
                for(int i=startPage;i<len;i++){
                    PDPage page = pages.get(i);
                    Iterable<COSName> objectNames = page.getResources().getXObjectNames();
                    for(COSName imageObjectName : objectNames){
                        if(page.getResources().isImageXObject(imageObjectName)){
                            imageList.add((PDImageXObject) page.getResources().getXObject(imageObjectName));
                        }
                    }
                }
            }
        }
        return imageList;
    }
    
    /**
     * 读取图片文件流信息  PDImageXObject转换为普通数据流
     * @param iamge
     * @return
     * @throws Exception 
     */
    private static InputStream getImageInputStream(PDImageXObject iamge){
   	 try {
   		 if(null!=iamge && null!= iamge.getImage()){
   			 BufferedImage bufferImage = iamge.getImage();  
   			 ByteArrayOutputStream os = new ByteArrayOutputStream();  
   			 ImageIO.write(bufferImage, iamge.getSuffix(), os);  
   			 return new ByteArrayInputStream(os.toByteArray());
   		 }
		} catch (Exception e) {
			// TODO: handle exception
		}
        return null;
    }
    
    private static final byte[] input2byte(InputStream inStream) {  
    	try {
    		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
    		byte[] buff = new byte[100];  
    		int rc = 0;  
    		while ((rc = inStream.read(buff, 0, 100)) > 0) {  
    			swapStream.write(buff, 0, rc);  
    		}  
    		byte[] in2b = swapStream.toByteArray();  
    		return in2b;  
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
    }
	
	/**
	 * pdf转 word
	 * 
	 * @param pdfFileName
	 * @param docFileName
	 */
	public static void pdfTodoc(String pdfFileName,String docFileName){
		try {
			File file = new File(pdfFileName);
			
			PDDocument doc=PDDocument.load(file);
			int pagenumber=doc.getNumberOfPages();
			System.out.print("pages"+pagenumber);
			
			FileOutputStream fos=new FileOutputStream(docFileName);
			Writer writer=new OutputStreamWriter(fos,"UTF-8");
			
			PDFTextStripper stripper=new PDFTextStripper();
			
			stripper.setSortByPosition(true);//排序
			stripper.setStartPage(0);//设置转换的开始页
			stripper.setEndPage(pagenumber);//设置转换的结束页
			stripper.writeText(doc,writer);
			
			writer.close();
			doc.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 保存图片
	 */
	public static void readImageAndText(String srcPdf,String docPath){
		
		// 待解析PDF
		File pdfFile = new File(srcPdf);      
		PDDocument document = null,outDoc = null;  
		
		try {
			document = PDDocument.load(pdfFile);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		int pages_size = document.getNumberOfPages();
		
		System.out.println("getAllPages==============="+pages_size); 
		
		if(pages_size>0){
			createBlankDoc(docPath);
		}
		
		int j=0;
		try {
			for(int i=0;i<pages_size;i++) {  
	            PDPage page = document.getPage(i);
				PDResources resources = page.getResources();  
				Iterable xobjects = resources.getXObjectNames();
				if (xobjects != null) {  
					Iterator imageIter = xobjects.iterator();  
					while (imageIter.hasNext()) {  
						COSName key = (COSName) imageIter.next();  
						if(resources.isImageXObject(key)){              
							try {
								PDImageXObject image = (PDImageXObject) resources.getXObject(key);
								
								// 方式一：将PDF文档中的图片 存入word对应的页码中
								InputStream inputStream = getImageInputStream(image);
								addDocData(docPath, inputStream);
	                            System.out.println(image.getSuffix() + ","+image.getHeight() +"," + image.getWidth());
								break;
							} catch (IOException e) {
								e.printStackTrace();
							} 
							//image count
							j++;  
						}                 
					}  
				} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(j);
	} 
	
	/**
	 * 创建空白文档
	 * @param path
	 * @param pages
	 */
	private static void createBlankDoc(String path){
		try {
			//Blank Document
			XWPFDocument document= new XWPFDocument();
			
			//Write the Document in file system
			FileOutputStream out = new FileOutputStream(new File(path));
			
			//XWPFParagraph firstParagraph = document.createParagraph();
			//XWPFRun run = firstParagraph.createRun();
			//run.addBreak(BreakType.PAGE);
			
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
			System.out.println("新文档生成成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 文档追加数据 或页数
	 * @param path
	 * @param pages
	 */
	private static void addDocData(String path,InputStream inputStream){
		try {
			XWPFDocument document = new XWPFDocument(new FileInputStream(new File(path)));
	 
	        //Write the Document in file system
	        FileOutputStream out = new FileOutputStream(new File(path));
	        
	        XWPFParagraph paragraphImg = document.createParagraph();
	        XWPFRun paragraphRunImg = paragraphImg.createRun();
	        //paragraphRunImg.setText("asdfasdfasdf 轩阿嘎的发生的飞洒发");
	        XWPFPicture xp = paragraphRunImg.addPicture(inputStream, 
	        						XWPFDocument.PICTURE_TYPE_JPEG, "1.jpg", 
	        						Units.toEMU(430),Units.toEMU(611));
	        /*CTInline inline = paragraphRunImg.getCTR().addNewDrawing().addNewInline();
	        inline.setDistT(10);*/
	        
	        document.write(out);
	        out.close();
	        System.out.println("文档追加成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 创建足够页数的pdf空文档
	 * @param blankPdfPath
	 * @param page
	 */
	public static void createNewBlankPDF(String blankPdfPath,int page){
		try {
			// Creating PDF document object
	        PDDocument document = new PDDocument();

	        // Add an empty page to it
	        for (int i = 0; i < page; i++) {
	        	document.addPage(new PDPage());
			}
	        // Saving the document
	        document.save(blankPdfPath);
	        System.out.println("PDF created");

	        // Closing the document
	        document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存图片
	 */
	public static void readImage(String srcPdf ,String destForder){

        // 待解析PDF
        File pdfFile = new File(srcPdf);      
        // 空白PDF
//        File pdfFile_out = new File(destPdf);

        PDDocument document = null;  
//        PDDocument document_out = null;  
        try {  
            document = PDDocument.load(pdfFile);  
//            document_out = PDDocument.load(pdfFile_out);  
        } catch (IOException e) {  
            e.printStackTrace();
        }  

        int pages_size = document.getNumberOfPages();

        System.out.println("getAllPages==============="+pages_size);  
        int j=0;

        for(int i=0;i<pages_size;i++) {  
            PDPage page = document.getPage(i);
//            PDPage page1 = document_out.getPage(0);
            PDResources resources = page.getResources();  
            Iterable xobjects = resources.getXObjectNames();
            
            if (xobjects != null) {  
                Iterator imageIter = xobjects.iterator();  
                while (imageIter.hasNext()) {  
                    COSName key = (COSName) imageIter.next();  
                    if(resources.isImageXObject(key)){              
                        try {
                            PDImageXObject image = (PDImageXObject) resources.getXObject(key);

                            // 方式一：将PDF文档中的图片 分别存到一个空白PDF中。
                            /*PDPageContentStream contentStream = new PDPageContentStream(document_out,page1,AppendMode.APPEND,true);

                            float scale = 1f;
                            contentStream.drawImage(image, 20,20,image.getWidth()*scale,image.getHeight()*scale);
                            contentStream.close();
                            document_out.save("d:/opt/pdf/"+j+".pdf");

                            System.out.println(image.getSuffix() + ","+image.getHeight() +"," + image.getWidth());*/

                            // 方式二：将PDF文档中的图片 分别另存为图片。
                            File file = new File(destForder+i+"_"+j+".jpg");
                            FileOutputStream out = new FileOutputStream(file);

//                            InputStream input = image.createInputStream();                   
                            InputStream input = getImageInputStream(image);                   

                            int byteCount = 0;
                            byte[] bytes = new byte[1024];

                            while ((byteCount = input.read(bytes)) > 0)
                            {                       
                                out.write(bytes,0,byteCount);       
                            }

                            out.close();
                            input.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } 
                        //image count
                        j++;  
                    }                 
                }  
            } 
        } 

        System.out.println(j);
    } 
	
	
	/**
	 * 得到pdf中所有文本信息
	 * @param fileName
	 * @return
	 */
	public static String pdftoText(String fileName) {
        PDFParser parser;
        String parsedText = null;
        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        File file = new File(fileName);
        if (!file.isFile()) {
            System.err.println("File " + fileName + " does not exist.");
            return null;
        }
        try {
        	RandomAccessRead randomAccessRead = new RandomAccessBufferedFileInputStream(fileName);
            parser = new PDFParser(randomAccessRead);
        } catch (IOException e) {
            System.err.println("Unable to open PDF Parser. " + e.getMessage());
            return null;
        }
        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
        } catch (Exception e) {
            System.err
                    .println("An exception occured in parsing the PDF Document."
                            + e.getMessage());
        } finally {
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return parsedText;
    }

}
