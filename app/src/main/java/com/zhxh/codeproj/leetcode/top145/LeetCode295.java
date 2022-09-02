package com.zhxh.codeproj.leetcode.top145;

import java.util.PriorityQueue;
import java.util.TreeMap;

/*
中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例：

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2
进阶:

如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？

 */
public class LeetCode295 {
    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        finder.addNum(3);
        finder.addNum(4);
        System.out.println(finder.findMedian());

        MedianFinder2 finder2 = new MedianFinder2();
        finder2.addNum(1);
        finder2.addNum(2);
        finder2.addNum(3);
        finder2.addNum(4);
        System.out.println(finder2.findMedian());
    }

    /*
    方法一：优先队列
    用两个优先队列，分别记录大于中位数的数和小于中位数的数。
    当累计添加的数的数量为奇数时，queMin中的数的数量比queMax多一个，此时中位数为queMin的队头。
    当累计添加的数的数量为偶数时，两个优先队列中的数的数量相同，此时中位数为它们的队头的平均值。
     */
    static class MedianFinder {
        PriorityQueue<Integer> queMin;//大顶堆
        PriorityQueue<Integer> queMax;//小顶堆

        public MedianFinder() {
            //降序排列
            queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
            //默认升序排列
            queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
        }

        public void addNum(int num) {
            if (queMin.isEmpty() || num <= queMin.peek()) {
                queMin.offer(num);
                if (queMax.size() + 1 < queMin.size()) {
                    queMax.offer(queMin.poll());
                }
            } else {
                queMax.offer(num);
                if (queMax.size() > queMin.size()) {
                    queMin.offer(queMax.poll());
                }
            }
        }

        public double findMedian() {
            if (queMin.size() > queMax.size()) {
                return queMin.peek();
            }
            return (queMin.peek() + queMax.peek()) / 2.0;
        }
    }

    /*
    方法二：有序集合 + 双指针
     */
    static class MedianFinder2 {
        TreeMap<Integer, Integer> nums;
        int n;
        int[] left;
        int[] right;

        public MedianFinder2() {
            nums = new TreeMap<Integer, Integer>();
            n = 0;
            left = new int[2];
            right = new int[2];
        }

        public void addNum(int num) {
            nums.put(num, nums.getOrDefault(num, 0) + 1);
            if (n == 0) {
                left[0] = right[0] = num;
                left[1] = right[1] = 1;
            } else if ((n & 1) != 0) {
                if (num < left[0]) {
                    decrease(left);
                } else {
                    increase(right);
                }
            } else {
                if (num > left[0] && num < right[0]) {
                    increase(left);
                    decrease(right);
                } else if (num >= right[0]) {
                    increase(left);
                } else {
                    decrease(right);
                    System.arraycopy(right, 0, left, 0, 2);
                }
            }
            n++;
        }

        public double findMedian() {
            return (left[0] + right[0]) / 2.0;
        }

        private void increase(int[] iterator) {
            iterator[1]++;
            if (iterator[1] > nums.get(iterator[0])) {
                iterator[0] = nums.ceilingKey(iterator[0] + 1);
                iterator[1] = 1;
            }
        }

        private void decrease(int[] iterator) {
            iterator[1]--;
            if (iterator[1] == 0) {
                iterator[0] = nums.floorKey(iterator[0] - 1);
                iterator[1] = nums.get(iterator[0]);
            }
        }
    }
}
