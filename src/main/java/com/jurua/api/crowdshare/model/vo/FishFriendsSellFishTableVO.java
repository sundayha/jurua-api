package com.jurua.api.crowdshare.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@ApiModel(value = "渔友出鱼列表VO对象")
public class FishFriendsSellFishTableVO implements Serializable {
    private static final long serialVersionUID = 3634329371051039919L;
    @ApiModelProperty(value = "主键Id")
    private Long fBId;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "鱼种id 主键")
    private Long fishId;
    @ApiModelProperty(value = "qq号")
    private Long qq;
    @ApiModelProperty(value = "qq名")
    private String qqName;
    @ApiModelProperty(value = "鱼名")
    private String fishName;
    @ApiModelProperty(value = "鱼学名")
    private String scientificName;
    @ApiModelProperty(value = "鱼出处、起源 “野生”、“f1”")
    private String provenance;
    @ApiModelProperty(value = "鱼长度 cm")
    private String fishHeight;
    @ApiModelProperty(value = "价格 元")
    private BigDecimal fishPrice;
    @ApiModelProperty(value = "数量。0 是1对，1 是一条")
    private String fishQuantity;
    @ApiModelProperty(value = "优惠说明")
    private String discounts;
    @ApiModelProperty(value = "发货条件说明")
    private String sendOutCondition;
    @ApiModelProperty(value = "不发货范围")
    private String noShipmentsScope;

    public Long getfBId() {
        return fBId;
    }

    public void setfBId(Long fBId) {
        this.fBId = fBId;
    }

    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }

    public String getQqName() {
        return qqName;
    }

    public void setQqName(String qqName) {
        this.qqName = qqName;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getFishHeight() {
        return fishHeight;
    }

    public void setFishHeight(String fishHeight) {
        this.fishHeight = fishHeight;
    }

    public BigDecimal getFishPrice() {
        return fishPrice;
    }

    public void setFishPrice(BigDecimal fishPrice) {
        this.fishPrice = fishPrice;
    }

    public String getFishQuantity() {
        return fishQuantity;
    }

    public void setFishQuantity(String fishQuantity) {
        this.fishQuantity = fishQuantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFishId() {
        return fishId;
    }

    public void setFishId(Long fishId) {
        this.fishId = fishId;
    }

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    public String getSendOutCondition() {
        return sendOutCondition;
    }

    public void setSendOutCondition(String sendOutCondition) {
        this.sendOutCondition = sendOutCondition;
    }

    public String getNoShipmentsScope() {
        return noShipmentsScope;
    }

    public void setNoShipmentsScope(String noShipmentsScope) {
        this.noShipmentsScope = noShipmentsScope;
    }

    @Override
    public String toString() {
        return "FishFriendsSellFishTableVO.....";
    }
}
