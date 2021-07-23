package com.jay.cn.juc.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15. 接着再由线程1打印16,17,18,19,20….以此类推, 直到打印到75. 程序的输出结果应该为:
 * 线程1: 1
 * 线程1: 2
 * 线程1: 3
 * 线程1: 4
 * 线程1: 5
 * 线程2: 6
 * 线程2: 7
 * 线程2: 8
 * 线程2: 9
 * 线程2: 10
 * …
 * 线程3: 71
 * 线程3: 72
 * 线程3: 73
 * 线程3: 74
 * 线程3: 75
 *
 * 线程1:1-5  16-20
 * 线程2:6-10 21-25
 * 线程3:11-15 26-30
 */
public class TestThread5 {

    public static void main(String[] args) {
        Object o = new Object();

        AtomicInteger count= new AtomicInteger(1);

        new Thread(() ->{
            while (count.get() <=75){
                if(count.get()/5%3 + 1 ==1){
                    synchronized (o){
                        for (int i=0;i<5;i++){
                            System.out.println(Thread.currentThread().getName() + count);
                            count.getAndIncrement();
                        }
                        if (count.get() !=5){
                            o.notifyAll();
                        }

                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        },"线程1: ").start();

        new Thread(() ->{
            while (count.get() <=75){
                if(count.get()/5%3 + 1 ==2){
                    synchronized (o){
                        for (int i=0;i<5;i++){
                            System.out.println(Thread.currentThread().getName()+count);
                            count.incrementAndGet();
                        }
                        o.notifyAll();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        },"线程2: ").start();

        new Thread(() ->{
            while (count.get() <=75){
                if(count.get()/5%3 + 1 ==3){
                    synchronized (o){
                        for (int i=0;i<5;i++){
                            System.out.println(Thread.currentThread().getName()+count);
                            count.incrementAndGet();
                        }
                        o.notifyAll();
                        try {
                            if (count.get() !=76){
                                o.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"线程3: ").start();

    }
}
