package com.zhxh.codeproj.leetcode.top145;

import java.util.HashMap;
import java.util.Map;

/*
给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。



示例 1：


输入：points = [[1,1],[2,2],[3,3]]
输出：3
示例 2：


输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出：4


提示：

1 <= points.length <= 300
points[i].length == 2
-104 <= xi, yi <= 104
points 中的所有点 互不相同

 */
public class LeetCode149 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        System.out.println(new Solution2().maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
    }

    /*
    方法一：哈希表
     */
    static class Solution {
        public int maxPoints(int[][] points) {
            int n = points.length;
            if (n <= 2) {
                return n;
            }
            int ret = 0;
            for (int i = 0; i < n; i++) {
                if (ret >= n - i || ret > n / 2) {
                    break;
                }
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                for (int j = i + 1; j < n; j++) {
                    int x = points[i][0] - points[j][0];
                    int y = points[i][1] - points[j][1];
                    if (x == 0) {
                        y = 1;
                    } else if (y == 0) {
                        x = 1;
                    } else {
                        if (y < 0) {
                            x = -x;
                            y = -y;
                        }
                        int gcdXY = gcd(Math.abs(x), Math.abs(y));
                        x /= gcdXY;
                        y /= gcdXY;
                    }
                    int key = y + x * 20001;
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
                int maxn = 0;
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    int num = entry.getValue();
                    maxn = Math.max(maxn, num + 1);
                }
                ret = Math.max(ret, maxn);
            }
            return ret;
        }

        public int gcd(int a, int b) {
            return b != 0 ? gcd(b, a % b) : a;
        }
    }

    /*
    一句话解释: 固定一点, 找其他点和这个点组成直线, 统计他们的斜率!
    这里有一个重点: 求斜率.用两种方法
    用最大约数方法(gcd), 把他化成最简形式, 3/6 == 2/4 == 1/2
    除数(不太精确, 速度快!)
     */
    static class Solution2 {
        public int maxPoints(int[][] points) {
            int n = points.length;
            if (n == 0) return 0;
            if (n == 1) return 1;
            int res = 0;
            for (int i = 0; i < n - 1; i++) {
                Map<String, Integer> slope = new HashMap<>();
                int repeat = 0;
                int tmp_max = 0;
                for (int j = i + 1; j < n; j++) {
                    int dy = points[i][1] - points[j][1];
                    int dx = points[i][0] - points[j][0];
                    if (dy == 0 && dx == 0) {
                        repeat++;
                        continue;
                    }
                    int g = gcd(dy, dx);
                    if (g != 0) {
                        dy /= g;
                        dx /= g;
                    }
                    String tmp = dy + "/" + dx;
                    slope.put(tmp, slope.getOrDefault(tmp, 0) + 1);
                    tmp_max = Math.max(tmp_max, slope.get(tmp));
                }
                res = Math.max(res, repeat + tmp_max + 1);
            }
            return res;
        }

        private int gcd(int dy, int dx) {
            if (dx == 0) return dy;
            else return gcd(dx, dy % dx);
        }
    }
}
