package com.jay.cn.juc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {

    AtomicInteger count = new AtomicInteger(0);

    void m(){
        for (int i=0;i<10000;i++){
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        TestAtomicInteger t =new TestAtomicInteger();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i=0;i<10;i++){
            threads.add(new Thread(() -> t.m(), "Thread" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach( (o) ->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}
