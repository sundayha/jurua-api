package com.jurua.api.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Method;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 配置redis缓存
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/19 下午5:42
     * @apiNote 生成一个以md5加密，并由类名，方法名，参数名组成的key
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (Object target, Method method, Object... params) -> {
            StringBuilder sb = new StringBuilder();
            ObjectMapper mapper = new ObjectMapper();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                try {
                    sb.append(mapper.writeValueAsString(obj));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
        };
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/20 上午10:58
     * @param redisTemplate redis模板
     * @apiNote 返回实现redis的缓存管理器
     */
    //@Bean
    //public CacheManager cacheManager(RedisTemplate redisTemplate) {
    //    return new RedisCacheManager(redisTemplate);
    //}

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/20 上午11:09
     * @apiNote redis的Jedis工厂对象
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        //设置连接超时时间
        factory.setTimeout(0);
        factory.setDatabase(0);
        return factory;
    }

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/10/20 上午11:09
     * @param factory redis 工厂对象
     * @apiNote 返回redis模板
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        // 对象序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
