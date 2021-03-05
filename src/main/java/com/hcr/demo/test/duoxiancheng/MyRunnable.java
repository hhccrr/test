package com.hcr.demo.test.duoxiancheng;

public class MyRunnable implements Runnable {

    public static volatile  int a;
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() +": "+i);
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();
        Thread thread2 = new Thread(new MyRunnable());
        thread2.start();
    }
}
