package com.jurua.api.user.service.impl;

import com.jurua.api.common.utils.logger.LogUtils;
import com.jurua.api.config.exception.service.UserServiceException;
import com.jurua.api.user.mapper.UserMapper;
import com.jurua.api.user.model.User;
import com.jurua.api.user.model.vo.UsersVO;
import com.jurua.api.user.service.IUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@Service
public class UserImpl implements IUser {

    //private Logger logger = Logger.getLogger(UserImpl.class);

    private UserMapper userMapper;

    public UserImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UsersVO> findUsers() throws UserServiceException {
        try {
            return userMapper.findUsers();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserServiceException("下拉框-得到所有用户异常", e);
        }
    }

    @Override
    public User findUserByPhoneNum(String phoneNum) throws UserServiceException {
        try {
            return userMapper.findUserByPhoneNum(phoneNum);
        } catch (Exception e) {
            LogUtils.error("根据电话号得到user对象异常", e);
            throw new UserServiceException("根据电话号得到user对象异常", e);
        }
    }
}
