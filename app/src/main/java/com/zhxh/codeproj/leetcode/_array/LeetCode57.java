package com.zhxh.codeproj.leetcode._array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给出一个无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

示例1:

输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
输出: [[1,5],[6,9]]
示例2:

输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出: [[1,2],[3,10],[12,16]]
解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10]重叠。

 */
class LeetCode57 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5})));
    }

    static class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int left = newInterval[0];
            int right = newInterval[1];
            boolean placed = false;
            List<int[]> ansList = new ArrayList<int[]>();
            for (int[] interval : intervals) {
                if (interval[0] > right) {
                    // 在插入区间的右侧且无交集
                    if (!placed) {
                        ansList.add(new int[]{left, right});
                        placed = true;
                    }
                    ansList.add(interval);
                } else if (interval[1] < left) {
                    // 在插入区间的左侧且无交集
                    ansList.add(interval);
                } else {
                    // 与插入区间有交集，计算它们的并集
                    left = Math.min(left, interval[0]);
                    right = Math.max(right, interval[1]);
                }
            }
            if (!placed) {
                ansList.add(new int[]{left, right});
            }
            int[][] ans = new int[ansList.size()][2];
            for (int i = 0; i < ansList.size(); ++i) {
                ans[i] = ansList.get(i);
            }
            return ans;
        }
    }
}
