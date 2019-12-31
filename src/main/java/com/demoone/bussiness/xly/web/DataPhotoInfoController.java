package com.demoone.bussiness.xly.web;


import com.demoone.bussiness.xly.service.IDataPhotoInfoService;
import com.demoone.support.sys.OptResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 照片信息表 前端控制器
 * </p>
 *
 * @author 华强
 * @since 2019-12-31
 */
@RestController
@RequestMapping("/api/xly/photo")
@Api(value = "DataPhotoInfoController", description = "文件管理")
public class DataPhotoInfoController {


	@Autowired
	private IDataPhotoInfoService iDataPhotoInfoService;

	@ApiOperation(value = "上传图片信息", notes = "上传图片信息")
	@PostMapping("uploadPhoto")
	public OptResult uploadPhoto(@RequestParam("file") MultipartFile[] file,String id, int type) {
		OptResult result = OptResult.success();
//		MultipartFile [] files = new MultipartFile[]{file};
		result.setData(iDataPhotoInfoService.uploadPhoto(file,id,type));
		return result;
	}


	@ApiOperation(value = "删除图片信息", notes = "删除图片信息")
	@PostMapping("deletePhoto")
	public OptResult deleteFile(String fileId) {
		OptResult result = null;
		if (iDataPhotoInfoService.deleteFile(fileId)){
			result = OptResult.success();
		}else {
			result = OptResult.fail();
		}
		return result;
	}


	@ApiOperation(value = "删除图片信息", notes = "删除图片信息")
	@GetMapping("getPhotoList")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "学员id 教练id ", paramType = "query"),
			@ApiImplicitParam(name = "type", value = "1学院入营照片 2学员离营照片 3学员合同照片 4教练照片", paramType = "query")
	})
	public OptResult getPhotoList(String id,int type) {
		OptResult result =  OptResult.success();
		result.setData(iDataPhotoInfoService.getPhotoList(id,type));
		return result;
	}
}
