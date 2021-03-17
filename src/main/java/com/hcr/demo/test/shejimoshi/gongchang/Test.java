package com.hcr.demo.test.shejimoshi.gongchang;

public class Test {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        Car jc = carFactory.getCar("JC");
        jc.draw();
        Car zxc = carFactory.getCar("ZXC");
        zxc.draw();
        Car kc = carFactory.getCar("KC");
        kc.draw();
    }
}
