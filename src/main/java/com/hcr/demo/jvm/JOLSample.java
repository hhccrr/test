package com.hcr.demo.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * 功能描述：计算对象大小
 *
 * @Author:hr
 * @param:
 * @date: 11:41 2021/2/4 0004
 */
public class JOLSample {

    /**
     * 功能描述：
     * ‐XX:+UseCompressedOops 默认开启的压缩所有指针
     * 关闭压缩‐XX:‐UseCompressedClassPointers或‐XX:‐UseCompressedOops
     * ‐XX:+UseCompressedClassPointers 默认开启的压缩对象头里的类型指针Klass Pointer
     *
     * @Author:hr
     * @param:
     * @date: 14:38 2021/2/4 0004
     */
    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());

        ClassLayout layout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());

        ClassLayout layout2 = ClassLayout.parseInstance(new A());
        System.out.println(layout2.toPrintable());
    }

    public static class A {


        int id;
        String name;
        byte b;
        Object o;
    }
}
