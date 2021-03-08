package com.hcr.demo.test.duoxiancheng.MySynchronized;

/**
 * @author Administrator
 */
public class MySynchronized extends Thread{

    public static void main(String[] args) {
        Thread thread1 = new MySynchronized();
        thread1.setName("test1");

        Thread thread2 = new MySynchronized();
        thread2.setName("test2");

        Thread thread3 = new MySynchronized();
        thread3.setName("test3");

        thread1.start();
        thread2.start();
        thread3.start();


    }

    @Override
    public void run() {
        this.test();

    }

    public synchronized   void test(){
        System.out.println(Thread.currentThread().getName()+"a");
        System.out.println(Thread.currentThread().getName()+"b");
        System.out.println(Thread.currentThread().getName()+"c");
    }
}
