package com.zhxh.codeproj.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/*
有 n 个网络节点，标记为1到 n。

给你一个列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点， wi是一个信号从源节点传递到目标节点的时间。

现在，从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1 。

示例 1：

输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
输出：2
示例 2：

输入：times = [[1,2,1]], n = 2, k = 1
输出：1
示例 3：

输入：times = [[1,2,1]], n = 2, k = 2
输出：-1


提示：

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
所有 (ui, vi) 对都 互不相同（即，不含重复边）

 */
class LeetCode743 {
    public static void main(String[] args) {
        System.out.println(new Solution().networkDelayTime(new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}}, 4, 2));
    }

    static class Solution {
        public int networkDelayTime(int[][] times, int n, int k) {
            int[][] graph = new int[n][n];    //邻接矩阵初始化图
            for (int i = 0; i < n; i++) {
                Arrays.fill(graph[i], Integer.MAX_VALUE / 2);   //因为可能点与点之间无连接，因此初始化为无穷大。此处为避免33行计算溢出，>>2
            }
            for (int i = 0; i < times.length; i++) {         //根据题目给的信息times，对graph初始化。
                int source = times[i][0] - 1;
                int target = times[i][1] - 1;
                graph[source][target] = times[i][2];
            }
            boolean[] visited = new boolean[n];             //初始值为false,若访问，则为true。
            int[] dist = new int[n];                        //距离表。
            Arrays.fill(dist, Integer.MAX_VALUE / 2);   //初始化距离表为无穷。
            dist[k - 1] = 0;             //初始化起始点K-K的距离为0
            //dijkstra
            for (int j = 0; j < n; j++) {  //一次加入一个节点到路径中
                int cur = -1;
                for (int i = 0; i < n; i++) {  //找出距离表中最小的节点 收录进来 即锁死该点，以后就不用了
                    if (!visited[i] && (cur == -1 || dist[i] < dist[cur])) {   //！vidsited[i]即锁死的不用
                        cur = i;
                    }
                }

                visited[cur] = true;   //锁死上述找到的距离表中最小的节点。

                for (int i = 0; i < n; i++) {    //更新锁死节点与其相邻节点的dist.
                    dist[i] = Math.min(dist[i], dist[cur] + graph[cur][i]);
                }
            }
            int result = 0;     //题目要求，若dist中有未到达的节点，return-1；
            for (int i = 0; i < n; i++) {
                if (dist[i] == Integer.MAX_VALUE / 2) {
                    return -1;
                }
                result = Math.max(result, dist[i]);
            }
            return result;
        }
    }
}
