package com.hcr.demo.jvm;

public class A {
    static {
        System.out.println(("*************load A************"));
    }

    public A() {
        System.out.println("*************initial A************");
    }
}
