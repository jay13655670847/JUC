package com.jay.cn.juc.test;

public class TestVolatile2 {

    private static volatile TestVolatile2 instance;

   static TestVolatile2 get(){

        if (instance ==null){
            synchronized (TestVolatile2.class){
                if(instance ==null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    instance = new TestVolatile2();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
       for (int i=0;i<100;i++){
           new Thread(() -> {
               System.out.println(TestVolatile2.get().hashCode());
           }).start();
       }
    }

}
