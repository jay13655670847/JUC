package com.jay.cn.juc.test;

/**
 * 线程的启动方式
 */
public class TestThread2 {

    private static class myThread extends Thread{
        @Override
        public void run() {
            System.out.println("Hello MyThread~~~");
        }
    }

    private static class myRun implements Runnable{

        @Override
        public void run() {
            System.out.println("Hello MyRun~~~");
        }
    }

    public static void main(String[] args) {
        //1、集成Thread类
        new myThread().start();

        //2、实现Runnable接口
        new Thread(new myRun()).start();

        //表达式
        new Thread(() -> {

            System.out.println("Hello!!!!");
        }).start();
    }
}
