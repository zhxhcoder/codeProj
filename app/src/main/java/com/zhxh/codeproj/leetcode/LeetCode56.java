package com.zhxh.codeproj.leetcode;

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
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。

 */
public class LeetCode56 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<Solution.Interval> intervals = Arrays.asList(
                new Solution.Interval(1, 3)
                , new Solution.Interval(2, 6)
                , new Solution.Interval(8, 10)
                , new Solution.Interval(15, 18)
        );

        List<Solution.Interval> results = solution.merge(intervals);

        for (Solution.Interval interval : results) {
            System.out.println(interval.toString());
        }
    }

    static class Solution {
        private class IntervalComparator implements Comparator<Interval> {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
            }
        }

        public List<Interval> merge(List<Interval> intervals) {
            Collections.sort(intervals, new IntervalComparator());

            LinkedList<Interval> merged = new LinkedList<Interval>();
            for (Interval interval : intervals) {
                // if the list of merged intervals is empty or if the current
                // interval does not overlap with the previous, simply append it.
                if (merged.isEmpty() || merged.getLast().end < interval.start) {
                    merged.add(interval);
                }
                // otherwise, there is overlap, so we merge the current and previous
                // intervals.
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
                return "Interval{" +
                        "start=" + start +
                        ", end=" + end +
                        '}';
            }
        }
    }
}
