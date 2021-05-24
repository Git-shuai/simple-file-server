package com.file.server.controller;

import com.file.server.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件控制器
 *
 * @author FTS
 * @date 2021/5/22 12:27
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件的上传服务
     *
     * @param file client端传入的文件
     * @return 返回文件的id
     */
    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @PostMapping("/upload")
    public String uploadFile() {
        return "s";
    }

    /**
     * 文件的下载服务
     *
     * @param fileId 文件的id
     * @return
     */
    @GetMapping("/downloadFile/{fileId}")
    public HttpServletResponse downloadFile(@PathVariable("fileId") String fileId,HttpServletResponse response) {
        return fileService.downloadFile(fileId,response);
    }

    /**
     * 文件的查看服务
     *
     * @param fileId 文件的id
     * @return 根据文件的ID返回文件等信息
     */
    @GetMapping("/selectFile/{fileId}")
    public String selectFile(@PathVariable("fileId") String fileId) {
        return fileService.selectFile(fileId);
    }
}
