package com.jurua.api.common.constants;

/**
 * 创建人：张博
 */
public enum StatusCode {

    // 通用状态码
    COMMON_OK(2000000, "成功"),
    // 系统状态码
    SESSION_TIME_OUT(1000000, "session超时"),
    IP_EQUALS_ERROR(1000001, "ip错误"),
    TOKEN_TIME_OUT(1000002, "令牌超时"),
    TOKEN_INVALID(1000003, "令牌失效"),
    FILTER_OK(1000004, "过滤器通过正常"),
    // 业务状态码
    TEST(100001, "StatusCode测试"),
    ;

    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
