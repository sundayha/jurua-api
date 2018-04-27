package com.jurua.api.fishinfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 张博【zhangb@lianliantech.cn】
 * @since 2018/4/23 下午2:25
 */
@ApiModel(value = "鱼种类表")
public class FishFamily implements Serializable {
    private static final long serialVersionUID = -710955872561907581L;
    @ApiModelProperty(value = "主键id")
    private Long fFId;
    @ApiModelProperty(value = "分布")
    private String distribution;
    @ApiModelProperty(value = "品种描述")
    private String represent;
    @ApiModelProperty(value = "创建日期")
    private Date gmtCreate;
    @ApiModelProperty(value = "修改日期")
    private Date gmtModified;

    public Long getfFId() {
        return fFId;
    }

    public void setfFId(Long fFId) {
        this.fFId = fFId;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution == null ? null : distribution.trim();
    }

    public String getRepresent() {
        return represent;
    }

    public void setRepresent(String represent) {
        this.represent = represent == null ? null : represent.trim();
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