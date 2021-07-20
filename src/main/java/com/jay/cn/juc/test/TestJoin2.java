package com.jay.cn.juc.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestJoin2  {

    AtomicInteger count = new AtomicInteger(0);
    static AtomicStampedReference reference = new AtomicStampedReference(0,1);

    public static void main(String[] args) {
       // new Thread(new parent()).start();
        System.out.println(reference.getReference());

    }


    static class parent implements Runnable{
        Thread thread = new Thread(new child());

        @Override
        public void run() {
            for (int i=0;i<10;i++){
                System.out.println("父线程m "+i);

                if (i==2){
                    try {
                        thread.start();
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class child implements Runnable{

        @Override
        public void run() {
            for (int i=0;i<10;i++){
                System.out.println("子线程m "+i);
            }
        }
    }
}
