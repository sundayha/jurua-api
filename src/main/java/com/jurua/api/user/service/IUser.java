package com.jurua.api.user.service;

import com.jurua.api.config.exception.service.UserServiceException;
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
}
