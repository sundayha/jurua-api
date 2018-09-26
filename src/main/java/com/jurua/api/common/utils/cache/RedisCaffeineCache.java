package com.jurua.api.common.utils.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.redisson.api.RedissonClient;
import org.springframework.cache.caffeine.CaffeineCache;

import java.util.concurrent.Callable;
import java.util.function.Function;

import static com.jurua.api.common.constants.SysConstants.CAFFEINE_CACHE_JURUA_SERVICE_NAME;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public class RedisCaffeineCache extends CaffeineCache {

    private final com.github.benmanes.caffeine.cache.Cache<Object, Object> cache;

    private RedissonClient redissonClient;

    public RedisCaffeineCache(String name, Cache<Object, Object> cache, boolean allowNullValues, RedissonClient redissonClient) {
        super(name, cache, allowNullValues);
        this.cache = cache;
        this.redissonClient = redissonClient;
    }

    @Override
    public ValueWrapper get(Object key) {
        if (this.cache instanceof LoadingCache) {
            Object value = ((LoadingCache<Object, Object>) this.cache).get(key);
            return toValueWrapper(value);
        }
        return super.get(key);
    }

    @Override
    public <T> T get(Object key, final Callable<T> valueLoader) {
        return (T) fromStoreValue(this.cache.get(key, new LoadFunction(valueLoader)));
    }

    @Override
    protected Object lookup(Object key) {
        return this.cache.getIfPresent(key);
    }

    @Override
    public void put(Object key, Object value) {
        redissonClient.getMap(CAFFEINE_CACHE_JURUA_SERVICE_NAME).put(key, value);
        this.cache.put(key, toStoreValue(value));
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, final Object value) {
        PutIfAbsentFunction callable = new PutIfAbsentFunction(value);
        Object result = this.cache.get(key, callable);
        return (callable.called ? null : toValueWrapper(result));
    }

    @Override
    public void evict(Object key) {
        this.cache.invalidate(key);
    }

    @Override
    public void clear() {
        this.cache.invalidateAll();
    }

    private class PutIfAbsentFunction implements Function<Object, Object> {

        private final Object value;

        private boolean called;

        public PutIfAbsentFunction(Object value) {
            this.value = value;
        }

        @Override
        public Object apply(Object key) {
            this.called = true;
            return toStoreValue(this.value);
        }
    }

    private class LoadFunction implements Function<Object, Object> {

        private final Callable<?> valueLoader;

        public LoadFunction(Callable<?> valueLoader) {
            this.valueLoader = valueLoader;
        }

        @Override
        public Object apply(Object o) {
            try {
                return toStoreValue(valueLoader.call());
            }
            catch (Exception ex) {
                throw new ValueRetrievalException(o, valueLoader, ex);
            }
        }
    }
}
