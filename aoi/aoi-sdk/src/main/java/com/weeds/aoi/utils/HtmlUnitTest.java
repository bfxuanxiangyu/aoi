package com.weeds.aoi.utils;

import java.io.IOException;
import java.net.MalformedURLException;

import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

 
public class HtmlUnitTest {
 
	public static void main(String[] args) {
		try {
			HtmlUnitTest.testFormSubmit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testGetHtmlContent(){
		try {
			final WebClient client=new WebClient();
			final HtmlPage page=client.getPage("http://www.baidu.com");
			System.out.println(page.asText());  //asText()是以文本格式显示
//			System.out.println(page.asXml());   //asXml()是以xml格式显示
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
     * 用get方法获取页面内容 
     * @throws MalformedURLException 
     * @throws IOException 
     * @throws SAXException 
     */  
    public static void testGetMethod(){  
        
  
    } 
    
    /** 
     * 用post方法获取页面内容 
     * @throws MalformedURLException 
     * @throws IOException 
     * @throws SAXException 
     */  
    public static void testPostMethod(){  
       
    }
    
    /** 
     * 获取页面链接并模拟点击 
     * @throws MalformedURLException 
     * @throws IOException 
     * @throws SAXException 
     */  
    public static void testClickLink() {  
        
  
    }
    
    /**
	 * 测试WebForm的处理表单和提交能力
	 */
	public static void testFormSubmit(){
		try {
			WebClient client = new WebClient();
			client.getOptions().setJavaScriptEnabled(false);//关闭css  js调用
			client.getOptions().setCssEnabled(false);
			// 做的第一件事，去拿到这个网页，只需要调用getPage这个方法即可  
			HtmlPage htmlpage = client.getPage("http://www.baidu.com");  
			 
			// 根据名字得到一个表单，查看上面这个网页的源代码可以发现表单的名字叫“f”  
			final HtmlForm form = htmlpage.getFormByName("f");  
			// 同样道理，获取”百度一下“这个按钮  
			final HtmlSubmitInput button = form.getInputByValue("百度一下");  
			// 得到搜索框  
			final HtmlTextInput textField = form.getInputByName("wd");  
			//搜索我的id
			textField.setValueAttribute("ip");  
			// 输入好了，我们点一下这个按钮  
			final HtmlPage nextPage = button.click();  
			// 我把结果转成String  
			String result = nextPage.asXml();  
			
			if(result.contains("本机IP")){
				result = result.substring(result.indexOf("本机IP")+5);
				result = result.substring(0,result.indexOf("</span>"));
			}
			System.out.println(result);  //得到的是点击后的网页
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}