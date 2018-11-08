package com.jurua.api.test.service;

import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.common.model.page.PagingResult;
import com.jurua.api.config.exception.service.TestServiceException;
import com.jurua.api.test.model.Test;
import com.jurua.api.test.model.query.TestQuery;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */
public interface ITestService {

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2017/9/6 下午5:28
     * @apiNote
     */
    String test() throws TestServiceException;

    Integer insertTest(Test test) throws TestServiceException;

    PagingResult<Test, TestQuery> findTestById(PagingInfo<TestQuery> pagingInfo) throws TestServiceException;

    //User getUser(String userName) throws TestServiceException;

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/11/8 1:44 PM
     * @param key key
     * @apiNote 测试缓存注解查询
     * @return String
     * @throws TestServiceException 异常
     */
    String findDataAnnotation(String key) throws TestServiceException;

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/11/8 1:44 PM
     * @param key key
     * @apiNote 测试缓存注解查询1
     * @return String
     * @throws TestServiceException 异常
     */
    String findDataAnnotation1(String key) throws TestServiceException;

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/11/8 1:44 PM
     * @param key key
     * @apiNote 测试注解删除
     * @return String
     * @throws TestServiceException 异常
     */
    String delDataAnnotation(String key) throws TestServiceException;

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/11/8 1:44 PM
     * @param key key
     * @apiNote 测试注解更新
     * @return String
     * @throws TestServiceException 异常
     */
    String updateDataAnnotation(String key) throws TestServiceException;

    /**
     * 创建人：张博【zhangb@novadeep.com】
     * 时间：2018/11/8 1:44 PM
     * @apiNote 测试 redisson 主题
     * @return String
     * @throws TestServiceException 异常
     */
    String redissonTopicT() throws TestServiceException;

    void tAsync();
}
