package com.zhxh.codeproj.leetcode.top145;

import java.util.*;

/*
城市的 天际线 是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回 由这些建筑物形成的 天际线 。

每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：

lefti 是第 i 座建筑物左边缘的 x 坐标。
righti 是第 i 座建筑物右边缘的 x 坐标。
heighti 是第 i 座建筑物的高度。
你可以假设所有的建筑都是完美的长方形，在高度为 0 的绝对平坦的表面上。

天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。

注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]



示例 1：


输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
解释：
图 A 显示输入的所有建筑物的位置和高度，
图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
示例 2：

输入：buildings = [[0,2,3],[2,5,3]]
输出：[[0,3],[5,0]]


提示：

1 <= buildings.length <= 104
0 <= lefti < righti <= 231 - 1
1 <= heighti <= 231 - 1
buildings 按 lefti 非递减排序

 */
public class LeetCode218 {
    public static void main(String[] args) {
        System.out.println(new Solution().getSkyline(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}}));
        System.out.println(new Solution2().getSkyline(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}}));
    }

    /*
    方法一：扫描线 + 优先队列
    观察题目我们可以发现，关键点的横坐标总是落在建筑的左右边缘上。
    这样我们可以只考虑每一座建筑的边缘作为横坐标，这样其对应的纵坐标为「包含该横坐标」的所有建筑的最大高度。
     */
    static class Solution {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);
            List<Integer> boundaries = new ArrayList<Integer>();
            for (int[] building : buildings) {
                boundaries.add(building[0]);
                boundaries.add(building[1]);
            }
            Collections.sort(boundaries);

            List<List<Integer>> ret = new ArrayList<List<Integer>>();
            int n = buildings.length, idx = 0;
            for (int boundary : boundaries) {
                while (idx < n && buildings[idx][0] <= boundary) {
                    pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                    idx++;
                }
                while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                    pq.poll();
                }

                int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
                if (ret.size() == 0 || maxn != ret.get(ret.size() - 1).get(1)) {
                    ret.add(Arrays.asList(boundary, maxn));
                }
            }
            return ret;
        }
    }

    static class Solution2 {
        public List<List<Integer>> getSkyline(int[][] buildings) {
            // 如果将所有的建筑的边界作为一条线，那么所有的答案都在这些线上
            // 考虑任意一条线，那么这条线和所有相交的建筑（这里排除掉刚好和建筑右边界相交），取一个最高的
            // 高度，然后判断这个高度是否和ans末尾最后一个元素的高度相等，不相等就加入进去
            // 在这里为了快速得到最高的高度，使用一个堆来进行记录

            // 得到所有由建筑边界构成的边界线，并升序
            int[] boundaries = new int[buildings.length * 2];
            for (int i = 0; i < buildings.length; i++) {
                boundaries[2 * i] = buildings[i][0];
                boundaries[2 * i + 1] = buildings[i][1];
            }
            Arrays.sort(boundaries);

            // 创建一个堆，维护一个边界-高度值对
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

            List<List<Integer>> ans = new ArrayList<>(); // 返回答案
            int index = 0; // 指向buildings
            for (int boundary : boundaries) {
                // 对于一个建筑，如果其左边界在当前判断的边界线左边或重叠，那么向堆加入右边界-高度值对
                while (index < buildings.length && buildings[index][0] <= boundary) {
                    pq.offer(new int[]{buildings[index][1], buildings[index][2]});
                    index++;
                }
                // 对于那些加入了堆中的建筑，从堆的顶部移出建筑右边界在边界线左边或重叠的边界-高度值对
                while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                    pq.poll();
                }
                // 经过上面的两步操作之后，当前边界线穿过的建筑（不含右边界）全都在堆中，并且堆的顶端是所有穿过的建筑中，高度最高的，也就是天际线高度
                // 如果此时的堆为空，证明边界线没有穿过任何建筑，来到了建筑的分割位置，天际线为0
                int maxHeight = pq.isEmpty() ? 0 : pq.peek()[1];
                // 按照这种算法，每一条边界线都会产生一个天际线高度，如果这个高度和ans末尾元素的高度一致，那么就说明两条边界线穿过了同一个建筑，并且相邻，那么按照规则只取最左端
                if (ans.size() == 0 || maxHeight != ans.get(ans.size() - 1).get(1)) {
                    ans.add(Arrays.asList(boundary, maxHeight));
                }
            }
            return ans;
        }
    }
}
