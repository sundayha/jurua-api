package com.jurua.api.common.utils.cache.broadcast;

import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张博 [zhangb@novadeep.com]
 */
@Configuration
public class InitCache {

    private RedissonClient redissonClient;

    public InitCache(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Bean
    public CacheMsgBroadcast initCaches() {
        JuruaCacheFactory<CacheMsgBroadcast> cacheFactory = new JuruaCacheFactory<>();
        //return  cacheFactory.newInstance(RedissonBroadcast.class, redissonClient, "redisson");
        return  cacheFactory.newInstance(RabbitMQBroadcast.class, null, "");
    }
}
