package com.demoone.bussiness.xly.mapper;

import com.demoone.bussiness.xly.entity.DataPhotoInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 照片信息表 Mapper 接口
 * </p>
 *
 * @author 华强
 * @since 2019-12-31
 */
public interface DataPhotoInfoDao extends BaseMapper<DataPhotoInfo> {

	int deleteFile(@Param("fileId") String fileId);

	List<String> getPhotoList(@Param("id") String id, @Param("type") int type);
}
