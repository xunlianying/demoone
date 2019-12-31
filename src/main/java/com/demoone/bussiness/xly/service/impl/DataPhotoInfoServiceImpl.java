package com.demoone.bussiness.xly.service.impl;

import com.demoone.bussiness.xly.entity.DataPhotoInfo;
import com.demoone.bussiness.xly.mapper.DataPhotoInfoDao;
import com.demoone.bussiness.xly.service.IDataPhotoInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.demoone.support.exception.BusinessException;
import com.demoone.support.sys.ErrCode;
import com.demoone.sys.entity.SysLocalFile;
import com.demoone.sys.service.ISysLocalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 照片信息表 服务实现类
 * </p>
 *
 * @author 华强
 * @since 2019-12-31
 */
@Service
public class DataPhotoInfoServiceImpl extends ServiceImpl<DataPhotoInfoDao, DataPhotoInfo> implements IDataPhotoInfoService {

	@Autowired
	private ISysLocalFileService iSysLocalFileService;

	@Override
	public String uploadPhoto(MultipartFile[] file, String id, int type) {
		int count=0;
		if (file!=null && file.length>0){
			for (int i =0;i<file.length;i++) {
				if(file[i] != null) {
				//调用上传方法
					try {
						SysLocalFile sysLocalFile = iSysLocalFileService.upload(file[i]);
						DataPhotoInfo dataPhotoInfo = new DataPhotoInfo();
						dataPhotoInfo.setFileId(sysLocalFile.getId());
						dataPhotoInfo.setType(type);
						dataPhotoInfo.setInfoId(id);
						dataPhotoInfo.setCreateTime(new Date());
						dataPhotoInfo.setStatus(0);
						if (insert(dataPhotoInfo)){
							++count;
						}
					} catch (IOException e) {
						e.printStackTrace();
						throw new BusinessException(ErrCode.FAIL,"文件上传失败！");
					}
				}
			}
		}
		return "成功上传"+count+"图片！";
	}

	@Override
	public boolean deleteFile(String fileId) {
		if (baseMapper.deleteFile(fileId)>0){
			return true;
		}
		return false;
	}

	@Override
	public List<String> getPhotoList(String id, int type) {
		return baseMapper.getPhotoList(id,type);
	}
}
