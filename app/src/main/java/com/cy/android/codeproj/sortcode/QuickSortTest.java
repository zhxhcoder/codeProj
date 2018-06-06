package com.cy.android.codeproj.sortcode;

public class QuickSortTest {

    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 4, 5, 1, 5, 1, 5, 1, 5, 1};
        quickSort(arr, 0, arr.length - 1);
        for (int a : arr) {
            System.out.println(a);
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low == high || low == (high - 1)) return;

        if (low < high) {
            int pivot = partition(arr, low, high);              //将数组分为两部分
            quickSort(arr, low, pivot - 1);                   //递归排序左子数组
            quickSort(arr, pivot + 1, high);                  //递归排序右子数组
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];     //枢轴记录
        while (low < high) {
            while (low < high && arr[high] >= pivot) --high;
            arr[low] = arr[high];             //交换比枢轴小的记录到左端
            while (low < high && arr[low] <= pivot) ++low;
            arr[high] = arr[low];           //交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[low] = pivot;
        //返回的是枢轴的位置
        return low;
    }
}
