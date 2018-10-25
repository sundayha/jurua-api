package com.jurua.api.config.threadpool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @author 张博 [zhangb@novadeep.com]
 */
public class JuruaAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    private final Log logger = LogFactory.getLog(JuruaAsyncUncaughtExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        logger.error("异步调用异常 -------> 方法".concat(method.getName()), ex);
    }
}
