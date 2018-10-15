package com.jurua.api.config.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public class AsyncCaffeineConfig {

    private CompletableFuture completableFuture(Object o) {
        return new CompletableFuture();
    }

    //@Bean("asyncCaffeine")
    public AsyncLoadingCache<Object, Object> AsyncCaffeine() {
        return Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(this::completableFuture);
    }
}
