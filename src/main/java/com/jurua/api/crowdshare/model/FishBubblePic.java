package com.jurua.api.crowdshare.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
/**
 * @author 张博【zhangb@lianliantech.cn】
 * @since 2018/4/23 下午1:47
 */
@ApiModel(value = "鱼泡图片")
public class FishBubblePic implements Serializable {
    private static final long serialVersionUID = -909817462565535337L;
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "与 鱼泡，fish_bubble 表关联")
    private Long fBId;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;
    @ApiModelProperty(value = "图片路径")
    private String filePath;
    @ApiModelProperty(value = "是否是封面 1 是 ，0不是")
    private Boolean isCover;
    @ApiModelProperty(value = "创建日期")
    private Date gmtCreate;
    @ApiModelProperty(value = "修改日期")
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getfBId() {
        return fBId;
    }

    public void setfBId(Long fBId) {
        this.fBId = fBId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Boolean getIsCover() {
        return isCover;
    }

    public void setIsCover(Boolean isCover) {
        this.isCover = isCover;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}