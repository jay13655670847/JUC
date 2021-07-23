package com.jay.cn.juc.test;

import com.sun.corba.se.pept.transport.ReaderThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 5个窗口，卖100张票
 */
public class TestSellTickets {

    //此处也可采用普通的int
    //static int count = 30;
    static AtomicInteger count = new AtomicInteger(30);

    static String[] windows = {"窗口1","窗口2","窗口3","窗口4","窗口5"};

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        Object o =new Object();

        for (int i=0;i<5;i++){
            threads[i] = new Thread(() -> { //采用lambda表达式创建线程，同时初始化给threads中的每个线程threads[i]
                while (true){
                    if (count.get() >0){    //加锁前后双重判断，避免浪费锁资源
                        synchronized (o){   //加锁，避免多个窗口同时卖一张票，实现线程安全
                            if (count.get() >0) {   //加锁前后双重判断，避免浪费锁资源
                                try {
                                    Thread.sleep(100);  //模拟卖票业务，停顿0.1秒
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(Thread.currentThread().getName() + " 卖出第" + count + "张票");
                                count.decrementAndGet();
                            }else{
                                System.out.println(Thread.currentThread().getName() + "发现余票不足，停止售票");
                                break;
                            }
                        }
                    }else{
                        System.out.println(Thread.currentThread().getName() + "发现余票不足，停止售票");
                        break;
                    }
                }

            }, windows[i]);//windows[i]定义线程的名称
        }

        for (Thread thread:threads){
            thread.start(); //启动线程
        }
    }
}
