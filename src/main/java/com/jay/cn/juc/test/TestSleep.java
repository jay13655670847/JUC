package com.jay.cn.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSleep {

    Lock lock = new ReentrantLock();

     void m(){
        for (int i=0;i<100;i++){
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+" "+i);
                //Thread.sleep(1000);

                Thread.yield();
            }finally {
                lock.unlock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    public static void main(String[] args) {
        TestSleep t = new TestSleep();
        new Thread(() -> t.m(),"Thread1").start();
        new Thread(() -> t.m(),"Thread2").start();
    }
}
