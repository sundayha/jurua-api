package com.jurua.api.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jurua.api.common.constants.StatusCode;
import com.jurua.api.common.model.result.ResultApi;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.jurua.api.common.constants.SysConstants.*;
import static com.jurua.api.common.constants.StatusCode.COMMON_OK;
import static com.jurua.api.common.constants.StatusCode.FILTER_OK;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * jwt token
 */
@Component
public class JwtTokenUtil {
    @Value("${jwt.apiKey}")
    private String apiKey;
    @Value("${jwt.exp}")
    private Long exp;
    @Value("${jwt.header}")
    private String authorization;
    @Value("${server.session.timeout}")
    private long serverSessionTimeout;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 加密方式
     */
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/17 下午5:54
     * @apiNote 根据自定义签名密钥，返回生成key
     */
    private Key getJwtKey() {
        // 将apiKey转成Base64的字节数组
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey);
        // 根据算法生成一个密钥
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/18 上午10:20
     * @param claims 载荷数据
     * @param uuid 用户登陆时产生的唯一标识
     * @param request 请求对象
     * @param response 响应对象
     * @apiNote 生成jwt token串
     */
    public Boolean generateToken(Map<String, Object> claims, String uuid, HttpServletRequest request, HttpServletResponse response) {
        Date now = new Date();
        try {
            JwtBuilder builder = Jwts.builder()
                    // 设置载荷内容
                    .setClaims(claims)
                    // 设置token创建时间
                    .setIssuedAt(now)
                    // 设置token过期时间
                    .setExpiration(calculateExpirationDate(now))
                    // 设置主题
                    .setSubject(uuid)
                    // 设置token使用者
                    .setIssuer(getRequestIp(request))
                    // 设置接收者
                    .setAudience("jurua-api")
                    // 根据算法和密钥生成jwt token
                    .signWith(signatureAlgorithm, getJwtKey());
            String jwtToken = builder.compact();
            // 响应返回到前端的Authorization响应头信息
            response.setHeader(authorization, BEARER.concat(jwtToken));
            // 把用户对象，放入redis中并设置30分钟redis key过期，过期后自动删除
            redisTemplate.opsForValue().set(uuid, uuid, serverSessionTimeout, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/24 上午11:18
     * @param claims 载荷数据
     * @param uuid 用户登陆时产生的唯一标识
     * @param oldToken 旧令牌
     * @param request 请求对象
     * @param response 响应对象
     * @apiNote 当token在过滤器中成功验证后，刷新token，并返回前端
     */
    protected void refreshToken(Map<String, Object> claims, String uuid, String oldToken, HttpServletRequest request, HttpServletResponse response) {
        // 刷新令牌时，将旧令牌先放入，响应头中，如果令牌过期后，重新生成令牌，在该函数中覆盖这个响应头
        response.setHeader(authorization, BEARER.concat(oldToken));
        Date expDate = getExpirationDateFromToken(oldToken);
        // 如果到刷新令牌时，expDate为空说明前面验证时令牌已经验证通过，但是到刷新令牌时，令牌刚过期，那么这时，刷新令牌
        if (expDate == null) {
            generateNewTokenAndAddOldTokenTOBlackList(claims, uuid, oldToken, request, response);
        } else {
            // 得到过期时间前5分钟的时间
            Date date = new Date(expDate.getTime() - 300 * 1000);
            // 得到当前时间
            Date now = new Date();
            // 如果当前时间，大于过期时间5分钟时间并且小与过期时间，那么刷新令牌
            if (date.before(now) && now.before(expDate)) {
                generateNewTokenAndAddOldTokenTOBlackList(claims, uuid, oldToken, request, response);
            }
        }
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/25 上午10:46
     * @param claims 载荷数据
     * @param uuid 用户登陆时产生的唯一标识
     * @param oldToken 旧令牌
     * @param request 请求对象
     * @param response 响应对象
     * @apiNote 生成新的令牌，把老的令牌放进令牌黑名单里
     */
    private void generateNewTokenAndAddOldTokenTOBlackList(Map<String, Object> claims, String uuid, String oldToken, HttpServletRequest request, HttpServletResponse response) {
        // 如果成功生成新的token，那么把老的token放入令牌黑名单中
        if (generateToken(claims, uuid, request, response)) {
            addTokenBlackList(oldToken);
        }
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/18 下午2:45
     * @param token 令牌
     * @apiNote 校验令牌
     */
    protected Boolean validateToken(String token) {
        return isTokenExpired(token);
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/18 上午11:05
     * @param token 令牌
     * @apiNote 从令牌得到uuid
     */
    protected String getUuidFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/18 上午11:08
     * @param token 令牌
     * @apiNote 从令牌中得到签发时间
     */
    protected Date getIssuedAtFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/24 上午10:47
     * @param token 令牌
     * @apiNote 从令牌中得到ip
     */
    protected String getIssuerFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuer);
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/18 上午11:17
     * @param token 令牌
     * @apiNote 从令牌中得到令牌过期时间
     */
    protected Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/18 上午11:28
     * @param token 令牌
     * @apiNote 判断令牌是否过期
     */
    private Boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token) == null;
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/18 上午11:38
     * @param createdDate 令牌创建时间
     * @apiNote 计算令牌过期时间
     */
    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + exp * 1000);
        //return new Date(createdDate.getTime() + exp);
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/18 上午10:58
     * @param token token
     * @param claimsResolver 函数式接口
     * @apiNote 返回对应的函数值
     */
    private  <R> R getClaimFromToken(String token, Function<Claims, R> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        // 为空说明令牌过期失效、jws签名认证失效、不是一个有效的jwt串
        if (claims == null) {
            return null;
        }
        return claimsResolver.apply(claims);
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/18 上午10:57
     * @param token 令牌
     * @apiNote 得到Claims
     */
    private Claims getAllClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(apiKey))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // 令牌已过期
            return claims;
        }
        return claims;
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/23 下午5:20
     * @param response 响应对象
     * @param statusCode 状态码
     * @apiNote 从过滤器中，返回结果到前端
     */
    protected void responseResult(HttpServletResponse response, StatusCode statusCode) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            ResultApi<Map<String, String>> ra = new ResultApi<>();
            ra.setMessage(statusCode.getMessage());
            ra.setStatusCode(statusCode.getCode());
            out.write(mapper.writeValueAsString(ra));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/24 上午10:26
     * @param request 请求对象
     * @apiNote 得到请求Ip 
     */
    protected String getRequestIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isEmpty(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/24 下午7:52
     * @param token 将要加入令牌黑名单中的令牌
     * @apiNote 将令牌加入令牌黑名单中
     */
    private void addTokenBlackList(String token) {
        redisTemplate.opsForSet().add(TOKEN_BLACK_LIST, token);
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/30 上午10:38
     * @param request 请求对象
     * @apiNote 得到当前api地址
     */
    public String getApiPath(HttpServletRequest request) {
        return request.getRequestURI().substring(request.getContextPath().length());
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/24 下午5:55
     * @param request 请求对象
     * @param token 登出时，要被放进令牌黑名单里的令牌
     * @apiNote 处理拦截各种api的方法（暂定）
     */
    protected StatusCode aboutPath(HttpServletRequest request, String token) {
        // 得到api资源地址
        String apiPath = getApiPath(request);
        // 如果是登出
        if (StringUtils.contains(apiPath, LOG_OUT)) {
            // 删除redis中与登陆信息相关的key-value
            redisTemplate.delete(getUuidFromToken(token));
            // 然后把该令牌加入令牌黑名单
            addTokenBlackList(token);
            //responseResult(response, COMMON_OK);
            return COMMON_OK;
        } else {
            //responseResult(response, TEST);
            return FILTER_OK;
        }
         //&& StringUtils.contains(apiPath, "jurua")
    }
}
