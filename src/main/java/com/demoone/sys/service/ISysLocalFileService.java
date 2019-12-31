package com.demoone.sys.service;

import com.demoone.sys.entity.SysLocalFile;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 华强
 * @since 2019-12-30
 */
public interface ISysLocalFileService extends IService<SysLocalFile> {

	SysLocalFile upload(MultipartFile file) throws IOException;

	void downloadFile(HttpServletResponse response, String id)throws IOException;

	void downLoadFileOnline(String id, boolean online);

}
