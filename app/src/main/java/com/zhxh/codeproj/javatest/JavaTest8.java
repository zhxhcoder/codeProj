package com.zhxh.codeproj.javatest;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zhxh on 2020/7/14
 * PriorityQueue的使用
 */
class JavaTest8 {
    public static void main(String[] args) {
        int[] a = {45, 36, 18, 53, 72, 30, 48, 93, 15, 35};
        minHeap(a);
        maxHeap(a);
    }

    public static void minHeap(int[] a) {
        //1，默认实现的是最小堆，元素按照natural ordering排序（自然排序，例如，数字的从小到大）
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (int i = 0; i < a.length; i++) {
            minHeap.offer(a[i]);
        }
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println("\n");
        //输出（升序）：15 18 30 35 36 45 48 53 72 93
    }

    public static void maxHeap(int[] a) {
        //2，通过比较器排序，实现最大堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                /**以下是对比较器升序、降序的理解.
                 *(1) 写成return o1.compareTo(o2) 或者 return o1-o2表示升序
                 *(2) 写成return o2.compareTo(o1) 或者return o2-o1表示降序
                 */
                return o2.compareTo(o1);
            }
        });

        for (int i = 0; i < a.length; i++) {
            maxHeap.offer(a[i]);
        }
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println("\n");
        //输出（降序）：93 72 53 48 45 36 35 30 18 15
    }
}
