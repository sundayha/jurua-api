package com.jurua.api.fishinfo.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@ApiModel(value = "鱼种下拉vo")
public class FishLibsVO implements Serializable {

    private static final long serialVersionUID = 7376607899328794177L;
    @ApiModelProperty(value = "鱼种id 主键")
    private Long fishId;
    @ApiModelProperty(value = "鱼名")
    private String fishName;

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    @Override
    public String toString() {
        return "FishLibsVO.....";
    }
}

