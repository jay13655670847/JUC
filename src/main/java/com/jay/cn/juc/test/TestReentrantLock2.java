package com.jay.cn.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            System.out.println("m1 .......");
            lock.lock();
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void m2(){
        System.out.println("m2 start........");
        try {
            lock.lockInterruptibly();//可以对interrupt()方法做出响应
            Thread.sleep(5000);
            System.out.println("m2 end ..........");
        } catch (InterruptedException e) {
            System.out.println("m2 InterruptedException");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TestReentrantLock2 t =new TestReentrantLock2();

        new Thread(()->t.m1()).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("22222222");
        Thread t2 = new Thread(() -> t.m2());
        t2.start();
        t2.interrupt();
    }
}
