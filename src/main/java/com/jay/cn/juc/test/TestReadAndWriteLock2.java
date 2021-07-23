package com.jay.cn.juc.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadAndWriteLock2 {

    static int value = 10;
    static int value2 = 30;
    static ReadWriteLock lock = new ReentrantReadWriteLock( );


    void read(Lock lock){
        lock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Read over~ "+value);
        lock.unlock();
    }

    void read2(Lock lock){
        lock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Read over2~ "+value2);
        lock.unlock();
    }

    void write(Lock lock,int val){
        lock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value = val;
        System.out.println("Write over~ "+value);
        lock.unlock();
    }

    public static void main(String[] args) {
        TestReadAndWriteLock2 t =new TestReadAndWriteLock2();
        Lock readLock = TestReadAndWriteLock2.lock.readLock();
        Lock writeLock = TestReadAndWriteLock2.lock.writeLock();

        for (int i=0;i<18;i++){
            new Thread(() ->{
                t.read(readLock);
            }).start();
            new Thread(() ->{
                t.read2(readLock);
            }).start();
        }

        for (int i=0;i<2;i++){
            int finalI = i;
            new Thread(() ->{
                t.write(writeLock, finalI +20);
            }).start();
        }


    }
}
