package com.hcr.demo.jvm;

//添加运行JVM参数： -XX:+PrintGCDetails
public class GCTest {

    public static void main(String[] args) {
        byte[] allocation1, allocation2/*, allocation3, allocation4, allocation5, allocation6*/;

        allocation1 = new byte[55000 * 1024];

        allocation2 = new byte[8000 * 1024];

        //allocation3 = new byte[1000*1024];
        //allocation3 = new byte[1000*1024];
        //allocation3 = new byte[1000*1024];
        //allocation3 = new byte[1000*1024];
    }
}
