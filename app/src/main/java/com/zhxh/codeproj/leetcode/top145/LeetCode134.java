package com.zhxh.codeproj.leetcode.top145;

/*
在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。

给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。

 

示例 1:

输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
输出: 3
解释:
从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。
示例 2:

输入: gas = [2,3,4], cost = [3,4,3]
输出: -1
解释:
你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
因此，无论怎样，你都不可能绕环路行驶一周。
 

提示:

gas.length == n
cost.length == n
1 <= n <= 105
0 <= gas[i], cost[i] <= 104

 */
public class LeetCode134 {
    public static void main(String[] args) {
        System.out.println(new Solution().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(new Solution2().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(new Solution3().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

    /*
    方法一：一次遍历
     */
    static class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int i = 0;
            while (i < n) {
                int sumOfGas = 0, sumOfCost = 0;
                int cnt = 0;
                while (cnt < n) {
                    int j = (i + cnt) % n;
                    sumOfGas += gas[j];
                    sumOfCost += cost[j];
                    if (sumOfCost > sumOfGas) {
                        break;
                    }
                    cnt++;
                }
                if (cnt == n) {
                    return i;
                } else {
                    i = i + cnt + 1;
                }
            }
            return -1;
        }
    }

    static class Solution2 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int i = 0;

            // 从头到尾遍历每个加油站，并且检查以该加油站为起点，能否行驶一周
            while (i < n) {
                int sumOfGas = 0; // 总共加的油
                int SumOfCost = 0; // 总共消费的油
                int count = 0;     // 记录能走过几个站点
                while (count < n) {  // 退出循环的条件是走过所有的站点
                    int j = (i + count) % n; // 加油站是环形的
                    sumOfGas += gas[j];
                    SumOfCost += cost[j];
                    if (SumOfCost > sumOfGas) { // 如果这个站点发现油不够了
                        break;
                    }
                    count++; // 这个站点满足情况
                }

                if (count == n) {  // 如果能环绕一圈
                    return i;
                } else { // 不行的话 从下一个站点开始 检查
                    i = i + count + 1;
                }
            }
            // 所有加油站作为起点都不满足
            return -1;
        }
    }

    static class Solution3 {
        /*
         * 一次遍历
         * 如果某点ans能一直走到数组结尾，保留从数组结尾到数组第一个节点时剩余汽油数cur。
         * 并保留从数组第一个点走到该点时剩余汽油数res。
         * 比较两个数的大小，如果cur + res >= 0，说明可以从数组第一个节点走到ans。即可以绕环路行驶一周
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int cur = 0, res = 0, ans = 0;

            for (int i = 0; i < n; i++) {
                cur += gas[i] - cost[i];
                if (cur < 0) {
                    ans = i + 1;
                    res += cur;
                    cur = 0;
                }
            }
            return cur + res >= 0 ? ans : -1;
        }
    }
}
