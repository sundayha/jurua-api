package com.jurua.api.fishinfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
/**
 * @author 张博【zhangb@lianliantech.cn】
 * @since 2018/4/23 下午2:23
 */
@ApiModel(value = "鱼种库")
public class FishLib implements Serializable {
    private static final long serialVersionUID = -918789022030689870L;
    @ApiModelProperty(value = "鱼种id 主键")
    private Long fishId;
    @ApiModelProperty(value = "与“鱼类别表，fish_family”关联")
    private Long fFId;
    @ApiModelProperty(value = "鱼名")
    private String fishName;
    @ApiModelProperty(value = "鱼学名")
    private String scientificName;
    @ApiModelProperty(value = "全长 cm 是一个范围 例如7-8")
    private String fullLength;
    @ApiModelProperty(value = "鱼描述")
    private String fishDescription;
    @ApiModelProperty(value = "创建日期")
    private Date gmtCreate;
    @ApiModelProperty(value = "修改日期")
    private Date gmtModified;

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public Long getfFId() {
        return fFId;
    }

    public void setfFId(Long fFId) {
        this.fFId = fFId;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName == null ? null : fishName.trim();
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName == null ? null : scientificName.trim();
    }

    public String getFullLength() {
        return fullLength;
    }

    public void setFullLength(String fullLength) {
        this.fullLength = fullLength == null ? null : fullLength.trim();
    }

    public String getFishDescription() {
        return fishDescription;
    }

    public void setFishDescription(String fishDescription) {
        this.fishDescription = fishDescription == null ? null : fishDescription.trim();
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