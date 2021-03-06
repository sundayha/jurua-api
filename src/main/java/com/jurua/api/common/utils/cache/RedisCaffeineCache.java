package com.jurua.api.common.utils.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.jurua.api.common.utils.cache.broadcast.CacheMsgBroadcast;
import org.redisson.api.RedissonClient;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 继承 AbstractValueAdaptingCache，使用 redisson 实现2级缓存
 */
public class RedisCaffeineCache extends AbstractValueAdaptingCache {

    private com.github.benmanes.caffeine.cache.Cache<Object, Object> cache;
    private RedissonClient redissonClient;
    /**
     * 缓存消息广播
     */
    private CacheMsgBroadcast cacheMsgBroadcast;
    /**
     * 缓存名
     */
    private String name;

    private static CacheManager cacheManager;

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/4 10:12 AM
     * @param name cache name
     * @param cache caffeine cache
     * @param allowNullValues 是否允许为 null
     * @param redissonClient redisson 访问接口
     * @apiNote spring 注入
     */
    public RedisCaffeineCache(String name, Cache<Object, Object> cache,
                              boolean allowNullValues, RedissonClient redissonClient,
                              CacheMsgBroadcast cacheMsgBroadcast, CacheManager cacheManager) {
        super(allowNullValues);
        this.cache = cache;
        this.name = name;
        this.redissonClient = redissonClient;
        this.cacheMsgBroadcast = cacheMsgBroadcast;
        RedisCaffeineCache.cacheManager = cacheManager;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        if (cache instanceof LoadingCache) {
            Object value = ((LoadingCache<Object, Object>) cache).get(key);
            return toValueWrapper(value);
        }
        return super.get(key);
    }

    @Override
    public <T> T get(Object key, final Callable<T> valueLoader) {
        return (T) fromStoreValue(cache.get(key, new LoadFunction(valueLoader)));
    }

    @Override
    protected Object lookup(Object key) {
        Object value = cache.getIfPresent(key);
        Object redisValue = null;
        if (Objects.isNull(value)) {
            redisValue = redissonClient.getMap(getName()).get(key);
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
        cache.put(key, toStoreValue(value));
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
        try {
            if (!Objects.isNull(key) && !Objects.isNull(value)) {
                redissonClient.getMap(getName()).put(key, value);
            }
            cache.put(key, toStoreValue(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 通知集群中的服务，使它们对应 key 的一级缓存失效
            cacheMsgBroadcast.sentEvict(getName(), cacheMsgBroadcast.getNetIdentity(), (String) key);
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, final Object value) {
        PutIfAbsentFunction callable = new PutIfAbsentFunction(value);
        Object result = cache.get(key, callable);
        return (callable.called ? null : toValueWrapper(result));
    }

    @Override
    public void evict(Object key) {
        try {
            if (!Objects.isNull(key)) {
                redissonClient.getMap(getName()).remove(key);
            }
            cache.invalidate(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 通知集群中的服务，使它们对应 key 的一级缓存失效
            cacheMsgBroadcast.sentEvict(getName(), cacheMsgBroadcast.getNetIdentity(), (String) key);
        }
    }

    @Override
    public void clear() {
        try {
            redissonClient.getMap(getName()).readAllMap();
            cache.invalidateAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 通知集群中的服务，使它们一级缓存全部失效
            cacheMsgBroadcast.sentAllClear(getName(), cacheMsgBroadcast.getNetIdentity());
        }

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

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/8 1:14 PM
     * @param key 缓存 key 值
     * @apiNote 接收来自其它集群中服务的通知后，用于广播清除一级缓存
     */
    public static void evict(String cacheName, String key) {
        RedisCaffeineCache redisCaffeineCache = (RedisCaffeineCache)cacheManager.getCache(cacheName);
        redisCaffeineCache.cache.invalidate(key);
    }

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/8 1:14 PM
     * @apiNote 接收来自其它集群中服务的通知后，用于广播清除一级缓存
     */
    public static void allClear(String cacheName) {
        RedisCaffeineCache redisCaffeineCache = (RedisCaffeineCache)cacheManager.getCache(cacheName);
        redisCaffeineCache.cache.invalidateAll();
    }
}
