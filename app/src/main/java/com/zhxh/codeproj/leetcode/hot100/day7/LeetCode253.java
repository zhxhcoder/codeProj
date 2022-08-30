package com.zhxh.codeproj.leetcode.hot100.day7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。

示例 1:

输入: [[0, 30],[5, 10],[15, 20]]
输出: 2
示例 2:

输入: [[7,10],[2,4]]
输出: 1
 */
public class LeetCode253 {
    public static void main(String[] args) {
        System.out.println(new Solution().minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println(new Solution2().minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
    }

    /*
     优先队列
     */
    static class Solution {
        public int minMeetingRooms(int[][] intervals) {
            // Check for the base case. If there are no intervals, return 0
            if (intervals.length == 0) {
                return 0;
            }
            // Min heap
            PriorityQueue<Integer> allocator =
                    new PriorityQueue<Integer>(
                            intervals.length,
                            new Comparator<Integer>() {
                                public int compare(Integer a, Integer b) {
                                    return a - b;
                                }
                            });
            // Sort the intervals by start time
            Arrays.sort(
                    intervals,
                    new Comparator<int[]>() {
                        public int compare(final int[] a, final int[] b) {
                            return a[0] - b[0];
                        }
                    });
            // Add the first meeting
            allocator.add(intervals[0][1]);
            // Iterate over remaining intervals
            for (int i = 1; i < intervals.length; i++) {
                // If the room due to free up the earliest is free, assign that room to this meeting.
                if (intervals[i][0] >= allocator.peek()) {
                    allocator.poll();
                }
                // If a new room is to be assigned, then also we add to the heap,
                // If an old room is allocated, then also we have to add to the heap with updated end time.
                allocator.add(intervals[i][1]);
            }
            // The size of the heap tells us the minimum rooms required for all the meetings.
            return allocator.size();
        }
    }

    /*
    有序化
     */
    static class Solution2 {
        public int minMeetingRooms(int[][] intervals) {
            // Check for the base case. If there are no intervals, return 0
            if (intervals.length == 0) {
                return 0;
            }
            Integer[] start = new Integer[intervals.length];
            Integer[] end = new Integer[intervals.length];
            for (int i = 0; i < intervals.length; i++) {
                start[i] = intervals[i][0];
                end[i] = intervals[i][1];
            }
            // Sort the intervals by end time
            Arrays.sort(
                    end,
                    new Comparator<Integer>() {
                        public int compare(Integer a, Integer b) {
                            return a - b;
                        }
                    });
            // Sort the intervals by start time
            Arrays.sort(
                    start,
                    new Comparator<Integer>() {
                        public int compare(Integer a, Integer b) {
                            return a - b;
                        }
                    });
            // The two pointers in the algorithm: e_ptr and s_ptr.
            int startPointer = 0, endPointer = 0;
            // Variables to keep track of maximum number of rooms used.
            int usedRooms = 0;
            // Iterate over intervals.
            while (startPointer < intervals.length) {
                // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
                if (start[startPointer] >= end[endPointer]) {
                    usedRooms -= 1;
                    endPointer += 1;
                }
                // We do this irrespective of whether a room frees up or not.
                // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
                // remain the same in that case. If no room was free, then this would increase used_rooms
                usedRooms += 1;
                startPointer += 1;
            }
            return usedRooms;
        }
    }
}
