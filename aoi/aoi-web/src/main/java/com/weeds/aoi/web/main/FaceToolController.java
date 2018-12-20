package com.weeds.aoi.web.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.arcsoft.face.FaceInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.weeds.aoi.arcface.domain.FaceSearchResDto;
import com.weeds.aoi.arcface.domain.FaceUserInfo;
import com.weeds.aoi.arcface.domain.ImageInfo;
import com.weeds.aoi.arcface.domain.ProcessInfo;
import com.weeds.aoi.arcface.domain.UserFaceInfo;
import com.weeds.aoi.arcface.service.FaceEngineService;
import com.weeds.aoi.arcface.utils.ImageUtil;
import com.weeds.aoi.utils.AoiDateUtils;
import com.weeds.aoi.utils.AoiResponseUtil;
import com.weeds.aoi.utils.AoiStringUtils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * 人脸工具
 * @author simon
 * 2018年11月26日
 * The spirit is full of spirit
 */
@Controller
@RequestMapping("/api")
public class FaceToolController {

	private Logger logger = LoggerFactory.getLogger(FaceToolController.class);
	
	@Value("${img.savePath}")
	private String savePath;
	@Value("${img.imgUrl}")
	private String imgUrl;
	
	@Resource
	private FaceEngineService faceEngineService; 
	
	@ResponseBody
	@RequestMapping("/face/face_register")
	public String faceVersion(String name,String groupId,@RequestParam(value="file", required=false) MultipartFile file){
		try {
			if(file==null || file.getInputStream()==null){
				return AoiResponseUtil.printFailJson(404, "缺少图片", null);
			}
			InputStream inputStream = file.getInputStream();
			ImageInfo imageInfo = ImageUtil.getRGBData(inputStream);
			byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
			
			if(AoiStringUtils.isBlank(groupId)){
				groupId = AoiStringUtils.getUUID();
			}
			//先写入本地一份
			writeLocal(file.getInputStream(), groupId);
			
			UserFaceInfo userFaceInfo = new UserFaceInfo();
			userFaceInfo.setName(name);
			userFaceInfo.setGroupId(groupId);
			userFaceInfo.setFaceFeature(bytes);
			userFaceInfo.setFaceId(RandomUtil.randomString(10));
			
			faceEngineService.saveUserFaceInfo(userFaceInfo);
			
			
			FaceUserInfo faceUserInfo = new FaceUserInfo();
            faceUserInfo.setName(name);
            faceUserInfo.setFaceId(userFaceInfo.getFaceId());
            faceUserInfo.setFaceFeature(bytes);
            //人脸信息添加到缓存
            faceEngineService.addFaceToCache(groupId, faceUserInfo);

            logger.info("faceAdd:" + name);
			
			return AoiResponseUtil.printJson("人脸注册成功", userFaceInfo.getGroupId());
		} catch (Exception e) {
			logger.error("人脸注册异常"+e.getMessage(),e);
			return AoiResponseUtil.printFailJson(500, "服务器升级", null);
		}
	}
	
	private void writeLocal(InputStream is,String groupId){
		try {
			String porder = savePath + AoiDateUtils.dateToStr(new Date(), "yyyyMM")+File.separator;
			File porderFile = new File(porder);
			if(!porderFile.exists()){
				porderFile.mkdirs();
			}
			String fileName = porder + groupId+"_"+AoiStringUtils.getRandomNum(5)+".jpg";
			int index;
			byte[] bytes = new byte[1024];
			FileOutputStream out = new FileOutputStream(fileName);
			while ((index = is.read(bytes)) != -1) {
				out.write(bytes, 0, index);
				out.flush();
			}
			out.close();
			is.close();
		} catch (Exception e) {
			logger.error("写入本地异常"+e.getMessage(),e);
		}
	}
	
	@ResponseBody
	@RequestMapping("/face/face_compare")
	public String faceCompare(String groupId,@RequestParam(value="file", required=false) MultipartFile file){
		try {
			if(AoiStringUtils.isBlank(groupId)){
				return AoiResponseUtil.printFailJson(404, "缺少照片组编号", null);
			}
			if(file==null || file.getInputStream()==null){
				return AoiResponseUtil.printFailJson(404, "缺少图片", null);
			}
			InputStream inputStream = file.getInputStream();
			BufferedImage bufImage = ImageIO.read(inputStream);
			ImageInfo imageInfo = ImageUtil.bufferedImage2ImageInfo(bufImage);
			if (inputStream != null) {
				inputStream.close();
			}
			//人脸特征获取
			byte[] bytes = faceEngineService.extractFaceFeature(imageInfo);
			
			if (bytes == null) {
				logger.info("no result");
				return AoiResponseUtil.printFailJson(404, "无人脸信息比对", null);
			}
			//人脸比对，获取比对结果
			List<FaceUserInfo> userFaceInfoList = faceEngineService.compareFaceFeature(bytes, groupId);
			
			FaceSearchResDto faceSearchResDto = new FaceSearchResDto();
			if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
	            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
	            BeanUtil.copyProperties(faceUserInfo, faceSearchResDto);
	            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
	            if (CollectionUtil.isNotEmpty(processInfoList)) {
	                //人脸检测
	                List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
	                int left = faceInfoList.get(0).getRect().getLeft();
	                int top = faceInfoList.get(0).getRect().getTop();
	                int width = faceInfoList.get(0).getRect().getRight() - left;
	                int height = faceInfoList.get(0).getRect().getBottom()- top;

	                Graphics2D graphics2D = bufImage.createGraphics();
	                graphics2D.setColor(Color.RED);//红色
	                BasicStroke stroke = new BasicStroke(5f);
	                graphics2D.setStroke(stroke);
	                graphics2D.drawRect(left, top, width, height);
	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                ImageIO.write(bufImage, "jpg", outputStream);
	                byte[] bytes1 = outputStream.toByteArray();
	                faceSearchResDto.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
	                faceSearchResDto.setAge(processInfoList.get(0).getAge());
	                faceSearchResDto.setGender(processInfoList.get(0).getGender().equals(1) ? "女" : "男");
	            }
	            return AoiResponseUtil.printJson("人脸对比成功", faceSearchResDto);
	        }
			return AoiResponseUtil.printFailJson(505, "无匹配图片", null);
		} catch (Exception e) {
			logger.error("人脸比对异常"+e.getMessage(),e);
			return AoiResponseUtil.printFailJson(500, "服务器升级", null);
		}
	}
	
    @ResponseBody
    @RequestMapping(value = "/face/face_detect", method = RequestMethod.POST)
    public String detectFaces(@RequestParam(value="file", required=false) MultipartFile file){
    	try {
    		if(file==null || file.getInputStream()==null){
    			return AoiResponseUtil.printFailJson(404, "缺少图片", null);
    		}
    		InputStream inputStream = file.getInputStream();
    		//先写入本地一份
			writeLocal(file.getInputStream(), AoiStringUtils.getUUID());
    		BufferedImage bufImage = ImageIO.read(inputStream);
    		ImageInfo imageInfo = ImageUtil.bufferedImage2ImageInfo(bufImage);
//    		ImageInfo imageInfo = ImageUtil.getRGBData(inputStream);
    		if (inputStream != null) {
    			inputStream.close();
    		}
    		List<FaceInfo> faceInfoList = faceEngineService.detectFaces(imageInfo);
    		
    		if(faceInfoList==null || faceInfoList.isEmpty()){
    			return AoiResponseUtil.printFailJson(400, "无人脸信息", null);
    		}
    		
    		Map<String, Object> resMap = Maps.newHashMap();
    		resMap.put("total", faceInfoList.size());
    		
            Graphics2D graphics2D = bufImage.createGraphics();
            graphics2D.setColor(Color.RED);//红色
            BasicStroke stroke = new BasicStroke(5f);
            graphics2D.setStroke(stroke);
            
            for (int i = 0; i < faceInfoList.size(); i++) {
            	//勾画出人脸
            	int left = faceInfoList.get(i).getRect().getLeft();
            	int top = faceInfoList.get(i).getRect().getTop();
            	int width = faceInfoList.get(i).getRect().getRight() - left;
            	int height = faceInfoList.get(i).getRect().getBottom()- top;
            	graphics2D.drawRect(left, top, width, height);
    		}
            List<ProcessInfo> processInfoList = faceEngineService.process(imageInfo);
            if(processInfoList != null){
            	List<Map<String, Object>> infos = Lists.newArrayList();
            	for (ProcessInfo pi : processInfoList) {
            		Map<String, Object> infoMap = Maps.newHashMap();
					infoMap.put("age", pi.getAge());
					infoMap.put("gender",pi.getGender().equals(1) ? "女" : "男");
					infos.add(infoMap);
				}
            	resMap.put("infos", infos);
            }
            
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufImage, "jpg", outputStream);
            byte[] bytes1 = outputStream.toByteArray();
            resMap.put("image", "data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
    		
    		
    		return AoiResponseUtil.printJson("人脸检测成功", resMap);
		} catch (Exception e) {
			logger.error("人脸检测异常"+e.getMessage(),e);
			return AoiResponseUtil.printFailJson(500, "服务器升级", null);
		}
    }
	
	
}
