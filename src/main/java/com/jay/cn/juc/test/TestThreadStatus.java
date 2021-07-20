package com.jay.cn.juc.test;

import javax.sound.sampled.FloatControl;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadStatus {

    static Lock lock = new ReentrantLock();

    static Thread[] threads = new Thread[100];

    public static void main(String[] args) {

        for(int i=0;i<threads.length;i++){
             threads[i] = new Thread(() -> {
                lock.lock();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.unlock();

            },"thread"+i);
        }

        for (Thread t :threads){
            System.out.println(t.currentThread().getName()+" "+t.getState());
            t.start();
        }

//        while (true){
//            for (Thread t :threads){
//                System.out.println(t.currentThread().getName()+" "+t.getState());
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


    }
}
