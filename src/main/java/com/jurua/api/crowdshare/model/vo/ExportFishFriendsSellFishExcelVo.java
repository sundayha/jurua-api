package com.jurua.api.crowdshare.model.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@ApiModel("导出渔友出鱼excel vo")
public class ExportFishFriendsSellFishExcelVo implements Serializable {
    private static final long serialVersionUID = 404311375923656658L;

    @Excel(name = "QQ")
    @ApiModelProperty(value = "qq号")
    private Long qq;
    @Excel(name = "QQ昵称")
    @ApiModelProperty(value = "qq名")
    private String qqName;
    @Excel(name = "鱼名")
    @ApiModelProperty(value = "鱼名")
    private String fishName;
    @Excel(name = "学名")
    @ApiModelProperty(value = "鱼学名")
    private String scientificName;
    @Excel(name = "出处")
    @ApiModelProperty(value = "鱼出处、起源 “野生”、“f1”")
    private String provenance;
    @Excel(name = "长度 cm")
    @ApiModelProperty(value = "鱼长度 cm")
    private String fishHeight;
    @Excel(name = "售价 元")
    @ApiModelProperty(value = "价格 元")
    private BigDecimal fishPrice;
    @Excel(name = "数量")
    @ApiModelProperty(value = "数量。0 是1对，1 是一条")
    private String fishQuantity;
    @Excel(name = "优惠说明", width = 30)
    @ApiModelProperty(value = "优惠说明")
    private String discounts;
    @Excel(name = "发货条件说明", width = 50)
    @ApiModelProperty(value = "发货条件说明")
    private String sendOutCondition;
    @Excel(name = "不发货范围", width = 30)
    @ApiModelProperty(value = "不发货范围")
    private String noShipmentsScope;

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
}
