package com.jay.cn.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadAndWriteLock {

    static int value = 0;
    static ReentrantLock reentrantLock = new ReentrantLock();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLOck = readWriteLock.writeLock();

    static void read(Lock lock){
        lock.lock();

        try {
            Thread.sleep(1000);
            System.out.println("Read over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    static void write(Lock lock,int val){
        lock.lock();

        try {
            Thread.sleep(1000);
            value = val;
            System.out.println("Write Over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        //for (int i=0;i<18;i++) new Thread(()->read(reentrantLock)).start();
        //for (int i=0;i<2;i++) new Thread(()->write(reentrantLock,2)).start();

        for(int i=0;i<18;i++) new Thread(()->read(readLock)).start();
        for (int i=0;i<2;i++) new Thread(()->write(writeLOck,5)).start();
    }

}
