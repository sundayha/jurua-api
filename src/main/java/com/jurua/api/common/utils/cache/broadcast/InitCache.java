package com.jurua.api.common.utils.cache.broadcast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张博 [zhangb@novadeep.com]
 */
@Configuration
public class InitCache {

    @Bean
    public CacheMsgBroadcast initCache() {
        JuruaCacheFactory<CacheMsgBroadcast> cacheFactory = new JuruaCacheFactory<>();
        return  cacheFactory.newInstance(RabbitMQBroadcast.class);
    }
}
