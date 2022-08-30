package com.zhxh.codeproj.leetcode.hot100.day6;

/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。



示例 1：

输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
    偷窃到的最高金额 = 1 + 3 = 4 。
示例 2：

输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
    偷窃到的最高金额 = 2 + 9 + 1 = 12 。


提示：

0 <= nums.length <= 100
0 <= nums[i] <= 400

 */
public class LeetCode198 {
    public static void main(String[] args) {
        System.out.println(new Solution().rob(new int[]{3, 3, 24, 5, 2, 1, 9}));
        System.out.println(new Solution2().rob(new int[]{3, 3, 24, 5, 2, 1, 9}));
        System.out.println(new Solution3().rob(new int[]{3, 3, 24, 5, 2, 1, 9}));
        System.out.println(new Solution4().rob(new int[]{3, 3, 24, 5, 2, 1, 9}));
        System.out.println(new Solution5().rob(new int[]{3, 3, 24, 5, 2, 1, 9}));
    }

    static class Solution {
        public int rob(int[] nums) {
            int prevMax = 0;
            int currMax = 0;
            for (int x : nums) {
                int temp = currMax;
                currMax = Math.max(prevMax + x, currMax);
                prevMax = temp;
            }
            return currMax;
        }
    }

    /*
    动态规划
     */
    static class Solution2 {
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            }
            int[] dp = new int[length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < length; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[length - 1];
        }
    }

    static class Solution3 {
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            if (length == 1) {
                return nums[0];
            }
            int first = nums[0], second = Math.max(nums[0], nums[1]);
            for (int i = 2; i < length; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }
    }

    static class Solution4 {
        public int rob(int[] nums) {
            int pre = 0, cur = 0, tmp;
            for (int num : nums) {
                tmp = cur;
                cur = Math.max(pre + num, cur);
                pre = tmp;
            }
            return cur;
        }
    }

    /*
    动态规划
     */
    static class Solution5 {
        public int rob(int[] nums) {
            // 设定dp[i]：代表当打劫到第i间房子时能得到的最大钱数（设定第i间房子必定被打劫）
            // 那么除了打劫第i间房子能获得的钱之外，还需要加上从第0到第i间房子能得到的最大钱数
            // 如下为状态转移方程
            // dp[i] = Max(dp[0]..dp[i - 2]) + nums[i]
            int len = nums.length;
            if (len == 1) return nums[0];
            int[] dp = new int[len];
            dp[0] = nums[0];
            dp[1] = nums[1]; // 设定第0和第1间房子的dp值
            int max = 0; // max用于记录前i-2个房间的dp最大值
            int maxMoney = Math.max(dp[0], dp[1]); // maxMoney用于记录整个dp数组的最大值
            for (int i = 2; i < len; i++) {
                max = Math.max(dp[i - 2], max);
                dp[i] = max + nums[i]; // 对应状态转移方程
                maxMoney = Math.max(maxMoney, dp[i]);
            }
            return maxMoney;
        }
    }
}
