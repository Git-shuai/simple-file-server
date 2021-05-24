package com.file.server.mapper;

import com.file.server.bean.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author FTS
 * @date 2021/5/22 16:40
 */
@Mapper
public interface FileMapper {

    //上传文件
    public void uploadFile(FileInfo file);

    //查询文件
    public FileInfo selectFile(String fileId);
}
