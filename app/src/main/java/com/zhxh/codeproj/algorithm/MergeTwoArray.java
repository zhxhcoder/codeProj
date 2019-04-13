package com.zhxh.codeproj.algorithm;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by zhxh on 2019/4/13
 */
public class MergeTwoArray {

    @Test
    public void test1() {
        Assert.assertTrue(mergeWithSpace(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3));
        Assert.assertTrue(mergeWithoutSpace(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3));
    }


    public boolean mergeWithSpace(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }
        if (i != m) {
            while (i < m) {
                result[k++] = nums1[i++];
            }
        }

        if (j != n) {
            while (j < n) {
                result[k++] = nums2[j++];
            }
        }

        k = 0;

        for (i = 0; i < nums1.length; i++)
            nums1[i] = result[k++];


        for (i = 0; i < nums1.length; i++)
            System.out.println(nums1[i]);


        return true;
    }

    public boolean mergeWithoutSpace(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        if (i > 0) {
            while (i >= 0) {
                nums1[k--] = nums1[i--];
            }
        }

        if (j >= 0) {
            while (j >= 0) {
                nums1[k--] = nums2[j--];
            }
        }

        for (i = 0; i < nums1.length; i++)
            System.out.println(nums1[i]);


        return true;
    }

}
















