package com.zhxh.codeproj.leetcode.day8;

import java.util.Arrays;

/*
给定一个包含n + 1 个整数的数组nums ，其数字都在[1, n]范围内（包括 1 和 n），可知至少存在一个重复的整数。

假设 nums 只有 一个重复的整数 ，返回这个重复的数 。

你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。



示例 1：

输入：nums = [1,3,4,2,2]
输出：2
示例 2：

输入：nums = [3,1,3,4,2]
输出：3


提示：

1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次


进阶：

如何证明 nums 中至少存在一个重复的数字?
你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？

 */
public class LeetCode287 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(new Solution().findDuplicate(nums));
        System.out.println(new Solution().findDuplicate2(nums));
        System.out.println(new Solution().findDuplicate3(nums));
    }

    static class Solution {
        /*
        快慢指针
         */
        public int findDuplicate(int[] nums) {
            int slow = 0, fast = 0;
            do {
                slow = nums[slow];
                fast = nums[nums[fast]];
            } while (slow != fast);
            slow = 0;
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }

        /*
        排序
         */
        public int findDuplicate2(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    return nums[i];
                }
            }
            return -1;
        }

        /*
        循环遍历
         */
        public int findDuplicate3(int[] nums) {
            int n = nums.length;
            int l = 1, r = n - 1, ans = -1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                int cnt = 0;
                for (int i = 0; i < n; ++i) {
                    if (nums[i] <= mid) {
                        cnt++;
                    }
                }
                if (cnt <= mid) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                    ans = mid;
                }
            }
            return ans;
        }
    }
}
