package com.jurua.api.common.utils.cache;

import java.util.Iterator;
import java.util.function.Function;

/**
 * @author 张博【zhangb@novadeep.com】
 *
 * 调用 caffeine 提供的方法，其方法均为线程安全。
 */
public interface CaffeineUtilsI<K, V> {

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/8/30 下午4:36
     * @param key 键
     * @apiNote 是否包含 key
     * @return boolean
     * @throws
     */
    boolean containsKey(K key);

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/8/31 上午9:23
     * @param key 键
     * @param value 值
     * @apiNote 当 key 对应的值为 null 时，put 方法相当于插入。当 key 对应的值不为 null 时，put 方法 相当于更新
     * @throws
     */
    void put(K key, V value);

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/8/31 上午9:25
     * @param key 键
     * @apiNote 删除 key 对应的 value
     * @throws
     */
    void del(K key);

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/8/31 上午9:36
     * @apiNote 移除全部 cache
     * @throws
     */
    void delAll();

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/8/31 上午9:45
     * @param keys 多个 key
     * @apiNote 根据多个 key 删除对应的值
     * @throws
     */
    void delKeys(Iterator<?> keys);

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/8/30 下午9:48
     * @param key 键
     * @apiNote 得到当前 key 的值
     * @return V
     * @throws
     */
    V getValue(K key);

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/8/31 上午9:26
     * @param key 键
     * @param mappingFunction key 对应的值为 null 时，根据 mappingFunction 方法生成新的值
     * @apiNote 返回 key 对应的值，如果 key 对应的值为 null 时，根据 mappingFunction 方法生成新的值
     * @return V
     * @throws
     */
    V getValue(K key, Function<K, V> mappingFunction);

}
