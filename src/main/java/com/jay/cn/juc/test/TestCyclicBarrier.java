package com.jay.cn.juc.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier1 =new CyclicBarrier(20);
        CyclicBarrier barrier2 =new CyclicBarrier(20,()->{System.out.println("满人发车"); });

        CyclicBarrier barrier =new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人发车");
            }
        });

        for (int i=0;i<100;i++){
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
            System.out.println("已有"+i+"人上车");
        }
    }
}
