package com.jay.cn.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestInterrupt {

    static Lock lock = new ReentrantLock();

    static void m(){
        try {
            lock.lock();
            System.out.println("m start~~~");
            try {
                Thread.sleep(2000);
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println("m end~~~");
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            m();

        });

        thread.start();


        try {
            Thread.sleep(1000);
            boolean interrupted = thread.isInterrupted();
            System.out.println(interrupted);

            thread.interrupt();
            System.out.println(thread.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
