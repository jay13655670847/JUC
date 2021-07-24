package com.jay.cn.juc.test;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class TestConcurrentHashMap {

    public static final int COUNT = 1000000;
    public static final int THREAD_COUNT = 100;

    static Map<Object, Object> hashtable = new ConcurrentHashMap<>();

    static UUID[] keys =new UUID[COUNT];
    static UUID[] values =new UUID[COUNT];

    static {
        for (int i=0;i<COUNT;i++){
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    public static void main(String[] args) {

        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("");
        BlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>();

        Queue<String> strings = new ConcurrentLinkedQueue<>();
        SynchronousQueue<Object> queue = new SynchronousQueue<>();
        TransferQueue transferQueue = new LinkedTransferQueue<>();
        

        Thread[] threads =new Thread[THREAD_COUNT];

        int start;
        int gap = COUNT/THREAD_COUNT;

        for (int i=0;i<THREAD_COUNT;i++){
            start = i  * gap;

            int finalStart = start;
            threads[i] = new Thread(()->{
                for (int j = finalStart; j<finalStart+gap; j++){
                    hashtable.put(keys[j],values[j]);
                }
            });
        }

        long begin = System.currentTimeMillis();

        for (int i=0;i<THREAD_COUNT;i++){
            threads[i].start();
        }

        for (int i=0;i<THREAD_COUNT;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println(end-begin);
        System.out.println(hashtable.size());

        //*******************************************************************

        for (int i=0;i<THREAD_COUNT;i++){
            threads[i] = new Thread(() ->{
                for (int j=0;j<1000000;j++){
                    hashtable.get(10);
                }
            });
        }

        begin = System.currentTimeMillis();

        for (int i=0;i<THREAD_COUNT;i++){
            threads[i].start();
        }

        for (int i=0;i<THREAD_COUNT;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();

        System.out.println(end-begin);

    }
}
