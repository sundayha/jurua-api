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
     * 登出
     */
    public final static String LOGOUT = "/logOut";
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
    /**
     * 应用dev方式启动
     */
    public final static String APPLICATION_START_DEV = "DEV";
    /**
     * 在cookie中存token串的名字
     */
    public final static String JWT_TOKEN = "jwtToken";

    /**
     * caffeine 缓存名
     */
    public final static String CAFFEINE_CACHE_JURUA_SERVICE_NAME = "juruaServiceCache";

    /**
     * redisson map 实例名
     */
    public final static String REDISSON_MAP_INSTANCE_NAME = "users";

    /**
     * 启用 redisson 进行广播通知
     */
    public final static String REDISSON_NOTICE = "redissonNotice";
}
