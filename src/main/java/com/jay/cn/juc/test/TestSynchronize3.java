package com.jay.cn.juc.test;

/**
 * 同步方法和非同步方法是否可以同时调用
 */
public class TestSynchronize3 {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"m1 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m1 end");
    }

    public void m2(){
        System.out.println(Thread.currentThread().getName()+"m2 start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"m2 end");
    }


    public static void main(String[] args) {
        TestSynchronize3 t = new TestSynchronize3();

        new Thread(() -> t.m1(),"Thread1").start();
        new Thread(() -> t.m2(),"Thread2").start();
    }

}
