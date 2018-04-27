package com.jurua.api.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
/**
 * @author 张博【zhangb@lianliantech.cn】
 * @since 2018/4/23 下午1:26
 * 用户表
 */
@ApiModel(value = "用户表")
public class User implements Serializable {
    private static final long serialVersionUID = 7665011784965374476L;
    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "登录名")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
    @ApiModelProperty(value = "用户名")
    private String personName;
    @ApiModelProperty(value = "电子信箱")
    private String email;
    @ApiModelProperty(value = "手机号")
    private String phoneNum;
    @ApiModelProperty(value = "ip地址")
    private String ipAddress;
    @ApiModelProperty(value = "用户是否被禁言")
    private Boolean isUserState;
    @ApiModelProperty(value = "创建日期")
    private Date gmtCreate;
    @ApiModelProperty(value = "修改日期")
    private Date gmtModified;
    @ApiModelProperty(value = "qq号")
    private Long qq;
    @ApiModelProperty(value = "qq名")
    private String qqName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public Boolean getIsUserState() {
        return isUserState;
    }

    public void setIsUserState(Boolean isUserState) {
        this.isUserState = isUserState;
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
        this.qqName = qqName == null ? null : qqName.trim();
    }
}