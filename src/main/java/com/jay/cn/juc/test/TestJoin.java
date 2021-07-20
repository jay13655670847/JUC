package com.jay.cn.juc.test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestJoin {

    public static void main(String[] args) {
        Thread[] threads =  new Thread[10];
        AtomicInteger count =new AtomicInteger(0);
        Object o =new Object();


        for (int i=0;i<threads.length;i++){
             threads[i] = new Thread(() -> {
                 for (int j = 0; j < 10; j++) {
                     try {
                         Thread.sleep(100);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     count.incrementAndGet();
                     System.out.println(Thread.currentThread().getName()+" "+j);

                 }

            },"Thread"+i);

        }

//        for (Thread thread :threads){
//            thread.start();
//            System.out.println(thread.getName() +"  start");
//        }
//
//        for (Thread thread:threads){
//            try {
//                thread.join();
//                System.out.println(thread.getName() +"  join");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        threads[0].start();
        System.out.println(threads[0].getName() +"  start");
        threads[1].start();
        System.out.println(threads[1].getName() +"  start");
        try {
            System.out.println(threads[1].getName() +"  join start");
            threads[1].join();
            System.out.println(threads[1].getName() +"  join end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(count);
    }
}
