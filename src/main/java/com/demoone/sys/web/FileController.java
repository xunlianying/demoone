package com.demoone.sys.web;

import com.alibaba.fastjson.JSONObject;
import com.demoone.support.sys.OptResult;
import com.demoone.sys.service.ISysLocalFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

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


    @ApiOperation(value = "文件上传",
            notes = "文件上传 返回文件ID",
            response = JSONObject.class)
    @PostMapping("upload")
    @ResponseBody
    public OptResult upload(@RequestParam("file") MultipartFile file) throws IOException {
		OptResult optResult = OptResult.success();
		optResult.setData(iSysLocalFileService.upload(file));
        return optResult;
    }

    @ApiOperation(value = "文件下载",
            notes = "文件下载 根据文件ID下载文件           例：http://192.168.1.92:8089/api/file/download/a7d9df315957425aa887aca63989a2fa")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文件ID", required = true, paramType = "query")
    })
    @GetMapping(value="/download/{id}")
    public void downloadFile(HttpServletResponse response, @PathVariable("id") String id) throws IOException {
		iSysLocalFileService.downloadFile(response,id);
    }



}
