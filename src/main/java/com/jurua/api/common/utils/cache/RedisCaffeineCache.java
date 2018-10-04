package com.jurua.api.common.utils.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.redisson.api.RedissonClient;
import org.springframework.cache.caffeine.CaffeineCache;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;

import static com.jurua.api.common.constants.SysConstants.CAFFEINE_CACHE_JURUA_SERVICE_NAME;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 继承 CaffeineCache，使用 redisson 实现2级缓存
 */
public class RedisCaffeineCache extends CaffeineCache {

    private final com.github.benmanes.caffeine.cache.Cache<Object, Object> cache;

    private RedissonClient redissonClient;

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/4 10:12 AM
     * @param name cache name
     * @param cache caffeine cache
     * @param allowNullValues 是否允许为 null
     * @param redissonClient redisson 访问接口
     * @apiNote spring 注入
     */
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
        Object value = this.cache.getIfPresent(key);
        Object redisValue = null;
        if (Objects.isNull(value)) {
            redisValue = redissonClient.getMap(CAFFEINE_CACHE_JURUA_SERVICE_NAME).get(key);
        }
        // 如果二级缓存中有值
        if (!Objects.isNull(redisValue)) {
            // 则把二级缓存中的值放入一级缓存中
            this.putCaffeine(key, redisValue);
            // 赋值相当于 @Cacheable 注解不 miss
            value = redisValue;
        }
        return value;
    }

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/4 11:14 AM
     * @param key 键
     * @param value 值
     * @apiNote 只更新 caffeine 的缓存
     */
    private void putCaffeine(Object key, Object value) {
        this.cache.put(key, toStoreValue(value));
    }

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/4 9:36 AM
     * @param key 键
     * @param value 值
     * @apiNote 如果 @CachePut 或者 @Cacheable miss 则走该方法。value 为 null 时不存入 redis。
     * 因为 RMap 继承了 ConcurrentHashMap，ConcurrentHashMap 要走键值都不能为 null。为了不破坏兼容性，RMap 也不允许键值为 null
     */
    @Override
    public void put(Object key, Object value) {
        if (!Objects.isNull(value)) {
            redissonClient.getMap(CAFFEINE_CACHE_JURUA_SERVICE_NAME).put(key, value);
        }
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
        redissonClient.getMap(CAFFEINE_CACHE_JURUA_SERVICE_NAME).remove(key);
        this.cache.invalidate(key);
    }

    @Override
    public void clear() {
        redissonClient.getMap(CAFFEINE_CACHE_JURUA_SERVICE_NAME).readAllMap();
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
