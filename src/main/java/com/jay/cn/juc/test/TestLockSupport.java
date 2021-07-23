package com.jay.cn.juc.test;

import java.util.*;

public class TestLockSupport {

    //List<Object> list=new ArrayList<>();
    volatile List<Object> list=Collections.synchronizedList(new ArrayList<>());

     void add(String value){
        list.add(value);
    }

     int getSize(){
        return list.size();
    }

    public static void main(String[] args) {
        TestLockSupport t = new TestLockSupport();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                String value = UUID.randomUUID().toString();
                t.add(value);
                System.out.println("add:"+value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                if (t.list.size() == 5) {
                    System.out.println("stop");
                    thread1.stop();
                    break;
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.list.size());
        System.out.println(Arrays.toString(t.list.toArray()));

    }
}
