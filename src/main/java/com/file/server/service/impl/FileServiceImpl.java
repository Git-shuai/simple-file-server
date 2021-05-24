package com.file.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.file.server.bean.FileInfo;
import com.file.server.mapper.FileMapper;
import com.file.server.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author FTS
 * @date 2021/5/22 16:22
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    private final static String uploadUrl = "src\\main\\resources\\upload";
    private final static String dateFormat = "yyyyMMdd";

    @Resource
    private FileMapper fileMapper;

    /**
     * 文件的上传服务
     *
     * @param file client端传入的文件
     * @return 返回文件的id
     */
    @Override
    public String uploadFile(MultipartFile file) {
        //1、判断是否为空，为空直接返回
        if (file == null) {
            return "";
        }
        //2、得到文件的名称,后缀,以及大小
        String originalName = file.getOriginalFilename();
        if (originalName == null) {
            return "";
        }
        //文件原来的名字
        String fileName = originalName.substring(0, originalName.lastIndexOf("."));
        //文件的类型
        String fileType = originalName.substring(originalName.lastIndexOf(".") + 1);
        Long fileSize = file.getSize();
        //3、创建文件的UUID
        UUID uuid = UUID.randomUUID();
        //4、将文件名重命名后放入日期文件夹
        Date createTime = new Date();
        //将要存放的文件夹名称
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        String folderName = df.format(createTime);
        //获取项目路径
        String path = System.getProperty("user.dir");
        //上传文件保存路径
        String targetFolder = path + "\\" + uploadUrl + "\\" + folderName + "\\";
        File saveFile = new File(targetFolder);
        //创建文件如果有文件就跳过，没有就创建
        if (!saveFile.exists()) {
            saveFile.mkdir();
        }
        String fileUrl = targetFolder + uuid + "." + fileType;
        //以绝对路径保存重名命后的图片
        try {
            file.transferTo(new File(fileUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5、创建File对象，插入数据库，并返回文件的uuid
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(uuid.toString());
        fileInfo.setFileSize(fileSize);
        fileInfo.setFileType(fileType);
        fileInfo.setFileName(fileName);
        fileInfo.setCreateTime(createTime);
        //存放目录
        fileInfo.setFileUrl(targetFolder);
        fileMapper.uploadFile(fileInfo);
        return uuid.toString();
    }

    /**
     * 文件的下载服务
     *
     * @param fileId 文件的id
     * @return
     */
    @Override
    public HttpServletResponse downloadFile(String fileId,HttpServletResponse response) {
        if (fileId == null || fileId.equals("")) {
            return null;
        }
        try {
            //1、使用id,查询出文件的信息。
            FileInfo file = fileMapper.selectFile(fileId);
            if (file==null){
                return null;
            }
            //2、使用文件信息拼接出文件的地址
            String filename=fileId + "." + file.getFileType();
            String path = file.getFileUrl() + filename;
            String originalName=file.getFileName()+ "." + file.getFileType();
            //3、以流的形式下载文件
            InputStream inputStream = new BufferedInputStream(new FileInputStream(path));
            response.setHeader("Content-Disposition", "attachment; filename="+ java.net.URLEncoder.encode(originalName, "UTF-8"));
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            try {
                while ((len = inputStream.read(b)) > 0) {
                    response.getOutputStream().write(b, 0, len);
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        } catch (Exception e) {
            //4、出现错误返回错误码410
            response.setStatus(410);
            //return response;
        }
        return null;
    }

    /**
     * 文件的查看服务
     *
     * @param fileId 文件的id
     * @return 根据文件的ID返回文件等信息
     */
    @Override
    public String selectFile(String fileId) {
        if (fileId == null || fileId.equals("")) {
            return null;
        }
        FileInfo fileInfo = fileMapper.selectFile(fileId);
        JSON.toJSONString(fileInfo);
        return JSON.toJSONString(fileInfo);
    }
}
