package com.jurua.api.common.utils.cache.broadcast;

import com.google.gson.Gson;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 消息类型
 */
public class MsgType {

    /**
     * 驱逐单个 key
     */
    public final static int EVENT = 0;

    /**
     * 全部取消
     */
    public final static int CLEAR = 1;

    /**
     * 消息类型，是 EVENT 或 CLEAR
     */
    public int msgType;

    /**
     * 缓存 key 值
     */
    public String key;

    /**
     * app 地址标识，集群中唯一标识
     */
    public String netIdentity;

    /**
     * 缓存名，表示某个方法调用的返回结果存储在指定的缓存名下
     */
    public String cacheName;

    /**
     * 创建人：张博 [zhangb@novadeep.com]
     * 时间：2018/10/25 4:03 PM
     * @apiNote 创建默认构造器，否则序列化时会报错
     */
    public MsgType() {}

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/8 10:21 AM
     * @param cacheName 缓存名
     * @param netIdentity app 地址标识
     * @param msgType 消息类型
     * @apiNote MsgType 构造函数
     */
    MsgType(String cacheName, String netIdentity, int msgType) {
        this.cacheName = cacheName;
        this.netIdentity = netIdentity;
        this.msgType = msgType;
    }

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/10/8 10:21 AM
     * @param cacheName 缓存名
     * @param netIdentity app 地址标识
     * @param msgType 消息类型
     * @param key 缓存 key 值
     * @apiNote MsgType 构造函数
     */
    MsgType(String cacheName, String netIdentity, int msgType, String key) {
        this.cacheName = cacheName;
        this.netIdentity = netIdentity;
        this.msgType = msgType;
        this.key = key;
    }

    /**
     * 创建人：张博 [zhangb@novadeep.com]
     * 时间：2018/10/25 4:31 PM
     * @apiNote 将该当前 MsgType 对象转换成字节数组
     * @return byte[]
     */
    public byte[] toBytes() {
        Gson gson = new Gson();
        return gson.toJson(this).getBytes();
    }

    /**
     * 创建人：张博 [zhangb@novadeep.com]
     * 时间：2018/10/25 4:32 PM
     * @param bytes MsgType 对象的字节数组
     * @apiNote 把 MsgType 对象的字节数组转换为 MsgType 对象
     * @return MsgType
     */
    public static MsgType toObject(byte[] bytes) {
        Gson gson = new Gson();
        return gson.fromJson(new String(bytes), MsgType.class);
    }
}
