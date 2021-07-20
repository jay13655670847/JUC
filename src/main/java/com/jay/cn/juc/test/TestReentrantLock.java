package com.jay.cn.juc.test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();

            for (int i=0;i<=9;i++){
                Thread.sleep(1000);
                System.out.println(i);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void m2(){
        try {
            lock.lock();
            System.out.println("m2 ..............");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TestReentrantLock t = new TestReentrantLock();

        new Thread(()->{t.m1();}).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->t.m2()).start();
    }
}
