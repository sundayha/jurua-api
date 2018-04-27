package com.jurua.api.user.service.impl;

import com.jurua.api.config.exception.service.UserServiceException;
import com.jurua.api.user.mapper.UserMapper;
import com.jurua.api.user.model.vo.UsersVO;
import com.jurua.api.user.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
@Service
public class UserImpl implements IUser {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UsersVO> findUsers() throws UserServiceException {
        try {
            return userMapper.findUsers();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserServiceException("下拉框-得到所有用户异常", e);
        }
    }
}
