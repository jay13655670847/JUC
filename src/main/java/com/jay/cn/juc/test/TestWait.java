package com.jay.cn.juc.test;

public class TestWait {

    public static void main(String[] args) {
        Object o = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (o) {
                System.out.println("m1~~~~~~~~~~~~~~~start");

                try {
                    o.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("m1~~~~~~~~~~~~~~~end");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("m2:" + i);
                }
                try {
                    o.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("m2 end");
            }
        });

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

    }
}
