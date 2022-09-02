package com.zhxh.codeproj.leetcode.top145;

import java.util.*;

/*
给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。



示例 1：

输入：nums = [5,2,6,1]
输出：[2,1,1,0]
解释：
5 的右侧有 2 个更小的元素 (2 和 1)
2 的右侧仅有 1 个更小的元素 (1)
6 的右侧有 1 个更小的元素 (1)
1 的右侧有 0 个更小的元素
示例 2：

输入：nums = [-1]
输出：[0]
示例 3：

输入：nums = [-1,-1]
输出：[0,0]


提示：

1 <= nums.length <= 105
-104 <= nums[i] <= 104
 */
public class LeetCode315 {
    public static void main(String[] args) {
        System.out.println(new Solution().countSmaller(new int[]{5, 2, 6, 1}));
        System.out.println(new Solution2().countSmaller(new int[]{5, 2, 6, 1}));
    }

    /*
    方法一：离散化树状数组
     */
    static class Solution {
        private int[] c;
        private int[] a;

        public List<Integer> countSmaller(int[] nums) {
            List<Integer> resultList = new ArrayList<Integer>();
            discretization(nums);
            init(nums.length + 5);
            for (int i = nums.length - 1; i >= 0; --i) {
                int id = getId(nums[i]);
                resultList.add(query(id - 1));
                update(id);
            }
            Collections.reverse(resultList);
            return resultList;
        }

        private void init(int length) {
            c = new int[length];
            Arrays.fill(c, 0);
        }

        private int lowBit(int x) {
            return x & (-x);
        }

        private void update(int pos) {
            while (pos < c.length) {
                c[pos] += 1;
                pos += lowBit(pos);
            }
        }

        private int query(int pos) {
            int ret = 0;
            while (pos > 0) {
                ret += c[pos];
                pos -= lowBit(pos);
            }

            return ret;
        }

        private void discretization(int[] nums) {
            Set<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                set.add(num);
            }
            int size = set.size();
            a = new int[size];
            int index = 0;
            for (int num : set) {
                a[index++] = num;
            }
            Arrays.sort(a);
        }

        private int getId(int x) {
            return Arrays.binarySearch(a, x) + 1;
        }
    }

    /*
    方法二：归并排序
     */
    static class Solution2 {
        private int[] index;
        private int[] temp;
        private int[] tempIndex;
        private int[] ans;

        public List<Integer> countSmaller(int[] nums) {
            this.index = new int[nums.length];
            this.temp = new int[nums.length];
            this.tempIndex = new int[nums.length];
            this.ans = new int[nums.length];
            for (int i = 0; i < nums.length; ++i) {
                index[i] = i;
            }
            int l = 0, r = nums.length - 1;
            mergeSort(nums, l, r);
            List<Integer> list = new ArrayList<Integer>();
            for (int num : ans) {
                list.add(num);
            }
            return list;
        }

        public void mergeSort(int[] a, int l, int r) {
            if (l >= r) {
                return;
            }
            int mid = (l + r) >> 1;
            mergeSort(a, l, mid);
            mergeSort(a, mid + 1, r);
            merge(a, l, mid, r);
        }

        public void merge(int[] a, int l, int mid, int r) {
            int i = l, j = mid + 1, p = l;
            while (i <= mid && j <= r) {
                if (a[i] <= a[j]) {
                    temp[p] = a[i];
                    tempIndex[p] = index[i];
                    ans[index[i]] += (j - mid - 1);
                    ++i;
                    ++p;
                } else {
                    temp[p] = a[j];
                    tempIndex[p] = index[j];
                    ++j;
                    ++p;
                }
            }
            while (i <= mid) {
                temp[p] = a[i];
                tempIndex[p] = index[i];
                ans[index[i]] += (j - mid - 1);
                ++i;
                ++p;
            }
            while (j <= r) {
                temp[p] = a[j];
                tempIndex[p] = index[j];
                ++j;
                ++p;
            }
            for (int k = l; k <= r; ++k) {
                index[k] = tempIndex[k];
                a[k] = temp[k];
            }
        }
    }
}
