package com.jurua.api.common.utils.cache.broadcast;

import org.redisson.api.RedissonClient;

import static com.jurua.api.common.constants.SysConstants.REDISSON_NOTICE;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 缓存通知工厂
 *
 * @param <I> 缓存通知接口
 */
public class JuruaCacheFactory<I> {

    public <T extends I> T newInstance(Class<T> tClass, RedissonClient redissonClient, String type) {
        T t = null;
        try {
            if (REDISSON_NOTICE.equals(type)) {
                t = tClass.cast(Class.forName(tClass.getName()).getConstructor(RedissonClient.class).newInstance(redissonClient));
            } else {
                t = tClass.cast(Class.forName(tClass.getName()).getConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
