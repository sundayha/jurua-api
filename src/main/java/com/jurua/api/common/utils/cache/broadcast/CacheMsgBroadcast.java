package com.jurua.api.common.utils.cache.broadcast;

import com.jurua.api.common.utils.cache.RedisCaffeineCache;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 缓存消息广播。集群时，当某一台机器的一级缓存更改后，使其它机器的一级缓存失效
 */
public interface CacheMsgBroadcast {

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/5 4:58 PM
     * @apiNote 链接 rabbitmq、redisson
     */
    void connect();

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/5 4:58 PM
     * @param msg 消息对象
     * @apiNote 具体实现调用其实现类方法。广播发送消息
     */
    void broadcast(MsgType msg);

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/8 10:54 AM
     * @apiNote 标识网络中发广播的机器
     * @return String
     */
    String getNetIdentity();

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/8 11:28 AM
     * @param cacheName 缓存名称
     * @param netIdentity app 地址标识
     * @param key 缓存 key 值
     * @apiNote 委派，向集群发送消息，使一级缓存失效
     */
    default void sentEvict(String cacheName, String netIdentity, String key) {
        broadcast(new MsgType(cacheName, netIdentity, MsgType.EVENT, key));
    }

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/8 12:55 PM
     * @param cacheName 缓存名称
     * @param netIdentity app 地址标识
     * @apiNote 委派，向集群发送消息，使一级缓存失效
     */
    default void sentAllClear(String cacheName, String netIdentity) {
        broadcast(new MsgType(cacheName, netIdentity, MsgType.CLEAR));
    }

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/8 12:55 PM
     * @param cacheName 缓存名称
     * @param key 缓存 key 值
     * @apiNote 使当前 key 值的缓存失效(升级到 jdk 8以上降此方法定义为私有)
     */
    default void evict(String cacheName, String key) {
        RedisCaffeineCache.evict(cacheName, key);
    }

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/8 12:57 PM
     * @param cacheName 缓存名称
     * @apiNote 使一级缓存失效（升级到 jdk 8以上降此方法定义为私有）
     */
    default void allClear(String cacheName) {
        RedisCaffeineCache.allClear(cacheName);
    }

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/8 12:57 PM
     * @param msgType 消息类型
     * @apiNote 根据消息类型，选择适当的缓存失效方法
     */
    default void switchMsg(MsgType msgType) {
        switch (msgType.msgType) {
            case MsgType.EVENT:
                evict(msgType.cacheName, msgType.key);
                break;
            case MsgType.CLEAR:
                allClear(msgType.cacheName);
                break;
            default:
                break;
        }
    }
}
