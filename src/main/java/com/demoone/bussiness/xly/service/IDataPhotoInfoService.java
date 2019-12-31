package com.demoone.bussiness.xly.service;

import com.demoone.bussiness.xly.entity.DataPhotoInfo;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 照片信息表 服务类
 * </p>
 *
 * @author 华强
 * @since 2019-12-31
 */
public interface IDataPhotoInfoService extends IService<DataPhotoInfo> {

	String uploadPhoto(MultipartFile[] file, String id, int type);

	boolean deleteFile(String fileId);

	List<String> getPhotoList(String id, int type);
}
