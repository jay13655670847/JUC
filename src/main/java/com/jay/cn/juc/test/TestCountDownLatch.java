package com.jay.cn.juc.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

public class TestCountDownLatch {

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        LongAdder adder =new LongAdder();

        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    adder.increment();
                }
                latch.countDown();
            });
        }

        for (Thread thread:threads){
            thread.start();
        }

//        for (Thread thread:threads){
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(adder);

    }
}
