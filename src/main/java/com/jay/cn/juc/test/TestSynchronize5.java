package com.jay.cn.juc.test;

/**
 * 可重入锁
 */
public class TestSynchronize5 {

     synchronized void m1(){
        System.out.println("m1 start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.m2();
    }

    synchronized void m2(){
        System.out.println("m2 start");
    }

    public static void main(String[] args) {
        TestSynchronize5 t = new TestSynchronize5();

        new Thread(() -> t.m1()).start();
    }
}
