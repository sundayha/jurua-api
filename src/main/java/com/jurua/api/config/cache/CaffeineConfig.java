package com.jurua.api.config.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@Configuration
public class CaffeineConfig<K, V> {

    @Bean("caffeineCacheManager")
    @Primary
    public CacheManager caffeineCacheManager() {
        final CaffeineCacheManager manager = new CaffeineCacheManager("caffeineCache");
        final Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .removalListener((Object key, Object value, RemovalCause cause) -> System.out.println("移除键" + key + "值：" + value));
        manager.setCaffeine(caffeineBuilder);
        return manager;

    }

    @Bean("caffeineCache")
    public Cache<K, V> caffeineCache() {
        return Caffeine.newBuilder()
                // 访问后7天过期，期间再次访问，则过期时间刷新
                .expireAfterAccess(7, TimeUnit.DAYS)
                // 初始容量
                .initialCapacity(100)
                // 最大缓存数
                .maximumSize(1000)
                // 监听值更新或移除
                .removalListener((Object key, Object value, RemovalCause cause) -> System.out.println("移除键：" + key + " 对应值值：" + value))
                // 启用操作缓存期间的记录
                .recordStats()
                .build();
    }
}
