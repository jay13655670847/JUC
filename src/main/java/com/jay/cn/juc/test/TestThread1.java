package com.jay.cn.juc.test;

/**
 * start()和run()方法的区别
 */
public class TestThread1 {


    private static class T1 extends Thread{
        @Override
        public void run() {
            for (int i=1;i<=10;i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        //new T1().run();
        new T1().start();
        for (int i=1;i<=10;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}
