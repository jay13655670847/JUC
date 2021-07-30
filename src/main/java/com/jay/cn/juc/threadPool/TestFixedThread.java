package com.jay.cn.juc.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFixedThread {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i=0;i<15;i++){
            executorService.execute(new Thread(()->{
                System.out.println(Thread.currentThread().getName());
            }));
        }

    }
}
