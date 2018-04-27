package com.jurua.api.user.mapper;

import com.jurua.api.user.model.User;
import com.jurua.api.user.model.vo.UsersVO;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/26 下午2:49
     * @apiNote 查询所有用户的集合对象给下拉框用
     * @return List<UsersVO>
     */
    List<UsersVO> findUsers();
}