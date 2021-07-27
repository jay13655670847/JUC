package com.jay.cn.juc.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Test1A2B3C_2 {

    Thread t1,t2=null;

    void testWaitNotify(){
        Object o =new Object();

        t1 = new Thread(()->{
            synchronized (o){
              for (int i=1;i<=26;i++){
                   System.out.printf(i+"");
                   o.notifyAll();
                   try {
                       o.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
                o.notifyAll();
           }
        });

        t2 = new Thread(()->{
            synchronized (o){
             for (char i='A';i<='Z';i++){
                    System.out.printf(i+"");

                    o.notifyAll();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
             o.notifyAll();
           }
        });

        t1.start();
        t2.start();
    }

    void testReentrantLock(){
        Lock lock = new ReentrantLock();
        t1 = new Thread(()->{
            for (int i=1;i<=26;i++){
                try {
                    lock.lock();
                    System.out.printf(i+"");

                    try {
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {
                    lock.unlock();
                }
            }
            lock.notifyAll();
        });
        t2 = new Thread(()->{
            for (int i='A';i<='Z';i++){
                try {
                    lock.lock();
                    System.out.printf(i+"");

                    try {
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {
                    lock.unlock();
                }
            }
            lock.notifyAll();
        });

        t1.start();
        t2.start();
    }

    void testLockSupport(){
        t1 = new Thread(()->{
            for (int i=1;i<=26;i++){
                    System.out.printf(i+"");
                    LockSupport.unpark(t2);
                    LockSupport.park();
            }
            LockSupport.unpark(t2);
        });
        t2 = new Thread(()->{
            for (char i='A';i<='Z';i++){
                LockSupport.park();
                System.out.print(i);
                LockSupport.unpark(t1);
            }
            LockSupport.unpark(t1);
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        Test1A2B3C_2 t = new Test1A2B3C_2();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        //t.testWaitNotify();
        t.testLockSupport();
    }

}
