package com.jurua.api.crowdshare.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 张博【zhangb@lianliantech.cn】
 * @since 2018/4/23 下午1:44
 */
@ApiModel(value = "鱼泡表")
public class FishBubble implements Serializable {
    private static final long serialVersionUID = 4880050570611911013L;
    @ApiModelProperty(value = "主键Id")
    private Long fBId;
    @ApiModelProperty(value = "与“用户表，user”关联")
    private Long userId;
    @ApiModelProperty(value = "与“鱼种表，fish_lib”关联")
    private Long fishId;
    @ApiModelProperty(value = "鱼泡标题")
    private String bubbleTitle;
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
    @ApiModelProperty(value = "创建日期")
    private Date creationDate;
    @ApiModelProperty(value = "是否展示鱼泡：0 不展示，1 展示")
    private Boolean isShowBubble;
    @ApiModelProperty(value = "被擦亮的日期，如果过了15天后，重置到第15天的当天日期，并且吧show_bubble，设置为0")
    private Date showDate;
    @ApiModelProperty(value = "创建日期")
    private Date gmtCreate;
    @ApiModelProperty(value = "修改日期")
    private Date gmtModified;

    public Long getfBId() {
        return fBId;
    }

    public void setfBId(Long fBId) {
        this.fBId = fBId;
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

    public String getBubbleTitle() {
        return bubbleTitle;
    }

    public void setBubbleTitle(String bubbleTitle) {
        this.bubbleTitle = bubbleTitle == null ? null : bubbleTitle.trim();
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance == null ? null : provenance.trim();
    }

    public String getFishHeight() {
        return fishHeight;
    }

    public void setFishHeight(String fishHeight) {
        this.fishHeight = fishHeight == null ? null : fishHeight.trim();
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
        this.fishQuantity = fishQuantity == null ? null : fishQuantity.trim();
    }

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts == null ? null : discounts.trim();
    }

    public String getSendOutCondition() {
        return sendOutCondition;
    }

    public void setSendOutCondition(String sendOutCondition) {
        this.sendOutCondition = sendOutCondition == null ? null : sendOutCondition.trim();
    }

    public String getNoShipmentsScope() {
        return noShipmentsScope;
    }

    public void setNoShipmentsScope(String noShipmentsScope) {
        this.noShipmentsScope = noShipmentsScope == null ? null : noShipmentsScope.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getIsShowBubble() {
        return isShowBubble;
    }

    public void setIsShowBubble(Boolean isShowBubble) {
        this.isShowBubble = isShowBubble;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
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