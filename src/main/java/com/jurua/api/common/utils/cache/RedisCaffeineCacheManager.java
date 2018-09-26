package com.jurua.api.common.utils.cache;

import org.redisson.api.RedissonClient;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCacheManager;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public class RedisCaffeineCacheManager extends CaffeineCacheManager {

    private RedissonClient redissonClient;

    public RedisCaffeineCacheManager(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/9/26 下午2:50
     * @param name 缓存名
     * @apiNote 新建一个 Caffeine 缓存实例，并指定缓存名
     * @return Cache 返回 spring Caffeine 缓存适配器
     */
    @Override
    protected Cache createCaffeineCache(String name) {
        // 返回一个自定义 redis caffeine 缓存实例
        return new RedisCaffeineCache(name, createNativeCaffeineCache(name), isAllowNullValues(), redissonClient);
    }
}
