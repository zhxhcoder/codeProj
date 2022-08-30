package com.zhxh.codeproj.leetcode.hot100.day3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。

 */
public class LeetCode56 {
    public static void main(String[] args) {
        System.out.println(new Solution().merge(Arrays.asList(
                new Solution.Interval(1, 3)
                , new Solution.Interval(2, 6)
                , new Solution.Interval(8, 10)
                , new Solution.Interval(15, 18)
        )));
    }

    static class Solution {
        public List<Interval> merge(List<Interval> intervals) {
            Collections.sort(intervals, new Comparator<Interval>() {
                @Override
                public int compare(Interval a, Interval b) {
                    return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
                }
            });

            LinkedList<Interval> merged = new LinkedList<Interval>();
            for (Interval interval : intervals) {
                // 如果合并区间列表为空，或者当前区间与前一个区间不重叠，则简单地追加它。
                if (merged.isEmpty() || merged.getLast().end < interval.start) {
                    merged.add(interval);
                }
                // 否则，有重叠，所以我们合并当前和以前的间隔。
                else {
                    merged.getLast().end = Math.max(merged.getLast().end, interval.end);
                }
            }
            return merged;
        }

        private static class Interval {
            public int start;
            public int end;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "{" +
                        "start=" + start +
                        ", end=" + end +
                        '}';
            }
        }
    }
}
