package com.zhxh.codeproj.leetcode.ace100.topk;

import java.util.PriorityQueue;
import java.util.Random;

/*
215. 数组中的第K个最大元素
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1:

输入: [3,2,1,5,6,4], k = 2
输出: 5
示例2:

输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4


提示：

1 <= k <= nums.length <= 105
-104<= nums[i] <= 104

 */
class LeetCode215 {
    public static void main(String[] args) {
        //todo topK问题
        System.out.println(new Solution().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 3));
        System.out.println(new Solution2().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 3));
        System.out.println(new Solution3().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 3));
    }

    /*
    基于堆排序的选择方法
    堆是一种完全二叉树
    完全二叉树：若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。
    之前说到，堆是一种完全二叉树，但是否就意味着我们真的要用树来表示它呢？
    答案是否定的，因为完全二叉树有其非常卓越的性质：对于任意一个父节点的序号n来说（这里n从0算），它的子节点的序号一定是2n+1,2n+2，因此我们可以直接用数组来表示一个堆。
    所以对于上图来说，我们可以用[0,1,3,2,4,6,7,8,9,5,10]来表示这个堆。
    最小堆要求，对于任意一个父结点来说，其子结点的值都大于这个父节点,
    同理，最大堆就是说，其子节点的值都小于这个父节点。
     */
    static class Solution {
        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length < k) {
                throw new IllegalArgumentException("找不到了");
            }
            int heapSize = nums.length;
            buildMaxHeap(nums, heapSize);
            for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
                swap(nums, 0, i);
                --heapSize;
                maxHeapify(nums, 0, heapSize);
            }
            return nums[0];
        }

        public void buildMaxHeap(int[] a, int heapSize) {
            for (int i = heapSize / 2; i >= 0; --i) {
                maxHeapify(a, i, heapSize);
            }
        }

        public void maxHeapify(int[] a, int i, int heapSize) {
            int l = i * 2 + 1, r = i * 2 + 2, largest = i;
            if (l < heapSize && a[l] > a[largest]) {
                largest = l;
            }
            if (r < heapSize && a[r] > a[largest]) {
                largest = r;
            }
            if (largest != i) {
                swap(a, i, largest);
                maxHeapify(a, largest, heapSize);
            }
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    /*
    优先队列
     */
    static class Solution2 {
        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length < k) {
                throw new IllegalArgumentException("找不到了");
            }
            // 初始化最小元素优先堆
            PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
            //在堆中保留 k 个最大元素
            for (int n : nums) {
                heap.add(n);
                if (heap.size() > k)
                    heap.poll();
            }
            return heap.poll();
        }
    }

    /*
    基于快速排序的选择方法
     */
    static class Solution3 {

        Random random = new Random();

        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length < k) {
                throw new IllegalArgumentException("找不到了");
            }
            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }

        public int quickSelect(int[] a, int l, int r, int index) {
            int q = randomPartition(a, l, r);
            if (q == index) {
                return a[q];
            } else {
                return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
            }
        }

        public int randomPartition(int[] a, int l, int r) {
            int i = random.nextInt(r - l + 1) + l;
            swap(a, i, r);
            return partition(a, l, r);
        }

        public int partition(int[] a, int l, int r) {
            int x = a[r], i = l - 1;
            for (int j = l; j < r; ++j) {
                if (a[j] <= x) {
                    swap(a, ++i, j);
                }
            }
            swap(a, i + 1, r);
            return i + 1;
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
