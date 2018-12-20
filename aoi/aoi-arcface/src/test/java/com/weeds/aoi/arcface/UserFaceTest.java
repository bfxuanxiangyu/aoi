
package com.weeds.aoi.arcface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Maps;
import com.weeds.aoi.arcface.domain.UserFaceInfo;
import com.weeds.aoi.arcface.mapper.UserFaceInfoMapper;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserFaceTest {

	@Resource
	private UserFaceInfoMapper userFaceInfoMapper;
	
	@Test
	public void saveLocalPic(){
		try {
			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("groupId", "7bbda083b52c4584baec4fd33a858776");
			List<UserFaceInfo> list = userFaceInfoMapper.selectAll(parameters);
			
			
			
			for (int i=0 ; i<list.size(); i++) {
				UserFaceInfo ufi = list.get(i);
				ufi.getFaceFeature();
				writeLocal(ufi.getGroupId(),ufi.getFaceFeature(), i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void writeLocal(String groupId,byte[] data,int index){
		try {
			String fileName = "D:\\opt\\face\\online\\"+groupId+"_"+index+".jpg";
			System.out.println(fileName);
			OutputStream out = new FileOutputStream(new File(fileName));
			out.write(data);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
