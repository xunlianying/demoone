package com.demoone.sys.service.impl;

import com.demoone.support.exception.BusinessException;
import com.demoone.support.sys.ErrCode;
import com.demoone.sys.entity.SysLocalFile;
import com.demoone.sys.enums.FileType;
import com.demoone.sys.mapper.SysLocalFileDao;
import com.demoone.sys.service.ISysLocalFileService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 华强
 * @since 2019-12-30
 */
@Service
public class SysLocalFileServiceImpl extends ServiceImpl<SysLocalFileDao, SysLocalFile> implements ISysLocalFileService {

	@Value("${sys.path.report}")
	private String filePath;

	@Override
	public SysLocalFile upload(MultipartFile file) throws IOException {
		String path = new SimpleDateFormat("yyyyMMdd").format(new Date()) + File.separator + System.currentTimeMillis() + "_" +
				file.getOriginalFilename() ;
		File tempFile = new File(filePath + File.separator + path);
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}

		file.transferTo(tempFile);

		SysLocalFile localFile = new SysLocalFile();
		localFile.setName(tempFile.getName());
		/**
		 * TODO 这里先固定一个类型
		 */
		String[] temps = file.getOriginalFilename().split("\\.");
		if (temps.length >= 1) {
			localFile.setType(temps[temps.length - 1]);
		} else {
			localFile.setType(FileType.PDF.getValue());
		}
//        localFile.setId(UUID.randomUUID().toString().replace("-",""));
		localFile.setVolume("/");
		localFile.setPath(tempFile.getParent());
		localFile.setCreateBy("admin");
		localFile.setCreateTime(new Date());
		insert(localFile);
		return null;
	}

	@Override
	public void downloadFile(HttpServletResponse response, String id) throws IOException {
		SysLocalFile localFile = selectById(id);
		if (localFile == null) {
			return;
		}
		File file = null;
		file = new File(localFile.getPath() + File.separator + localFile.getName());

		if(!file.exists()){
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}

		String mimeType= URLConnection.guessContentTypeFromName(file.getName());
		if(mimeType==null){
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
		}

		System.out.println("mimetype : "+mimeType);

		response.setContentType(mimeType);
		System.out.println("file : "+ file.getName());

		// response.setHeader("Content-Disposition", EncodeUtils.urlEncode("inline; filename=\"" + file.getName() +"\""));
		response.setHeader("Content-Disposition", "attachment;filename="+new String( file.getName().getBytes("gb2312"), "ISO8859-1"
		));
		response.setContentLength((int)file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
}
