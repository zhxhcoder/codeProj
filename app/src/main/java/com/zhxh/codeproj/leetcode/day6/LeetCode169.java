package com.zhxh.codeproj.leetcode.day6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*

给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2
 */
public class LeetCode169 {
    public static void main(String[] args) {
        System.out.println(new Solution().majorityElement(new int[]{1, 7, 7, 2, 7, 3, 7, 4, 7, 5, 7}));
        System.out.println(new Solution2().majorityElement(new int[]{1, 7, 7, 2, 7, 3, 7, 4, 7, 5, 7}));
        System.out.println(new Solution3().majorityElement(new int[]{1, 7, 7, 2, 7, 3, 7, 4, 7, 5, 7}));
        System.out.println(new Solution4().majorityElement(new int[]{1, 7, 7, 2, 7, 3, 7, 4, 7, 5, 7}));
    }

    static class Solution {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }

    static class Solution2 {
        private Map<Integer, Integer> countNums(int[] nums) {
            Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
            for (int num : nums) {
                if (!counts.containsKey(num)) {
                    counts.put(num, 1);
                } else {
                    counts.put(num, counts.get(num) + 1);
                }
            }
            return counts;
        }

        public int majorityElement(int[] nums) {
            Map<Integer, Integer> counts = countNums(nums);

            Map.Entry<Integer, Integer> majorityEntry = null;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                    majorityEntry = entry;
                }
            }
            return majorityEntry.getKey();
        }
    }

    /*
    投票法
    如果我们把众数记为 +1，把其他数记为 −1，将它们全部加起来，显然和大于 0，从结果本身我们可以看出众数比其他数多。
    如果候选人不是maj 则 maj,会和其他非候选人一起反对 会反对候选人,所以候选人一定会下台(maj==0时发生换届选举)
    如果候选人是maj , 则maj 会支持自己，其他候选人会反对，同样因为maj 票数超过一半，所以maj 一定会成功当选
     */
    static class Solution3 {
        public int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;
            for (int num : nums) {
                if (count == 0) {//换届
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }
            return candidate;
        }
    }

    /*
    投票法
    也可以用出栈入栈来解决
     */
    static class Solution4 {
        public int majorityElement(int[] nums) {
            //假设第一个元素的票数
            int count = 1;
            //假设第一个元素为当选人
            int candidate = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == candidate) {
                    count++;
                } else {
                    count--;
                }
                //如果票数小于等于0的话就更换当选人
                //如果候选人不是maj 则 maj,会和其他非候选人一起反对 会反对候选人,所以候选人一定会下台(maj==0时发生换届选举)
                //如果候选人是maj , 则maj 会支持自己，其他候选人会反对，同样因为maj 票数超过一半，所以maj 一定会成功当选
                if (count <= 0) {
                    //更换当选人
                    candidate = nums[i];
                    //票数重新置为1
                    count = 1;
                }
            }
            return candidate;
        }
    }
}

