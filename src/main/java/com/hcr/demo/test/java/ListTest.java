package com.hcr.demo.test.java;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ListTest {
    public static int length = 1048576; //10的20次幂
    public static List<Integer> list1 = new ArrayList<>();
    public static List<Integer> list2 = new ArrayList<>(length);

    public static void addList(int sign) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            if (sign == 0) {
                list1.add(sign);
            } else {
                list2.add(sign);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("list1长度" + list1.size() + "== list2的长度" + list2.size());
        System.out.println(sign + " exec time is: " + (end - start));
    }

    public static void main(String[] args) {
        addList(0);
        addList(1);
        ArrayList<Integer> list = new ArrayList();
        System.out.println(list.size());
        list.add(1);
        System.out.println(list.size());

    }
}
