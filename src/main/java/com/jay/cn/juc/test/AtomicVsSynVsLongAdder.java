package com.jay.cn.juc.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AtomicVsSynVsLongAdder {

    static Long count1 = 0L;
    static AtomicLong count2 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    static Thread[] threads  = new Thread[1000];
    static Object o =new Object();

    static void testSyn(){

        for (int i=0;i<threads.length;i++){
            threads[i] = new Thread(() -> {
                synchronized (o) {
                    for (int j = 0; j < 100000; j++) {
                        count1++;
                    }
                }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread t:threads) t.start();

        for (Thread t:threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("synchronized:"+count1+"   "+(end-start));
    }


    static void testAtomic(){


        for(int i=0;i<threads.length;i++){
            threads[i] =new Thread(()->{
                for (int j=0;j<100000;j++){
                    count2.incrementAndGet();
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread t:threads) t.start();

        for (Thread t:threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Atomic:"+count2+"   "+(end-start));
    }

   static void testLongAdder(){
        for(int i=0;i<threads.length;i++){
            threads[i] =new Thread(()->{
                for (int j=0;j<100000;j++){
                    count3.increment();
                }
            });
        }

       long start = System.currentTimeMillis();
       for (Thread t:threads) t.start();

       for (Thread t:threads) {
           try {
               t.join();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

        long end = System.currentTimeMillis();
        System.out.println("LongAdder:"+count3+"   "+(end-start));
    }

    public static void main(String[] args) {
        testSyn();
        testAtomic();
        testLongAdder();
    }
}
