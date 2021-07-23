package com.jay.cn.juc.test;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 */
public class TestContainer1 {

    static int MAX = 10;

    static LinkedList<String> list = new LinkedList<>();

    static AtomicInteger putCount = new AtomicInteger(0);
    static AtomicInteger getCount = new AtomicInteger(0);

    synchronized void put(String value){
        while (list.size()==MAX){
            System.out.println("list已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(value);
        System.out.println("put:"+value);
        putCount.incrementAndGet();
        this.notifyAll();
    }

    synchronized void get(){
        System.out.println("size:"+list.size()+"  version:"+getCount);
        while (list.size()==0){
            System.out.println("list已空");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("size:"+list.size()+"  version:"+getCount);
        System.out.println("get:"+list.getFirst());
        list.removeFirst();
        getCount.incrementAndGet();
        this.notifyAll();
    }

    public static void main(String[] args) {
        TestContainer1 t = new TestContainer1();

       for (int i=0;i<10;i++){
           new Thread(()->{
                for (int j=0;j<5;j++){
                    t.get();
                }
           }).start();
       }

        for (int i=0;i<2;i++){
            new Thread(()->{
                for (int j=0;j<25;j++){
                    t.put(UUID.randomUUID().toString());
                }
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("putCount:"+putCount);
        System.out.println("getCount:"+getCount);

    }

}
