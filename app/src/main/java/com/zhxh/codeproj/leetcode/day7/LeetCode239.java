package com.zhxh.codeproj.leetcode.day7;

import java.util.*;

/*
给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。

返回 滑动窗口中的最大值 。



示例 1：

输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
示例 2：

输入：nums = [1], k = 1
输出：[1]


提示：

1 <= nums.length <= 105
-104<= nums[i] <= 104
1 <= k <= nums.length

 */
public class LeetCode239 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(new Solution2().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(new Solution3().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(new Solution4().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    /*
    单调队列
     */
    static class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            Deque<Integer> deque = new LinkedList<Integer>();
            for (int i = 0; i < k; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
            }

            int[] ans = new int[n - k + 1];
            ans[0] = nums[deque.peekFirst()];
            for (int i = k; i < n; ++i) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
                while (deque.peekFirst() <= i - k) {
                    deque.pollFirst();
                }
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
            return ans;
        }
    }

    static class Solution2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            // 窗口个数
            int[] res = new int[nums.length - k + 1];
            LinkedList<Integer> queue = new LinkedList<>();

            // 遍历数组中元素，right表示滑动窗口右边界
            for (int right = 0; right < nums.length; right++) {
                // 如果队列不为空且当前考察元素大于等于队尾元素，则将队尾元素移除。
                // 直到，队列为空或当前考察元素小于新的队尾元素
                while (!queue.isEmpty() && nums[right] >= nums[queue.peekLast()]) {
                    queue.removeLast();
                }

                // 存储元素下标
                queue.addLast(right);

                // 计算窗口左侧边界
                int left = right - k + 1;
                // 当队首元素的下标小于滑动窗口左侧边界left时
                // 表示队首元素已经不再滑动窗口内，因此将其从队首移除
                if (queue.peekFirst() < left) {
                    queue.removeFirst();
                }

                // 由于数组下标从0开始，因此当窗口右边界right+1大于等于窗口大小k时
                // 意味着窗口形成。此时，队首元素就是该窗口内的最大值
                if (right + 1 >= k) {
                    res[left] = nums[queue.peekFirst()];
                }
            }
            return res;
        }

    }

    /*
    优先队列
     */
    static class Solution3 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                public int compare(int[] pair1, int[] pair2) {
                    return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
                }
            });
            for (int i = 0; i < k; ++i) {
                pq.offer(new int[]{nums[i], i});
            }
            int[] ans = new int[n - k + 1];
            ans[0] = pq.peek()[0];
            for (int i = k; i < n; ++i) {
                pq.offer(new int[]{nums[i], i});
                while (pq.peek()[1] <= i - k) {
                    pq.poll();
                }
                ans[i - k + 1] = pq.peek()[0];
            }
            return ans;
        }
    }

    /*
    分块 + 预处理
     */
    static class Solution4 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] prefixMax = new int[n];
            int[] suffixMax = new int[n];
            for (int i = 0; i < n; ++i) {
                if (i % k == 0) {
                    prefixMax[i] = nums[i];
                } else {
                    prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
                }
            }
            for (int i = n - 1; i >= 0; --i) {
                if (i == n - 1 || (i + 1) % k == 0) {
                    suffixMax[i] = nums[i];
                } else {
                    suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
                }
            }

            int[] ans = new int[n - k + 1];
            for (int i = 0; i <= n - k; ++i) {
                ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
            }
            return ans;
        }
    }
}
