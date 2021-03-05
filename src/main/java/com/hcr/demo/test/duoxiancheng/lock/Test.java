package com.hcr.demo.test.duoxiancheng.lock;


import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock()获取锁，发现被已被其他线程获取则等待
 * tryLock()获取锁，如果获取到了返回true，如果没有获取到返回false，不会等待
 * tryLock(long time, TimeUnit unit) 在规定时间内获取锁，如果在规定时间内获取到锁则返回true，在规定时间没有获取到就返回false
 * lockInterruptibly()获取锁 当一个线程获取了锁之后，是不会被interrupt()方法中断的。
 *                    因为本身在前面的文章中讲过单独调用interrupt()方法不能中断正在运行过程中的线程，只能中断阻塞过程中的线程。
 * 　　               因此当通过lockInterruptibly()方法获取某个锁时，如果不能获取到，只有进行等待的情况下，是可以响应中断的。
 * 　　               而用synchronized修饰的话，当一个线程处于等待某个锁的状态，是无法被中断的，只有一直等待下去。
 * unLock()方法是用来释放锁的。
 * newCondition()
 *
 * ReentrantLock，意思是“可重入锁”，ReentrantLock是唯一实现了Lock接口的类，并且ReentrantLock提供了更多的方法。
 */
public class Test {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    //注意这个地方
    private Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
//      testLock();
//        testTryLock();
        lockInterruptiblyTest();
    }

    public static void testLock(){
        final Test test = new Test();

        new Thread(){
            @Override
            public void run() {
                test.insertLock(Thread.currentThread());
            };
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.insertLock(Thread.currentThread());
            };
        }.start();
    }

    public void insertLock(Thread thread) {
        //注意这个地方
//      Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }

    public static void testTryLock(){
        final Test test = new Test();

        new Thread(){
            @Override
            public void run() {
                test.insertTryLock(Thread.currentThread());
            };
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.insertTryLock(Thread.currentThread());
            };
        }.start();
    }

    public void insertTryLock(Thread thread) {
       if(lock.tryLock()){
           try {
               System.out.println(thread.getName()+"得到了锁");
               for(int i=0;i<5;i++) {
                   arrayList.add(i);
               }
           } catch (Exception e) {
               // TODO: handle exception
           }finally {
               System.out.println(thread.getName()+"释放了锁");
               lock.unlock();
           }
       }

    }


    /**
     * lockInterruptibly()响应中断的使用方法：
     */
    public static void lockInterruptiblyTest(){
        final Test test = new Test();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();


    }

    public void insertLockInterruptibly(Thread thread) throws InterruptedException{
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE){
                    break;
                    //插入数据
                }
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }
}

    class MyThread extends Thread {
        private Test test = null;
        public MyThread(Test test) {
            this.test = test;
        }
        @Override
        public void run() {

            try {
                test.insertLockInterruptibly(Thread.currentThread());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"被中断");
            }
        }
    }

