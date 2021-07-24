package com.jay.cn.juc.test;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class TestThreadLocal {

    static ThreadLocal<String> t = new ThreadLocal<>();

    public static void main(String[] args) {
        try {
            //set
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.set("hello threadLocal");

                System.out.println(t.get());
            }).start();

            //get
            new Thread(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(t.get());
            }).start();
        }finally {
            t.remove();
        }
    }

    static class person {
        String value="0";
    }

}
