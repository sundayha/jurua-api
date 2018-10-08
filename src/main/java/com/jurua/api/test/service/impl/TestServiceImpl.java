package com.jurua.api.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.common.model.page.PagingResult;
import com.jurua.api.common.utils.page.BeanUtil;
import com.jurua.api.config.exception.service.TestServiceException;
import com.jurua.api.test.mapper.TestMapper;
import com.jurua.api.test.model.Test;
import com.jurua.api.test.model.query.TestQuery;
import com.jurua.api.test.service.ITestService;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.jurua.api.common.constants.SysConstants.CAFFEINE_CACHE_JURUA_SERVICE_NAME;

//import com.rabbitmq.client.Connection;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */

@CacheConfig(cacheNames = {CAFFEINE_CACHE_JURUA_SERVICE_NAME})
@Service
public class TestServiceImpl implements ITestService {

    private TestMapper testMapper;
    private RedissonClient redissonClient;
    //private RabbitTemplate rabbitTemplate;
    //private DirectExchange directExchange;
    //private FanoutExchange fanoutExchange;

    //private ConnectionFactory connectionFactory;
    //private Cache cache;

    public TestServiceImpl(TestMapper testMapper, RedissonClient redissonClient) {
        this.testMapper = testMapper;
        this.redissonClient = redissonClient;
        //this.rabbitTemplate = rabbitTemplate;
        //this.directExchange = directExchange;
        //this.fanoutExchange = fanoutExchange;
        //this.connectionFactory = connectionFactory;
        //this.cache = cache;
    }

    @Override
    public String test() throws TestServiceException {

        try {
            int a = 9 / 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw new TestServiceException("测试异常拦截器", e);
        }

        return "";
    }

    @Override
    public Integer insertTest(Test test) throws TestServiceException {
        try {
            return testMapper.insert(test);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TestServiceException("测试插入异常", e);
        }
    }

    @Override
    public PagingResult<Test, TestQuery>  findTestById(PagingInfo<TestQuery> pagingInfo) throws TestServiceException {
        try {
            PagingResult<Test, TestQuery> pagingResult;
            PageHelper.startPage(pagingInfo.getCurrent(), pagingInfo.getPageSize());
            pagingResult = BeanUtil.toPagedResultMybatis(testMapper.selectById(pagingInfo), pagingInfo);
            return pagingResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TestServiceException("分页查询异常", e);
        }
    }

    //@Override
    //public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //    return testMapper.getUser(username);
    //}


    @Cacheable(key = "#key")
    @Override
    public String findDataAnnotation(String key) throws TestServiceException {
        return "数据库查询结果".concat(key);
    }

    @CacheEvict(key = "#key")
    @Override
    public String delDataAnnotation(String key) throws TestServiceException {
        return "删除数据库结果".concat(key);
    }

    @CachePut(key = "#key")
    @Override
    public String updateDataAnnotation(String key) throws TestServiceException {
        return "更新数据库查询结果：狂操霍雨佳，操的她只喊爽我还要".concat(key);
    }


    //private Connection conn_publisher;
    //private Channel channel;


    @Override
    public String rabbitMQT() throws TestServiceException {
        //rabbitTemplate.convertAndSend(directExchange.getName(),"t", cache);
        return null;
    }

    @Override
    public String redissonTopicT() throws TestServiceException {

        RTopic<Object> rTopic = redissonClient.getTopic("redissonTopic");
        rTopic.publish("远程狂操霍雨佳的小逼");
        return null;
    }
}
