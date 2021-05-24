package com.file.server.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author FTS
 * @date 2021/5/22 16:37
 */
public class FileInfo {
    private String id;
    private Long fileSize;
    private String fileType;
    private String fileName;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String fileUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", createTime=" + createTime +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }


}
