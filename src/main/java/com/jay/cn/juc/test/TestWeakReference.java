package com.jay.cn.juc.test;

import java.lang.ref.WeakReference;

public class TestWeakReference {

    public static void main(String[] args) {
        WeakReference<OneObject> weakReference = new WeakReference<>(new OneObject());
        System.out.println(weakReference.get());

        System.gc();
        System.out.println(weakReference.get());
    }
}
