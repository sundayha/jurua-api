package com.jurua.api.common.constants;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public class Constants {
    /**
     * 登出path
     */
    public final static String LOG_OUT = "/logOut";
    /**
     * 用户登录时唯一的串号，这里是require.setAttribute 以后可能删除
     */
    public final static String UUID = "uuid";
    /**
     * 令牌黑名单
     */
    public final static String TOKEN_BLACK_LIST = "tokenBlackList";

    /**
     * 客户端发送的Authorization HTTP HEADER格式是 "Bearer YOUR_JWT_TOKEN",这是OAuth的规范规定的。Bearer 后有空格
     */
    public final static String BEARER = "Bearer ";
}
