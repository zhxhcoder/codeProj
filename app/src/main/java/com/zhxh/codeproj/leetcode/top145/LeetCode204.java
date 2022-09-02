package com.zhxh.codeproj.leetcode.top145;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。

 

示例 1：

输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
示例 2：

输入：n = 0
输出：0
示例 3：

输入：n = 1
输出：0
 

提示：

0 <= n <= 5 * 106

 */
public class LeetCode204 {
    public static void main(String[] args) {
        System.out.println(new Solution().countPrimes(10));
        System.out.println(new Solution2().countPrimes(10));
        System.out.println(new Solution3().countPrimes(10));
    }

    /*
    方法一：枚举
     */
    static class Solution {
        public int countPrimes(int n) {
            int ans = 0;
            for (int i = 2; i < n; ++i) {
                ans += isPrime(i) ? 1 : 0;
            }
            return ans;
        }

        public boolean isPrime(int x) {
            for (int i = 2; i * i <= x; ++i) {
                if (x % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /*
    方法二：埃氏筛
     */
    static class Solution2 {
        public int countPrimes(int n) {
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            int ans = 0;
            for (int i = 2; i < n; ++i) {
                if (isPrime[i] == 1) {
                    ans += 1;
                    if ((long) i * i < n) {
                        for (int j = i * i; j < n; j += i) {
                            isPrime[j] = 0;
                        }
                    }
                }
            }
            return ans;
        }
    }

    /*
    方法三：线性筛
     */
    static class Solution3 {
        public int countPrimes(int n) {
            List<Integer> primes = new ArrayList<Integer>();
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            for (int i = 2; i < n; ++i) {
                if (isPrime[i] == 1) {
                    primes.add(i);
                }
                for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                    isPrime[i * primes.get(j)] = 0;
                    if (i % primes.get(j) == 0) {
                        break;
                    }
                }
            }
            return primes.size();
        }
    }
}
