package com.zhxh.codeproj.kotlin

import junit.framework.Assert

import org.junit.Test

/**
 * Created by zhxh on 2019/4/27
 * kotlin版本的合并两个数组
 */
class KMergeTwoArray {

    @Test
    fun test1() {
        Assert.assertTrue(mergeWithSpace(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3))
        Assert.assertTrue(mergeWithoutSpace(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3))
    }

    private fun mergeWithSpace(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Boolean {
        val result = IntArray(m + n)
        var i = 0
        var j = 0
        var k = 0
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                result[k++] = nums1[i++]
            } else {
                result[k++] = nums2[j++]
            }
        }
        if (i != m) {
            while (i < m) {
                result[k++] = nums1[i++]
            }
        }

        if (j != n) {
            while (j < n) {
                result[k++] = nums2[j++]
            }
        }

        k = 0

        i = 0
        while (i < nums1.size) {
            nums1[i] = result[k++]
            i++
        }


        i = 0
        while (i < nums1.size) {
            println(nums1[i])
            i++
        }


        return true
    }

    private fun mergeWithoutSpace(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Boolean {
        var k = m + n - 1
        var i = m - 1
        var j = n - 1
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--]
            } else {
                nums1[k--] = nums2[j--]
            }
        }

        if (i > 0) {
            while (i >= 0) {
                nums1[k--] = nums1[i--]
            }
        }

        if (j >= 0) {
            while (j >= 0) {
                nums1[k--] = nums2[j--]
            }
        }

        i = 0
        while (i < nums1.size) {
            println(nums1[i])
            i++
        }


        return true
    }

}
















