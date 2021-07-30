package com.jay.cn.juc.threadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class TestSingleThread {

    public static void main(String[] args) {

        CountDownLatch count = new CountDownLatch(10);

        for (int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"start~~~~");
            }).start();
            count.countDown();
        }
        try {
            count.await();
            System.out.println("thread over:"+count.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Thread(()->{
            System.out.println("SingleThread over~~~");
        }));
    }

}
