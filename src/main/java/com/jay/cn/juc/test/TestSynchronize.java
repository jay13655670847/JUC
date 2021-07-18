package com.jay.cn.juc.test;

public class TestSynchronize {

    public void m1() {
        Object o =new Object();
        synchronized (o){
            System.out.println(Thread.currentThread().getName());
        }
    }

    public void m2() {
        synchronized (this){
            System.out.println(Thread.currentThread().getName());
        }
    }

    public synchronized void m3() {
            System.out.println(Thread.currentThread().getName());
    }


}
