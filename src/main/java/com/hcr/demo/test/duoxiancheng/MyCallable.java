package com.hcr.demo.test.duoxiancheng;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MyCallable implements Callable<Object> {

    private int taskNum;

    public MyCallable(int taskNum) {
        this.taskNum = taskNum;
    }


    /**
    *功能描述：使用Executors.newFixedThreadPool创建线程池
    *@Author:hr
    *@param:
    *@date: 10:55 2021/3/4 0004
    */
    public static void test1() throws ExecutionException, InterruptedException {
        System.out.println("test1程序开始执行");
        Date date1 = new Date();
        int taskSize = 5;
        ExecutorService pool =  Executors.newFixedThreadPool(taskSize);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < taskSize; i++) {
            Callable callable = new MyCallable(i);
            //执行任务，并获得Future对象
            Future f = pool.submit(callable);
            list.add(f);
        }

        //关闭线程池
        pool.shutdown();

        //获取所有并发任务的运行结果
        for (Future future : list) {
            //OPTION + return 抛异常
            System.out.println(">>>" + future.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】");
    }

    /**
    *功能描述：线程直接使用new Thread来创建
    *@Author:hr
    *@param:
    *@date: 11:09 2021/3/4 0004
    */
    public void test2() throws ExecutionException, InterruptedException {
        System.out.println("test1程序开始执行");
        Date date1 = new Date();
        int taskSize = 5;

        FutureTask[] randomNumberTasks = new FutureTask[5];
        List<Future> list = new ArrayList<>();

        for (int i = 0; i < randomNumberTasks.length; i++) {
            Callable callable = new MyCallable(i);

            //开始执行任务，并获取Future对象
            randomNumberTasks[i] = new FutureTask(callable);

            Thread t = new Thread(randomNumberTasks[i]);
            t.start();
        }

        //获取所有并发任务的运行结果

        Date date2 = new Date();
        for (Future future : list) {
            // 从Future对象上获取任务的返回值，并输
            System.out.println(future.get().toString());
        }
        System.out.println("----程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】");
    }

    public ThreadPoolExecutor  test3(){
        System.out.println("test1程序开始执行");
        Date date1 = new Date();
        int taskSize = 5;


        /**
        *功能描述：使用ThreadPollExecutor创建线程池
        *@Author:hr
        *@param:
         * corePoolSize:线程池维护线程的最少数量(core:核心)
         *maximumPoolSize:线程池维护线程的最大数量 
         *keepAliveTime:线程池维护线程所允许的空闲时间
         *unit:线程池维护线程所允许的空闲时间的单位
         *workQueue:线程池所使用的缓冲队列
         *handler:线程池对拒绝任务的处理策略
        *@date: 11:22 2021/3/4 0004
        */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,10,60,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100,true),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("thread_test");
                    return thread;
                },
                (r, executor) -> {
                    BlockingQueue<Runnable> queue = executor.getQueue();
                    try{
                        queue.put(r);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                });
        return threadPool;
    }

    /**
     *功能描述：call方法实现，主要用于执行线程的具体实现，并返回结果
     *@Author:hr
     *@param:
     *@date: 10:53 2021/3/4 0004
     */
    @Override
    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "任务启动");
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
//        test1();
    }
}
