package com.hcr.demo.jvm;

/**
 * 功能描述：jvm设置 Xss128k(默认1M)
 *
 * @Author:hr
 * @param:
 * @date: 15:10 2021/2/1 0001
 */
public class StackOverflowTest {
    static int count = 0;

    static void redo() {
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println(count);
        }
    }

}
