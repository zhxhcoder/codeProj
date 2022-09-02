package com.zhxh.codeproj.leetcode.top145;

import java.util.Arrays;
import java.util.Random;

/*
给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。

你可以假设所有输入数组都可以得到满足题目要求的结果。



示例 1：

输入：nums = [1,5,1,1,6,4]
输出：[1,6,1,5,1,4]
解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
示例 2：

输入：nums = [1,3,2,2,3,1]
输出：[2,3,1,3,1,2]


提示：

1 <= nums.length <= 5 * 104
0 <= nums[i] <= 5000
题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果


进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？

 */
public class LeetCode324 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().wiggleSort(new int[]{1, 5, 1, 1, 6, 4})));
        System.out.println(Arrays.toString(new Solution2().wiggleSort(new int[]{1, 5, 1, 1, 6, 4})));
        System.out.println(Arrays.toString(new Solution3().wiggleSort(new int[]{1, 5, 1, 1, 6, 4})));
        System.out.println(Arrays.toString(new Solution4().wiggleSort(new int[]{1, 5, 1, 1, 6, 4})));
    }

    /*
    方法一：排序
     */
    static class Solution {
        public int[] wiggleSort(int[] nums) {
            int[] arr = nums.clone();
            Arrays.sort(arr);
            int n = nums.length;
            int x = (n + 1) / 2;
            for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
                nums[i] = arr[j];
                if (i + 1 < n) {
                    nums[i + 1] = arr[k];
                }
            }
            return nums;
        }
    }

    /*
    方法二：三向切分
     */
    static class Solution2 {
        Random random = new Random();

        public int[] wiggleSort(int[] nums) {
            int n = nums.length;
            int x = (n + 1) / 2;
            int mid = x - 1;
            int target = findKthLargest(nums, n - mid);
            for (int k = 0, i = 0, j = n - 1; k <= j; k++) {
                if (nums[k] > target) {
                    while (j > k && nums[j] > target) {
                        j--;
                    }
                    swap(nums, k, j--);
                }
                if (nums[k] < target) {
                    swap(nums, k, i++);
                }
            }
            int[] arr = nums.clone();
            for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
                nums[i] = arr[j];
                if (i + 1 < n) {
                    nums[i + 1] = arr[k];
                }
            }
            return nums;
        }

        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }

        public int quickSelect(int[] a, int l, int r, int index) {
            int q = randomPartition(a, l, r);
            if (q == index) {
                return a[q];
            } else {
                return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
            }
        }

        public int randomPartition(int[] a, int l, int r) {
            int i = random.nextInt(r - l + 1) + l;
            swap(a, i, r);
            return partition(a, l, r);
        }

        public int partition(int[] a, int l, int r) {
            int x = a[r], i = l - 1;
            for (int j = l; j < r; ++j) {
                if (a[j] <= x) {
                    swap(a, ++i, j);
                }
            }
            swap(a, i + 1, r);
            return i + 1;
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }


    /*
    方法三：索引转换
     */
    static class Solution3 {
        Random random = new Random();

        public int[] wiggleSort(int[] nums) {
            int n = nums.length;
            int x = (n + 1) / 2;
            int mid = x - 1;
            int target = findKthLargest(nums, n - mid);
            for (int k = 0, i = 0, j = n - 1; k <= j; k++) {
                if (nums[transAddress(k, n)] > target) {
                    while (j > k && nums[transAddress(j, n)] > target) {
                        j--;
                    }
                    swap(nums, transAddress(k, n), transAddress(j--, n));
                }
                if (nums[transAddress(k, n)] < target) {
                    swap(nums, transAddress(k, n), transAddress(i++, n));
                }
            }
            return nums;
        }

        public int transAddress(int i, int n) {
            return (2 * n - 2 * i - 1) % (n | 1);
        }

        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }

        public int quickSelect(int[] a, int l, int r, int index) {
            int q = randomPartition(a, l, r);
            if (q == index) {
                return a[q];
            } else {
                return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
            }
        }

        public int randomPartition(int[] a, int l, int r) {
            int i = random.nextInt(r - l + 1) + l;
            swap(a, i, r);
            return partition(a, l, r);
        }

        public int partition(int[] a, int l, int r) {
            int x = a[r], i = l - 1;
            for (int j = l; j < r; ++j) {
                if (a[j] <= x) {
                    swap(a, ++i, j);
                }
            }
            swap(a, i + 1, r);
            return i + 1;
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }


    /*
    方法四：递归优化
     */
    static class Solution4 {
        Random random = new Random();

        public int[] wiggleSort(int[] nums) {
            int n = nums.length;
            int x = (n + 1) / 2;
            int mid = x - 1;
            int target = findKthLargest(nums, n - mid);
            for (int k = 0, i = 0, j = n - 1; k <= j; k++) {
                if (nums[transAddress(k, n)] > target) {
                    while (j > k && nums[transAddress(j, n)] > target) {
                        j--;
                    }
                    swap(nums, transAddress(k, n), transAddress(j--, n));
                }
                if (nums[transAddress(k, n)] < target) {
                    swap(nums, transAddress(k, n), transAddress(i++, n));
                }
            }
            return nums;
        }

        public int findKthLargest(int[] nums, int k) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int pivot = random.nextInt(right - left + 1) + left;
                int newPivot = partitionAroundPivot(left, right, pivot, nums);
                if (newPivot == k - 1) {
                    return nums[newPivot];
                } else if (newPivot > k - 1) {
                    right = newPivot - 1;
                } else {
                    left = newPivot + 1;
                }
            }
            return nums[k - 1];
        }

        public int transAddress(int i, int n) {
            return (2 * n - 2 * i - 1) % (n | 1);
        }

        public int partitionAroundPivot(int left, int right, int pivot, int[] nums) {
            int pivotValue = nums[pivot];
            int newPivot = left;
            swap(nums, pivot, right);
            for (int i = left; i < right; ++i) {
                if (nums[i] > pivotValue) {
                    swap(nums, i, newPivot++);
                }
            }
            swap(nums, right, newPivot);
            return newPivot;
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
