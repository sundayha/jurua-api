package com.jurua.api.common.utils.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 张博【zhangb@lianliantech.cn】
 */
public class LogUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);

    public static void error(String msg, Throwable throwable) {

        LOGGER.error(msg, throwable);
    }
}
