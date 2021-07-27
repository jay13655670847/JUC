package com.jay.cn.juc.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFutureTask {

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<String>(()->{
             return "Hello FutureTask";
        });

        //futureTask.run();

        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
