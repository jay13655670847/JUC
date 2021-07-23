package com.jay.cn.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSellTickets2 {

    static int counts =30;
    static String[] windows={"窗口1","窗口2","窗口3","窗口4","窗口5"};

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread[] threads = new Thread[5];

        for (int i=0;i<5;i++){
            threads[i] = new Thread(() -> {
                while (true) {
                    if (counts > 0) {
                            lock.lock();
                            try {
                                if (counts > 0) {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println(Thread.currentThread().getName() + "卖出第" + counts + "张票");
                                    counts--;
                                } else {
                                    System.out.println(Thread.currentThread().getName() + "发现余票不足，停止售票");
                                    break;
                                }
                            }finally {
                                lock.unlock(); //释放锁
                                try {
                                    Thread.sleep(10);   //晕眩10毫秒
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                    } else {
                        System.out.println(Thread.currentThread().getName() + "发现余票不足，停止售票");
                        break;
                    }
                }
            },windows[i]);
        }

        for (Thread thread:threads){
            thread.start();
        }

    }
}
