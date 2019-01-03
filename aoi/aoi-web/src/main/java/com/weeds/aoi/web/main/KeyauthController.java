package com.weeds.aoi.web.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.weeds.aoi.utils.AoiResponseUtil;
import com.weeds.aoi.utils.AoiStringUtils;
import com.weeds.aoi.utils.des.Des3Utils;
import com.weeds.aoi.utils.file.ZipUtils;

@Controller
@RequestMapping("/api")
public class KeyauthController {

	private Logger logger = LoggerFactory.getLogger(KeyauthController.class);
	
	@Value("${key.auth}")
	private String keyauthPath;
	
	@RequestMapping("/srkeyauth")
	public ModelAndView moneymsg(){
		ModelAndView view = new ModelAndView();
		view.setViewName("keyauth");
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/keyauth")
	public String extractMoney(String uuid,String version,String path,String orders,HttpServletResponse response){
		String res = null;
		try {
			if(AoiStringUtils.isBlank(uuid)||AoiStringUtils.isBlank(version)){
				res = AoiResponseUtil.printFailJson(404, "lack off parameters  uuid or version", null);
				return res;
			}
			//auth授权
			boolean writeData = writeData(keyauthPath+"auth", uuid, version,orders);
			if(!writeData){
				res = AoiResponseUtil.printFailJson(500, "error Contact the xxy", null);
				return res;
			}
			//写把文件写入
			File authFile = new File(keyauthPath+"auth.bat");
			String javaRunStr = "java -jar component";
			if(AoiStringUtils.isNotBlank(path)){
				path  = path.replace("\\", "/");
				if(!path.endsWith("/")){
					path = path+"/";
					//bat文件重写
					javaRunStr = path+"/jre/bin/"+javaRunStr;
				}
			}
			PrintWriter pw = new PrintWriter(authFile);
			pw.write(javaRunStr);
			pw.flush();
			pw.close();
			//文件夹压缩
			//先删除再生成
			File porderFile = new File(keyauthPath);
			String zipFilePath = porderFile.getParent()+File.separator+"keyauth.zip";
			File zipFile = new File(zipFilePath);
			if(zipFile.exists()){
				zipFile.delete();
			}
			boolean zipFlag = ZipUtils.toZip(keyauthPath,zipFilePath,true);
			if(!zipFlag){
				res = AoiResponseUtil.printFailJson(500, "zip error Contact the xxy", null);
				return res;
			}
			
			return AoiResponseUtil.printJson("success", zipFilePath);
		} catch (Exception e) {
			logger.error("授权失败"+e.getMessage(),e);
			res = AoiResponseUtil.printFailJson(500, "error", null);
			return res;
		}
	}
	
	/**
	 * 下载文件
	 * @param response
	 * @param zipFile
	 */
	@ResponseBody
	@RequestMapping("/keyauth_down")
	public void download(HttpServletResponse resp,String zipFileName){
		
		FileInputStream fis = null;
		try {
			zipFileName = URLDecoder.decode(zipFileName,"utf-8");
			
			File zipFile = new File(zipFileName);
			
			resp.setContentType("application/octet-stream");
		    resp.setHeader("Content-Disposition","attachment;filename=keyauth.zip");
		    resp.setContentLength((int) zipFile.length());
		    
	        fis = new FileInputStream(zipFile);
	        byte[] buffer = new byte[1024];
	        int count = 0;
	        while ((count = fis.read(buffer)) > 0) {
	            resp.getOutputStream().write(buffer, 0, count);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		resp.getOutputStream().flush();
	    		resp.getOutputStream().close();
	    		fis.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
	    }

	}
	
	private boolean writeData(String porder, String keyCode,String version,String orders){
		boolean flag = false;
		try {
			File file = new File(porder);
			if(file.exists()){
				file.delete();
			}
			String groupfile_num = "3";//群体侧写默认值
			String functions = "";//功能multiple_collect
			if(AoiStringUtils.isNotBlank(orders)){
				String[] farray = orders.split(";");
				for (int i = 0; i < farray.length; i++) {
					String fun = farray[i];
					String[] funcs = fun.split("=");
					if(funcs.length==2){
						if(funcs[0].equals("groupfileNum")){
							groupfile_num=funcs[1];
						}else if(funcs[0].equals("functions")){
							functions=funcs[1];
						}
					}
				}
			}
			
			Map<String, Object> map = Maps.newHashMap();
			Map<String, Object> cmap_baseInfo = Maps.newHashMap();
			Map<String, Object> cmap_function = Maps.newHashMap();
			String menu = "/collection/caseentry,/collection/datamain";
			map.put("menu", menu);
			cmap_baseInfo.put("key_code", keyCode);//狗码
			cmap_baseInfo.put("version", version);//狗码
			map.put("base_info", cmap_baseInfo);
			cmap_function.put("functions", functions);
			cmap_function.put("groupfile_num", groupfile_num);
			map.put("function", cmap_function);
			String mS = AoiStringUtils.getJsonObj(map);
			String mSs=Des3Utils.ecryptData(mS);
			mSs = strToHex(mSs);
			//写入到本地
			FileWriter fw = new FileWriter(porder);
			fw.write(mSs);
			fw.flush();
			fw.close();
			flag = true;
		} catch (Exception e) {
			logger.error("写入授权信息异常"+e.getMessage(),e);
		}
		return flag;
	}
	
	/**
	 * 字符串转16进制
	 * @param s
	 * @return
	 */
	public static String strToHex(String s) {
	    String str = "";
	    for (int i = 0; i < s.length(); i++) {
	        int ch = (int) s.charAt(i);
	        String s4 = Integer.toHexString(ch);
	        str = str + s4;
	    }
	    return str;
	}
	
	public static void desData(String str){
		String decryptData = Des3Utils.decryptData(str);
		System.out.println(decryptData);
	}
	
	
}
