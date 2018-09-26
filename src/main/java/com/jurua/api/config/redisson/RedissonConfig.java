package com.jurua.api.config.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 使用 redisson 管理的 redis 集群做为二级缓存
 */
@Configuration
public class RedissonConfig {

    @Bean(destroyMethod="shutdown")
    RedissonClient redisson(@Value("classpath:/redisson.yaml") Resource configFile) throws IOException {
        Config config = Config.fromYAML(configFile.getInputStream());
        return Redisson.create(config);
    }

    //@Bean
    //CacheManager cacheManager(RedissonClient redissonClient) {
    //    return new RedissonSpringCacheManager(redissonClient, "classpath:/redisson.yaml");
    //}
}
