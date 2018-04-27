package com.jurua.api.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@ApiModel(value = "用户下拉框VO")
public class UsersVO implements Serializable {

    private static final long serialVersionUID = 5751304284509489913L;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户名")
    private String personName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public String toString() {
        return "UsersVO.....";
    }
}
