package com.weeds.aoi.arcface.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.arcsoft.face.FaceInfo;
import com.weeds.aoi.arcface.domain.FaceUserInfo;
import com.weeds.aoi.arcface.domain.ImageInfo;
import com.weeds.aoi.arcface.domain.ProcessInfo;
import com.weeds.aoi.arcface.domain.UserFaceInfo;


public interface FaceEngineService {
	
	void saveUserFaceInfo(UserFaceInfo ufi);
	
	void addFaceToCache(String groupId, FaceUserInfo userFaceInfo) throws ExecutionException;

    List<FaceInfo> detectFaces(ImageInfo imageInfo);

    /**
     * 年龄特征
     * @param imageInfo
     * @return
     */
    List<ProcessInfo> process(ImageInfo imageInfo);

    /**
     * 人脸特征
     * @param imageInfo
     * @return
     */
    byte[] extractFaceFeature(ImageInfo imageInfo) throws InterruptedException;

    /**
     * 人脸比对
     * @param groupId
     * @param faceFeature
     * @return
     */
    List<FaceUserInfo> compareFaceFeature(byte[] faceFeature, String groupId) throws InterruptedException, ExecutionException;



}
