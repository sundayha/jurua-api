package com.jurua.api.common.utils.cache.broadcast;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static com.jurua.api.common.constants.SysConstants.REDISSON_NOTICE;

/**
 * @author 张博 [zhangb@novadeep.com]
 */
@Configuration
public class InitCache {

    @Value("${application.broadcast}")
    private String broadcast;
    private RedissonClient redissonClient;

    public InitCache(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @PostConstruct
    @Bean
    public CacheMsgBroadcast initCaches() {
        JuruaCacheFactory<CacheMsgBroadcast> cacheFactory = new JuruaCacheFactory<>();
        if (REDISSON_NOTICE.equals(broadcast)) {
            return  cacheFactory.newInstance(RedissonBroadcast.class, redissonClient, REDISSON_NOTICE);
        } else {
            return  cacheFactory.newInstance(RabbitMQBroadcast.class);
        }
    }
}
