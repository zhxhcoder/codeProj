package com.zhxh.codeproj.leetcode.hot100;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.*;

/*
给定一个用字符数组表示的 CPU 需要执行的任务列表。
其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。

然而，两个相同种类的任务之间必须有长度为n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的最短时间。

示例 ：

输入：tasks = ['A','A','B','B','A','B'], n = 2
输出：8
解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。

提示：

任务的总个数为[1, 10000]。
n 的取值范围为 [0, 100]。

 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class LeetCode621 {
    public static void main(String[] args) {
        System.out.println(new Solution().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 3));
        System.out.println(new Solution2().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 3));
        System.out.println(new Solution3().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 3));
    }

    /*
    1、手下把最大的任务量排好，并列的最大按照最小间隔排好；
    2、剩下的任务（出来最大的任务以及与最大并列的最后一个任务），需要插前边的空（不需要插最后一组后边的）；
    3、假如空不够插，直接返回task长度，否则就是此时最后一个最大量任务的结束时间
     */
    static class Solution {
        public int leastInterval(char[] tasks, int n) {
            int count[] = new int[26];
            for (int i = 0; i < tasks.length; i++) {
                count[tasks[i] - 'A']++;
            }
            Arrays.sort(count);
            int max = count[25];
            int numMax = 0;//与最大数并列的任务数
            for (int i = 25; i >= 0; i--) {
                if (max == count[i]) {
                    numMax++;
                } else {
                    break;
                }
            }
            //最大任务产生空隙：n*(max-1)
            //需要插空的其他任务的数量task.length-max-(numMax-1)
            return n * (max - 1) <= tasks.length - max - (numMax - 1) ? tasks.length : (n + 1) * (max - 1) + numMax;
        }
    }

    /*
    模拟
    一种容易想到的方法是，我们按照时间顺序，依次给每一个时间单位分配任务。
    那么如果当前有多种任务不在冷却中，那么我们应该如何挑选执行的任务呢？
    直觉上，我们应当选择剩余执行次数最多的那个任务，将每种任务的剩余执行次数尽可能平均，
    使得 CPU 处于待命状态的时间尽可能少。当然这也是可以证明的，详细证明见下一个小标题。
     */
    static class Solution2 {
        public int leastInterval(char[] tasks, int n) {
            Map<Character, Integer> freq = new HashMap<Character, Integer>();
            for (char ch : tasks) {
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
            // 任务总数
            int m = freq.size();
            List<Integer> nextValid = new ArrayList<Integer>();
            List<Integer> rest = new ArrayList<Integer>();
            Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                int value = entry.getValue();
                nextValid.add(1);
                rest.add(value);
            }

            int time = 0;
            for (int i = 0; i < tasks.length; ++i) {
                ++time;
                int minNextValid = Integer.MAX_VALUE;
                for (int j = 0; j < m; ++j) {
                    if (rest.get(j) != 0) {
                        minNextValid = Math.min(minNextValid, nextValid.get(j));
                    }
                }
                time = Math.max(time, minNextValid);
                int best = -1;
                for (int j = 0; j < m; ++j) {
                    if (rest.get(j) != 0 && nextValid.get(j) <= time) {
                        if (best == -1 || rest.get(j) > rest.get(best)) {
                            best = j;
                        }
                    }
                }
                nextValid.set(best, time + n + 1);
                rest.set(best, rest.get(best) - 1);
            }
            return time;
        }
    }

    /*
    构造
    我们首先考虑所有任务种类中执行次数最多的那一种，记它为 A,的执行次数maxExeC
     */
    static class Solution3 {
        public int leastInterval(char[] tasks, int n) {
            Map<Character, Integer> freq = new HashMap<Character, Integer>();
            // 最多的执行次数
            int maxExec = 0;
            for (char ch : tasks) {
                int exec = freq.getOrDefault(ch, 0) + 1;
                freq.put(ch, exec);
                maxExec = Math.max(maxExec, exec);
            }

            // 具有最多执行次数的任务数量
            int maxCount = 0;
            Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                int value = entry.getValue();
                if (value == maxExec) {
                    ++maxCount;
                }
            }
            return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
        }
    }
}
