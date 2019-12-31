package com.demoone.sys.service.impl;

import com.demoone.support.exception.BusinessException;
import com.demoone.support.sys.ErrCode;
import com.demoone.sys.entity.SysLocalFile;
import com.demoone.sys.enums.FileType;
import com.demoone.sys.mapper.SysLocalFileDao;
import com.demoone.sys.service.ISysLocalFileService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
@Slf4j
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
		return localFile;
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

	@Override
	public void downLoadFileOnline(String id, boolean online) {
		SysLocalFile sysLocalFile = selectById(id);
		if (sysLocalFile == null) {
			throw new BusinessException(ErrCode.FAIL,"改文件不存在");
		}
		try {
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			File file = null;
			file = new File(sysLocalFile.getPath() + File.separator + sysLocalFile.getName());

			if (!file.exists()) {
				String errorMessage = "Sorry. The file you are looking for does not exist";
				System.out.println(errorMessage);
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
				outputStream.close();
				return;
			}

			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				String[] temps = file.getName().split("\\.");
				if (temps.length < 1) {
					mimeType = URLConnection.guessContentTypeFromName(file.getName() + sysLocalFile.getType());
					if (mimeType == null) {
						System.out.println("mimetype is not detectable, will take default");
						mimeType = "application/octet-stream";
					}
				} else {
					System.out.println("mimetype is not detectable, will take default");
					mimeType = "application/octet-stream";
				}
			}

			response.setContentType(mimeType);

			//方法1：
			//response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			//方法2：使用指定编码，并告诉浏览器编码类型
			//response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8");
			//方法3：
			//response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1"));

			//离线下载文件
			//response.setHeader("Content-Disposition", "attachment;filename="+new String( file.getName().getBytes("gb2312"), "ISO8859-1" ));
			//response.setHeader("Content-Disposition", "inline;filename=" + new String(file.getName().getBytes("gb2312"), "ISO8859-1"));
			//在线下载文件
			if (online) {
				response.setHeader("Content-Disposition", "inline;filename=" + new String(file.getName().getBytes("gb2312"), "ISO8859-1"));
			} else {
				response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("gb2312"), "ISO8859-1"));
			}

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
			return;
		} catch (Exception e) {
			log.error("", e);
			throw new BusinessException(ErrCode.FAIL,"下载文件失败");
		}
	}
}
