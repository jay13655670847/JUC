package com.jay.cn.juc.test;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 *
 */
public class TestContainer2 {

    int Max=10;
    LinkedList<String> list =new LinkedList<>();

    Lock lock = new ReentrantLock();
    Condition consumer = lock.newCondition();
    Condition producer = lock.newCondition();

    static AtomicInteger putCount = new AtomicInteger(0);
    static AtomicInteger getCount = new AtomicInteger(0);

    void put(String value){
        try {
            lock.lock();

            while (list.size()==Max){
                System.out.println("list已满");
                producer.await();
            }

            list.add(value);
            System.out.println("put:"+value);
            putCount.incrementAndGet();
            consumer.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void get(){
        try {
            lock.lock();
            System.out.println("size:"+list.size()+"  version:"+getCount);
            while (list.size()==0){
                System.out.println("list已空");
                consumer.await();
            }

            list.removeFirst();
            System.out.println("size:"+list.size()+"  version:"+getCount);
            System.out.println("get:"+list.getFirst());
            getCount.incrementAndGet();

            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TestContainer2 t =new TestContainer2();

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
