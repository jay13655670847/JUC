package com.jay.cn.juc.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将一万个线程名字放进容器中
 */
public class TestThread6 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i=0;i<10;i++){
            new Thread(() -> {
                //list.add(i);
            }).start();
        }

        System.out.println("集合大小:"+list.size());
        System.out.println(Arrays.toString(list.toArray()));
    }
}
