package com.zhxh.codeproj.leetcode.ace100.topk;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
703. 数据流中的第 K 大元素
设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。

请实现 KthLargest 类：

KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。


示例：

输入：
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
输出：
[null, 4, 5, 5, 8, 8]

解释：
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8


提示：
1 <= k <= 104
0 <= nums.length <= 104
-104 <= nums[i] <= 104
-104 <= val <= 104
最多调用 add 方法 104 次
题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 */
public class LeetCode703 {
    public static void main(String[] args) {
        //todo topK问题
        System.out.println(new KthLargest(3, new int[]{4, 5, 8, 2}).add(9));
        System.out.println(new KthLargest2(3, new int[]{4, 5, 8, 2}).add(9));
    }

    /*
    我们可以使用一个大小为k的优先队列来存储前k大的元素，其中优先队列的队头为队列中的最小元素，也就是k大的元素。
    在次
     */
    static class KthLargest {
        PriorityQueue<Integer> pq;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            pq = new PriorityQueue<Integer>();
            for (int x : nums) {
                add(x);
            }
        }

        public int add(int val) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
            return pq.peek();
        }
    }

    /*
    初始化使用快速排序时间复杂度O(nlogn),单次插入采用二分查找加插入排序时间复杂度O(k)。
    因为需要从大到小排序，二分查找也是查找的从大到小排序的数组，所以采用手写的快排和JDK二分查找微调版本。
     */
    static class KthLargest2 {
        private final int k;
        private final int[] nums;

        public KthLargest2(int k, int[] nums) {
            this.k = k;
            int n = nums.length;
            quickSort(nums, 0, n - 1);
            this.nums = Arrays.copyOf(nums, k);
            if (n < k) {
                // Arrays.fill(this.nums, n, k, Integer.MIN_VALUE);
                for (int i = n; i < k; i++) {
                    this.nums[i] = Integer.MIN_VALUE;
                }
            }
        }

        public int add(int val) {
            int index = binarySearch(nums, val);
            if (index < 0) {
                index = -index - 1;
            }
            if (k - 1 - index >= 0) {
                System.arraycopy(nums, index, nums, index + 1, k - 1 - index);
                nums[index] = val;
            }
            return nums[k - 1];
        }

        private static void quickSort(int[] arr, int l, int r) {
            if (l >= r) return;
            int pivot = arr[l];
            int i = l, j = r;
            while (i < j) {
                // 一定要先右再左
                while (i < j && arr[j] <= pivot)
                    j--;
                while (i < j && arr[i] >= pivot)
                    i++;
                if (i < j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            // 基准数归位
            arr[l] = arr[i];
            arr[i] = pivot;
            quickSort(arr, l, i);
            quickSort(arr, i + 1, r);
        }

        private int binarySearch(int[] a, int key) {
            int low = 0;
            int high = a.length - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                int midVal = a[mid];

                if (midVal > key)
                    low = mid + 1;
                else if (midVal < key)
                    high = mid - 1;
                else
                    return mid; // key found
            }
            return -(low + 1);  // key not found.
        }
    }

    static class KthLargest3 {
        /*
        因为每次只保留前k大的元素,因此每一次插入只要保存好前k大的元素即可
        也就是说<=第k大的元素都可以不用保留,因为他们对当前返回的结果以及后面的结果均无影响
        边界条件非常多!!!!做好心理准备
        */
        // 存储最大的前k个元素
        int[] preK;
        int k;

        public KthLargest3(int k, int[] nums) {
            this.k = k;
            this.preK = new int[k];
            // 这里要先将nums装箱才可以用匿名类排序
            Integer[] arr = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                arr[i] = nums[i];
            }
            int len = arr.length;
            // 先将nums降序排序
            Arrays.sort(arr, (a, b) -> b - a);
            // 将初始nums的最大前k个元素装进preK
            for (int i = 0; i < k; i++) {
                // 这里出现k<len的情况,要用MIN_VALUE补充后面的空位,表示还没有数填充的地方
                // 利用MIN_VALUE填充之后,有数进来就将MIN_VALUE驱逐出preK,先填满preK
                if (i > len - 1) {
                    preK[i] = Integer.MIN_VALUE;
                    continue;
                }
                preK[i] = arr[i];
            }
        }

        public int add(int val) {
            // 添加元素时,当且仅当val>preK[k-1]对结果有影响,否则直接返回preK[k-1]
            // eg:preK=[8,6,5],加入4不影响结果,而加入5也不影响,而加入6就变成了[8,6,6]返回6
            if (val > preK[k - 1]) {
                // 将val加入preK并重新调整位置
                // 从后开始找出首个不小于val的位置
                int pos = k - 1;
                while (pos >= 0 && preK[pos] < val) {
                    pos--;
                }
                // 如果val=6,那么pos将停留在上面例子的6位置,将pos右边的额元素全部右移一位
                // 这里有个坑:覆盖元素要从后面开始,如果从前面开始,下一个要用的preK[i]将会被覆盖
                // 从后面遍历可以取得原始数据
                for (int i = k - 2; i >= pos + 1; i--) {
                    preK[i + 1] = preK[i];
                }
                // 腾出来preK[pos+1]空间就是用来装val的
                preK[pos + 1] = val;
            }
            // 最后再返回preK最后一个数字(无论有没有执行if的都返回这个,执行了if的充其量就是更新了preK罢了)
            return preK[k - 1];
        }
    }
}
