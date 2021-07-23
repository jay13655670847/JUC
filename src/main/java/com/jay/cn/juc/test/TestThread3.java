package com.jay.cn.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建两个线程，其中一个输出1-52，另外一个输出A-Z。输出格式要求：12A 34B 56C 78D ...
 */
public class TestThread3 {

    static String[] strings = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public static void main(String[] args) {
        Object o = new Object();

        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 52; i++) {
                synchronized (o){
                    System.out.printf(i + "");
                    if (i % 2 == 0) {
                        try {
                            if (i != 2) {
                                o.notifyAll();
                            }
                            o.wait();
                            if (i ==52){
                                o.notifyAll();
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("thread1释放all");

        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                synchronized (o){
                    System.out.println(strings[i-1]);
                    try {
                        o.notifyAll();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("thread2释放all");
        });

        thread1.start();
        thread2.start();

    }

}
