package com.jay.cn.juc.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

public class Test1A2B3C {

    static String[] strings = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    static Thread t1,t2=null;

    static Object o = new Object();

    static void testWaitNotify(){
        t1 = new Thread(()->{
           synchronized(o){
               for (int i=1;i<=26;i++){
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

                   System.out.printf(i+"");
                   if (i!=1){
                       o.notify();
                   }

                   try {
                       o.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               o.notify();
           }
        });

        t2=new Thread(()->{
           synchronized (o){
               for (char i='A';i<='Z';i++){
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

                   System.out.print(i);
                   o.notify();
                   try {
                       o.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               o.notify();
           }
        });

        t1.start();
        t2.start();
    }

    static void testLockSupport() {

         t1 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                System.out.printf(i + "");

                if(i!=1){
                    LockSupport.unpark(t2);
                }
                LockSupport.park();

            }
             System.out.println("t1释放t2");
            LockSupport.unpark(t2);

        });

         t2 = new Thread(()->{
            for (int i=0;i<26;i++){
                System.out.printf(strings[i]);

                LockSupport.unpark(t1);
                LockSupport.park();
            }
             System.out.println("t2释放t1");
            LockSupport.unpark(t1);
        });

         t1.start();
         t2.start();

    }

    public static void main(String[] args) {
        testWaitNotify();
       // testLockSupport();
    }
}
