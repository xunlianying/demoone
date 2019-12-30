package com.demoone.sys.web;

import com.alibaba.fastjson.JSONObject;
import com.demoone.support.sys.OptResult;
import com.demoone.sys.entity.SysLocalFile;
import com.demoone.sys.enums.FileType;
import com.demoone.sys.service.ISysLocalFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2017/11/28 13:33
 */
@Controller
@Api(value = "FileController", description = "文件上传下载")
@RequestMapping("api/file")
public class FileController {

    @Autowired
    private ISysLocalFileService iSysLocalFileService;

    @Value("${sys.path.report}")
    private String filePath;

    @ApiOperation(value = "文件上传",
            notes = "文件上传 返回文件ID",
            response = JSONObject.class)
    @PostMapping("upload")
    @ResponseBody
    public OptResult upload(@RequestParam("file") MultipartFile file) throws IOException {
		OptResult optResult = OptResult.success();
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
		iSysLocalFileService.insert(localFile);
		optResult.setData(localFile.getId());
        return optResult;
    }

    @ApiOperation(value = "文件下载",
            notes = "文件下载 根据文件ID下载文件           例：http://192.168.1.92:8089/api/file/download/a7d9df315957425aa887aca63989a2fa")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文件ID", required = true, paramType = "query")
    })
    @GetMapping(value="/download/{id}")
    public void downloadFile(HttpServletResponse response, @PathVariable("id") String id) throws IOException {

		SysLocalFile localFile = iSysLocalFileService.selectById(id);
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
