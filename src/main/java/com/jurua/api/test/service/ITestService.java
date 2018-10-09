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

    String findDataAnnotation(String key) throws TestServiceException;

    String findDataAnnotation1(String key) throws TestServiceException;

    String delDataAnnotation(String key) throws TestServiceException;

    String updateDataAnnotation(String key) throws TestServiceException;

    String rabbitMQT() throws TestServiceException;

    String redissonTopicT() throws TestServiceException;
}
