package com.jurua.api.common.model.result;


import com.jurua.api.common.constants.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 时间：2017/9/6 下午4:49
 *
 * 响应前端结果对象
 */
@Api(value = "ResultApi", description = "对返回结果进行统一封装")
public class ResultApi<T> {

    @ApiModelProperty("状态码")
    private int statusCode = StatusCode.COMMON_OK.getCode();
    @ApiModelProperty("结果信息")
    private String message = StatusCode.COMMON_OK.getMessage();
    @ApiModelProperty("结果数据")
    private T data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

