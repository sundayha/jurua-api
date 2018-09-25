package com.jurua.api.common.utils.cache;

import com.github.benmanes.caffeine.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author 张博【zhangb@novadeep.com】
 */
@Component
public class CaffeineUtils<K, V> implements CaffeineUtilsI<K, V> {

    private final static Logger logger = LoggerFactory.getLogger(CaffeineUtils.class);

    @Autowired
    private Cache<K, V> cache;

    @Override
    public boolean containsKey(K key) {
        notNull(key, "caffeine->containsKey->键为空");
        return Objects.nonNull(cache.getIfPresent(key));
    }

    @Override
    public void put(K key, V value) {
        notNull(key, value, "caffeine->put->键或值为空");
        // 如果 key 存在则更新，不存在则什么都不做
        //if (containsKey(key)) {
            cache.put(key, value);
        //}
    }

    @Override
    public void del(K key) {
        notNull(key, "caffeine->del->键为空");
        cache.invalidate(key);
    }

    @Override
    public void delAll() {
        cache.invalidateAll();
    }

    @Override
    public void delKeys(Iterator<?> keys) {
        notNull(keys, "caffeine->delKeys->键为空");
        cache.invalidate(keys);
    }

    @Override
    public V getValue(K key) {
        notNull(key, "caffeine->getValue->键为空");
        return cache.getIfPresent(key);
    }

    @Override
    public V getValue(K key, Function<K, V> mappingFunction) {
        notNull(key, mappingFunction, "caffeine->getValue->键或 mappingFunction 为空");
        // 如果是 null 从 redis 中取值回填
        return cache.get(key, mappingFunction);
    }

    private void notNull(K key, String msg) {
        if (Objects.isNull(key)) {
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }
    }

    private void notNull(K key, V value, String msg) {
        if (Objects.isNull(key) || Objects.isNull(value)) {
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }
    }

    private void notNull(Iterator<?> keys, String msg) {
        if (Objects.isNull(keys)) {
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }
    }

    private void notNull(K key, Function<K, V> mappingFunction, String msg) {
        if (Objects.isNull(key) || Objects.isNull(mappingFunction)) {
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }
    }
}
