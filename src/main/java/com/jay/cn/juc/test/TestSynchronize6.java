package com.jay.cn.juc.test;

public class TestSynchronize6 {

    private int count=100;

    public synchronized void m(){

        while (true){
//            System.out.println(Thread.currentThread().getName()+" count:"+count);
//            count--;
//
//            if (count == 95){
//                int a=1/0;
//            }
//
//            if (count ==80){
//                break;
//            }

            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" count:"+count);
                count--;

                if (count == 95){
                    int a=1/0;
                }

                if (count ==80){
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestSynchronize6 t =new TestSynchronize6();

        Runnable r =new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };

        new Thread(r,"Thread1").start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r,"Thread2").start();
    }
}
