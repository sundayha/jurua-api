package com.jurua.api.test.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author 张博 [zhangb@novadeep.com]
 */
@Component
public class TestAsync {

    @Async(value = "getAsyncExecutor")
    public void go(int i) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName().concat(" 接收到的参数：") + i);
    }
}
