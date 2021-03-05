package com.hcr.demo.jvm;

public class TestDynamicLoad {
    static {
        System.out.println("*************load TestDynamicLoad************");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("*************load test************");
        //这里b不会被加载，除非这里是new B()
        B b = null;
    }
}
