package com.hcr.demo.jvm;

import java.util.ArrayList;

/**
 * 功能描述：
 *
 * @Author:hr
 * @param:
 * @date: 10:57 2021/2/3 0003
 */
public class HeapTest {


    byte[] a = new byte[1024 * 100];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }
    }


}
