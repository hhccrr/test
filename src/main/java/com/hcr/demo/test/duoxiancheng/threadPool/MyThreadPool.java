package com.hcr.demo.test.duoxiancheng.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class MyThreadPool {

    public static void main(String[] args) {
//        myNewCachedThreadPool();
//        myNewFixedThreadPool();
//        myNewScheduledThreadPool1();
        myNewScheduledThreadPool2();
//        myNewSingleThreadExecutor();
    }

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     */
    public static void myNewCachedThreadPool(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
//            try{
//                Thread.sleep(10);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"-----"+index);
                }
            });
        }
//        cachedThreadPool.shutdown();
    }

    /**
     * 创建一个订长的线程池，可控制线程的最大并发数，超出的线程会在队列的中等待
     */
    public static void myNewFixedThreadPool(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
//            try{
//                Thread.sleep(10);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"-----"+index);
                }
            });
        }
//        fixedThreadPool.shutdown();
    }

    /**
     * 创建一个订长的线程池，支持定时，及周期性任务
     *
     * myNewScheduledThreadPool1:延迟3秒后执行任务
     */
    public static void myNewScheduledThreadPool1(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.schedule(new Runnable() {
                public void run() {
                    System.out.println("delay 3 seconds");
                }
            }, 3, TimeUnit.SECONDS);
        }
//        scheduledThreadPool.shutdown();
    }

    /**
     * 启动后8秒执行任务，中间间隔三秒执行一次
     */
    public static void myNewScheduledThreadPool2(){
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    System.out.println("delay 3 seconds");
                }
            }, 8,3, TimeUnit.SECONDS);
        }
//        scheduledThreadPool.shutdown();
    }

    /**
     * 按顺序来执行线程任务   但是不同于单线程，这个线程池只是只能存在一个线程，这个线程死后另外一个线程会补上。
     */
    public static void myNewSingleThreadExecutor(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println(Thread.currentThread().getName()+"---"+index);
                        Thread.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
//        singleThreadExecutor.shutdown();
    }
}
