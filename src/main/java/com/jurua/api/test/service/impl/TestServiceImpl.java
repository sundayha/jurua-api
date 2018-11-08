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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

//import com.rabbitmq.client.Connection;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */

//@CacheConfig(cacheNames = {CAFFEINE_CACHE_JURUA_SERVICE_NAME})
@Service
public class TestServiceImpl implements ITestService {

    private TestMapper testMapper;
    private RedissonClient redissonClient;
    private TestAsync testAsync;

    public TestServiceImpl(TestMapper testMapper, RedissonClient redissonClient, TestAsync testAsync) {
        this.testMapper = testMapper;
        this.redissonClient = redissonClient;
        this.testAsync = testAsync;
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


    @Cacheable(cacheNames={"juruaServiceCache"}, key = "#key")
    @Override
    public String findDataAnnotation(String key) throws TestServiceException {
        return "数据库查询结果".concat(key);
    }

    @Cacheable(cacheNames={"apiCache"}, key = "#key")
    @Override
    public String findDataAnnotation1(String key) throws TestServiceException {
        return "数据库查询结果 findDataAnnotation1 ".concat(key);
    }

    @CacheEvict(cacheNames={"apiCache"}, key = "#key")
    @Override
    public String delDataAnnotation(String key) throws TestServiceException {
        return "删除数据库结果".concat(key);
    }

    @CachePut(cacheNames={"juruaServiceCache"}, key = "#key")
    @Override
    public String updateDataAnnotation(String key) throws TestServiceException {
        return "更新数据库查询结果：狂操霍雨佳，操的她只喊爽我还要".concat(key);
    }

    @Override
    public String redissonTopicT() throws TestServiceException {
        RTopic<Object> rTopic = redissonClient.getTopic("redissonCacheTopic");
        rTopic.publish("远程狂操霍雨佳的小逼");
        return null;
    }

    @Override
    public void tAsync() {
        IntStream.range(0, 100).forEach(
                i -> {
                    testAsync.go(i);
                }
        );
    }
}
