package com.jay.cn.juc.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestThreadLocal2 {

    static ThreadLocal<String> t = new InheritableThreadLocal<>();

    public static void main(String[] args) {

        t.set("hello threadLocal");

        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());

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
