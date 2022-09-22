package com.zhxh.codeproj.sortcode;

import java.util.Arrays;

/*
快速排序介绍：
选择一个基准数，通过一趟排序将要排序的数组分割成独立的两部分；
其中一部分的所有数据都比另外一部分的所有数据都要小。
然后，再按次方法对这两部分数据分别快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。

快速排序是不稳定的算法，它不满足稳定算法的定义。
算法稳定性 -- 假设在数列中存在a[i]=a[j]，若在排序之前，a[i]在a[j]前面；并且排序之后，a[i]仍然在a[j]前面。则这个排序算法是稳定的！

快速排序的时间复杂度在最坏情况下是O(N2)，平均的时间复杂度是O(N*lgN)。
著作权归https://pdai.tech所有。
链接：https://pdai.tech/md/algorithm/alg-sort-x-fast.html

为什么最少是lg(N+1)次? 快速排序是采用的分治法进行遍历的，我们将它看作一棵二叉树，
它需要遍历的次数就是二叉树的深度，而根据完全二叉树的定义，它的深度至少是lg(N+1)。因此，快速排序的遍历次数最少是lg(N+1)次。
为什么最多是N次? 这个应该非常简单，还是将快速排序看作一棵二叉树，它的深度最大是N。因此，快读排序的遍历次数最多是N次

原始数据
被操作数据
已排序数据

 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {1, 3, -2, 5, 4, -3, 2, 6, -1, 7, 1};
        System.out.println("排序前:" + Arrays.toString(a));
        quickSort(a, 0, a.length - 1);
        System.out.println("排序后:" + Arrays.toString(a));
    }

    /*
     * 快速排序
     * 参数说明:
     * a -- 待排序的数组
     * l -- 数组的左边界(例如，从起始位置开始排序，则l=0)
     * r -- 数组的右边界(例如，排序截至到数组末尾，则r=a.length-1)
     */
    public static void quickSort(int[] a, int l, int r) {
        if (l < r) {
            int i, j, x;

            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while (i < j && a[j] > x)
                    j--; // 从右向左找第一个小于x的数
                //判断必须存在
                if (i < j)
                    a[i++] = a[j];
                while (i < j && a[i] < x)
                    i++; // 从左向右找第一个大于x的数
                //判断必须存在
                if (i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            quickSort(a, l, i - 1); /* 递归调用 */
            quickSort(a, i + 1, r); /* 递归调用 */
        }
    }
}
  