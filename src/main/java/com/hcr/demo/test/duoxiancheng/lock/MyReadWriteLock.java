package com.hcr.demo.test.duoxiancheng.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Administrator
 * 读写锁 ReadWriteLock
 */
public class MyReadWriteLock {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
//        synchronizedTest();
        readLockTest();
    }

    /**
     * 多个线程进行读操作
     * 使用synchronized 一个线程完之后，另一个线程才能获取锁执行
     */
    public static void synchronizedTest(){
        final MyReadWriteLock test = new MyReadWriteLock();

        new Thread(){
            @Override
            public void run() {
               test.synchronizedSet(Thread.currentThread());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.synchronizedSet(Thread.currentThread());
            }
        }.start();
    }

    public void synchronizedSet(Thread thread){
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1){
            System.out.println(thread.getName()+"正在进行读操作");
        }
        System.out.println(thread.getName()+"读操作完毕");
    }

    /**
     * 使用读写锁
     *
     * 在这里两个线程同事进行读操作
     *
     * 注意：
     * 如果一个线程获取了读锁，其他线程来申请写锁是，会等待读锁释放
     * 如果一个线程获取了写锁，其他线程来申请读锁和写锁都会等待写锁释放
     */
    public static void readLockTest(){
        final MyReadWriteLock test = new MyReadWriteLock();

        new Thread(){
            @Override
            public void run() {
                test.synchronizedSet(Thread.currentThread());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.synchronizedSet(Thread.currentThread());
            }
        }.start();
    }

    public void readLockSet(Thread thread){
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
}
