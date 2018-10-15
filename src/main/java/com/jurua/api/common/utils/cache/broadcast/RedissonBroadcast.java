package com.jurua.api.common.utils.cache.broadcast;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author 张博 [zhangb@novadeep.com]
 */
public class RedissonBroadcast implements CacheMsgBroadcast {

    private static final Logger log = LoggerFactory.getLogger(RedissonBroadcast.class);

    @Autowired
    private RedissonClient redissonClient;

    private String uuid;

    @PostConstruct
    @Override
    public void connect() {
        uuid = UUID.randomUUID().toString();
        redissonBroadcastReceive();
    }

    @Override
    public void broadcast(MsgType msg) {
        try {
            RTopic<Object> topic = redissonClient.getTopic("redissonCacheTopic");
            topic.publish(msg);
        } catch (Exception e) {
            log.error("Redisson 发布消息异常", e);
        }
    }

    @Override
    public String getNetIdentity() {
        return uuid;
    }

    /**
     * 创建人：张博 [zhangb@novadeep.com]
     * 时间：2018/10/15 10:58 AM
     * @apiNote 接收 redisson 的广播内容
     */
    private void redissonBroadcastReceive() {
        RTopic<Object> topic = redissonClient.getTopic("redissonCacheTopic");
        topic.addListener((CharSequence channel, Object msg) -> {
            MsgType msgType = (MsgType) msg;
            // 不接收自己发布的信息
            if (!msgType.netIdentity.equals(uuid)) {
                switchMsg(msgType);
            }
            System.out.println(msg);
        });
    }
}
