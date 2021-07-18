package com.jay.cn.juc.test;

public class TestSynchronize2 implements Runnable{

    private int count=100;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+" count:"+count);
    }

    public static void main(String[] args) {
        TestSynchronize2 t =new TestSynchronize2();
        for (int i=0;i<100;i++){
            new Thread(t,"Thread"+i).start();
        }
    }
}
