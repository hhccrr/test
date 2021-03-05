package com.hcr.demo.test.duoxiancheng.Volatile;

/**
 * @author Administrator
 * 原理：在生成汇编代码时会在volatile修饰共享变量进行写操作的时候会多出lock前缀指令
 *作用：
 *1：lock前缀指令会引起处理器缓存写回内存
 *2：一个处理器的缓存写回内存会让其他处理器的缓存失效
 *3：当其他处理器发现本地缓存失效之后，会重新到内存中读取数据，就得到了最新的数据
 *不能代替锁，只能保证可见性，无法保证原子性（只能保证对单次读写的原子性，i++这种操作不能保证原子性）
 *
 */
public class VolatileTest extends Thread {
    private static Boolean a = false;

    private static volatile Boolean b = false;

    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    @Override
    public void run() {
        while (!a){
        }
    }

    /**
    *因为a不保证可见性，所以线程会一直执行
    */
    public static void test1() throws InterruptedException {
        new VolatileTest().start();
        Thread.sleep(1000);
        a = true;
    }

    /**
     *因为保证了可见性，在主线程将b改为true之后其他线程会从内存中读取最新值
     */
    public static void test2() throws InterruptedException {
        new VolatileTest().start();
        Thread.sleep(1000);
        b = true;
    }


    /**
     * 一个i++的例子
     *    假如某个时刻变量inc的值为10，
     * 　　线程1对变量进行自增操作，线程1先读取了变量inc的原始值，然后线程1被阻塞了；
     * 　　然后线程2对变量进行自增操作，线程2也去读取变量inc的原始值，由于线程1只是对变量inc进行读取操作，而没有对变量进行修改操作，所以不会导致线程2的工作内存中缓存变量inc的缓存行无效，所以线程2会直接去主存读取inc的值，发现inc的值时10，然后进行加1操作，并把11写入工作内存，最后写入主存。
     * 　　然后线程1接着进行加1操作，由于已经读取了inc的值，注意此时在线程1的工作内存中inc的值仍然为10，所以线程1对inc进行加1操作后inc的值为11，然后将11写入工作内存，最后写入主存。
     * 　　那么两个线程分别进行了一次自增操作后，inc只增加了1。
     *
     * 我刚开始很疑惑：“线程1在读取inc为10后被阻塞了，没有进行修改所以不会去通知其他线程，此时线程2拿到的还是10，这点可以理解。但是后来线程2修改了inc变成11后写回主内存，这下是修改了，线程1再次运行时，难道不会去主存中获取最新的值吗？按照volatile的定义，如果volatile修饰的变量发生了变化，其他线程应该去主存中拿变化后的值才对啊？”
     * 严格的说，对任意单个volatile变量的读/写具有原子性，但类似于volatile++这种复合操作不具有原子性。在《Java并发编程的艺术》中有这一段描述：“在多处理器下，为了保证各个处理器的缓存是一致的，就会实现缓存一致性协议，每个处理器通过嗅探在总线上传播的数据来检查自己缓存的值是不是过期了，当处理器发现自己缓存行对应的内存地址被修改，就会将当前处理器的缓存行设置成无效状态，当处理器对这个数据进行修改操作的时候，会重新从系统内存中把数据读到处理器缓存里。”注意的是，这里的修改操作，是指的一个操作。
     * 答疑：可以知道自增操作是三个原子操作组合而成的复合操作。在一个操作中，读取了inc变量后，是不会再读取的inc的，所以它的值还是之前读的10，它的下一步是自增操作。
     */
    public static void test3(){
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    for(int j=0;j<1000;j++){
                        test.increase();
                    }
                };
            }.start();
        }
        //得到是存活的线程数
        System.out.println(Thread.activeCount());
        while(Thread.activeCount()>1){
            //使当前线程由执行状态，变成为就绪状态，让出cpu时间，在下一个线程执行时候，此线程有可能被执行，也有可能没有被执行
            Thread.yield();
        }

        System.out.println(test.inc);
    }

    public static void main(String[] args) throws InterruptedException {
        test3();
    }
}
