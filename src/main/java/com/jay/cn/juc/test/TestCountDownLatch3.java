package com.jay.cn.juc.test;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 模拟张三、李四、王五去饭店吃饭
 */
public class TestCountDownLatch3 {

    static Thread[] threads = new Thread[3];
    static CountDownLatch start =new CountDownLatch(3);
    static CountDownLatch end =new CountDownLatch(3);

    void m1(){
        System.out.println(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date())+" "+Thread.currentThread().getName()+" 开始去饭店~");
        start.countDown();
    }

    void m2(){
        try {
            if(Thread.currentThread().getName().equals("王五")){
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date())+" "+Thread.currentThread().getName()+" 到达饭店~");
        end.countDown();

    }

    public static void main(String[] args) {
        TestCountDownLatch3 t =new TestCountDownLatch3();
        String[] strings = "张三,李四,王五".split(",");

        for (int i=0;i<3;i++){
            threads[i] = new Thread(() -> t.m1(),strings[i]);
        }

        for(Thread thread:threads){
            thread.start();
        }

        try {
            start.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("服务员❤小芳❤开始等待");


        for (int i=0;i<3;i++){
            threads[i] = new Thread(() -> t.m2(),strings[i]);
        }

        for(Thread thread:threads){
            thread.start();
        }

        try {
            end.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("服务员❤小芳❤开始上菜");
    }
}
