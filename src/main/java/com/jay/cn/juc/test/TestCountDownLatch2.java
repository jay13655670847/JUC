package com.jay.cn.juc.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟百米赛跑
 */
public class TestCountDownLatch2 {

    static CountDownLatch begin = new CountDownLatch(4);
    static CountDownLatch end = new CountDownLatch(10);
    static AtomicInteger count = new AtomicInteger();

    void m1(){
        for (int i=3;i>=1;i--){
            System.out.println("倒计时："+i);
            begin.countDown();
        }
    }

    void m2(){
        for (int i=1;i<=100;i++){
            if (i%10 ==0){
                System.out.println(Thread.currentThread().getName()+"跑了"+i);
            }
            count.incrementAndGet();
        }
        end.countDown();
    }

    public static void main(String[] args) {
        TestCountDownLatch2 t =new TestCountDownLatch2();

        //倒计时
        new Thread(() -> t.m1()).start();

        try {
            begin.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //选手开始
        Thread[] threads =new Thread[10];

        for (int i=0;i<10;i++){
            threads[i] = new Thread(() -> {
                t.m2();
            },"Thread"+i);
        }

        for (Thread thread:threads){
            thread.start();
        }

        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }

}
