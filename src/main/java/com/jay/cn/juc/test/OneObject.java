package com.jay.cn.juc.test;

public class OneObject {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("垃圾回收期开始执行~~~~");
    }
}
