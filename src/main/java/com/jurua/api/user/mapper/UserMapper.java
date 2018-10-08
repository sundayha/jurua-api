package com.jurua.api.user.mapper;

import com.jurua.api.config.mybatis.Mapper;
import com.jurua.api.user.model.User;
import com.jurua.api.user.model.vo.UsersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
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

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/5/9 上午10:24
     * @param phoneNum 电话号
     * @apiNote 根据电话号得到user对象
     * @return User
     */
    User findUserByPhoneNum(@Param("phoneNum") String phoneNum);
}