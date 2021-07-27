package com.jay.cn.juc.test;

import java.util.concurrent.*;

public class TestCallable {
    public static void main(String[] args) {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return "Hello World~~~";
            }
        };

        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future future = threadPool.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
