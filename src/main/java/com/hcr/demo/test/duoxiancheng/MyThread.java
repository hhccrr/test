package com.hcr.demo.test.duoxiancheng;

public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName()+"开始执行 ："+i);
        }
    }

    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        thread1.start();
        MyThread thread2 = new MyThread();
        thread2.start();
    }
}
