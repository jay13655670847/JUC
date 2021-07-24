package com.jay.cn.juc.test;

import java.io.IOException;
import java.lang.ref.SoftReference;

public class TestSoftReference {

    public static void main(String[] args) {
        //创建一个10M的数组
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());

        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        //再分配一个数组，此时内存不足会执行垃圾回收
        byte[] bytes =new byte[1024*1024*20];
        System.out.println(m.get());

    }

}
