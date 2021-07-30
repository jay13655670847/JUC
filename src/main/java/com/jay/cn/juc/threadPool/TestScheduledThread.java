package com.jay.cn.juc.threadPool;

import java.util.concurrent.*;

public class TestScheduledThread {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        service.scheduleAtFixedRate(()->{
            System.out.println("hello");
        },2,5,TimeUnit.SECONDS);

    }
}
