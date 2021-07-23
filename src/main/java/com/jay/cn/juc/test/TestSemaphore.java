package com.jay.cn.juc.test;

import java.util.concurrent.Semaphore;

/**
 * 操场上有5个跑道，一个跑道一次只能有一个学生在上面跑步，一旦所有跑道在使用，那么后面的学生就需要等待，直到有一个学生不跑了
 */
public class TestSemaphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for (int i=0;i<10;i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+" 通过跑道");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"Thread"+(i+1)).start();
        }
    }
}
