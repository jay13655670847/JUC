package com.jay.cn.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock3 {

    private static Lock lock =new ReentrantLock();

    void m(){
        for (int i=0;i<=99;i++){
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"获得锁 "+i);
                }finally {
                    lock.unlock();
                }
            }

    }

    public static void main(String[] args) {
        TestReentrantLock3 t = new TestReentrantLock3();
        new Thread(()->t.m(),"Thread1").start();
        new Thread(()-> t.m(),"Thread2").start();
    }
}
