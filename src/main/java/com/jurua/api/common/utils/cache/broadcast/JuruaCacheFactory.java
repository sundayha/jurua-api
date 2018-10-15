package com.jurua.api.common.utils.cache.broadcast;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 缓存通知工厂
 *
 * @param <I> 缓存通知接口
 */
public class JuruaCacheFactory<I> {

    @SuppressWarnings("unchecked")
    public <T> T newInstance(Class<T> tClass) {
        I i = null;
        try {
            i = (I) Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) i;
    }
}
