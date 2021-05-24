package com.file.server.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author FTS
 * @date 2021/5/22 16:07
 */
public interface FileService {

    /**
     * 文件的上传服务
     *
     * @param file client端传入的文件
     * @return 返回文件的id
     */
    public String uploadFile(MultipartFile file);

    /**
     * 文件的下载服务
     * @param fileId 文件的id
     * @return
     */
    public HttpServletResponse downloadFile(String fileId,HttpServletResponse response);

    /**
     * 文件的查看服务
     * @param fileId 文件的id
     * @return 根据文件的ID返回文件等信息
     */
    public String selectFile(String fileId);
}
