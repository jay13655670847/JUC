package com.jay.cn.juc.test;

public class TestThreadLocal2 {

    static ThreadLocal<String> t = new InheritableThreadLocal<>();

    public static void main(String[] args) {

        t.set("hello threadLocal");

        try {
            //set
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


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
