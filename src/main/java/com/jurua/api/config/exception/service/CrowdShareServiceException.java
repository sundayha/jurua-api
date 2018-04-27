package com.jurua.api.config.exception.service;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 * 群共享异常捕获类
 */
public class CrowdShareServiceException extends Exception {

    public CrowdShareServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
