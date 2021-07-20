package com.jay.cn.juc.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 5个窗口，卖100张票
 */
public class TestSellTickets {

    static AtomicInteger count = new AtomicInteger(100);

    static String[] windows = {"窗口1","窗口2","窗口3","窗口4","窗口5"};

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        Object o =new Object();

        for (int i=0;i<5;i++){
            threads[i] = new Thread(() -> {
                while (true){
                    if (count.get() >0){
                        synchronized (o){
                            System.out.println(Thread.currentThread().getName() + " 卖出第" + count + "张票");
                            count.decrementAndGet();
                        }
                    }
                }

            }, windows[i]);
        }

        for (Thread thread:threads){
            thread.start();
        }

        for (Thread thread:threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
