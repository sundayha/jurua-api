package com.jurua.api.test.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.pagehelper.PageHelper;
import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.common.model.page.PagingResult;
import com.jurua.api.common.utils.cache.CaffeineUtilsI;
import com.jurua.api.common.utils.page.BeanUtil;
import com.jurua.api.config.exception.service.TestServiceException;
import com.jurua.api.test.mapper.TestMapper;
import com.jurua.api.test.model.Test;
import com.jurua.api.test.model.User;
import com.jurua.api.test.model.query.TestQuery;
import com.jurua.api.test.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */
//@CacheConfig(cacheNames = {"caffeineCache"})
@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private Cache<Object, Object> cache;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CaffeineUtilsI<Object, Object> caffeineUtilsI;
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

    @Override
    public User getUser(String userName) throws TestServiceException {
        return null;
    }

    private Object a(String k) {
        return k.concat("狂操霍雨佳");
    }

    @Override
    public String cacheData(String p) {
        String result;
        //System.out.println(caffeineUtilsI.contains(p));
        //System.out.println(cache.getIfPresent(p));
        if (caffeineUtilsI.containsKey(null)) {
            caffeineUtilsI.getValue(p, null);
            return (String) caffeineUtilsI.getValue(p);
        }
        result = "zb".concat(p);
        caffeineUtilsI.put(p, result);
        return result;
    }

    @Override
    public String getCacheData(String p) {

        return "zb".concat(p);
    }

    @Override
    public String getAllCacheData() {
        try {
            caffeineUtilsI.containsKey(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            caffeineUtilsI.getValue(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            caffeineUtilsI.getValue(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            caffeineUtilsI.del(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            caffeineUtilsI.delKeys(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            caffeineUtilsI.put(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String delCacheData(String p) {
        caffeineUtilsI.del(null);
        return "zb";
    }

    @Override
    public String delAllCacheData() {
        return "";
    }

    @Override
    public String updateCacheData(String p) {
        String result = "狂操霍雨佳".concat(p);
        caffeineUtilsI.put(p, result);
        return "狂操霍雨佳".concat(p);
    }

    //@Override
    //public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //    return testMapper.getUser(username);
    //}
}
