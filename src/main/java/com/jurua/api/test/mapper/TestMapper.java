package com.jurua.api.test.mapper;

import com.jurua.api.common.model.page.PagingInfo;
import com.jurua.api.test.model.Test;
import com.jurua.api.test.model.User;
import com.jurua.api.test.model.query.TestQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */
public interface TestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Test record);

    Test selectByPrimaryKey(Long id);

    List<Test> selectAll();

    int updateByPrimaryKey(Test record);

    List<Test> selectById(PagingInfo<TestQuery> pagingInfo);

    User getUser(@Param("userName") String userName);
}