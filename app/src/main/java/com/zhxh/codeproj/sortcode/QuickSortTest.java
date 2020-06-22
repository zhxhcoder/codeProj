package com.zhxh.codeproj.sortcode;

import java.util.Arrays;

public class QuickSortTest {
    public static void main(String[] args) {
        int[] arr = {0, 3, -10, 4, 5, 1, -5, 1, 5, -1, 5, 1};
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int begin, int end) {
        if (begin == end || begin == (end - 1)) return;
        if (begin < end) {
            int pivot = partition(arr, begin, end);              //将数组分为两部分
            quickSort(arr, begin, pivot - 1);                   //递归排序左子数组
            quickSort(arr, pivot + 1, end);                  //递归排序右子数组
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[begin];     //枢轴记录
        while (begin < end) {
            while (begin < end && arr[end] >= pivot) {
                --end;
            }
            arr[begin] = arr[end]; //交换比枢轴小的记录到左端
            while (begin < end && arr[begin] <= pivot) {
                ++begin;
            }
            arr[end] = arr[begin];//交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[begin] = pivot;
        //返回的是枢轴的位置
        return begin;
    }
}
