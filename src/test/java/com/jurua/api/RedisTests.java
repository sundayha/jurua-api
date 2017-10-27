package com.jurua.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisShardInfo;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public class RedisTests {

    private RedisTemplate<String, String> template;
    private JedisConnectionFactory connectionFactory;
    private String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJpZCIsImlhdCI6MTUwODc1MjMyMCwic3ViIjoic3ViamVjdCIsImlzcyI6Imlzc3VlciIsImF1ZCI6ImxhbGFsYSIsImV4cCI6MTUwODc1MjMyMH0.D6Z2_VTltCznr_HP91tEfiygW8CRROC8z4zeUbj8u78";

    @Before
    public void setUp() throws Exception {
        JedisShardInfo shardInfo = new JedisShardInfo("localhost", 6379);
        connectionFactory = new JedisConnectionFactory();
        connectionFactory.setShardInfo(shardInfo);

        template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
        template.afterPropertiesSet();
    }

    @Test
    public void setToSets() throws Exception {
        template.opsForSet().add("tokenBlack", jwt);
        //System.out.println(template.opsForSet().isMember("tokenBlack", "wo"));
        System.out.println(template.opsForSet().isMember("tokenBlack", "wo1"));
        System.out.println(template.opsForSet().isMember("tokenBlack", jwt));
    }
}
