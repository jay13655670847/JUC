package com.jay.cn.juc.test;

import java.util.concurrent.CountDownLatch;

public class TestCountLatch {

    static CountDownLatch begin = new CountDownLatch(3);

    static CountDownLatch end = new CountDownLatch(5);

    static void m1(){

        for (int i=3;i>=1;i--){
            System.out.println("倒计时:"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            begin.countDown();
        }
    }

    static void m3(){

        for (int i=1;i<=100;i++){
            if (i%10==0){
                System.out.println(Thread.currentThread().getName()+"跑了"+i+"米");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end.countDown();
    }

    public static void main(String[] args) {
        new Thread(()->m1()).start();

        try {
            begin.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0;i<10;i++){
            new Thread(()->m3(),"Thread"+i).start();
        }

        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
