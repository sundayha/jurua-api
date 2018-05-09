package com.jurua.api.user.service;

import com.jurua.api.config.exception.service.UserServiceException;
import com.jurua.api.user.model.User;
import com.jurua.api.user.model.vo.UsersVO;

import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public interface IUser {

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/4/26 下午2:49
     * @apiNote 查询所有用户的集合对象给下拉框用
     * @return List<UsersVO>
     * @throws UserServiceException 下拉框-得到所有用户异常
     */
    List<UsersVO> findUsers() throws UserServiceException;

    /**
     * 创建人：张博【zhangb@lianliantech.cn】
     * 时间：2018/5/9 上午10:24
     * @param phoneNum 电话号
     * @apiNote 根据电话号得到user对象
     * @return User
     * @throws UserServiceException 根据电话号得到user对象异常
     */
    User findUserByPhoneNum(String phoneNum) throws UserServiceException;
}
