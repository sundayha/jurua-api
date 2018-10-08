package com.jurua.api.config.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.jurua.api.common.utils.cache.RedisCaffeineCacheManager;
import com.jurua.api.common.utils.cache.broadcast.CacheMsgBroadcast;
import org.redisson.api.RedissonClient;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.jurua.api.common.constants.SysConstants.CAFFEINE_CACHE_JURUA_SERVICE_NAME;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 使用 caffeine 做为一级缓存
 */
@Configuration
public class CaffeineConfig {

    private RedissonClient redissonClient;
    private CacheMsgBroadcast cacheMsgBroadcast;

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/9/26 下午5:35
     * @param redissonClient redisson 客户端
     * @apiNote 在该类注入 redissonClient
     */
    public CaffeineConfig(RedissonClient redissonClient, CacheMsgBroadcast cacheMsgBroadcast) {
        this.redissonClient = redissonClient;
        this.cacheMsgBroadcast = cacheMsgBroadcast;
    }

    @Bean
    public CacheManager caffeineCacheManager() {
        final RedisCaffeineCacheManager manager = new RedisCaffeineCacheManager(redissonClient, cacheMsgBroadcast);
        manager.setCaffeine(caffeineBuilder());
        manager.setCacheNames(Arrays.asList(CAFFEINE_CACHE_JURUA_SERVICE_NAME));
        return manager;
    }

    private Caffeine<Object, Object> caffeineBuilder() {
        return Caffeine.newBuilder()
                .expireAfterWrite(7, TimeUnit.DAYS)
                .initialCapacity(100)
                .maximumSize(1000)
                .removalListener(
                        (Object key, Object value, RemovalCause cause) ->
                                System.out.println("caffeineCacheManager -> 移除键" + key + "值：" + value)
                )
                .recordStats();
    }

    @Bean
    public Cache cache() {
        return caffeineBuilder().build();
    }

    //@Bean
    //public Cache cache() {
    //    return Caffeine.newBuilder()
    //            // 访问后7天过期，期间再次访问，则过期时间刷新
    //            .expireAfterAccess(7, TimeUnit.DAYS)
    //            // 初始容量
    //            .initialCapacity(100)
    //            // 最大缓存数
    //            .maximumSize(1000)
    //            // 监听值更新或移除
    //            .removalListener((Object key, Object value, RemovalCause cause) -> System.out.println("caffeineCache -> 移除键：" + key + " 对应值值：" + value))
    //            // 启用操作缓存期间的记录
    //            .recordStats()
    //            .build();
    //}
}
