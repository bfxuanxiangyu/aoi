package com.weeds.aoi.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import com.weeds.aoi.utils.HttpUtils;

public class FaceToolTest {
	
	
	public static void main(String[] args) {
		
		   String url = "http://localhost:8989/api/face/face_register";//注册接口
//		   String url = "http://localhost:8989/api/face/aoi_face_compare";//人脸比对接口
		   String httpRes = null;
		   String localFileName = "d:opt/face/a.jpg";
				
		   Map<String,ContentBody> reqParam = new HashMap<String,ContentBody>();
		   reqParam.put("file", new FileBody(new File(localFileName)));
		   reqParam.put("name", new StringBody("玛利亚", ContentType.APPLICATION_JSON));
		   reqParam.put("groupId", new StringBody("085ba5ec70ce4d71a5b8d2494843d17f", ContentType.MULTIPART_FORM_DATA));
				
		   httpRes = HttpUtils.postFileMultiPart(url,reqParam);
		   
		   System.out.println("人脸接口返回结果："+httpRes);
	}

}
