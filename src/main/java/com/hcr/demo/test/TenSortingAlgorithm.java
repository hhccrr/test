package com.hcr.demo.test;

import java.util.Arrays;

/**
 * 功能描述：十大排序算法
 *
 * @Author:hr
 * @param:
 * @date: 15:23 2021/1/21 0021
 */
public class TenSortingAlgorithm {

    /**
     * 功能描述：交换类
     * 冒泡排序 循环遍历多次，从前往后把大元素往后调，每次确定一个最大（最小）元素，多次后达到排序序列（或者从后往前把小元素往前调）
     *
     * @Author:hr
     * @param:
     * @date: 15:32 2021/1/21 0021
     */
    public static int[] bubbleSort(int[] a) {
        for (int i = a.length - 1; i >= 0; i--) {

            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    int team = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = team;
                }
            }
        }
        return a;
    }

    /**
     * 功能描述：交换类
     * 快速排序
     *
     * @Author:hr
     * @param:
     * @date: 15:46 2021/1/21 0021
     */
    public static int[] quickSort(int s[], int l, int r) {
        if (l < r) {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换
            int i = l, j = r, x = s[l];
            while (i < j) {
                // 从右向左找第一个小于x的数
                while (i < j && s[j] >= x) {
                    j--;
                }
                if (i < j) {
                    s[i++] = s[j];
                }
                // 从左向右找第一个大于等于x的数
                while (i < j && s[i] < x) {
                    i++;
                }
                if (i < j) {
                    s[j--] = s[i];
                }
            }
            s[i] = x;
            // 递归调用
            quickSort(s, l, i - 1);
            quickSort(s, i + 1, r);
        }
        return s;
    }

    /**
     * 功能描述：插入类
     * 直接插入排序
     *
     * @Author:hr
     * @param:
     * @date: 09:20 2021/1/25 0025
     */
    public static int[] directInsertionSort(int[] a) {

        int team = 0;
        for (int i = 1; i < a.length; i++) {
            team = a[i];
            for (int j = i - 1; j >= 0; j--) {
                if (team < a[j]) {
                    a[i] = a[j];
                    a[j] = team;
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = {3, 5, 2, 53, 234, 22, 1, 21};
//        int[] bubbleSort = bubbleSort(a);
//        int[] directInsertionSort = directInsertionSort(a);
        int[] ints = quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(ints));
    }
}
