package com.jurua.api.config.threadpool;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author 张博 [zhangb@novadeep.com]
 */
@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {

    @Bean
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadGroupName(" my threadGroup name ---> ");
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setThreadNamePrefix("my thread name ---> ");
        taskExecutor.setQueueCapacity(50);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }
}
