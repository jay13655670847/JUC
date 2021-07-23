package com.jay.cn.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestAQS {

    static Lock lock =new ReentrantLock();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                lock.lock();
                System.out.println("lock~~~~~~");
            }finally {
                lock.unlock();
            }
        }).start();
    }

}
