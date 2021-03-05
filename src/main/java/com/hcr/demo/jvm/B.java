package com.hcr.demo.jvm;

public class B {
    static {
        System.out.println(("*************load B************"));
    }

    public B() {
        System.out.println("*************initial B************");
    }
}
