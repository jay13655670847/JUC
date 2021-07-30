package com.jay.cn.juc.threadPool;

import java.util.concurrent.*;

public class TestCachedThread {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i=0;i<10;i++){
            service.execute(new Thread(()->{
                System.out.println(Thread.currentThread().getName());
            }));
        }

        FutureTask task = new FutureTask<String>(()->{
            return "Hello";
        });
        service.submit(task);
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
