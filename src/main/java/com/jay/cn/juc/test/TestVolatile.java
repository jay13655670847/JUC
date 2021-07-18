package com.jay.cn.juc.test;

import java.util.concurrent.TimeUnit;

public class TestVolatile {

    boolean running = true;

    void m(){
        System.out.println("m start");

        while(running){

        }

        System.out.println("m end");
    }

    public static void main(String[] args) {
        TestVolatile t = new TestVolatile();

        new Thread(() ->t.m(),"m1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //t.running = false;
    }
}
