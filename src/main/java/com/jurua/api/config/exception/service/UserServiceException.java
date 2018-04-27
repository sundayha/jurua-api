package com.jurua.api.config.exception.service;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public class UserServiceException extends Exception {
    public UserServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
