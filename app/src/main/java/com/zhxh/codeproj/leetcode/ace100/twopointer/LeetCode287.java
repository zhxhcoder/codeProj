package com.zhxh.codeproj.leetcode.ace100.twopointer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
287. 寻找重复数
给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。

假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。

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
        int[] nums = {3, 1, 3, 4, 2};
        System.out.println(new Solution().findDuplicate(nums));
        System.out.println(new Solution().findDuplicate2(nums));
        System.out.println(new Solution().findDuplicate3(nums));
        System.out.println(new Solution().findDuplicate4(nums));
        System.out.println(new Solution().findDuplicate5(nums));
    }

    static class Solution {
        /*
        快慢指针
        -等价于排队链表是否存在环
        -模式识别：可以用快慢指针判断是否存在环
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
        二分查找
        -考虑输入：{1,3,4,2,2}
        -cnt[i]，计算小于等于nums[i]的元素个数
        -重复元素满足cnt[i]>i
        -验证：没有缺失元素，缺失元素小于重复元素，缺失元素大于重复元素

        cnt[i]表示数组中小于等于i的元素个数
        思路：找到第一个cnt[i]>i的位置，cnt[i]单调递增
        模式识别：排序序列中寻找元素，可以使用二分搜索或者二分搜索的变种
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

        /*
        哈希表
        关键词：重复
        模式识别：一旦涉及出现次数，可以使用哈希表
         */
        public int findDuplicate4(int[] nums) {
            Set<Integer> seen = new HashSet<>();
            for (int num : nums) {
                if (seen.contains(num)) {
                    return num;
                }
                seen.add(num);
            }
            return -1;
        }

        /*
        快慢指针
        -等价于排队链表是否存在环
        -模式识别：可以用快慢指针判断是否存在环
        https://leetcode.cn/problems/find-the-duplicate-number/solution/287xun-zhao-zhong-fu-shu-by-kirsche/
        环形链表
        问题转换为142题目，那么针对此题，快慢指针改如何走呢。根据上述数组链表的映射关系，可以退出
        142题中，慢指针走一步，slow=slow.next =》 本题 slow=nums[slow]
        142题中，快指针走两步，fast=fast.next.next =》本题 fast=nums[nums[fast]]
        */
        public int findDuplicate5(int[] nums) {
            int slow = 0;
            int fast = 0;
            slow = nums[slow];
            fast = nums[nums[fast]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            int pre1 = 0;
            int pre2 = slow;
            while (pre1 != pre2) {
                pre1 = nums[pre1];
                pre2 = nums[pre2];
            }
            return pre1;
        }
    }
}
