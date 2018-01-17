package com.jurua.api.common.constants;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 系统常量
 */
public class SysConstants {

    /**
     * 登入
     */
    public final static String LOGIN = "/login";
    /**
     * swagger2的url
     */
    public final static String SWAGGER_URL = "/swagger-ui.html, /swagger-resources/configuration/ui, /swagger-resources, /v2/api-docs, /swagger-resources/configuration/security";

    /**
     *  swagger2的html中请求资源，js css等
     */
    public final static String SWAGGER_RESOURCES_REGEX = "/webjars.*$";
    /**
     * 登出path
     */
    public final static String LOG_OUT = "/logOut";
    /**
     * 用户登录时唯一的串号，这里是 require.setAttribute 以后可能删除
     */
    public final static String UUID = "uuid";
    /**
     * 令牌黑名单
     */
    public final static String TOKEN_BLACK_LIST = "tokenBlackList";

    /**
     * 客户端发送的 Authorization HTTP HEADER 格式是 "Bearer YOUR_JWT_TOKEN",这是OAuth的规范规定的。Bearer 后有空格
     */
    public final static String BEARER = "Bearer ";
}
