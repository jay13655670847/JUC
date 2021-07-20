package com.jay.cn.juc.test;

import java.util.concurrent.Exchanger;

public class TestExchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {

        new Thread(()->{
            String s="T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  "+s);
        },"Thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String s="T2";
                try {
                    s= exchanger.exchange(s);
                    System.out.println(Thread.currentThread().getName()+"  "+s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread2").start();
    }
}
