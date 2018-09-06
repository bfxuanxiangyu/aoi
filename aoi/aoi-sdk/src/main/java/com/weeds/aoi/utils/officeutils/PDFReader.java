package com.weeds.aoi.utils.officeutils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

/** 
 * 
 * @author Angela 
 */
public class PDFReader {
	/**  
     * 获取格式化后的时间信息  
     * @param calendar   时间信息  
     * @return     
     * */
	 public static  String dateFormat( Calendar calendar ){    
        if( null == calendar )     return null;    
        String date = null;      
        String pattern = "yyyy-MM-dd HH:mm:ss";    
        SimpleDateFormat format = new SimpleDateFormat( pattern );    
        date = format.format( calendar.getTime() );    
        return date == null ? "" : date;    
    }    
  
        /**打印纲要**/
	 public static void  getPDFOutline(String file){  
        try {    
            //打开pdf文件流  
            FileInputStream fis = new   FileInputStream(file);  
            //加载 pdf 文档,获取PDDocument文档对象  
            PDDocument document=PDDocument.load(fis);  
            //获取PDDocumentCatalog文档目录对象  
            PDDocumentCatalog catalog=document.getDocumentCatalog();  
            //获取PDDocumentOutline文档纲要对象  
            PDDocumentOutline outline=catalog.getDocumentOutline();  
            //获取第一个纲要条目（标题1）  
            PDOutlineItem item=outline.getFirstChild();  
            if(outline!=null){  
                //遍历每一个标题1
            	while(item!=null){  
                    //打印标题1的文本  
                    System.out.println("Item:"+item.getTitle());  
                    //获取标题1下的第一个子标题（标题2）  
                    PDOutlineItem child=item.getFirstChild();   
                    //遍历每一个标题2
                    while(child!=null){  
                        //打印标题2的文本  
                        System.out.println("    Child:"+child.getTitle());  
                        //指向下一个标题2  
                        child=child.getNextSibling();  
                    }  
                    //指向下一个标题1  
                    item=item.getNextSibling();  
                }  
            }  
            //关闭输入流  
            document.close();  
            fis.close();  
        } catch (FileNotFoundException ex) {  
            ex.printStackTrace(); 
        } catch (IOException ex) {  
        	ex.printStackTrace();
        }   
    }  
  
    /**打印一级目录**/
	public static void  getPDFCatalog(String file){  
        try {    
            //打开pdf文件流  
            FileInputStream fis = new   FileInputStream(file);  
            //加载 pdf 文档,获取PDDocument文档对象  
            PDDocument document=PDDocument.load(fis);  
            //获取PDDocumentCatalog文档目录对象  
            PDDocumentCatalog catalog=document.getDocumentCatalog();  
            //获取PDDocumentOutline文档纲要对象  
            PDDocumentOutline outline=catalog.getDocumentOutline();  
            //获取第一个纲要条目（标题1）
            if(outline!=null){  
                PDOutlineItem item=outline.getFirstChild();  
                //遍历每一个标题1
                while(item!=null){  
                    //打印标题1的文本  
                    System.out.println("Item:"+item.getTitle());                 
                    //指向下一个标题1  
                    item=item.getNextSibling();  
                }  
            }  
            //关闭输入流  
            document.close();  
            fis.close();  
        } catch (FileNotFoundException ex) {  
        	ex.printStackTrace();
        } catch (IOException ex) {  
        	ex.printStackTrace();
        }   
    }  
  
    /**获取PDF文档元数据**/
    public static void  getPDFInformation(String file){  
        try {    
            //打开pdf文件流  
            FileInputStream fis = new   FileInputStream(file);  
            //加载 pdf 文档,获取PDDocument文档对象  
            PDDocument document=PDDocument.load(fis);  
            /** 文档属性信息 **/            
            PDDocumentInformation info = document.getDocumentInformation();   
  
            System.out.println("页数:"+document.getNumberOfPages());  
  
            System.out.println( "标题:" + info.getTitle() );    
            System.out.println( "主题:" + info.getSubject() );    
            System.out.println( "作者:" + info.getAuthor() );    
            System.out.println( "关键字:" + info.getKeywords() );               
  
            System.out.println( "应用程序:" + info.getCreator() );    
            System.out.println( "pdf 制作程序:" + info.getProducer() );    
  
            System.out.println( "Trapped:" + info.getTrapped() );    
  
            System.out.println( "创建时间:" + dateFormat( info.getCreationDate() ));    
            System.out.println( "修改时间:" + dateFormat( info.getModificationDate()));    
  
            //关闭输入流  
            document.close();  
            fis.close();  
        } catch (FileNotFoundException ex) {  
            ex.printStackTrace();  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }   
    }  
  
  
     public static void pdfSaveImage(String file, String imgSavePath) {  
         try {  
             // 打开pdf文件流  
             FileInputStream fis = new FileInputStream(file);  
             // 加载 pdf 文档,获取PDDocument文档对象  
             PDDocument document = PDDocument.load(fis);  
             /** 文档页面信息 **/// 获取PDDocumentCatalog文档目录对象  
             PDDocumentCatalog catalog = document.getDocumentCatalog();  
             // 获取文档页面PDPage列表  
             int pages = document.getNumberOfPages();  
             int count = 1;  
             for (int j = 1; j < pages; j++) {  
                 PDPage page = document.getPage(j);  
                 PDResources resources = page.getResources();  
                 Iterable xobjects = resources.getXObjectNames();  
                 if (xobjects != null) {  
                     Iterator imageIter = xobjects.iterator();  
                     while (imageIter.hasNext()) {  
                         COSName key = (COSName) imageIter.next();  
                         if (resources.isImageXObject(key)) {  
                             try {  
                                 PDImageXObject image = (PDImageXObject) resources.getXObject(key);  
                                 BufferedImage bimage = image.getImage();  
                                 ImageIO.write(bimage, "jpg", new File(imgSavePath + j+"_"+count + ".jpg"));  
                                 count++;  
                                 System.out.println(count);  
                             } catch (Exception e) {  
                             }  
                         }  
   
                     }  
                 }  
             }  
//           document.close();  
//           fis.close();  
   
         } catch (Exception e) {  
             System.out.println();  
         }  
      }  
   
           
   
     public static void readImage() {  
   
         // 待解析PDF  
         File pdfFile = new File("C:\\Users\\weidx\\Documents\\My Access-IS Data\\PDFs\\in.pdf");  
         // 空白PDF  
         File pdfFile_out = new File("C:\\Users\\weidx\\Documents\\My Access-IS Data\\PDFs\\out.pdf");  
   
         PDDocument document = null;  
         PDDocument document_out = null;  
         try {  
             document = PDDocument.load(pdfFile);  
             document_out = PDDocument.load(pdfFile_out);  
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
   
         int pages_size = document.getNumberOfPages();  
   
         System.out.println("getAllPages===============" + pages_size);  
         int j = 0;  
   
         for (int i = 0; i < pages_size; i++) {  
             PDPage page = document.getPage(i);  
             PDPage page1 = document_out.getPage(0);  
             PDResources resources = page.getResources();  
             Iterable xobjects = resources.getXObjectNames();  
   
             if (xobjects != null) {  
                 Iterator imageIter = xobjects.iterator();  
                 while (imageIter.hasNext()) {  
                     COSName key = (COSName) imageIter.next();  
                     if (resources.isImageXObject(key)) {  
                         try {  
                             PDImageXObject image = (PDImageXObject) resources.getXObject(key);  
   
                             // 方式一：将PDF文档中的图片 分别存到一个空白PDF中。  
                             PDPageContentStream contentStream = new PDPageContentStream(document_out, page1, AppendMode.APPEND,  
                                     true);  
   
                             float scale = 1f;  
                             contentStream.drawImage(image, 20, 20, image.getWidth() * scale, image.getHeight() * scale);  
                             contentStream.close();  
                             document_out.save("C:\\Users\\weidx\\Documents\\My Access-IS Data\\PDFs\\" + j + ".pdf");  
   
                             System.out.println(image.getSuffix() + "," + image.getHeight() + "," + image.getWidth());  
   
                             /** 
                              * // 方式二：将PDF文档中的图片 分别另存为图片。 File file = new 
                              * File("/Users/xiaolong/Downloads/123"+j+".png"); 
                              * FileOutputStream out = new 
                              * FileOutputStream(file); 
                              *  
                              * InputStream input = image.createInputStream(); 
                              *  
                              * int byteCount = 0; byte[] bytes = new byte[1024]; 
                              *  
                              * while ((byteCount = input.read(bytes)) > 0) { 
                              * out.write(bytes,0,byteCount); } 
                              *  
                              * out.close(); input.close(); 
                              **/  
   
                         } catch (IOException e) {  
                             e.printStackTrace();  
                         }  
                         j++;  
                     }  
                 }  
             }  
         }  
         System.out.println(j);  
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
     public static InputStream getImageInputStream(PDImageXObject iamge){
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
  
  
    public static void  main(String args[]){  
        /*String file="F:\\pdf\\2013\\000608_阳光股份_2013年年度报告(更新后)_1.pdf";  
        String savePath="E:\\result1.txt";  
        long startTime=System.currentTimeMillis();  
        extractTXT(file,savePath);  
        long endTime=System.currentTimeMillis();  
        System.out.println("读写所用时间为："+(endTime-startTime)+"ms");  */
        
        String f="D:\\opt\\pdf\\腾讯.pdf";  
        String path="D:\\opt\\pdf\\images\\";  
        pdfSaveImage(f,path);
        
    }  
  
}  

