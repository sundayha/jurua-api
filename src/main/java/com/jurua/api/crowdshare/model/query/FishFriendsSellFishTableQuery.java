package com.jurua.api.crowdshare.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@ApiModel(value = "渔友出鱼表格查询条件")
public class FishFriendsSellFishTableQuery implements Serializable {
    private static final long serialVersionUID = 1693885264311958356L;
    @ApiModelProperty(value = "鱼名")
    private String fishName;
    @ApiModelProperty(value = "qq号")
    private String qq;

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }
}
