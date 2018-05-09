package com.jurua.api.user.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@ApiModel("用户登录查询对象")
public class UserLoginQ implements Serializable {
    private static final long serialVersionUID = -86206182381691017L;

    @ApiModelProperty(value = "手机号")
    private String phoneNum;
    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
