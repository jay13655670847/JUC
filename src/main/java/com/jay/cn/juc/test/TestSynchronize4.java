package com.jay.cn.juc.test;

/**
 * 银行账户，对写方法加锁，读方法不加锁，是否可行？
 */
public class TestSynchronize4 {

    private double money = 0;

    public synchronized void set(Double money){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.money=money;
    }

    public synchronized double get(){

        return this.money;
    }

    public static void main(String[] args) {
        TestSynchronize4 t =new TestSynchronize4();
        new Thread(() -> t.set(100.0)).start();
        System.out.println(t.get());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.get());

    }
}
