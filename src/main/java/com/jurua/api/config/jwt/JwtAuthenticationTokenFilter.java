package com.jurua.api.config.jwt;

import com.jurua.api.common.constants.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.jurua.api.common.constants.StatusCode.FILTER_OK;
import static com.jurua.api.common.constants.SysConstants.*;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * jwt 授权令牌过滤器，继承 OncePerRequestFilter
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    // header中的Authorization头
    @Value("${jwt.header}")
    private String authorization;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从header中得到授权token
        String requestHeader = request.getHeader(authorization);
        String apiPath = jwtTokenUtil.getApiPath(request);

        if (StringUtils.contains(apiPath, LOGIN) || StringUtils.contains(SWAGGER_URL, apiPath) || apiPath.matches(SWAGGER_RESOURCES_REGEX)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 登陆用户唯一标识（登录时生成）
        String uuid;
        // 认证令牌
        String token;
        // 得到header中的令牌，取得uuid
        if (requestHeader != null && requestHeader.startsWith(BEARER)) {
            token = requestHeader.substring(7);
            // 验证该token是否在令牌黑名单中 || 验证该令牌是否超时、jwt串是否符合要求等等
            if (redisTemplate.opsForSet().isMember(TOKEN_BLACK_LIST, token) || jwtTokenUtil.validateToken(token)) {
                // 返回令牌失效
                jwtTokenUtil.responseResult(response, StatusCode.TOKEN_INVALID);
                return;
            }
            // 校验通过后，取得令牌中的uuid
            uuid = jwtTokenUtil.getUuidFromToken(token);
            // 查看redis中是否存在与用户登陆时产生的值
            if (redisTemplate.opsForValue().get(uuid) == null) {
                // 返回会话过期（这里可能存在判断重复，token过期后这里访问不到，token刷新时，又会重置redis中的过期时间）
                jwtTokenUtil.responseResult(response, StatusCode.SESSION_TIME_OUT);
                return;
            }
            // 比较签名时的ip是否相同，如果不相同说明ip地址改变，token可能被劫持，需要重新登录（移动设备的IP经常换这点要注意）
            if (!StringUtils.equals(jwtTokenUtil.getRequestIp(request), jwtTokenUtil.getIssuerFromToken(token))) {
                jwtTokenUtil.responseResult(response, StatusCode.IP_EQUALS_ERROR);
                return;
            }
            // 过滤所有api
            StatusCode statusCode = jwtTokenUtil.aboutPath(request, token);
            // 如果过滤有问题，返回相应的状态
            if (statusCode != FILTER_OK) {
                jwtTokenUtil.responseResult(response, statusCode);
                return;
            }
            // 根据规则刷新令牌
            jwtTokenUtil.refreshToken(new HashMap<>(0), uuid, token, request, response);
            // 通过request可以得到当前的uuid，然后去redis取相应的对象进行操作
            request.setAttribute(UUID, uuid);
        }
        else {
            jwtTokenUtil.responseResult(response, StatusCode.TOKEN_INVALID);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
