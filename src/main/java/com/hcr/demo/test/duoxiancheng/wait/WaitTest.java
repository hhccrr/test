package com.hcr.demo.test.duoxiancheng.wait;

public class WaitTest {
    public static void main(String[] args) {
        Num n = new Num(10);
        Thread t = new MyThread1(n);
        Thread t2 = new MyThread2(n);
        t.setName("t1");
        t2.setName("t2");
        t.start();

        //确保t1在t2前运行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

    }
}

class  MyThread1  extends Thread{
    Num n;

    public MyThread1(Num n) {
        this.n = n;
    }

    @Override
    public  void run() {

        synchronized (n){
            System.out.println(currentThread().getName() + "begin");
            try {
                n.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currentThread().getName() + "over");
        }



    }
}

class  MyThread2  extends Thread{
    Num n;

    public MyThread2(Num n) {
        this.n = n;
    }

    @Override
    public  void run() {

        synchronized (n) {
            System.out.println(currentThread().getName() + "begin");
            n.notifyAll();
            System.out.println(currentThread().getName() + "over");
        }


    }
}


class Num{
    int num;

    public Num(int num) {
        this.num = num;
    }
}

